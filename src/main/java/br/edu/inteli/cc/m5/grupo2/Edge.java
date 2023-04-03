package br.edu.inteli.cc.m5.grupo2;

public class Edge {

// Define two private variables - arrivalVertex and weight
private final Vertex arrivalVertex;
private int weight;

// Constructor method for the Edge class
public Edge(Vertex arrivalVertex, int weight) {
    // Assigns the passed arrivalVertex and weight to the private variables
    this.arrivalVertex = arrivalVertex;
    this.weight = weight;
}

// Getter method for the arrivalVertex variable
public Vertex getArrivalVertex() {
    return this.arrivalVertex;
}

// Getter method for the weight variable
public int getWeight() {
    return this.weight;
}
public void increaseWeight() {
        this.weight *= 10000;
}

}