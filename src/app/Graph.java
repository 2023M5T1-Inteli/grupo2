import java.util.ArrayList;

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

    public ArrayList<Vertex> getVertices() {

        return this.vertices;

    }


}
