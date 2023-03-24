package br.edu.inteli.cc.m5.grupo2;

import com.rabbitmq.client.*;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Recv {

    // Name of the queue to be consumed
    private String QUEUE_NAME;
    // Connection URI to RabbitMQ
    private String URI;

    public Recv() {
        this.QUEUE_NAME = "process";
        this.URI = "amqp://guest:guest@localhost:5672";
    }

    // Creates a connection to RabbitMQ using the defined connection URI
    public Connection createConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(this.URI);
        return factory.newConnection();
    }

    // Creates a communication channel with RabbitMQ
    public Channel createChannel(Connection connection) throws Exception {
        return connection.createChannel();
    }

    // Declares the queue to be consumed by the channel
    public void declareQueue(Channel channel) throws Exception {
        // Define the queue declaration arguments
        boolean durable = true; // Persistent queue
        boolean exclusive = false; // Non-exclusive queue
        boolean autoDelete = false; // Queue is not automatically deleted
        // Defines additional properties of the queue
        // In this case, only the name of the queue is passed as an argument
        // But other properties can be defined, such as queue policy arguments, for example.
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, null);
        // Print a message informing that the queue has been declared successfully
        System.out.println(" [*] Waiting for new items in queue to process.");
    }

    public void consumeMessages(Channel channel) throws Exception {
        Gson gson = new Gson();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            JsonObject json = gson.fromJson(message, JsonObject.class);

            String objectKey = json.get("objectKey").getAsString();
            String projectId = json.get("id").getAsString();

            System.out.println(" [*] Received new processing request for project " + json.get("id") + ".");
            System.out.println(" [ ] Downloading file from S3...");

            DownloadFileFromS3 downloadFileFromS3 = new DownloadFileFromS3();
            downloadFileFromS3.downloadFileFromS3(objectKey, projectId);

            // Display a confirmation message
            System.out.println(" [*] File downloaded successfully.");

            // Instancing a new graph.
            Graph graph = new Graph();

            // Path to DTED file.
            String path = System.getProperty("user.dir") + "/downloads/" + projectId + ".dt2";;

            double[][] map = Dted.readDted(path, 180);

            System.out.println(" [*] File read  successfully.");
            System.out.println(" [ ] Loading graph instance...");

            // Sending all Vertices to the graph created.
            for (int i = 0;i < map.length - 1; i++) {
                graph.addVertex(map[i][1], map[i][2], map[i][0]);
            }

            System.out.println(" [*] Graph loaded successfully.");

            int rows = (int) map[map.length - 1][1];
            int cols = (int) map[map.length - 1][2];

            System.out.println(" [ ] Connecting graph vertices...");

            // Creating all possible connections in the graph.
            graph.connectVertices(180, rows, cols);

            System.out.println(" [*] Graph vertices connected successfully.");

            Neo4j neo4j = new Neo4j();

            ArrayList<Vertex> graphVertices = graph.getVertices();
            AtomicInteger currentVertex = new AtomicInteger();
            int quantityOfVertices = graphVertices.size();

            graphVertices.forEach(vertex -> {
                neo4j.createVertex(vertex);
                System.out.print("\r [ ] Persisting vertices in Neo4j: " +  String.format(Locale.US, "%.4f", ((double) currentVertex.get() / quantityOfVertices)) + "%");
                currentVertex.getAndIncrement();
            });

            System.out.println(" [*] Vertices persisted successfully.");

            currentVertex.set(0);

            graph.getVertices().forEach(vertex -> {
                neo4j.connectVertex(vertex);
                System.out.print("\r [ ] Linking vertices in Neo4j: " + String.format(Locale.US, "%.4f", ((double) currentVertex.get() / quantityOfVertices)) + "%");
                currentVertex.getAndIncrement();
            });

            System.out.println(" [*] Vertices linked successfully.");

        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

}
