package TrabalhoProdutorConsumidor;

import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Transportadora extends Thread{
    private int transportesEmParalelo;

    private final int identificacao;
    private Queue<String[]> filaFabricanteTransportadora;
    private final Semaphore mutex, fabricanteTransportadora, limitador;
    private Random random = new Random();

    public Transportadora(int identificacao, Queue filaFabricanteTransportadora, Semaphore mutex, Semaphore fabricanteTransportadora, Semaphore limitador){
        this.identificacao = identificacao;
        this.filaFabricanteTransportadora = filaFabricanteTransportadora;
        this.mutex = mutex;
        this.fabricanteTransportadora = fabricanteTransportadora;
        switch (this.identificacao){
            case 1: this.transportesEmParalelo = 10;break;
            case 2: this.transportesEmParalelo = 20;break;
        }
        limitador = new Semaphore(transportesEmParalelo);
        this.limitador = limitador;
    }

    private int receber(int identificacao){
        int tempo = 0;
        switch (identificacao){
            case 1: tempo = random.nextInt((200 - 100) + 1) + 100;transportesEmParalelo = 10;break;
            case 2: tempo = random.nextInt((600 - 400) + 1) + 400;transportesEmParalelo = 20;break;
        }
        return tempo;
    }

    private void entregar(){
        try {
            int l = limitador.availablePermits();
            while(limitador.availablePermits() > 0){
            limitador.acquire();
                if (!filaFabricanteTransportadora.isEmpty()) {
                    String[] p = filaFabricanteTransportadora.peek();
                    while (filaFabricanteTransportadora.peek() == p)
                        filaFabricanteTransportadora.poll();
                    Thread.sleep(receber(identificacao));
                    switch (identificacao) {
                        case 1:
                            p[3] = "TransportadoraA";
                            break;
                        case 2:
                            p[3] = "TransportadoraB";
                            break;
                    }

                    long fim = System.currentTimeMillis();
                    long start = Long.parseLong(p[5]);
                    long t = fim-start;
                    Relatorio.inserirProducao(Long.parseLong(p[4]));
                    Relatorio.inserirEntrega(t);
                    p[5] = Long.toString(t);
                    System.out.println(Arrays.toString(p) + " | ENTREGUE |");

                    System.err.println(Relatorio.contE);
                    if (Relatorio.contE >= 19)
                        Relatorio.gerarArquivo();
                } else System.out.println("Não há produtos disponiveis para entrega");
            }
            for (int i = 0; i < l;i++)
                limitador.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        while(true){
        //for (int i = 0; i < 7; i++) {
            try {
                mutex.acquire();
                if (fabricanteTransportadora.availablePermits() > 0) {
                    fabricanteTransportadora.acquire();
                    entregar();
                } else System.out.println("não trasportou");
                mutex.release();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}