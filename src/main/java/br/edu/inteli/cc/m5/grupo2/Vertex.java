package br.edu.inteli.cc.m5.grupo2;

import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {

    private final int id;
    private final double latitude;
    private final double longitude;
    private final double altitude;
    private final LinkedList<Edge> connections;
    private double custoDoInicio = Double.POSITIVE_INFINITY;
    private double custoEstimadoTotal;
    private Vertex pai;

    public Vertex(int id, double latitude, double longitude, double altitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.connections = new LinkedList<Edge>();
    }

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
     * Add a connection from a departure vertex to an arrival vertex by creating a new edge in connections adjacency list.
     * @param   Vertex   arrivalVertex
     */
    public void addConnectionTo(Vertex arrivalVertex) {
        int weight = (int) (Math.abs(arrivalVertex.getAltitude() - this.altitude) + arrivalVertex.getAltitude());
        this.connections.add(new Edge(arrivalVertex, weight));
    }

    /*
     * Return a string that represents the adjacency list of the vertex in a specific format.
     * @return   String   "0: 1 | 2 | 3 | ... | n"
     */
    public String getAdjacencyListAsString() {
        int quantityOfConnections = this.getNumberOfConnections();
        StringBuilder str = new StringBuilder(Integer.toString(this.id) + ": ");

        for (int i = 0; i < quantityOfConnections; i++) {
            str.append(getConnectionAt(i).getArrivalVertex().getId());
            if (i + 1 < quantityOfConnections) str.append(" | ");
        }

        return str.toString();
    }
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
