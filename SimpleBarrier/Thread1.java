package BarreiraSimples;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Thread1 extends Thread{

    private Funcionario[][] partes;
    private final Semaphore barreira;
    private Semaphore contador;
    private final Semaphore[] mutex;
    private int[] cont;
    private int[] n;

    public Thread1(int[] n, Funcionario[][] partes, Semaphore barreira, Semaphore contador, Semaphore[] mutex, int[] cont) {
        this.n = n;
        this.partes = partes;
        this.barreira = barreira;
        this.contador = contador;
        this.mutex = mutex;
        this.cont = cont;
    }

    public void calc_imp_renda() throws InterruptedException {
        int pos = 0;
        mutex[pos].acquire();
        int temp = pos;
        for (int i = 0; i < 4; i++) {
            for (Funcionario func : partes[pos]) {
                func.setDesc_renda(func.getSalario() * 0.2);
                func.setSalario_liquido(func.getSalario_liquido() - func.getDesc_renda());
            }
            System.out.println(" Thread1 "+pos);
            pos++;
            if (pos > 3) pos = 0;
        }
        mutex[temp].release();
    }

    private void gerarArquivo() throws IOException {
        FileWriter arquivo = new FileWriter("parte1.txt");
        for(Funcionario func : partes[0]) {
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
            calc_imp_renda();

            contador.acquire();
                cont[0]++;
                if (cont[0] == n[0]) barreira.release();
            contador.release();

            barreira.acquire();
            barreira.release();

            mutex[0].acquire();
                gerarArquivo();
                System.out.println(" Fim Thread1");
            mutex[0].acquire();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
