package QuickSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSortSequencial {
    static void quickSort(int list[], int lb, int ub) {
        int pivot, down, up;
        int temp;
        if(lb>=ub)
            return;
        pivot=list[lb];
        down=lb;
        up=ub;
        while(down<up) {
            while(list[down]<=pivot && down<ub) down++;
            while(list[up]>pivot) up--;
            if(down<up) {
                temp=list[down];
                list[down]=list[up];
                list[up]=temp;
            }
        }
        list[lb]=list[up];
        list[up]=pivot;

        quickSort(list, lb, up-1);
        quickSort(list, up+1, ub);
    }
}
