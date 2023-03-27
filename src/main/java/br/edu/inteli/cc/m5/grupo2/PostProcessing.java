package br.edu.inteli.cc.m5.grupo2;

import java.util.List;

public class PostProcessing {

    public static void main(String[] args) {
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

        List<Vertex> lista = AStar.findPath(graph.getVertices().get(0), graph.getVertices().get(504000));

        assert lista != null;
        double[] x = new double[lista.size()];
        double[] y = new double[lista.size()];

        for(int i = 0; i < lista.size(); i++){
            x[i] = lista.get(i).getLatitude();
            y[i] = lista.get(i).getLongitude();
        }

        PolynomialInterpolator interpolador = new PolynomialInterpolator(x,y);

        for(int i = 0; i < lista.size(); i++){
            System.out.println(y[i]);
            y[i] = lista.get(i).setLongitude(interpolador.interpolate(x[i]));
            System.out.println(y[i]);
            System.out.println("/////////////////////////////");
        }

        long end = System.currentTimeMillis();
        System.out.println("DEBUG: Took " + (end - start) + " MilliSeconds");
    }
}
