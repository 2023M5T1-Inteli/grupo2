package br.edu.inteli.cc.m5.grupo2;

import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {

    // instance variables
    private final int id;
    private final double latitude;
    private final double longitude;
    private final double altitude;
    private final LinkedList<Edge> connections;
    private double custoDoInicio = Double.POSITIVE_INFINITY; // start cost is infinite
    private int weightDangerArea;
    private double custoEstimadoTotal; // estimated total cost
    private Vertex pai; // parent node

    // constructor
    public Vertex(int id, double latitude, double longitude, double altitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.connections = new LinkedList<Edge>(); // initialize empty connections list
    }

    // getters for instance variables
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

    // getter for a specific connection at a given index
    public Edge getConnectionAt(int index) {
        return this.connections.get(index);
    }

    // getter for all connections
    public LinkedList<Edge> getAllConnections() {
        return this.connections;
    }

    // getter for connections
    public LinkedList<Edge> getConnections() {
        return this.connections;
    }

    // getter for the number of connections
    public int getNumberOfConnections() {
        return this.connections.size();
    }

    /*
     * Add a connection from a departure vertex to an arrival vertex by creating a new edge in connections adjacency list.
     * @param   Vertex   arrivalVertex
     */
    public void addConnectionTo(Vertex arrivalVertex) {
        int weight = (int) Math.abs(arrivalVertex.getAltitude() - this.altitude); // calculate weight based on altitude difference
        this.connections.add(new Edge(arrivalVertex, weight)); // add new edge to connections list
    }

    /*
     * Set the danger area weight, for make the algorithm doesn't take this node
     * @param int weightDangerArea
     */
    public void setDangerAreaWeight(int weightChosen) {
        weightChosen = this.weightDangerArea;
    }

    /*
     * Return a string that represents the adjacency list of the vertex in a specific format.
     * @return   String   "0: 1 | 2 | 3 | ... | n"
     */
    public String getAdjacencyListAsString() {
        int quantityOfConnections = this.getNumberOfConnections();
        StringBuilder str = new StringBuilder(Integer.toString(this.id) + ": "); // initialize string builder with vertex id

        // loop through all connections and append arrival vertex id to string builder
        for (int i = 0; i < quantityOfConnections; i++) {
            str.append(getConnectionAt(i).getArrivalVertex().getId());
            if (i + 1 < quantityOfConnections) str.append(" | "); // add pipe separator unless it's the last connection
        }

        return str.toString(); // return formatted string
    }

    // setters and getters for instance variables related to A* algorithm
    public void setCustoDoInicio(double custo) {
        this.custoDoInicio = custo;
    }
    public void setCustoEstimadoTotal(double custoEstimadoTotal) {
        this.custoEstimadoTotal = custoEstimadoTotal;
    }
    public double getCustoDoInicio() {
        return custoDoInicio;
    }
    public void setPai(Vertex pai) {
        this.pai = pai;
    }
    public Vertex getPai() {
        return pai;
    }
    public int compareTo(Vertex other) {
        return Double.compare(this.getCustoEstimadoTotal(), other.getCustoEstimadoTotal());
    }
    public double getCustoEstimadoTotal() {
        return this.custoEstimadoTotal;
    }
}
