// Importing required libraries
package br.edu.inteli.cc.m5.grupo2;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.neo4j.driver.Record;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.Neo4jException;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

// Defining Neo4j class which implements AutoCloseable
public class Neo4j implements AutoCloseable {
private final Driver driver;

// Constructor that sets up the connection with the Neo4j database
public Neo4j() {

    // Declaring URI, user and password to connect to the database
    String uri = "neo4j://localhost:7687";
    String user = "neo4j";
    String password = "x5aG^RSZz!zetdlM19XsJEsa227GsC32";

    // Disabling log output for Netty (Neo4j driver dependency) to avoid excessive log prints
    LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
    Logger rootLogger = loggerContext.getLogger("io.netty");
    rootLogger.setLevel(ch.qos.logback.classic.Level.OFF);

    // Establishing a connection to the Neo4j database
    this.driver = GraphDatabase.driver(
            uri,
            AuthTokens.basic(user, password),
            Config.defaultConfig());

}

// Method to create vertices in the Neo4j database from a Graph object
public void createVertices(Graph graph) throws FileNotFoundException {

    StringBuilder queryStr = new StringBuilder();

    // Iterating over the vertices in the graph and appending the query string to create each vertex
    graph.getVertices().forEach(vertex -> {

        queryStr.append(String.format(Locale.US, "CREATE (:Vertex { id: %d, latitude: %.5f, longitude: %.5f, altitude: %.5f })\n",
                vertex.getId(),
                vertex.getLatitude(),
                vertex.getLongitude(),
                vertex.getAltitude()));

    });

    // Creating a new query object with the constructed query string
    Query query = new Query(queryStr.toString());

    try {

        // Executing the query using a write session
        Session session = driver.session(SessionConfig.forDatabase("neo4j"));

        Record record = session.executeWrite(tx -> tx.run(query).single());

        // Printing a success message if the query executed without any errors
        System.out.println("Graph persisted successfully.");

    } catch (Neo4jException exception) {

        // Rethrowing the exception in case of any errors
        throw exception;

    }
}

// Method to create a single vertex in the Neo4j database
public void createVertex(Vertex vertex) {

    // Creating a new query object to create a vertex with the provided information
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

        // Executing the query using a write session
        Session session = driver.session(SessionConfig.forDatabase("neo4j"));

        Record record = session.executeWrite(tx -> tx.run(query).single());

    } catch (Neo4jException exception) {

        // Rethrowing the exception in case of any errors

            throw exception;

        }

    }

    @Override
    public void close() {
        driver.close();
    }

}
