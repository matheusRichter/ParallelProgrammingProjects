package BarreiraSimples;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int[] n = {4};

        Semaphore barreira = new Semaphore(0);
        Semaphore contador = new Semaphore(1);
        Semaphore[] mutex = {new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1)};

        int[] cont = {1};

        Funcionario[] lista = new Funcionario[16];
        for (int i = 0; i < lista.length; i++) lista[i] = new Funcionario(i);
        Thread0 t0 = new Thread0(n, barreira, contador, mutex, cont, lista);
        t0.start();
    }
}