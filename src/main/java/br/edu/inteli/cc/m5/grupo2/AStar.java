package br.edu.inteli.cc.m5.grupo2;

import java.util.*;

public class AStar {
    public static double heuristica(Vertex start, Vertex end) {
        double d1 = Math.abs(end.getLatitude() - start.getLatitude());
        double latDistance = d1 * 111319.9;
        double d2 = Math.abs(end.getLongitude() - start.getLongitude());
        double lonDistance = d2 * 111319.9;
        double altitudeAverage = (start.getAltitude() + end.getAltitude()) / 2;

        return (Math.sqrt(Math.pow(latDistance, 2) + Math.pow(lonDistance, 2))) * altitudeAverage;
    }

    public static List<Vertex> findPath(Vertex start, Vertex end) {
        //Lista de prioridade vazia
        TreeSet<Vertex> notVisited = new TreeSet<>();

        //Lista de prioridade com vértices vizitados
        Set<Vertex> visited = new HashSet<>();

        //Inicia com o ponto de partida
        notVisited.add(start);

        //Inicia o custo inicial
        start.setCustoDoInicio(0);

        //Inicia o custo estimado total
        start.setCustoEstimadoTotal(0.6 * start.getCustoDoInicio() + 0.4 * heuristica(start, end));

        //Condição que verifica, passo a passo qual o vértice mais barato
        while (!notVisited.isEmpty()) {
            //Pega o vértice com menor custo da fila
            Vertex current = notVisited.pollFirst();

            //Verifica se esse vértice é o final
            if (current == end) {
                return getPath(current);
            }

            //Adiciona o vértice atual ao visitados
            visited.add(current);

            //Verifica as conexões do vértice atual
            for (Edge edge : current.getConnections()) {

                Vertex neighbor = edge.getArrivalVertex();

                //Ignora se o vizinho já foi vizitado
                if (visited.contains(neighbor)) continue;

                //Calcula o custo do vizinho
                double custoTentativo = current.getCustoDoInicio() + edge.getWeight();

                //Se o vizinho não foi visitado ou se o custo for menor
                if (!visited.contains(neighbor) || custoTentativo < neighbor.getCustoDoInicio()) {

                    //Define o custo do inicio e o   estimado
                    neighbor.setCustoDoInicio(custoTentativo);
                    neighbor.setCustoEstimadoTotal(0.6 * custoTentativo + 0.4 * heuristica(neighbor, end));

                    //Define o vértice atual como nó pai do vizinho
                    neighbor.setPai(current);

                    //Adiciona o vizinho na fila de prioridade
                    if (!visited.contains(neighbor)) notVisited.add(neighbor);
                }
            }
        }
        //Retorna vazio se não houver caminho
        return null;
    }

    //Condição que cria o caminho
    private static List<Vertex> getPath(Vertex vertice){
        List<Vertex> caminho = new ArrayList<>();

        //Adicina o vértice atual ao caminho
        caminho.add(vertice);

        //Adiciona os pais do vértice até chegar no inicio
        while (vertice.getPai() != null) {
            vertice = vertice.getPai();
            caminho.add(0, vertice);
        }
        return caminho;
    }
    public static List<Vertex> chaikinSmoothing(List<Vertex> path, int iterations) {
        for (int k = 0; k < iterations; k++) {
            List<Vertex> smoothedPath = new ArrayList<>();

            for (int i = 0; i < path.size() - 1; i++) {
                Vertex v1 = path.get(i);
                Vertex v2 = path.get(i + 1);

                double lat1 = v1.getLatitude() * 0.75 + v2.getLatitude() * 0.25;
                double lon1 = v1.getLongitude() * 0.75 + v2.getLongitude() * 0.25;
                double alt1 = v1.getAltitude() * 0.75 + v2.getAltitude() * 0.25;

                double lat2 = v1.getLatitude() * 0.25 + v2.getLatitude() * 0.75;
                double lon2 = v1.getLongitude() * 0.25 + v2.getLongitude() * 0.75;
                double alt2 = v1.getAltitude() * 0.25 + v2.getAltitude() * 0.75;

                smoothedPath.add(v1);
                smoothedPath.add(new Vertex(-1, lat1, lon1, alt1));
                smoothedPath.add(new Vertex(-1, lat2, lon2, alt2));
            }

            smoothedPath.add(path.get(path.size() - 1));
            path = smoothedPath;
        }

        return path;
    }


}