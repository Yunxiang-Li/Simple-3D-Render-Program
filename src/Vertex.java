/**
 * This class represents a structure to store our three coordinates (X, Y and Z).
 * X coordinate means movement in left-right direction, Y means movement up-down on screen,
 * and Z will be depth (so Z axis is perpendicular to your screen). Positive Z will mean "towards the observer".
 */
public class Vertex {
    // Fields indicate positions in three axises.
    public double x;
    public double y;
    public double z;

    // Constructor
    Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
