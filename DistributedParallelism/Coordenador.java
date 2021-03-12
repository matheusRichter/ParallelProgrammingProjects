package ParalelismoDistribuido;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Coordenador {
    private static double[] getColumn(double[][] array, int index) {
        double[] column = new double[array.length];
        for(int i=0; i < column.length; i++){
            column[i] = array[i][index];
        }
        return column;
    }

    private static void write_matrix(String file, double[][] m) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                writer.append(String.valueOf(m[i][j]));
                writer.append(",");
            }
            writer.append("\n");
        }
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // recuperação das matrizes ///////////////////////////
        Geradora g = new Geradora();
        double[][] m1 = g.recupera_matrix("matrix.csv");
        double[][] m2 = g.recupera_matrix("matrix2.csv");
        ///////////////////////////////////////////////////////

        // inicia matrix resposta //////////////////////////////
        double[][] r = new double[m1.length][m2[0].length];
        ////////////////////////////////////////////////////////

        // divide as matrizes //////////////////////////////////////////////////////
        // divide m1
        Matrix matrix11 = new Matrix(Arrays.copyOfRange(m1,0,m1.length/4));
        Matrix matrix12 = new Matrix(Arrays.copyOfRange(m1,m1.length/4,m1.length/2));
        Matrix matrix13 = new Matrix(Arrays.copyOfRange(m1,m1.length/2,(m1.length/4)*3));
        Matrix matrix14 = new Matrix(Arrays.copyOfRange(m1,(m1.length/4)*3,m1.length));

        // Matriz 2 transposta
        Matrix m2_tt = new Matrix(m2);
        ////////////////////////////////////////////////////////////////////////////

        // envia as matrizes divididas /////////////////////////////////////////////
        Op1 m31 = new Op1(matrix11, m2_tt, 6000, 6001,6300,31);
        Op1 m32 = new Op1(matrix12, m2_tt, 6004, 6005,6301,32);
        Op1 m33 = new Op1(matrix13, m2_tt, 6007, 6008,6302,33);
        Op1 m34 = new Op1(matrix14, m2_tt, 6010, 6011,6303,34);

        m31.start();
        m32.start();
        m33.start();
        m34.start();

        m31.join();
        m32.join();
        m33.join();
        m34.join();
        ////////////////////////////////////////////////////////////////////////////

        // junta as matrizes calculadas ////////////////////////////////////////////
        int contador = 0;
        for (int i = 0; i < m31.getResutado().getMatriz().length; i++) {
            r[contador] = m31.getResutado().getMatriz()[i];
            contador++;
        }
        for (int i = 0; i < m32.getResutado().getMatriz().length; i++) {
            r[contador] = m32.getResutado().getMatriz()[i];
            contador++;
        }
        for (int i = 0; i < m33.getResutado().getMatriz().length; i++) {
            r[contador] = m33.getResutado().getMatriz()[i];
            contador++;
        }
        for (int i = 0; i < m34.getResutado().getMatriz().length; i++) {
            r[contador] = m34.getResutado().getMatriz()[i];
            contador++;
        }
        System.out.println("Matriz resultador montada");
        write_matrix("resultado.csv",r);
        ////////////////////////////////////////////////////////////////////////////
    }
}
