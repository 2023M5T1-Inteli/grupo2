package br.edu.inteli.cc.m5.grupo2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

    private final ArrayList<Vertex> vertices;
    private int nextVertexId = 0;

    public Graph() {
        this.vertices = new ArrayList<Vertex>();
    }

    public Vertex addVertex(double latitude, double longitude, double altitude) {
        Vertex vertex = new Vertex(nextVertexId++, latitude, longitude, altitude);
        this.vertices.add(vertex);
        return vertex;
    }

    public void addEdge(int vertexId, int arrivalVertex) {
        this.vertices.get(vertexId).addConnectionTo(this.vertices.get(arrivalVertex));
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    public LinkedList<Edge> getConnectionsOf(int vertexId) {
        return this.vertices.get(vertexId).getAllConnections();
    }

     public void connectVertices(int distance) {

        int x = (int) (this.vertices.get(this.vertices.size() - 1).getLatitude() - this.vertices.get(0).getLatitude());
        x = x/distance;

        int y = (int) (this.vertices.get(this.vertices.size() - 1).getLongitude() - this.vertices.get(0).getLongitude());
        y = y/distance;

        int currentVertex = 0;
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                if (i > 0 && j > 0){
                    this.addEdge(currentVertex, currentVertex - x - 2);
                }
                if (i > 0) {
                    this.addEdge(currentVertex, currentVertex - x - 1);
                }
                if (i > 0 && j < y){
                    this.addEdge(currentVertex, currentVertex - x);
                }
                if (j > 0) {
                    this.addEdge(currentVertex, currentVertex - 1);
                }
                if (j < y) {
                    this.addEdge(currentVertex, currentVertex + 1);
                }
                if (i < x && j > 0){
                    this.addEdge(currentVertex, currentVertex + x);
                }
                if (i < x) {
                    this.addEdge(currentVertex, currentVertex + x + 1);
                }
                if (i < x && j < y){
                    this.addEdge(currentVertex, currentVertex + x + 2);
                }
                currentVertex ++;
            }
        }
    }
}
