package Rendezvous;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Cliente extends Thread{

    int id;
    int[] contador;

    Semaphore[] semaforo;
    Semaphore mutex;
    Semaphore cliente;
    Semaphore cliente_satisfeito;
    Semaphore corte_concluido;

    Queue<Semaphore> fila;

    public Cliente(int id, int[] contador, Semaphore[] semaforo, Semaphore mutex, Semaphore cliente, Semaphore cliente_satisfeito, Semaphore corte_concluido, Queue fila){
        this.id = id;
        this.contador = contador;
        this.semaforo = semaforo;
        this.mutex = mutex;
        this.cliente = cliente;
        this.cliente_satisfeito = cliente_satisfeito;
        this.corte_concluido = corte_concluido;
        this.fila = fila;
    }

    private void desistir(){
        System.out.println("Cliente desistiu");
    }

    private void cortar_cabelo() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300 + 1));
    }

    public void run() {
        try {
            Thread.sleep(2000);

            semaforo[contador[0]] = new Semaphore(0);

            mutex.acquire();
                if(contador[0] == semaforo.length){
                    mutex.release();
                    desistir();
                } else {
                    contador[0]++;
                    fila.add(semaforo[contador[0]]);
                }
            mutex.release();

            cliente.release();
            semaforo[contador[0]].acquire();

            cortar_cabelo();

            cliente_satisfeito.release();
            corte_concluido.acquire();

            mutex.acquire();
                contador[0]--;
            mutex.release();

            Thread.sleep(2000);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
