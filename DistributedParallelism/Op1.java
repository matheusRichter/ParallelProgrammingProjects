package ParalelismoDistribuido;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Op1 extends Thread {
    private final Matrix matriz1, matriz2;
    private final int porta1, porta2, porta3;
    private final int id;
    private Matrix resultado;

    public Op1(Matrix matriz1, Matrix matriz2, int porta1, int porta2, int porta3, int id){
        this.matriz1 = matriz1;
        this.matriz2 = matriz2;
        this.porta1 = porta1;
        this.porta2 = porta2;
        this.porta3 = porta3;
        this.id = id;
    }

    public Matrix getResutado() { return this.resultado; }

    public void run() {
        try {
            // envia matrizes para serem multiplicadas
            // matriz 1
            Socket socket = new Socket("localhost", porta1);
            ObjectOutputStream gravador = new ObjectOutputStream(socket.getOutputStream());
            gravador.writeObject(matriz1);
            // matriz 2
            Socket socket2 = new Socket("localhost", porta2);
            ObjectOutputStream gravador2 = new ObjectOutputStream(socket2.getOutputStream());
            gravador2.writeObject(matriz2);

            // recebe matriz resultado
            ServerSocket matriz_31 = new ServerSocket(porta3);
            Socket socket11 = matriz_31.accept();
            ObjectInputStream leitor = new ObjectInputStream(socket11.getInputStream());
            resultado = (Matrix) leitor.readObject();
            System.out.println("matriz " + id + " recebida");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
