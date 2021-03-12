package Rendezvous;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        int[] contador = {0};

        Semaphore[] semaforo = {new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1)};

        Semaphore mutex = new Semaphore(1);
        Semaphore cliente = new Semaphore(0);
        Semaphore cliente_satisfeito = new Semaphore(0);
        Semaphore corte_concluido = new Semaphore(0);

        Queue<Semaphore> fila = new LinkedList<>();

        Cliente cliente1 = new Cliente(1,contador, semaforo, mutex, cliente, cliente_satisfeito, corte_concluido, fila);
        Cliente cliente2 = new Cliente(2,contador, semaforo, mutex, cliente, cliente_satisfeito, corte_concluido, fila);
        Cliente cliente3 = new Cliente(3,contador, semaforo, mutex, cliente, cliente_satisfeito, corte_concluido, fila);
        Cliente cliente4 = new Cliente(4,contador, semaforo, mutex, cliente, cliente_satisfeito, corte_concluido, fila);

        Barbeiro barbeiro = new Barbeiro(contador, mutex, cliente, cliente_satisfeito, corte_concluido, fila);

        barbeiro.start();

        cliente2.start();
        cliente1.start();
        cliente4.start();
        cliente3.start();
    }

}
