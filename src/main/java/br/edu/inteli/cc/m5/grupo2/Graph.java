package br.edu.inteli.cc.m5.grupo2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

// Class to save the graph and its vertices
public class Graph {

    private static final Logger LOGGER = Logger.getLogger(Graph.class.getName());
    private final ArrayList<Vertex> vertices;
    private int nextVertexId = 0;

    private int cols, rows;

    // Constructor initializes an empty ArrayList to store vertices
    public Graph() {
        this.vertices = new ArrayList<>();
    }

    // Method to add a new vertex to the graph given its latitude, longitude, and altitude
    public void addVertex(double latitude, double longitude, double altitude) {
        // Creates a new Vertex object with a unique ID and the given coordinates
        Vertex vertex = new Vertex(nextVertexId++, latitude, longitude, altitude);
        // Adds the new Vertex to the graph's ArrayList of vertices
        this.vertices.add(vertex);
    }

    // Returns the number of columns in the graph
    public int getCols() {
        return this.cols;
    }

    // Returns the number of rows in the graph
    public int getRows() {
        return this.rows;
    }

    // DEBUGGING METHOD: Adds a given vertex to the graph
    public void addVertex(Vertex vertex) {
        // Checks if a Vertex with the same ID already exists in the graph's ArrayList of vertices
        for (int i = 0; i < this.getVertices().size(); i++) {
            if (vertex.getId() == this.getVertices().get(i).getId()) {
                // If a Vertex with the same ID already exists, a warning is logged and the method returns
                LOGGER.log(Level.WARNING, "Vertex with id == " + vertex.getId() + " already exists");
                return;
            }
        }
        // If a Vertex with the same ID does not already exist, the Vertex is added to the graph's ArrayList of vertices
        this.vertices.add(vertex);
    }

    // Method to add a new edge between two vertices given their IDs
    public void addEdge(int vertexId, int arrivalVertexId) {
        // Finds the Vertex objects in the graph's ArrayList of vertices corresponding to the given IDs
        this.vertices.get(vertexId).addConnectionTo(this.vertices.get(arrivalVertexId));
    }

    // Returns the graph's ArrayList of vertices
    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    // Returns a LinkedList of all edges connected to a given Vertex
    public LinkedList<Edge> getConnectionsOf(int vertexId) {
        return this.vertices.get(vertexId).getAllConnections();
    }

    // Returns a string representation of each Vertex's adjacency list
    public String getAdjacencyListForEachVertex() {
        StringBuilder str = new StringBuilder();
        for (Vertex vertex : this.vertices) {
            str.append(vertex.getAdjacencyListAsString());
            str.append("\n");
        }
        return str.toString();
    }

    // Method to connect all vertices in the graph that are within a certain distance of each other
    public void connectVertices(int intervalDistance, int rows, int cols) {
        // Sets the number of columns and rows in the graph
        this.cols = cols;
        this.rows = rows;

        // Determines the total latitude and longitude differences between the first and last vertices in the graph
        double lat1 = this.vertices.get(0).getLatitude();
        double lat2 = this.vertices.get(this.vertices.size() - 1).getLatitude();
        double lon1 = this.vertices.get(0).getLongitude();
        double lon2 = this.vertices.get(this.vertices.size() - 1).getLongitude();

        double latDiff = Math.abs(Math.abs(lat1) - Math.abs(lat2));
        double latDistance = latDiff * 111319.9;

        double lonDiff = Math.abs(Math.abs(lon1) - Math.abs(lon2));
        double lonDistance = lonDiff * 111319.9;

        int y = (int) latDistance / intervalDistance;
        int x = (int) lonDistance / intervalDistance;

        // Loop to verify where it is possible to add new connections
        int currentVertex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i > 0 && i < rows - 1 && j > 0 && j < cols - 1) {
                    this.addEdge(currentVertex, currentVertex - cols);
                    this.addEdge(currentVertex, currentVertex - 1);
                    this.addEdge(currentVertex, currentVertex + 1);
                    this.addEdge(currentVertex, currentVertex + cols);
                } else if (i == 0 && j > 0 && j < cols - 1) {
                    this.addEdge(currentVertex, currentVertex - 1);
                    this.addEdge(currentVertex, currentVertex + 1);
                    this.addEdge(currentVertex, currentVertex + cols);
                } else if (i == rows - 1 && j > 0 && j < cols - 1) {
                    this.addEdge(currentVertex, currentVertex - cols);
                    this.addEdge(currentVertex, currentVertex - 1);
                    this.addEdge(currentVertex, currentVertex + 1);
                } else if (j == 0 && i > 0 && i < rows - 1) {
                    this.addEdge(currentVertex, currentVertex - cols);
                    this.addEdge(currentVertex, currentVertex + 1);
                    this.addEdge(currentVertex, currentVertex + cols);
                } else if (j == cols - 1 && i > 0 && i < rows - 1) {
                    this.addEdge(currentVertex, currentVertex - cols);
                    this.addEdge(currentVertex, currentVertex - 1);
                    this.addEdge(currentVertex, currentVertex + cols);
                } else if (i == 0 && j == 0) {
                    this.addEdge(currentVertex, currentVertex + 1);
                    this.addEdge(currentVertex, currentVertex + cols);
                } else if (i == 0 && j == cols - 1) {
                    this.addEdge(currentVertex, currentVertex - 1);
                    this.addEdge(currentVertex, currentVertex + cols);
                } else if (i == rows - 1 && j == 0) {
                    this.addEdge(currentVertex, currentVertex - cols);
                    this.addEdge(currentVertex, currentVertex + 1);
                } else if (i == rows - 1 && j == cols - 1) {
                    this.addEdge(currentVertex, currentVertex - cols);
                    this.addEdge(currentVertex, currentVertex - 1);
                }
                currentVertex++;
            }
        }
    }
    public Vertex findNearestVertex(double targetLatitude, double targetLongitude) {
        if (vertices.isEmpty()) {
            return null;
        }

        Vertex nearestVertex = vertices.get(0);
        double minDistance = distance(nearestVertex.getLatitude(), nearestVertex.getLongitude(), targetLatitude, targetLongitude);

        for (Vertex vertex : vertices) {
            double currentDistance = distance(vertex.getLatitude(), vertex.getLongitude(), targetLatitude, targetLongitude);

            if (currentDistance < minDistance) {
                nearestVertex = vertex;
                minDistance = currentDistance;
            }
        }

        return nearestVertex;
    }

    double distance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return 6371 * c;
    }
    public void increaseWeight(double latitude, double longitude, double radius) {
        for (Vertex vertex : this.vertices) {
            if (distance(vertex.getLatitude(), vertex.getLongitude(), latitude, longitude) <= radius) {
                for (Edge edge : vertex.getAllConnections()) {
                    edge.increaseWeight();
                }
            }
        }
    }
    public void increaseWeight(double latitude, double longitude, double radius) {
        for (Vertex vertex : this.vertices) {
            if (distance(vertex.getLatitude(), vertex.getLongitude(), latitude, longitude) <= radius) {
                for (Edge edge : vertex.getAllConnections()) {
                    edge.increaseWeight();
                }
            }
        }
    }
}
