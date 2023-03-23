package br.edu.inteli.cc.m5.grupo2;

import java.util.List;

public class PostProcessing {

    public static boolean findSharpAngles(List<Vertex> path){
        for (int i = path.size() - 1; i > 0; i--){
            Vertex current = path.get(i);
            Vertex currentFather = current.getPai();

            if (currentFather.getPai() == null ) return true;

            Vertex currentGrandFather = currentFather.getPai();

            double[] currentToFatherVector = { currentFather.getLatitude() - current.getLatitude(),
                    currentFather.getLongitude() - current.getLongitude()};

            double[] fatherToGrandFatherVector = { currentGrandFather.getLatitude() - currentFather.getLatitude(),
                    currentGrandFather.getLongitude() - currentFather.getLongitude()};

            double num = (currentToFatherVector[0] * fatherToGrandFatherVector[0] +
                    currentToFatherVector[1] * fatherToGrandFatherVector[1]);

            double den = (Math.sqrt(Math.pow(currentToFatherVector[0], 2)
                    + Math.pow(currentToFatherVector[1], 2)) * (Math.sqrt(Math.pow(fatherToGrandFatherVector[0], 2)
                    + Math.pow(fatherToGrandFatherVector[1], 2))) );

            double cos =  num / den;
            double angle = Math.toDegrees(Math.acos(cos));

            System.out.println("O ângulo é: " + angle + " graus");
            System.out.println("////////////////////////////");
        }

        return false;
    }

    public static void main(String[] args){
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

        PostProcessing.findSharpAngles(lista);

        long end = System.currentTimeMillis();

        System.out.println("DEBUG: Took " + (end - start) + " MilliSeconds");
    }
}
