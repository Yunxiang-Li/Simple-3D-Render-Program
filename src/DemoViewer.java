// Import swing for light weight GUI.
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
// import abstract Window Toolkit package for heavy weight GUI, distinct from OS to OS.
import java.awt.*;

// import to create a tetrahedron.
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the main entrance of our demo.
 */
public class DemoViewer {

    public static void main(String[] args) {

        // Create a JFrame object and get its panel container.
        JFrame frame = new JFrame();
        // A generic Abstract Window Toolkit(AWT) container object is a component that can contain other AWT components.
        Container contentPane = frame.getContentPane();
        // Set the contentPane's layout to be default.
        contentPane.setLayout(new BorderLayout());

        // Create a horizontal scroll bar with value range from -180 ~ 180 and initialize at 0.
        JSlider horizontalSlider = new JSlider(-180, 180, 0);
        // Place the horizontal scroll bar at south position(below).
        contentPane.add(horizontalSlider, BorderLayout.SOUTH);

        // Create a vertical scroll bar with value range from -90 ~ 90 and initialize at 0.
        JSlider verticalSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        // Place the horizontal scroll bar at east position(right).
        contentPane.add(verticalSlider, BorderLayout.EAST);

        // Create a DrawPanel object to render the visual view.
        JPanel myDrawPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                // Cast graphic to 2d form.
                Graphics2D myGraph = (Graphics2D)g;
                // Set the background color, equivalent to setColor method.
                myGraph.setPaint(Color.BLACK);
                // Fill the specified rectangle start from position(0, 0) with current DrawPanel object's width and height.
                myGraph.fillRect(0, 0, this.getWidth(), this.getHeight());

                // Create a regular tetrahedron by a Array List of four regular triangle objects with different colors.
                List<Triangle> tetrahedron = new ArrayList<>();
                tetrahedron.add(new Triangle(new Vertex(100, 100, 100), new Vertex(-100, -100, 100),
                    new Vertex(-100, 100, -100), Color.WHITE));
                tetrahedron.add(new Triangle(new Vertex(100, 100, 100), new Vertex(-100, -100, 100),
                    new Vertex(100, -100, -100), Color.RED));
                tetrahedron.add(new Triangle(new Vertex(-100, 100, -100), new Vertex(100, -100, -100),
                    new Vertex(100, 100, 100), Color.GREEN));
                tetrahedron.add(new Triangle(new Vertex(-100, 100, -100), new Vertex(100, -100, -100),
                    new Vertex(-100, -100, 100), Color.BLUE));

                // Convert the tetrahedron to a sphere.
                // This can be done by repeatedly subdividing each triangle into four smaller ones and inflating.
                // Here we only perform this process for only 4 times because this process demands high cost.
                for (int i = 0; i < 4; i++) {
                    tetrahedron = inflate(tetrahedron);
                }

                // Treat horizontal slider's value as radius of XZ plane.
                double horizontalRadius = Math.toRadians(horizontalSlider.getValue());
                // Initialize a Matrix3 object form XZ plane rotation.
                Matrix3 XZTransform = new Matrix3(new double[] {
                    Math.cos(horizontalRadius), 0,
                    Math.sin(horizontalRadius), 0, 1, 0, -Math.sin(horizontalRadius),
                    0, Math.cos(horizontalRadius)});

                // Treat vertical slider's value as radius of YZ plane.
                double verticalRadius = Math.toRadians(verticalSlider.getValue());
                // Initialize a Matrix3 object form YZ plane rotation.
                Matrix3 YZTransform = new Matrix3(new double[] {
                    1, 0, 0, 0, Math.cos(verticalRadius), Math.sin(verticalRadius), 0, Math.sin(verticalRadius),
                    Math.cos(verticalRadius)});

                // We can get the matrix object for rotation in XYZ plane by multiply two rotation matrix above.
                Matrix3 XYZTransform = XZTransform.multiply(YZTransform);

                // Create an BufferedImage object to exhibit the tetrahedron with color.
                BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

                // We create a 2D double array as a z-buffer(depth-buffer) and store depth
                // of last seen pixel and we only color the pixel which is closer to users.
                double[][] zBuffer = new double[img.getWidth()][img.getHeight()];

                // Initialize array with extremely far away depths
                for (int x = 0; x < img.getWidth(); ++x)
                    for (int y = 0; y < img.getHeight(); ++y)
                    zBuffer[x][y] = Double.NEGATIVE_INFINITY;

                // Draw each regular triangle's orthographic projection in order to draw
                // the regular tetrahedron's orthographic projection.
                for (Triangle triangle : tetrahedron) {
                    // Rotate each vertex of the triangle in XYZ plane.
                    Vertex v1 = XYZTransform.transform(triangle.v1);
                    Vertex v2 = XYZTransform.transform(triangle.v2);
                    Vertex v3 = XYZTransform.transform(triangle.v3);

                    // Since we are not using Graphics2D anymore, we have to do translation manually.
                    // We will choose the position (Width/2, Height/2) as the new zero position like before.
                    // Therefore, for each vertex's position, we just add Width/2 to its x and Height/2 to its y.
                    v1.x += getWidth() / 2;
                    v1.y += getHeight() / 2;
                    v2.x += getWidth() / 2;
                    v2.y += getHeight() / 2;
                    v3.x += getWidth() / 2;
                    v3.y += getHeight() / 2;

                    // We calculate two vectors of each triangle.
                    Vertex ab = new Vertex(v2.x - v1.x, v2.y - v1.y, v2.z - v1.z);
                    Vertex ac = new Vertex(v3.x - v1.x, v3.y - v1.y, v3.z - v1.z);

                    // Normal vector determines the flat shading of light source in computer graphic.
                    // We use cross product of two vectors to achieve the norm vector.
                    Vertex norm = new Vertex(
                        ab.y * ac.z - ab.z * ac.y,
                        ab.z * ac.x - ab.x * ac.z,
                        ab.x * ac.y - ab.y * ac.x
                    );

                    // Compute the length of the normal vector.
                    double normVecLength = Math.sqrt(norm.x * norm.x + norm.y * norm.y + norm.z * norm.z);

                    // Compute and the unit normal vector in three dimensions, which indicates cos in its specific plane.
                    norm.x /= normVecLength;
                    norm.y /= normVecLength;
                    norm.z /= normVecLength;

                    // We drop the sign from the result because we don't care which triangle side is facing the camera.
                    // In real application, we will need to keep track of that and apply shading accordingly.
                    double angleCos = Math.abs(norm.z);


                    // Compute rectangular bounds for each triangle.
                    // Find the minimum x and y positions of each triangle.
                    int minX = (int) Math.max(0, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x))));
                    int minY = (int) Math.max(0, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y))));

                    // Find the maximum x and y positions of each triangle
                    int maxX = (int) Math.min(img.getWidth() - 1, Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x))));
                    int maxY = (int) Math.min(img.getHeight() - 1, Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y))));

                    // A triangle's area is just two its side vector's cross product divides by 2.
                    double triangleArea =  ((v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x)) / 2;

                    // For each pixel, if its position p is in the triangle ABC, then p = a * A + b * B + c * C
                    // where a + b + c = 1 since barycentric coordinates are normalized and 0 <= a, b, c <= 1.

                    for (int y = minY; y <= maxY; y++) {
                        for (int x = minX; x <= maxX; x++) {
                            // Calculate each position's a, b and c in barycentric coordinate.
                            // We use each position's three barycentric triangles divide by the total triangle
                            // to calculate a, b and c.
                            double a = ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / (2 * triangleArea);
                            double b = ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / (2 * triangleArea);
                            double c = ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / (2 * triangleArea);
                            // Check whether each pixel's position is in our total triangle.
                            if (a >= 0 && a <= 1 && b >= 0 && b <= 1 && c >= 0 && c <= 1) {
                                // Handle rasterisation for each rasterized pixel.
                                // Calculate each pixel's depth under barycentric coordinate.
                                double depth = a * v1.z + b * v2.z + c * v3.z;
                                // Check if current pixel is closer to us
                                if (zBuffer[x][y] < depth) {
                                    // If so, set the deeper pixel to be red, green or blue color after the effect of light.
                                    img.setRGB(x, y, getShade(triangle.color, angleCos).getRGB());
                                    // Update last seen pixel's depth in the z buffer.
                                    zBuffer[x][y] = depth;
                                }
                            }
                        }
                    }
                    // Draw the image. The middle position of our image will be (0, 0) which means (200, 200)
                    // Because We choose the position (Width/2, Height/2) (200, 200) as our new zero position.
                    
                    // The observer parameter notifies the application of updates to an image that is loaded
                    // asynchronously. The observer parameter is not frequently used directly and is not needed
                    // for the BufferedImage class, so it usually is null.
                    myGraph.drawImage(img, 0, 0, null);
                }
            }
        };

        // Set the myDrawPanel object in the center.
        contentPane.add(myDrawPanel, BorderLayout.CENTER);

        // Create two new ChangeEvent objects which represent the change listener for two sliders.
        horizontalSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                myDrawPanel.repaint();
            }
        });

        verticalSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                myDrawPanel.repaint();
            }
        });

        // Set the frame size to be 400 in width and 400 in height (contains area and border).
        frame.setSize(400, 400);
        // Display the whole view.
        frame.setVisible(true);
    }

    /**
     * Reset each position's color after the effect of the directional light. BufferedImage class use sRGB space which
     * is not linear, We need to convert each color from scaled to linear format, apply shade, and then convert back to
     * scaled format again. Real conversion from sRGB to linear RGB is quite involved, we just use the basic approximation.
     * @param color a Color object which indicates the color of the triangle which holds the current position.
     * @param shade a double value between -1 to 1 which indicates the cos value to simulate the effect of directional light.
     * @return a Color object which indicates the final color of the specific position.
     */
    public static Color getShade(Color color, double shade) {
        // Convert from logarithmic to liner format.
        double redLinear = Math.pow(color.getRed(), 2.4) * shade;
        double greenLinear = Math.pow(color.getGreen(), 2.4) * shade;
        double blueLinear = Math.pow(color.getBlue(), 2.4) * shade;

        // Convert from liner to logarithmic format again.
        int red = (int) Math.pow(redLinear, 1/2.4);
        int green = (int) Math.pow(greenLinear, 1/2.4);
        int blue = (int) Math.pow(blueLinear, 1/2.4);

        // Return the final color of each position.
        return new Color(red, green, blue);
    }

    /**
     * Convert the input tetrahedron to a sphere. We do this by repeatedly subdividing each triangle
     * into four smaller ones and inflating.
     * @param tetrahedron a List of Triangle objects which indicates the input tetrahedron.
     * @return a List of Triangle objects which indicates the output sphere.
     */
    public static List<Triangle> inflate(List<Triangle> tetrahedron) {
        // Store our final output sphere.
        List<Triangle> result = new ArrayList<>();

        // For each triangle, subdivide into four smaller triangles.
        for (Triangle t : tetrahedron) {
            Vertex m1 = new Vertex((t.v1.x + t.v2.x)/2, (t.v1.y + t.v2.y)/2, (t.v1.z + t.v2.z)/2);
            Vertex m2 = new Vertex((t.v2.x + t.v3.x)/2, (t.v2.y + t.v3.y)/2, (t.v2.z + t.v3.z)/2);
            Vertex m3 = new Vertex((t.v1.x + t.v3.x)/2, (t.v1.y + t.v3.y)/2, (t.v1.z + t.v3.z)/2);
            result.add(new Triangle(t.v1, m1, m3, t.color));
            result.add(new Triangle(t.v2, m1, m2, t.color));
            result.add(new Triangle(t.v3, m2, m3, t.color));
            result.add(new Triangle(m1, m2, m3, t.color));
        }

        // Update and norm each vertex of each sub triangle.
        for (Triangle t : result) {
            for (Vertex v : new Vertex[] { t.v1, t.v2, t.v3 }) {
                double unitLen = Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z) / Math.sqrt(30000);
                v.x /= unitLen;
                v.y /= unitLen;
                v.z /= unitLen;
            }
        }

        // Return final result(list of Triangle objects as sphere)
        return result;
    }
}
