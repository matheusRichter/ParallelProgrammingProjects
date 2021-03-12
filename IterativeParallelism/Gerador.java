package TrabalhoParalelismoIterativo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Gerador {
    public static double[][] generateMatrix(int rows, int columns, int id) throws IOException {

        // output array to store the matrix values
        double[][] result = new double[rows][columns];

        // TO generate a random integer.
        Random random = new Random();

        // adding values at each index.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = random.nextDouble();
            }
        }

        // generates file with matrix elements
        FileWriter writer = new FileWriter(id + ".csv");
        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[i].length; j++){
                writer.append(String.valueOf(result[i][j]));
                writer.append(",");
            }
            writer.append('\n');
        }
        writer.flush();
        writer.close();

        // returning output.
        return result;
    }

    // to print the matrix
    public static void print(int[][] matrix) {

        System.out.println();

        int rows = matrix.length;
        int columns = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
}