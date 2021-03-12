import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Combinadora extends Thread {

    private final String extensao = "_ordenado.txt";

    private final int n;
    private int[] contador;
    private final Semaphore mutex, mutexFila, barreiraEntrada, barreiraSaida, mutexCombinadora;
    private Queue<String> fila;

    public Combinadora(int n, int[] contador, Semaphore mutex, Semaphore mutexFila, Semaphore barreiraEntrada, Semaphore barreiraSaida, Semaphore mutexCombinadora, Queue fila) {
        this.n = n;
        this.contador = contador;
        this.mutex = mutex;
        this.mutexFila = mutexFila;
        this.barreiraEntrada = barreiraEntrada;
        this.barreiraSaida = barreiraSaida;
        this.mutexCombinadora = mutexCombinadora;
        this.fila = fila;
    }

    private void removeDuplicates(int[] arr) {
        int n = arr.length;
        if (n==0 || n==1) ;

        int[] temp = new int[n];
        int j = 0;

        for (int i=0; i<n-1; i++)
            if (arr[i] != arr[i+1]) temp[j++] = arr[i];
        temp[j++] = arr[n-1];

        for (int i=0; i<j; i++) arr[i] = temp[i];
    }

    private void merge(String file1, String file2, String file3, String file4) throws IOException {
        FileReader reader1 = new FileReader(file1 + extensao);
        FileReader reader2 = new FileReader(file2 + extensao);
        FileReader reader3 = new FileReader(file3 + extensao);
        FileReader reader4 = new FileReader(file4 + extensao);

        int[] arr = new int[100 * 4];
        int cont = 0;

        //----------------------------------------------
        int i;
        String num = "";
        while((i = reader1.read()) != -1){
            num += ((char) i);
        }
        i = 0;
        String[] s = num.split(" ");
        for (String str : s){
            arr[cont] = Integer.parseInt(str);
            cont++;
        }
        num = "";
        reader1.close();

        //------------------------------------------------
        while((i = reader2.read()) != -1){
            num += ((char) i);
        }
        i = 0;
        s = num.split(" ");
        for (String str : s){
            arr[cont] = Integer.parseInt(str);
            cont++;
        }
        num = "";
        reader2.close();

        //----------------------------------------------
        while((i = reader3.read()) != -1){
            num += ((char) i);
        }
        i = 0;

        s = num.split(" ");
        for (String str : s){
            arr[cont] = Integer.parseInt(str);
            cont++;
        }
        num = "";
        reader3.close();

        //----------------------------------------------
        while((i = reader4.read()) != -1){
            num += ((char) i);
        }
        i = 0;

        s = num.split(" ");
        for (String str : s){
            arr[cont] = Integer.parseInt(str);
            cont++;
        }
        num = "";
        reader4.close();

        //----------------------------------------------
        Arrays.toString(arr);
        Arrays.sort(arr);
        Arrays.toString(arr);
        removeDuplicates(arr);
        Arrays.toString(arr);

        FileWriter writer = new FileWriter(file1 + "_" + file2 + "_" + file3 + "_" + file4 + ".txt");
        for (int k = 0; k < arr.length; k++){
            if(arr[k] == 9){
                writer.append(String.valueOf(arr[k]));
                writer.append(" ");
                break;
            }
            writer.append(String.valueOf(arr[k]));
            writer.append(" ");
        }
        writer.flush();
        writer.close();
    }

    public void run(){
        while(true) {
            try {

                mutexCombinadora.acquire(4);

                mutexFila.acquire();
                    String[] arquivo = new String[4];
                    for (int i = 0; i < 4; i++) {
                        arquivo[i] = fila.peek();
                        fila.poll();
                        System.out.println(Arrays.toString(fila.toArray()));
                    }
                    merge(arquivo[0], arquivo[1], arquivo[2], arquivo[3]);
                mutexFila.release();

                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}