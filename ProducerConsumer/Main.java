package TrabalhoProdutorConsumidor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args){
        Queue<String[]> filaLojaFabricante = new LinkedList<>();
        Queue<String[]> filaFabricanteTransportadora = new LinkedList<>();
        Semaphore mutex = new Semaphore(1);
        Semaphore lojaFabricante = new Semaphore(0);
        Semaphore fabricanteTransportadora = new Semaphore(0);
        Semaphore limitadorFabricante = new Semaphore(0);
        Semaphore limitadorTransportadora = new Semaphore(0);

        Loja lojaA = new Loja(filaLojaFabricante, mutex, lojaFabricante, 1);
        Loja lojaB = new Loja(filaLojaFabricante, mutex, lojaFabricante, 2);
        Loja lojaC = new Loja(filaLojaFabricante, mutex, lojaFabricante, 3);
        Loja lojaD = new Loja(filaLojaFabricante, mutex, lojaFabricante, 4);
        Loja lojaE = new Loja(filaLojaFabricante, mutex, lojaFabricante, 5);
        Loja lojaF = new Loja(filaLojaFabricante, mutex, lojaFabricante, 6);
        Loja lojaG = new Loja(filaLojaFabricante, mutex, lojaFabricante, 7);
        Loja lojaH = new Loja(filaLojaFabricante, mutex, lojaFabricante, 8);

        Fabricante fabricanteA =
        new Fabricante(1, filaLojaFabricante, filaFabricanteTransportadora, mutex, lojaFabricante, fabricanteTransportadora, limitadorFabricante);
        Fabricante fabricanteB =
        new Fabricante(2, filaLojaFabricante, filaFabricanteTransportadora, mutex, lojaFabricante, fabricanteTransportadora, limitadorFabricante);
        Fabricante fabricanteC =
        new Fabricante(3, filaLojaFabricante, filaFabricanteTransportadora, mutex, lojaFabricante, fabricanteTransportadora, limitadorFabricante);
        Fabricante fabricanteD =
        new Fabricante(4, filaLojaFabricante, filaFabricanteTransportadora, mutex, lojaFabricante, fabricanteTransportadora, limitadorFabricante);

        Transportadora transportadoraA =
        new Transportadora(1, filaFabricanteTransportadora, mutex, fabricanteTransportadora, limitadorTransportadora);
        Transportadora transportadoraB =
        new Transportadora(2, filaFabricanteTransportadora, mutex, fabricanteTransportadora, limitadorTransportadora);

        lojaA.start();
        lojaB.start();
        lojaC.start();
        lojaD.start();

        fabricanteA.start();
        fabricanteB.start();

        transportadoraA.start();

        lojaE.start();
        lojaF.start();
        lojaG.start();
        lojaH.start();

        fabricanteC.start();
        fabricanteD.start();

        transportadoraB.start();

        try{
            lojaA.join();
            lojaB.join();
            lojaC.join();
            lojaD.join();

            fabricanteA.join();
            fabricanteB.join();

            transportadoraA.join();

            lojaE.join();
            lojaF.join();
            lojaG.join();
            lojaH.join();

            fabricanteC.join();
            fabricanteD.join();

            transportadoraB.join();
        }catch (Exception e){
            e.printStackTrace();
        }

        //Relatorio.gerarArquivo();
    }
}