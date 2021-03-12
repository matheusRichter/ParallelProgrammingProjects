package QuickSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static int [] createArray(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) list.add(i);

        Collections.shuffle(list);

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) array[i] = list.get(i);

        return array;
    }

    static int highestPowerof2(int n) {
        int res = 0;
        for (int i = n; i >= 1; i--)
        {
            // If i is a power of 2
            if ((i & (i - 1)) == 0)
            {
                res = i;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        int[] a = createArray((int)Math.pow(2,15));
        int[] b = createArray((int)Math.pow(2,16));
        int[] c = createArray((int)Math.pow(2,17));
        int[] d = createArray((int)Math.pow(2,18));
        int[] e = createArray((int)Math.pow(2,19));

        int[] f = createArray((int)Math.pow(2,20));
        int[] g = createArray((int)Math.pow(2,21));
        int[] h = createArray((int)Math.pow(2,22));
        
        int[] i = createArray((int)Math.pow(2,23));
        int[] j = createArray((int)Math.pow(2,24));
        int[] k = createArray((int)Math.pow(2,25));
        int[] l = createArray((int)Math.pow(2,26));

        long media_a_Paralelo = 0;
        long media_a_Sequencial = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(a, a.length);
            int[] arr2 = Arrays.copyOf(a, a.length);
            long start_a = System.currentTimeMillis();
                QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long aParalelo = (end_a - start_a);
            media_a_Paralelo += aParalelo;

            long start_aS = System.currentTimeMillis();
                QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            media_a_Sequencial += aSequencial;
        }
        System.out.println("Tempo (2^15) paralelo: " + media_a_Paralelo/50);
        System.out.println("Tempo (2^15) sequencial: " + media_a_Sequencial/50);

        long media_b_Paralelo = 0;
        long media_b_Sequencial = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(b, b.length);
            int[] arr2 = Arrays.copyOf(b, b.length);
            long start_b = System.currentTimeMillis();
                QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_b = System.currentTimeMillis();
            long bParalelo = (end_b - start_b);
            media_b_Paralelo += bParalelo;

            long start_bS = System.currentTimeMillis();
                QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_bS = System.currentTimeMillis();
            long bSequencial = (end_bS - start_bS);
            media_b_Sequencial += bSequencial;
        }
        System.out.println("Tempo (2^16) paralelo: " + media_b_Paralelo/50);
        System.out.println("Tempo (2^16) sequencial: " + media_b_Sequencial/50);

        long media_c_Paralelo = 0;
        long media_c_Sequencial = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(c, c.length);
            int[] arr2 = Arrays.copyOf(c, c.length);
            long start_c = System.currentTimeMillis();
                QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_c = System.currentTimeMillis();
            long cParalelo = (end_c - start_c);
            media_c_Paralelo += cParalelo;

            long start_cS = System.currentTimeMillis();
                QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_cS = System.currentTimeMillis();
            long cSequencial = (end_cS - start_cS);
            media_c_Sequencial += cSequencial;
        }
        System.out.println("Tempo (2^17) paralelo: " + media_c_Paralelo/50);
        System.out.println("Tempo (2^17) sequencial: " + media_c_Sequencial/50);

        long media_d_Paralelo = 0;
        long media_d_Sequencial = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(d, d.length);
            int[] arr2 = Arrays.copyOf(d, d.length);
            long start_d = System.currentTimeMillis();
                QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_d = System.currentTimeMillis();
            long dParalelo = (end_d - start_d);
            media_d_Paralelo += dParalelo;

            long start_dS = System.currentTimeMillis();
                QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_dS = System.currentTimeMillis();
            long dSequencial = (end_dS - start_dS);
            media_d_Sequencial += dSequencial;
        }
        System.out.println("Tempo (2^18) paralelo: " + media_d_Paralelo/50);
        System.out.println("Tempo (2^18) sequencial: " + media_d_Sequencial/50);

        long media_e_Paralelo = 0;
        long media_e_Sequencial = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(e, e.length);
            int[] arr2 = Arrays.copyOf(e, e.length);
            long start_e = System.currentTimeMillis();
                QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_e = System.currentTimeMillis();
            long eParalelo = (end_e - start_e);
            media_e_Paralelo += eParalelo;

            long start_eS = System.currentTimeMillis();
                QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_eS = System.currentTimeMillis();
            long eSequencial = (end_eS - start_eS);
            media_e_Sequencial += eSequencial;
        }
        System.out.println("Tempo (2^19) paralelo: " + media_e_Paralelo/50);
        System.out.println("Tempo (2^19) sequencial: " + media_e_Sequencial/50);

        long media_f_Paralelo = 0;
        long media_f_Sequencial = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(f, f.length);
            int[] arr2 = Arrays.copyOf(f, f.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long fParalelo = (end_a - start_a);
            media_f_Paralelo += fParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long fSequencial = (end_aS - start_aS);
            media_f_Sequencial += fSequencial;
        }
        System.out.println("Tempo (2^20) paralelo: " + media_f_Paralelo/10);
        System.out.println("Tempo (2^20) sequencial: " + media_f_Sequencial/10);


        long media_g_Paralelo = 0;
        long media_g_Sequencial = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(g, g.length);
            int[] arr2 = Arrays.copyOf(g, g.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long aParalelo = (end_a - start_a);
            media_g_Paralelo += aParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            media_g_Sequencial += aSequencial;
        }
        System.out.println("Tempo (2^21) paralelo: " + media_g_Paralelo/10);
        System.out.println("Tempo (2^21) sequencial: " + media_g_Sequencial/10);

        long media_h_paralelo = 0;
        long media_h_sequencial = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(h, h.length);
            int[] arr2 = Arrays.copyOf(h, h.length);
            long start_h = System.currentTimeMillis();
                QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_h = System.currentTimeMillis();
            long hParalelo = (end_h - start_h);
            media_h_paralelo += hParalelo;

            long start_hS = System.currentTimeMillis();
                QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_hS = System.currentTimeMillis();
            long hSequencial = (end_hS - start_hS);
            media_h_sequencial += hSequencial;
        }
        System.out.println("Tempo (2^22) paralelo: " + media_h_paralelo/50);
        System.out.println("Tempo (2^22) sequencial: " + media_h_sequencial/50);

        long media_i_Paralelo = 0;
        long media_i_Sequencial = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(i, i.length);
            int[] arr2 = Arrays.copyOf(i, i.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long aParalelo = (end_a - start_a);
            media_i_Paralelo += aParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            media_i_Sequencial += aSequencial;
        }
        System.out.println("Tempo (2^23) paralelo: " + media_i_Paralelo/10);
        System.out.println("Tempo (2^23) sequencial: " + media_i_Sequencial/10);

        long media_j_Paralelo = 0;
        long media_j_Sequencial = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(j, j.length);
            int[] arr2 = Arrays.copyOf(j, j.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long aParalelo = (end_a - start_a);
            media_j_Paralelo += aParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            media_j_Sequencial += aSequencial;
        }
        System.out.println("Tempo (2^24) paralelo: " + media_j_Paralelo/10);
        System.out.println("Tempo (2^24) sequencial: " + media_j_Sequencial/10);

        long media_k_Paralelo = 0;
        long media_k_Sequencial = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(k, k.length);
            int[] arr2 = Arrays.copyOf(k, k.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long aParalelo = (end_a - start_a);
            media_k_Paralelo += aParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            media_k_Sequencial += aSequencial;
        }
        System.out.println("Tempo (2^25) paralelo: " + media_k_Paralelo/10);
        System.out.println("Tempo (2^25) sequencial: " + media_k_Sequencial/10);

        long media_l_Paralelo = 0;
        long media_l_Sequencial = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(l, l.length);
            int[] arr2 = Arrays.copyOf(l, l.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long lParalelo = (end_a - start_a);
            media_l_Paralelo += lParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            media_a_Sequencial += aSequencial;
        }
        System.out.println("Tempo (2^26) paralelo: " + media_l_Paralelo/10);
        System.out.println("Tempo (2^26) sequencial: " + media_l_Sequencial/10);

        FileWriter writer = new FileWriter("tempos.csv");

        writer.append("Tamanho");
        writer.append(",");
        writer.append("Tempo paralelo");
        writer.append(",");
        writer.append("Tempo sequencial");
        writer.append('\n');

        writer.append("2^15");
        writer.append(",");
        writer.append(String.valueOf(media_a_Paralelo/50));
        writer.append(",");
        writer.append(String.valueOf(media_a_Sequencial/50));
        writer.append('\n');

        writer.append("2^16");
        writer.append(",");
        writer.append(String.valueOf(media_b_Paralelo/50));
        writer.append(",");
        writer.append(String.valueOf(media_b_Sequencial/5));
        writer.append('\n');

        writer.append("2^17");
        writer.append(",");
        writer.append(String.valueOf(media_c_Paralelo/50));
        writer.append(",");
        writer.append(String.valueOf(media_c_Sequencial/50));
        writer.append('\n');

        writer.append("2^18");
        writer.append(",");
        writer.append(String.valueOf(media_d_Paralelo/50));
        writer.append(",");
        writer.append(String.valueOf(media_d_Sequencial/50));
        writer.append('\n');

        writer.append("2^19");
        writer.append(",");
        writer.append(String.valueOf(media_e_Paralelo/50));
        writer.append(",");
        writer.append(String.valueOf(media_e_Sequencial/50));
        writer.append('\n');

        writer.append("2^20");
        writer.append(",");
        writer.append(String.valueOf(media_f_Paralelo));
        writer.append(",");
        writer.append(String.valueOf(media_f_Sequencial));
        writer.append('\n');

        writer.append("2^21");
        writer.append(",");
        writer.append(String.valueOf(media_g_Paralelo));
        writer.append(",");
        writer.append(String.valueOf(media_g_Sequencial));
        writer.append('\n');

        writer.append("2^22");
        writer.append(",");
        writer.append(String.valueOf(media_h_paralelo/50));
        writer.append(",");
        writer.append(String.valueOf(media_h_sequencial/50));
        writer.append('\n');

        writer.append("2^23");
        writer.append(",");
        writer.append(String.valueOf(media_i_Paralelo));
        writer.append(",");
        writer.append(String.valueOf(media_i_Sequencial));
        writer.append('\n');

        writer.append("2^24");
        writer.append(",");
        writer.append(String.valueOf(media_j_Paralelo));
        writer.append(",");
        writer.append(String.valueOf(media_j_Sequencial));
        writer.append('\n');

        writer.append("2^25");
        writer.append(",");
        writer.append(String.valueOf(media_k_Paralelo));
        writer.append(",");
        writer.append(String.valueOf(media_k_Sequencial));
        writer.append('\n');

        writer.append("2^26");
        writer.append(",");
        writer.append(String.valueOf(media_l_Paralelo));
        writer.append(",");
        writer.append(String.valueOf(media_l_Sequencial));
        writer.append('\n');

        writer.flush();
        writer.close();

        System.out.println("Arquivo escrito");
    }
}

// Base em:
// http://www.lokprakashpandey.com.np/2018/03/Sequential-vs-Parallel-QuickSort.html