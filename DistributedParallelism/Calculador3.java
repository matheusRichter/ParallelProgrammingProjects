package ParalelismoDistribuido;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Calculador3 {
    private static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    private static double[][] multiplicador(double[][] m1, double[][] m2){
        double[][] result = new double[m1.length][m2[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(m1, m2, row, col);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // recebe matrizes ///////////////////////////
        // matriz 11
        ServerSocket matriz_13 = new ServerSocket(6007);
        Socket socket = matriz_13.accept();
        ObjectInputStream leitor = new ObjectInputStream(socket.getInputStream());
        Matrix m13 = (Matrix) leitor.readObject();
        System.out.println("matriz 1 recebida:");
        for (double[] i : m13.getMatriz()){
            System.out.println(Arrays.toString(i));
        }

        //matriz 21
        ServerSocket matriz_23 = new ServerSocket(6008);
        Socket socket2 = matriz_23.accept();
        ObjectInputStream leitor2 = new ObjectInputStream(socket2.getInputStream());
        Matrix m23 = (Matrix) leitor2.readObject();
        System.out.println("matriz 2 recebida:");
        for (double[] i : m23.getMatriz()){
            System.out.println(Arrays.toString(i));
        }
        //////////////////////////////////////////////

        // multiplica as matrizes ////////////////////
        Matrix resultado = new Matrix(multiplicador(m13.getMatriz(),m23.getMatriz()));
        System.out.println("Resultado:");
        for (double[] i : resultado.getMatriz()){
            System.out.println(Arrays.toString(i));
        }
        //////////////////////////////////////////////

        // envia as matrizes /////////////////////////
        Socket env_matriz_r = new Socket("localhost", 6302);
        ObjectOutputStream gravador = new ObjectOutputStream(env_matriz_r.getOutputStream());
        gravador.writeObject(resultado);
        //////////////////////////////////////////////
    }
}
