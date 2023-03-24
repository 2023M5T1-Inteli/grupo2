package br.edu.inteli.cc.m5.grupo2;

// Importing the LinkedList class from the java.util package
import java.util.LinkedList;

// Defining a class named "Vertex" that implements the "Comparable" interface
public class Vertex implements Comparable<Vertex> {

    // Declaring instance variables as "final" and "private"
    private final int id;
    private final double latitude;
    private final double longitude;
    private final double altitude;
    private final LinkedList<Edge> connections;
    private double custoDoInicio = Double.POSITIVE_INFINITY; // Setting the initial cost of the vertex as infinity
    private double custoEstimadoTotal;
    private Vertex pai;

    // Constructor that initializes the instance variables
    public Vertex(int id, double latitude, double longitude, double altitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.connections = new LinkedList<Edge>(); // Creating an empty list of connections for this vertex
    }

    // Getter methods for the instance variables
    public int getId() {
        return this.id;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public Edge getConnectionAt(int index) {
        return this.connections.get(index);
    }

    public LinkedList<Edge> getAllConnections() {
        return this.connections;
    }

    public LinkedList<Edge> getConnections() {
        return this.connections;
    }

    public int getNumberOfConnections() {
        return this.connections.size();
    }

    /*
     * Adds a connection from the current vertex to a given arrival vertex by creating a new edge in the connections adjacency list.
     * @param   Vertex   arrivalVertex
     */
    public void addConnectionTo(Vertex arrivalVertex) {
        int weight = (int) Math.abs(arrivalVertex.getAltitude() - this.altitude); // Calculates the weight/cost of the edge based on the difference of altitudes between the vertices
        this.connections.add(new Edge(arrivalVertex, weight)); // Adds a new edge to the list of connections for this vertex
    }

    /*
     * Returns a string that represents the adjacency list of the vertex in a specific format.
     * @return   String   "0: 1 | 2 | 3 | ... | n"
     */
    public String getAdjacencyListAsString() {
        int quantityOfConnections = this.getNumberOfConnections();
        StringBuilder str = new StringBuilder(Integer.toString(this.id) + ": "); // Starting with the vertex ID

        for (int i = 0; i < quantityOfConnections; i++) {
            str.append(getConnectionAt(i).getArrivalVertex().getId()); // Adding the ID of the connected vertices
            if (i + 1 < quantityOfConnections) str.append(" | "); // Separating the vertices by a pipe symbol
        }

        return str.toString(); // Returning the string representation of the adjacency list
    }

    // Setter and getter methods for the cost-related instance variables
    public void setCustoDoInicio(double custo) {
        this.custoDoInicio = custo;
    }

    public void setCustoEstimadoTotal(double custoEstimadoTotal) {
        this.custoEstimadoTotal = custoEstimadoTotal;
    }

    public double getCustoDoInicio() {
        return custoDoInicio;
    }

    // Setter and getter methods for the "pai" instance variable, which represents the parent vertex in the search tree
    public void setPai(Vertex pai) {
        this.pai = pai;
    }

  
 // Returns the parent vertex of this vertex.
 // @return the parent vertex of this vertex.
 
public Vertex getPai() {
    return pai;
}


 // Compares this vertex to another vertex based on their estimated total cost.
 // @param other the other vertex to compare to.
 // @return a negative integer, zero, or a positive integer as this vertex is less than, equal to, or greater than the specified vertex.
 
public int compareTo(Vertex other) {
    return Double.compare(this.getCustoEstimadoTotal(), other.getCustoEstimadoTotal());
}


 // Returns the estimated total cost to reach the target vertex from the start vertex through this vertex.
 // @return the estimated total cost to reach the target vertex from the start vertex through this vertex.
 
public double getCustoEstimadoTotal() {
    return this.custoEstimadoTotal;
}



}
