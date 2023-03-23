package br.edu.inteli.cc.m5.grupo2;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.neo4j.driver.Record;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.Neo4jException;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.Map;

public class Neo4j implements AutoCloseable {
    private final Driver driver;

    public Neo4j() {

        String uri = "neo4j://localhost:7687";
        String user = "neo4j";
        String password = "x5aG^RSZz!zetdlM19XsJEsa227GsC32";

        LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("io.netty");
        rootLogger.setLevel(ch.qos.logback.classic.Level.OFF);


        this.driver = GraphDatabase.driver(
                uri,
                AuthTokens.basic(user, password),
                Config.defaultConfig());

    }

    public void persistMapBounds(Graph graph, int rows, String projectId) throws FileNotFoundException {

        Query query = new Query(
                """
                        MATCH (p:Project)
                        WHERE p.id = $id
                        SET p.status = "processed"
                        
                        MERGE (p)-[:HAS]->(m:Map {topLeft: [$tL_latitude, $tL_longitude], topRight: [$tR_latitude, $tR_longitude], bottomLeft: [$bL_latitude, $bL_longitude], bottomRight: [$bR_latitude, $bR_longitude]});
                        """,
                Map.of(
                        "tL_latitude", graph.getVertices().get(0).getLatitude(),
                        "tL_longitude", graph.getVertices().get(0).getLongitude(),
                        "tR_latitude", graph.getVertices().get(rows).getLatitude(),
                        "tR_longitude", graph.getVertices().get(rows).getLongitude(),
                        "bL_latitude", graph.getVertices().get(graph.getVertices().size() - rows).getLatitude(),
                        "bL_longitude", graph.getVertices().get(graph.getVertices().size() - rows).getLongitude(),
                        "bR_latitude", graph.getVertices().get(graph.getVertices().size() - 1).getLatitude(),
                        "bR_longitude", graph.getVertices().get(graph.getVertices().size() - 1).getLongitude(),
                        "id", projectId));

        try {

            Session session = driver.session(SessionConfig.forDatabase("neo4j"));
            
            session.executeWrite(tx -> tx.run(query));

        } catch (Neo4jException exception) {

            throw exception;

        }
    }

    public void createVertex(Vertex vertex) {

        Query query = new Query(
                """
                        CREATE (vertex:Vertex {
                                id: $id,
                                latitude: $latitude,
                                longitude: $longitude,
                                altitude: $altitude,
                                connections: $connections
                            })
                            
                        RETURN vertex
                        """,
                Map.of(
                        "id",vertex.getId(),
                        "latitude", vertex.getLatitude(),
                        "longitude", vertex.getLongitude(),
                        "altitude", vertex.getAltitude(),
                        "connections", vertex.getAllConnections()));

        try {

            Session session = driver.session(SessionConfig.forDatabase("neo4j"));

            session.executeWrite(tx -> tx.run(query).single());

        } catch (Neo4jException exception) {

            throw exception;

        }

    }

    public void findVertex(int vertexId) {

        Query query = new Query(
                """
                        MATCH (vertex:Vertex)
                        WHERE vertex.id = $id
                        RETURN vertex.id, vertex.latitude, vertex.longitude, vertex.altitude
                        """,

                Map.of("id", vertexId));

        try {

            Session session = driver.session(SessionConfig.forDatabase("neo4j"));

            Record record = session.executeWrite(tx -> tx.run(query).single());

            System.out.println("Vertex found successfully: ");

            System.out.println("Vertex found successfully: ");
            System.out.println("id: " + record.get("vertex.id"));
            System.out.println("latitude: " + record.get("vertex.latitude"));
            System.out.println("longitude: " + record.get("vertex.longitude"));
            System.out.println("altitude: " + record.get("vertex.altitude"));


        } catch (Neo4jException exception) {

            throw exception;

        }

    }

