package br.edu.inteli.cc.m5.grupo2;

import org.testng.annotations.Test;

import java.util.ArrayList;

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
}
