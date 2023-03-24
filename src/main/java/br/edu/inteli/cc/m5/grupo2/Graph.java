package br.edu.inteli.cc.m5.grupo2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
//start of the graph backend
public class Graph {
// declares a private static final Logger object named "LOGGER", which is used to log messages in the Graph class.
private static final Logger LOGGER = Logger.getLogger(Graph.class.getName());
// declares a private final instance variable named "vertices", which is an ArrayList of Vertex objects.
private final ArrayList<Vertex> vertices;
// declares a private instance variable named "nextVertexId", which is an integer that represents the next available ID for a vertex.
private int nextVertexId = 0;
// declares private instance variables named "cols" and "rows", which represent the number of columns and rows in a grid of vertices, respectively.
private int cols, rows;

public Graph() {
    // initializes the "vertices" instance variable with an empty ArrayList.
    this.vertices = new ArrayList<>();
}

// declares a public method named "addVertex", which adds a new Vertex object to the "vertices" ArrayList.
public void addVertex(double latitude, double longitude, double altitude) {
    // creates a new Vertex object with the specified latitude, longitude, altitude, and the next available ID, and assigns it to a local variable named "vertex".
    Vertex vertex = new Vertex(nextVertexId++, latitude, longitude, altitude);
    // adds the new Vertex object to the "vertices" ArrayList.
    this.vertices.add(vertex);
}

// declares a public method named "getCols", which returns the number of columns in the grid.
public int getCols(){return this.cols;}

// declares a public method named "getRows", which returns the number of rows in the grid.
public int getRows(){return this.rows;}

// Remove before deploy
// declares a public method named "addVertex", which adds a new Vertex object to the "vertices" ArrayList.
public void addVertex(Vertex vertex){
    // begins a for loop that iterates through the "vertices" ArrayList.
    for (int i = 0; i < this.getVertices().size(); i++){
        // checks if the ID of the new Vertex object matches the ID of any existing Vertex objects in the "vertices" ArrayList.
        if (vertex.getId() == this.getVertices().get(i).getId()){
            // logs a warning message indicating that a Vertex object with the same ID already exists in the "vertices"
            LOGGER.log(Level.WARNING, "Vertex with id == " + vertex.getId() + " already exists");
            return;
        }
    }
    // adds the new Vertex object to the "vertices" ArrayList.
    this.vertices.add(vertex);
}

public void addEdge(int vertexId, int arrivalVertexId) {
    // adds a connection between the Vertex object with ID "vertexId" and the Vertex object with ID "arrivalVertexId".
    this.vertices.get(vertexId).addConnectionTo(this.vertices.get(arrivalVertexId));
}

public ArrayList<Vertex> getVertices() {
    // returns the "vertices" ArrayList.
    return this.vertices;
}

public LinkedList<Edge> getConnectionsOf(int vertexId) {
    // returns a LinkedList of Edge objects representing all the connections of the Vertex object with ID "vertexId".
    return this.vertices.get(vertexId).getAllConnections();
}

public String getAdjacencyListForEachVertex(){
    StringBuilder str = new StringBuilder();
    // begins a for loop that iterates through the "vertices" ArrayList.
    for (Vertex vertex : this.vertices) {
        // appends the adjacency list of each Vertex object to the StringBuilder object "
            str.append(vertex.getAdjacencyListAsString());
            str.append("\n");
        }
        return str.toString();
    }

    public void connectVertices(int intervalDistance, int rows, int cols) {
        this.cols = cols;
        this.rows = rows;

        // Amount of vertices per latitude (y) and longitude (x)
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
}
