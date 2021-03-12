package TrabalhoProdutorConsumidor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Relatorio {
    private static long[] producao = new long[20];
    private static long[] entrega = new long[20];
    private static int contP = 0;
    public static int contE = 0;
    private static int contArquivo = 0;

    public static void inserirProducao(long tempo){
        producao[contP] = tempo;
        contP++;
        if (contP == 20){
            //gerarArquivo();
            System.out.println("Tempos de Produção" + Arrays.toString(producao));
            contP = 0;
        }
    }
    public static void inserirEntrega(long tempo){
        entrega[contE] = tempo;
        contE++;
        if (contE == 20){
            //gerarArquivo();
            System.out.println("Tempos de Entrega" + Arrays.toString(entrega));
            contE = 0;
        }
    }
    public static void imprimirTempos(){
        System.out.println("Tempos de Produção" + Arrays.toString(producao));
        System.out.println("Tempos de Entrega" + Arrays.toString(entrega));
    }
    public static void gerarArquivo(){
        try{
            FileWriter tabela = new FileWriter("tempos" + contArquivo + ".csv");
            tabela.append("Tempo Producao");
            tabela.append(",");
            tabela.append("Tempo Entrega");
            tabela.append("\n");

            for (int i = 0; i < 20; i++){
                tabela.append(String.valueOf(producao[i]));
                tabela.append(",");
                tabela.append(String.valueOf(entrega[i]));
                tabela.append("\n");
            }

            tabela.flush();
            tabela.close();

            contArquivo++;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}