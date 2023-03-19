package br.edu.inteli.cc.m5.grupo2;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class AStarTest {

    
    //    This tests if the heuristic function is correct, by giving two vertexes
     //   to be calculated
    
    @Test
    private void heuristica(){
        Graph graph = new Graph();

        Vertex vertex = new Vertex(1,100.00, 100.00, 100.00);
        Vertex vertex4 = new Vertex(4,400.00, 400.00, 400.00);

        graph.addVertex(vertex);
        graph.addVertex(vertex4);

        assertEquals(424.26406871192853, AStar.heuristic(vertex, vertex4));
    }

   
    //    This tests if the best path, giving our considerations such as altitude and distance, is being calculated
     
    @Test(timeOut = 60000)
    private void findCorrectPath(){
        Graph graph = new Graph();

        Vertex vertex = new Vertex(1,100.00, 100.00, 100.00);
        Vertex vertex2 = new Vertex(2,100.00, 200.00, 200.00);
        Vertex vertex3 = new Vertex(3,150.00, 150.00, 150.00);
        Vertex vertex4 = new Vertex(4,150.00, 200.00, 150.00);

        graph.addVertex(vertex);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);

        graph.addEdge(vertex.getId() - 1, vertex2.getId() - 1);
        graph.addEdge(vertex.getId() - 1, vertex3.getId() - 1);
        graph.addEdge(vertex3.getId() - 1, vertex2.getId() - 1);
        graph.addEdge(vertex2.getId() - 1, vertex4.getId() - 1);

        List<Vertex> caminho = new ArrayList<>();

        caminho.add(vertex);
        caminho.add(vertex3);
        caminho.add(vertex2);
        caminho.add(vertex4);

        assertEquals(caminho, AStar.findPath(vertex, vertex4));
    }

    /*
        This tests finding a path in a graph created by one of the input files we expect to use,
        then prints the time it took to calculate in milliseconds
     */
    @Test(timeOut = 60000)
    private void findPathOneDted(){
        String path = "src/main/resources/dted/SaoPaulo/W045_S23.dt2";
        double[][] newMap = Dted.readDted(path, 180);
        Graph graph = new Graph();

        for (int i = 0;i < newMap.length - 1; i++) {
            graph.addVertex(newMap[i][1], newMap[i][2], newMap[i][0]);
        }

        int rows = (int) newMap[newMap.length - 1][1];
        int cols = (int) newMap[newMap.length - 1][2];

        // Creating all possible connections in the graph
        graph.connectVertices(180, rows, cols);

        long start = System.currentTimeMillis();

        AStar.findPath(graph.getVertices().get(0), graph.getVertices().get(503300));

        long end = System.currentTimeMillis();
        System.out.println("DEBUG: Took " + (end - start) + " MilliSeconds");
    }
}
