package br.edu.inteli.cc.m5.grupo2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class App {
    public static void main(String[] args) throws Exception {

        // Instantiate RabbitMQ receiver
        Recv recv = new Recv();

        // Create a connection to RabbitMQ
        Connection connection = recv.createConnection();

        // Create a channel for communication with RabbitMQ
        Channel channel = recv.createChannel(connection);

        // Declare the queue to be consumed
        recv.declareQueue(channel);

        // Start consuming messages from the queue
        recv.consumeMessages(channel);

    }
}
