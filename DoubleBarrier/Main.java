import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args){
        Queue<String> fila = new LinkedList();
        Semaphore mutex = new Semaphore(1);
        Semaphore mutexContador = new Semaphore(1);
        Semaphore mutexFila = new Semaphore(1);
        Semaphore barreiraEntrada = new Semaphore(0);
        Semaphore barreiraSaida = new Semaphore(1);
        Semaphore mutexCombinadora = new Semaphore(0);

        int[] contador = {0};
        int[] contadorArquivos = {0};
        final int n = 4;

        Trabalhadora t1 = new Trabalhadora(n, contador, contadorArquivos, mutex, mutexContador, mutexFila, barreiraEntrada, barreiraSaida, mutexCombinadora, fila);
        Trabalhadora t2 = new Trabalhadora(n, contador, contadorArquivos, mutex, mutexContador, mutexFila, barreiraEntrada, barreiraSaida, mutexCombinadora, fila);
        Trabalhadora t3 = new Trabalhadora(n, contador, contadorArquivos, mutex, mutexContador, mutexFila, barreiraEntrada, barreiraSaida, mutexCombinadora, fila);
        Trabalhadora t4 = new Trabalhadora(n, contador, contadorArquivos, mutex, mutexContador, mutexFila, barreiraEntrada, barreiraSaida, mutexCombinadora, fila);

        Combinadora c = new Combinadora(n, contador, mutex, mutexFila, barreiraEntrada, barreiraSaida, mutexCombinadora, fila);
        //for (int i = 0; i < 3; i++) t.run();

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        c.start();
    }
}