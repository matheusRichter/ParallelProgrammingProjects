package TrabalhoParalelismoIterativo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {

        // Gerar matrizes m1..m8
        double[][] m1 = Gerador.generateMatrix(1000,1000,1);
        double[][] m2 = Gerador.generateMatrix(1000,1000,2);
        double[][] m3 = Gerador.generateMatrix(2000,2000,3);
        double[][] m4 = Gerador.generateMatrix(2000,2000,4);
        double[][] m5 = Gerador.generateMatrix(1000,2000,5);
        double[][] m6 = Gerador.generateMatrix(2000,1000,6);
        double[][] m7 = Gerador.generateMatrix(2000,4000,7);
        double[][] m8 = Gerador.generateMatrix(4000,2000,8);

        // Multiplicação das matrizes e calculo do tempo das operações
        Date start1 = new Date();
            double[][] result1 = new double[m1.length][m2[0].length];
            ParallelThreadsCreator.multiply(m1, m2, result1);
        Date end1 = new Date();
        System.out.println("\nTime taken in miliseconds: " + (end1.getTime() - start1.getTime()));

        Date start2 = new Date();
            double[][] result2 = new double[m3.length][m4[0].length];
            ParallelThreadsCreator.multiply(m3, m4, result2);
        Date end2 = new Date();
        System.out.println("\nTime taken in miliseconds: " + (end2.getTime() - start2.getTime()));

        Date start3 = new Date();
            double[][] result3 = new double[m5.length][m6[0].length];
            ParallelThreadsCreator.multiply(m5, m6, result3);
        Date end3 = new Date();
        System.out.println("\nTime taken in miliseconds: " + (end3.getTime() - start3.getTime()));

        Date start4 = new Date();
            double[][] result4 = new double[m7.length][m8[0].length];
            ParallelThreadsCreator.multiply(m7, m8, result4);
        Date end4 = new Date();
        System.out.println("\nTime taken in miliseconds: " + (end4.getTime() - start4.getTime()));
    }
}