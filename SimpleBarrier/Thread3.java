package BarreiraSimples;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Thread3 extends Thread{

    private final Funcionario[][] partes;
    private Semaphore barreira;
    private Semaphore contador;
    private Semaphore[] mutex;
    private int[] cont;
    private int[] n;



    public Thread3(int[] n, Funcionario[][] partes, Semaphore barreira, Semaphore contador, Semaphore[] mutex, int[] cont){
        this.n = n;
        this.partes = partes;
        this.barreira = barreira;
        this.contador = contador;
        this.mutex = mutex;
        this.cont = cont;

    }

    private void calc_inss() throws InterruptedException {
        int pos = 2;
        mutex[pos].acquire();
        int temp = pos;
        for (int i = 0; i < 4; i++) {
            for (Funcionario func : partes[pos]) {
                func.setDesc_prev_priv(func.getSalario() * 0.04);
                func.setSalario_liquido(func.getSalario_liquido() - func.getDesc_prev_priv());
            }
            System.out.println(" Thread3 "+pos);
            pos++;
            if (pos > 3) pos = 0;
        }
        mutex[temp].release();
    }

    private void gerarArquivo() throws IOException {
        FileWriter arquivo = new FileWriter("parte3.txt");

        for(Funcionario func : partes[2]) {
            arquivo.append("Codigo: ");

            arquivo.append(String.valueOf(func.getCodigo()));
            arquivo.append(",");

            arquivo.append("Salario Liquido: ");
            arquivo.append(String.valueOf(func.getSalario_liquido()));
            arquivo.append("\n");
        }

            arquivo.flush();
            arquivo.close();

    }

    public void run(){
        try {
             calc_inss();

            contador.acquire();
                cont[0]++;
                if (cont[0] == n[0]) barreira.release();
            contador.release();

            barreira.acquire();
            barreira.release();

            mutex[2].acquire();
                gerarArquivo();
                System.out.println(" Fim Thread3");
            mutex[2].release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