    public void connectVertex(Vertex vertexA) {

        vertexA.getAllConnections().forEach(connection -> {

            Vertex vertexB = connection.getArrivalVertex();

            Query query = new Query(
                    """
                            MERGE (vertexA:Vertex {
                                    id: $vertexA_id,
                                    latitude: $vertexA_latitude,
                                    longitude: $vertexA_longitude,
                                    altitude: $vertexA_altitude
                                })
                                
                            MERGE (vertexB:Vertex {
                                    id: $vertexB_id,
                                    latitude: $vertexB_latitude,
                                    longitude: $vertexB_longitude,
                                    altitude: $vertexB_altitude
                                })
                                
                            MERGE (vertexA)-[:CONNECT_TO]->(vertexB)
                            
                            RETURN vertexA, vertexB
                            """,
                    Map.of(
                            "vertexA_id", vertexA.getId(),
                            "vertexA_latitude", vertexA.getLatitude(),
                            "vertexA_longitude", vertexA.getLongitude(),
                            "vertexA_altitude", vertexA.getAltitude(),
                            "vertexB_id", vertexB.getId(),
                            "vertexB_latitude", vertexB.getLatitude(),
                            "vertexB_longitude", vertexB.getLongitude(),
                            "vertexB_altitude", vertexB.getAltitude()));

            try {

                Session session = driver.session(SessionConfig.forDatabase("neo4j"));

                session.executeWrite(tx -> tx.run(query).list());

                System.out.println("Vertexes connected");

            } catch (Neo4jException exception) {

                throw exception;

            }

        });

    }

    public void deleteVertex(Vertex vertex) {

        Query query = new Query(
                """
                        MATCH (vertex:Vertex {
                                id: $id
                            })
                            
                        DETACH DELETE vertex
                        """,
                Map.of("id", vertex.getId()));

        try {

            Session session = driver.session(SessionConfig.forDatabase("neo4j"));

            session.executeWrite(tx -> tx.run(query));

            System.out.println("Vertex deleted successfully.");

        } catch (Neo4jException exception) {

            throw exception;

        }

    }

    public void updateVertex(int vertexId, Vertex updatedVertex) {

        Query query = new Query(
                """
                        MATCH (vertex:Vertex {
                                id: $id
                            })
                        
                        SET vertex.latitude = $latitude,
                            vertex.longitude = $longitude,
                            vertex.altitude = $altitude
                            
                        RETURN vertex
                        """,
                Map.of(
                        "id", vertexId,
                        "latitude", updatedVertex.getLatitude(),
                        "longitude", updatedVertex.getLongitude(),
                        "altitude", updatedVertex.getAltitude()));

        try {

            Session session = driver.session(SessionConfig.forDatabase("neo4j"));

            session.executeWrite(tx -> tx.run(query).single());

            System.out.println("Vertex updated successfully.");

        } catch (Neo4jException exception) {
            throw exception;
        }
    }

    @Override
    public void close() {
        driver.close();
    }

    // Test of this class' methods
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph();
        Neo4j neo4j = new Neo4j();

        Vertex vertex = new Vertex(0,100.00, 100.00, 100.00);
        Vertex vertex2 = new Vertex(1,100.00, 200.00, 200.00);
        Vertex vertex3 = new Vertex(2,150.00, 150.00, 150.00);
        Vertex vertex4 = new Vertex(3,150.00, 200.00, 150.00);
        Vertex vertex5 = new Vertex(4,150.00, 200.00, 150.00);

        graph.addVertex(vertex);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);

        graph.addEdge(vertex.getId(), vertex2.getId());
        graph.addEdge(vertex.getId(), vertex3.getId());
        graph.addEdge(vertex3.getId(), vertex2.getId());
        graph.addEdge(vertex2.getId(), vertex4.getId());

        neo4j.createVertex(vertex5);
        System.out.println("/////////////////////////////////////////////////////////////////");
        neo4j.findVertex(4);
        System.out.println("/////////////////////////////////////////////////////////////////");
        neo4j.connectVertex(vertex2);
        System.out.println("/////////////////////////////////////////////////////////////////");
        neo4j.updateVertex(1, vertex5);
        System.out.println("/////////////////////////////////////////////////////////////////");
        neo4j.deleteVertex(vertex);
        System.out.println("/////////////////////////////////////////////////////////////////");
    }

}
