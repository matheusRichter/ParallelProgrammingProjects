package ParalelismoDistribuido;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Calculador {
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
        ServerSocket matriz_11 = new ServerSocket(6000);
        Socket socket = matriz_11.accept();
        ObjectInputStream leitor = new ObjectInputStream(socket.getInputStream());
        Matrix m11 = (Matrix) leitor.readObject();
        System.out.println("matriz 1 recebida:");
        for (double[] i : m11.getMatriz()){
            System.out.println(Arrays.toString(i));
        }

        //matriz 21
        ServerSocket matriz_21 = new ServerSocket(6001);
        Socket socket2 = matriz_21.accept();
        ObjectInputStream leitor2 = new ObjectInputStream(socket2.getInputStream());
        Matrix m21 = (Matrix) leitor2.readObject();
        System.out.println("matriz 2 recebida:");
        for (double[] i : m21.getMatriz()){
            System.out.println(Arrays.toString(i));
        }
        //////////////////////////////////////////////

        // multiplica as matrizes ////////////////////
        Matrix resultado = new Matrix(multiplicador(m11.getMatriz(),m21.getMatriz()));
        System.out.println("Resultado:");
        for (double[] i : resultado.getMatriz()){
            System.out.println(Arrays.toString(i));
        }
        //////////////////////////////////////////////

        // envia as matrizes /////////////////////////
        Socket env_matriz_r = new Socket("localhost", 6300);
        ObjectOutputStream gravador = new ObjectOutputStream(env_matriz_r.getOutputStream());
        gravador.writeObject(resultado);
        //////////////////////////////////////////////*/
    }
}
