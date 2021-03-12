package BarreiraSimples;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Thread4 extends Thread {

    private final Funcionario[][] partes;
    private Semaphore barreira;
    private Semaphore contador;
    private Semaphore[] mutex;
    private int[] cont;
    private int[] n;


    public Thread4(int[] n, Funcionario[][] partes, Semaphore barreira, Semaphore contador, Semaphore[] mutex, int[] cont){

        this.partes = partes;
        this.barreira = barreira;
        this.contador = contador;
        this.mutex = mutex;
        this.cont = cont;
        this.n = n;

    }

    public void calc_Desc_plan_hp() throws InterruptedException {
        int pos = 3;
        mutex[pos].acquire();
        int temp = pos;
        for (int i = 0; i < 4; i++) {
            for (Funcionario func : partes[pos]) {
                func.setDesc_plan_hp(func.getSalario() * 0.02);
                func.setSalario_liquido(func.getSalario_liquido() - func.getDesc_plan_hp());
            }
            System.out.println(" Thread4 " + pos);
            pos++;
            if (pos > 3) pos = 0;
        }
        mutex[temp].release();
    }

    private void gerarArquivo() throws IOException {
        FileWriter arquivo = new FileWriter("parte4.txt");
        for (Funcionario func : partes[3]) {
            func.setDesc_plan_hp(func.getSalario() * 0.02);
            func.setSalario_liquido(func.getSalario_liquido() - func.getDesc_plan_hp());

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
            calc_Desc_plan_hp();

            contador.acquire();
                cont[0]++;
                if (cont[0] == n[0]) barreira.release();
            contador.release();

            barreira.acquire();
            barreira.release();

            mutex[3].acquire();
                gerarArquivo();
                System.out.println(" Fim Thread4");
            mutex[3].release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
