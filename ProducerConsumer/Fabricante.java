package TrabalhoProdutorConsumidor;

import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Fabricante extends Thread{
    private int produtosEmParalelo;

    private final int identificacao;
    private Queue<String[]> filaLojaFabricante, filaFabricanteTransportadora;
    private final Semaphore mutex, lojaFabricante, fabricanteTransportadora, limitador;
    private final Random random = new Random();

    public Fabricante(int indentificacao, Queue filaLojaFabricante, Queue filaFabricanteTransportadora, Semaphore mutex, Semaphore lojaFabricante, Semaphore fabricanteTransportadora, Semaphore limitador){
        this.identificacao = indentificacao;
        this.filaLojaFabricante = filaLojaFabricante;
        this.filaFabricanteTransportadora = filaFabricanteTransportadora;
        this.mutex = mutex;
        this.lojaFabricante = lojaFabricante;
        this.fabricanteTransportadora = fabricanteTransportadora;
        switch (this.identificacao){
            case 1: this.produtosEmParalelo = 4;break;
            case 2: this.produtosEmParalelo = 1;break;
            case 3: this.produtosEmParalelo = 4;break;
            case 4: this.produtosEmParalelo = 4;break;
        }
        limitador = new Semaphore(produtosEmParalelo);
        this.limitador = limitador;
    }

    private int receber(String produto){
        int tempo = 0;
        switch (identificacao){
            case 1:
                switch (produto){
                    case "produtoA": tempo = random.nextInt((1000 - 600)  + 1) + 600;break;
                    case "produtoB": tempo = random.nextInt((400  - 200)  + 1) + 200;break;
                    case "produtoC": tempo = random.nextInt((1200 - 1000) + 1) + 1000;break;
                    case "produtoD": tempo = random.nextInt((600 - 400)   + 1) + 400;break;
                    case "produtoE":
                    case "produtoH":
                        tempo = random.nextInt((1000 - 800)  + 1) + 800;break;
                    case "produtoF": tempo = random.nextInt((1600 - 1400) + 1) + 1400;break;
                    case "produtoG": tempo = random.nextInt((6000 - 400)  + 1) + 400;break;
                }
                produtosEmParalelo = 4;
            case 2:
                switch (produto){
                    case "produtoA": tempo = random.nextInt((600  - 400)  + 1) + 400;break;
                    case "produtoB":
                    case "produtoD":
                        tempo = random.nextInt((1000 - 800)  + 1) + 800;break;
                    case "produtoC": tempo = random.nextInt((1400 - 1200) + 1) + 1200;break;
                    case "produtoE": tempo = random.nextInt((400  - 200)  + 1) + 200;break;
                    case "produtoF":
                    case "produtoG":
                        tempo = random.nextInt((1200 - 1000) + 1) + 1000;break;
                    case "produtoH": tempo = random.nextInt((800  - 600)  + 1) + 800;break;
                }
                produtosEmParalelo = 1;
            case 3:
                switch (produto){
                    case "produtoA":
                    case "produtoG":
                        tempo = random.nextInt((1200 - 1000) + 1) + 1000;break;
                    case "produtoB": tempo = random.nextInt((1400 - 1200) + 1) + 1200;break;
                    case "produtoC":
                    case "produtoE":
                    case "produtoF":
                    case "produtoH":
                        tempo = random.nextInt((600  - 400)  + 1) + 400;break;
                    case "produtoD": tempo = random.nextInt((800  - 600)  + 1) + 600;break;
                }
                produtosEmParalelo = 4;
            case 4:
                switch (produto){
                    case "produtoA":
                    case "produtoF":
                        tempo = random.nextInt((1000 - 800)  + 1) + 800;break;
                    case "produtoB":
                    case "produtoG":
                        tempo = random.nextInt((800  - 600)  + 1) + 600;break;
                    case "produtoC": tempo = random.nextInt((600  - 400)  + 1) + 400;break;
                    case "produtoD": tempo = random.nextInt((1200 - 1000) + 1) + 1000;break;
                    case "produtoE":
                    case "produtoH":
                        tempo = random.nextInt((1400 - 1200) + 1) + 1200;break;
                }
                produtosEmParalelo = 4;
        }
        return tempo;
    }

    private void fabricar(String[] p, int identificacao){
        switch (identificacao){
            case 1: p[2] = "Fabricante A";break;
            case 2: p[2] = "Fabricante B";break;
            case 3: p[2] = "Fabricante C";break;
            case 4: p[2] = "Fabricante D";break;
        }
    }

    private void produzir(int identificacao){
        try{
            int l = limitador.availablePermits();
            while(limitador.availablePermits() > 0) {
                limitador.acquire();
                if (!filaLojaFabricante.isEmpty()) {
                    String[] p = filaLojaFabricante.peek();
                    while (filaLojaFabricante.peek() == p)
                        filaLojaFabricante.poll();
                    Thread.sleep(receber(p[0]));
                    fabricar(p, identificacao);
                    long fim = System.currentTimeMillis();
                    long start = Long.parseLong(p[4]);
                    long t = fim-start;
                    p[4] = Long.toString(t);
                    p[5] = Long.toString(fim);
                    filaFabricanteTransportadora.add(p);
                    System.out.println(Arrays.toString(p));
                } else System.out.println("Não há produtos com fabricação pendente");
            }
            for (int i = 0; i < l;i++)
                limitador.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
        //for (int i = 0; i < 7; i++) {
            try {
                mutex.acquire();
                if (lojaFabricante.availablePermits() > 0) {
                    lojaFabricante.acquire();
                    produzir(identificacao);
                    fabricanteTransportadora.release();
                } else System.out.println("não fabricou");
                mutex.release();
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}