package TrabalhoProdutorConsumidor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Loja extends Thread{
    private final String[] catalogo =
    {"produtoA","produtoB","produtoC","produtoD","produtoE","produtoF","produtoG","produtoH"};
    private int contadorA=0,contadorB=0,contadorC=0,contadorD=0,contadorE=0,contadorF=0,contadorG=0,contadorH=0;
    private Random random = new Random();
    private String[] pedido = new String[6];

    private final Queue<String[]> fila;
    private final Semaphore mutex, lojaFabricante;
    private final int identificacao;



    public Loja(Queue fila, Semaphore mutex, Semaphore lojaFabricante, int identificação){
        this.fila = fila;
        this.mutex = mutex;
        this.lojaFabricante = lojaFabricante;
        this.identificacao = identificação;
    }

    private String vender(){
        int venda = random.nextInt(8);
        switch (venda){
            case 0: return catalogo[0];
            case 1: return catalogo[1];
            case 2: return catalogo[2];
            case 3: return catalogo[3];
            case 4: return catalogo[4];
            case 5: return catalogo[5];
            case 6: return catalogo[6];
            case 7: return catalogo[7];
        }
        return null;
    }

    private void enviarPedido(int identificacao){
        try{
            Thread.sleep(random.nextInt((3000 - 1000) + 1) + 1000);
            String v = vender();
            pedido[0] = v;
            switch (identificacao){
                case 1: pedido[1] = "A" + contadorA++;break;
                case 2: pedido[1] = "B" + contadorB++;break;
                case 3: pedido[1] = "C" + contadorC++;break;
                case 4: pedido[1] = "D" + contadorD++;break;
                case 5: pedido[1] = "E" + contadorE++;break;
                case 6: pedido[1] = "F" + contadorF++;break;
                case 7: pedido[1] = "G" + contadorG++;break;
                case 8: pedido[1] = "H" + contadorH++;break;
            }
            long start = System.currentTimeMillis();
            pedido[4] = Long.toString(start);
            fila.add(pedido);
            System.out.println(Arrays.toString(pedido));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
        //for (int i = 0; i < 7; i++) {
            try {
                mutex.acquire();
                if (lojaFabricante.availablePermits() >= 0) {
                    enviarPedido(identificacao);
                    lojaFabricante.release();
                } else System.out.println("não vendeu");
                mutex.release();
                Thread.sleep(1000);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}