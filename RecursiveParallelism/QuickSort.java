package QuickSort;

public class QuickSort extends Thread {
    private int[] array;
    private int lb;
    private int ub;

    private static final int NO_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final int NO_OF_THREADS = 2; //Main.highestPowerof2(NO_OF_PROCESSORS);
    private static int count;

    public QuickSort(int[] array, int lb, int ub) {
        this.array = array;
        this.lb=lb;
        this.ub=ub;
    }

    static void quickSort(int[] list, int lb, int ub) {
        int pivot, down, up;
        int temp;
        if(lb >= ub) return;
        pivot = list[lb];
        down = lb;
        up = ub;

        while(down < up) {
            while(list[down] <= pivot  &&  down < ub) down++; //move up the array
            while(list[up] > pivot) up--; //move down the array
            if(down < up) {               //interchange
                temp = list[down];
                list[down] = list[up];
                list[up] = temp;
            }
        }
        list[lb] = list[up];
        list[up] = pivot;

        if (count < NO_OF_THREADS-1) {
            Thread t1 = new Thread(new QuickSort(list, lb, up - 1));
            t1.start();
            Thread t2 = new Thread(new QuickSort(list, up + 1, ub));
            t2.start();
            count--;
            try {
                t1.join();
                t2.join();
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            quickSort(list, lb, up-1);
            quickSort(list, up+1, ub);
        }
    }

    public void run() {
        count += 1;
        quickSort(array,lb,ub);
    }
}