import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Trabalhadora extends Thread {

    private Random random = new Random();
    private final int n;
    private int[] contador, contadorArquivos;
    private final Semaphore mutex, mutexContador, mutexFila, barreiraEntrada, barreiraSaida, mutexCombinadora;
    private Queue<String> fila;

    public Trabalhadora(int n, int[] contador, int[]contadorArquivos, Semaphore mutex, Semaphore mutexContador, Semaphore mutexFila, Semaphore barreiraEntrada, Semaphore barreiraSaida, Semaphore mutexCombinadora, Queue fila) {
        this.n = n;
        this.contador = contador;
        this.contadorArquivos = contadorArquivos;
        this.mutex = mutex;
        this.mutexContador = mutexContador;
        this.mutexFila = mutexFila;
        this.barreiraEntrada = barreiraEntrada;
        this.barreiraSaida = barreiraSaida;
        this.mutexCombinadora = mutexCombinadora;
        this.fila = fila;
    }

    private void criarArquivo(int num) throws IOException {
        FileWriter writer = new FileWriter(num + ".txt");
        for (int i = 0; i < 100; i++){
            int n = random.nextInt(1000);
            writer.append(String.valueOf(n));
            writer.append(" ");
        }
        writer.flush();
        writer.close();
    }

    private void ordenarArquivo(int file) throws IOException {
        FileReader reader = new FileReader(file + ".txt");
        int[] arr = new int[100];
        //-------------------------------------------
        int i;
        String num = "";
        while ((i=reader.read()) != -1)
            num += ((char) i);
        reader.close();
        //-------------------------------------------
        String[] s = num.split(" ");
        int cont = 0;
        for (String str : s){
            arr[cont] = Integer.parseInt(str);
            cont++;
        }
        //----------------------------------------------
        Arrays.sort(arr);
        FileWriter writer = new FileWriter(file + "_ordenado.txt");
        for (int k : arr){
            writer.append(String.valueOf(k));
            writer.append(" ");
        }
        writer.flush();
        writer.close();
    }

    public void run() {
        while(true) {
            try {
                int temp;
                mutexContador.acquire();
                    criarArquivo(contadorArquivos[0]);
                    ordenarArquivo(contadorArquivos[0]);
                    temp = contadorArquivos[0];
                    contadorArquivos[0]++;
                mutexContador.release();

                mutex.acquire();
                    contador[0]++;
                    if (contador[0] == n) {
                        barreiraSaida.acquire();
                        barreiraEntrada.release();
                    }
                mutex.release();
                barreiraEntrada.acquire();
                barreiraEntrada.release();

                mutexFila.acquire();
                    fila.add(Integer.toString(temp));
                    System.out.println(Arrays.toString(fila.toArray()));
                mutexFila.release();
                mutexCombinadora.release();

                mutex.acquire();
                contador[0]--;
                    if (contador[0] == 0) {
                        barreiraEntrada.acquire();
                        barreiraSaida.release();
                    }
                mutex.release();
                barreiraSaida.acquire();
                barreiraSaida.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}