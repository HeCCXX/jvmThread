package algorithm_9;

import java.util.Arrays;

/**
 * @ClassName MergeSort
 * @Description 归并排序
 * @Author HCX
 * @Date 2020/05/13 21:38
 * @Version 1.0
 **/
public class MergeSort {

    private static void mergeSort(int[] array){
        int[] tmp = new int[array.length];
        sort(array,0,array.length-1,tmp);
        System.out.println(Arrays.toString(tmp));
    }

    private static void sort(int[] array, int first, int last, int[] tmp) {
        if (first<last){
            int mid = (last+first)/2;
            sort(array,first,mid,tmp);
            sort(array,mid+1,last,tmp);
            merge(array,first,mid,last,tmp);
        }
    }

    private static void merge(int[] array, int first, int mid, int last, int[] tmp) {
        int i = first;
        int j = mid+1;
        int index = 0;
        while (i <= mid && j <= last){
            if (array[i]<=array[j]){
                tmp[index++] = array[i++];
            }else {
                tmp[index++] = array[j++];
            }
        }
        while (i<=mid){
            tmp[index++]=array[i++];
        }
        while (j<=last){
            tmp[index++]=array[j++];
        }
        //操作的是同一个array和tmp，所以需要将归并排序好的赋值
        index = 0;
        while (first<=last){
            array[first++] = tmp[index++];
        }
    }

    public static void main(String[] args) {
        int[] array = {8,3,1,6,5,2,10,7,2};
        mergeSort(array);
    }
}
