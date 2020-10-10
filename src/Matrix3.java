/**
 *  This class represents a 3D Matrix which will help us do matrix-matrix and vector-matrix multiplications.
 */
public class Matrix3 {
    public double[] values;

    // Constructor.
    Matrix3(double[] values) {
        this.values = values;
    }

    /**
     * Multiply two 3D matrix and return the result 3D matrix.(current matrix's column amount should equal to other
     * matrix's row)
     * @param other a Matrix3 object indicates another matrix.
     * @return a Matrix3 object which indicates the result 3D matrix.(current matrix's row and second matrix's column)
     */
    Matrix3 multiply(Matrix3 other) {
        // Use a double array to store the result data.
        double[] results = new double[9];

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                for (int i = 0; i < 3; ++i)
                    results[row * 3 + col] +=  this.values[row * 3 + i] * other.values[i * 3 + col];
            }
        }

        // Create the new Matrix object.
        return new Matrix3(results);
    }

    /**
     * Multiply a Vertex object with the current matrix and return the result vertex object.
     * (vertex's column amount should equal to current matrix's row amount)
     * @param curr a Vertex object indicates the input vertex.
     * @return a Vertex object which indicates the result vertex.(vertex's row and current matrix's column)
     */
    Vertex transform(Vertex curr) {
        return new Vertex(curr.x * this.values[0] + curr.y * this.values[3] + curr.z * this.values[6],
                          curr.x * this.values[1] + curr.y * this.values[4] + curr.z * this.values[7],
                          curr.x * this.values[2] + curr.y * this.values[5] + curr.z * this.values[8]);
    }

}
