package br.edu.inteli.cc.m5.grupo2;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class GraphTest {

    String path = "src/main/resources/dted/SaoPaulo/W045_S23.dt2";
    double[][] newMap = Dted.readDted(path, 180);
    Graph graph = createGraphForTests();


    private Graph createGraphForTests(){
        Graph graph = new Graph();

        for (int i = 0;i < newMap.length - 1; i++) {
            graph.addVertex(newMap[i][1], newMap[i][2], newMap[i][0]);
        }

        int rows = (int) newMap[newMap.length - 1][1];
        int cols = (int) newMap[newMap.length - 1][2];

        // Creating all possible connections in the graph
        graph.connectVertices(180, rows, cols);

        return graph;
    }

    @Test // Tests add vertex function using doubles
    public void addVertexWithDoubles(){
        Graph graph = new Graph();

        graph.addVertex(100.00, 100.00, 100.00);

        assertNotNull(graph.getVertices());
    }

    @Test // Tests getting the number of columns in graph
    public void getColsNumber(){ assertEquals((int) newMap[newMap.length - 1][2], graph.getCols()); }

    @Test // Tests getting the number of rows in graph
    public void getRowsNumber(){ assertEquals((int) newMap[newMap.length - 1][1], graph.getCols()); }

    @Test
    private void addVertexWithSameId(){
        Graph graph = new Graph();

        Vertex vertex = new Vertex(1,100.00, 100.00, 100.00);

        graph.addVertex(vertex);
        graph.addVertex(vertex);

        assertEquals(1, graph.getVertices().size());
    }

    @Test // Tests add a vertex with a vertex type parameter
    private void addVertexWithExistingVertex(){
        Graph graph = new Graph();

        Vertex vertex = new Vertex(1,100.00, 100.00, 100.00);

        graph.addVertex(vertex);

        assertNotNull(graph.getVertices());
    }

    @Test // Tests adding an edge between two vertexes
    private void addEdge(){
        Graph graph = new Graph();

        Vertex vertex = new Vertex(1,100.00, 100.00, 100.00);
        Vertex vertex2 = new Vertex(2,200.00, 200.00, 200.00);

        graph.addVertex(vertex);
        graph.addVertex(vertex2);

        graph.addEdge(vertex.getId() - 1, vertex2.getId() - 1);

        assertEquals(1, vertex.getNumberOfConnections());
    }

    @Test // Tests getting all vertexes from a graph
    private void getVertices(){
        Graph graph = new Graph();

        Vertex vertex = new Vertex(1,100.00, 100.00, 100.00);
        Vertex vertex2 = new Vertex(2,200.00, 200.00, 200.00);

        ArrayList<Vertex> listOfVertices= new ArrayList<>();

        listOfVertices.add(vertex);
        listOfVertices.add(vertex2);


        graph.addVertex(vertex);
        graph.addVertex(vertex2);

        assertEquals(listOfVertices, graph.getVertices());
    }

    @Test // Tests getting all connections from a vertex of a graph
    private void getAllConections(){
        Graph graph = new Graph();

        Vertex vertex = new Vertex(1,100.00, 100.00, 100.00);
        Vertex vertex2 = new Vertex(2,200.00, 200.00, 200.00);

        graph.addVertex(vertex);
        graph.addVertex(vertex2);
        graph.addEdge(vertex.getId() - 1, vertex2.getId() - 1);

        assertEquals(vertex.getAllConnections(), graph.getConnectionsOf(0));
    }

    @Test // Tests the number of connections from top or bottom corners vertexes
    public void vertexTwoConnections() {
        // Check that each vertex has the correct number of edges
        assertEquals(2, graph.getVertices().get(0).getAllConnections().size());
    }

    @Test // Tests the number of connections from a vertex on the edge but not on corner
    public void vertexThreeConnections() {
        // Check that each vertex has the correct number of edges
        assertEquals(3, graph.getVertices().get(2).getAllConnections().size());
    }

    @Test // Tests the number of connections from a vertex that is not on edges
    public void vertexFourConnections() {
        int cols = graph.getCols();

        // Check that each vertex has the correct number of edges
        assertEquals(4, graph.getVertices().get(cols + 2).getAllConnections().size());
    }

    @Test
    public void testDistance() {
        Graph graph = new Graph();

        // Test distance calculation between two points
        Vertex v1 = new Vertex(1, 37.7749, -122.4194, 0);
        Vertex v2 = new Vertex(2, 40.7128, -74.0060, 0);

        graph.addVertex(v1);
        graph.addVertex(v2);

        double expectedDistance = 4129.08616505731; // updated expected value
        double actualDistance = graph.distance(v1.getLatitude(), v1.getLongitude(), v2.getLatitude(), v2.getLongitude());
        assertEquals(expectedDistance, actualDistance, 2); // adjusting precision level

        Vertex v3 = new Vertex(3, 35.6895, 139.6917, 0);
        double expectedSamePointDistance = 0;
        double actualSamePointDistance = graph.distance(v3.getLatitude(), v3.getLongitude(), v3.getLatitude(), v3.getLongitude());
        assertEquals(expectedSamePointDistance, actualSamePointDistance, 0.001);
    }

    @Test
    public void testIncreaseWeight() {
        Graph graph = new Graph();

        // Create vertices and edges
        Vertex v1 = new Vertex(1, 37.7749, -122.4194, 1);
        Vertex v2 = new Vertex(2, 40.7128, -74.0060, 0);
        Vertex v3 = new Vertex(3, 41.8781, -87.6298, 0);
        Vertex v4 = new Vertex(4, 51.5074, -0.1278, 0);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(v1.getId() - 1, v2.getId() - 1);
        graph.addEdge(v1.getId() - 1, v3.getId() - 1);
        graph.addEdge(v1.getId() - 1, v4.getId() - 1);

        // Increase weight of all edges within 500 km of San Francisco (v1)
        double latitude = 37.7749;
        double longitude = -122.4194;
        double radius = 500;
        graph.increaseWeight(latitude, longitude, radius);

        // Check that edges connected to v1 have increased weight
        LinkedList<Edge> connections = graph.getConnectionsOf(1);
        for (Edge edge : connections) {
            double expectedWeight = 10000;
            double actualWeight = edge.getWeight();
            assertEquals(expectedWeight, actualWeight, 0.01);
        }
    }
}
