package BarreiraSimples;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Thread0 extends Thread {

    private Funcionario[] lista;
    private Semaphore barreira;
    private Semaphore contador;
    private Semaphore[] mutex;
    private int[] cont;
    private int[] n;

    public Thread0(int[] n, Semaphore barreira, Semaphore contador, Semaphore[] mutex, int[] cont, Funcionario[] lista){
        this.n = n;
        this.barreira = barreira;
        this.contador = contador;
        this.mutex = mutex;
        this.cont = cont;
        this.lista = lista;
    }

    public void divideLista(){
        Funcionario[] parte1 = Arrays.copyOfRange(lista, 0, lista.length/4);
        Funcionario[] parte2 = Arrays.copyOfRange(lista, lista.length/4, lista.length/2);
        Funcionario[] parte3 = Arrays.copyOfRange(lista, lista.length/2, (lista.length/4)*3);
        Funcionario[] parte4 = Arrays.copyOfRange(lista, (lista.length/4)*3, lista.length);

        Funcionario[][] partes = {parte1,parte2,parte3,parte4};

        Thread1 t1 = new Thread1(n, partes, barreira, contador, mutex, cont);
        Thread2 t2 = new Thread2(n, partes, barreira, contador, mutex, cont);
        Thread3 t3 = new Thread3(n, partes, barreira, contador, mutex, cont);
        Thread4 t4 = new Thread4(n, partes, barreira, contador, mutex, cont);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public void run() {
        try{
            System.out.println("Começou");
            divideLista();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}