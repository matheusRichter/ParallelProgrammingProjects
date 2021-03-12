package ParalelismoDistribuido;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Geradora {
    private final Random random = new Random();
    private int n_linhas;
    private int n_colunas;
    double[][] matrix;

    public Geradora(int n_linhas, int n_colunas) {
        this.n_linhas = n_linhas;
        this.n_colunas = n_colunas;
        this.matrix = new double[n_linhas][n_colunas];
    }

    public Geradora() {}

    private double[][] gera_matrix() {

        for (int i = 0; i < n_linhas; i++) {
            for (int j = 0; j < n_colunas; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }

    private void write_matrix(String file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < n_linhas; i++) {
            for (int j = 0; j < n_colunas; j++) {
                writer.append(String.valueOf(matrix[i][j]));
                writer.append(",");
            }
            writer.append("\n");
        }
        writer.flush();
        writer.close();
    }

    public double[][] recupera_matrix(String file) throws IOException {
        ArrayList<String[]> a = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader(file));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            a.add(data);
        }
        csvReader.close();

        double[][] m = new double[a.size()][a.get(0).length];
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(i).length; j++) {
                m[i][j] = Double.valueOf(a.get(i)[j]);
            }
        }
        return m;
    }

    public static void main(String[] args) throws IOException {
        Geradora g = new Geradora(8,4);
        g.gera_matrix();
        g.write_matrix("matrix_t1.csv");
        Geradora g2 = new Geradora(4,8);
        g2.gera_matrix();
        g2.write_matrix("matrix_t2.csv");
    }
}
