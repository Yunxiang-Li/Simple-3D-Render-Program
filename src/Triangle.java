import java.awt.*;

/**
 * This class represents a triangle binding together three vertices and stores its color.
 */
public class Triangle {
    // Fields of a triangle object, three vertex objects and one color object.
    public Vertex v1;
    public Vertex v2;
    public Vertex v3;
    public Color color;

    // Constructor
    Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }
}
