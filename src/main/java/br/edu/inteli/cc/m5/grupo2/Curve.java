package br.edu.inteli.cc.m5.grupo2;

import java.util.List;

public class Curve {
    private AStar aStar;
    private Graph graph;

    public Curve(Graph graph) {
        this.graph = graph;
        this.aStar = new AStar();
    }

    public static List<Vertex> findPathAndUpdateWeight(Vertex start, Vertex end) {
        // Primeiro, encontre o caminho usando o método findPath
        List<Vertex> path = AStar.findPath(start, end);

        if (path == null) {
            return null;
        }

        return updatePathAndCheckAngles(start, end, path, 0);
    }

    private static List<Vertex> updatePathAndCheckAngles(Vertex start, Vertex end, List<Vertex> path, int iteration) {
        if (iteration >= 1) {
            return path;
        }

        boolean angleBelow90 = false;

        // Verifique se há curvas menores ou iguais a 90° no caminho e aumente o peso das arestas correspondentes
        for (int i = 1; i < path.size() - 1; i++) {
            Vertex prev = path.get(i - 1);
            Vertex current = path.get(i);
            Vertex next = path.get(i + 1);

            double angle = Math.abs(Math.atan2(next.getLongitude() - current.getLongitude(), next.getLatitude() - current.getLatitude()) -
                    Math.atan2(current.getLongitude() - prev.getLongitude(), current.getLatitude() - prev.getLatitude()));

            angle = Math.toDegrees(angle);

            // Aproximando o ângulo para evitar problemas de precisão
            angle = Math.round(angle);

            if (angle <= 100) {
                for (Edge edge : current.getAllConnections()) {
                    edge.increaseWeight();
                }
                angleBelow90 = true;
            }
        }

        // Se algum ângulo menor ou igual a 90° foi encontrado, verifique novamente depois de atualizar os pesos das arestas
        if (angleBelow90) {
            List<Vertex> newPath = AStar.findPath(start, end);
            return updatePathAndCheckAngles(start, end, newPath, iteration + 1);
        } else {
            return path;
        }
    }
}
