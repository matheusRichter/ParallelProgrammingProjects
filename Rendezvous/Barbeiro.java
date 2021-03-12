package Rendezvous;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Barbeiro extends Thread{

    int[] contador;

    Semaphore mutex;
    Semaphore cliente;
    Semaphore cliente_satisfeito;
    Semaphore corte_concluido;

    Queue<Semaphore> fila;

    public Barbeiro(int[] contador, Semaphore mutex, Semaphore cliente, Semaphore cliente_satisfeito, Semaphore corte_concluido, Queue fila){

        this.contador = contador;
        this.mutex = mutex;
        this.cliente = cliente;
        this.cliente_satisfeito = cliente_satisfeito;
        this.corte_concluido = corte_concluido;
        this.fila = fila;
    }

    public void cortar_cabelo() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300 + 1));
        System.out.println("Cliente: " + contador[0] + " Cabelo Cortado");
    }

    public void run() {
        while (true){
            try {
                Thread.sleep(2000);

                cliente.acquire();

                mutex.acquire();
                    fila.remove();
                mutex.release();

                cortar_cabelo();

                cliente_satisfeito.acquire();
                corte_concluido.release();

                Thread.sleep(2000);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
