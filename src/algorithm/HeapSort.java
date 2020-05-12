package algorithm;

import java.util.Arrays;

/**
 * @ClassName HeapSort
 * @Description 堆排序
 * @Author HCX
 * @Date 2020/05/12 20:18
 * @Version 1.0
 **/
public class HeapSort {


    /**
    * 堆排序，首先构造二叉堆，从小到大需要最大堆，从大到小需要最小堆
    * @param array
    * @return void
    * @exception
    **/
    private static void heapSort(int[] array) {
        for (int i = (array.length-2)/2; i >= 0 ; i--) {
            downAdjust(array,i,array.length);
        }
        //交换第一个元素到末尾，之后在调整队首元素继续构成二叉堆最大堆
        for (int i = array.length-1; i > 0; i--) {
            int tmp = array[i];
            array[i] = array[0];
            array[0] = tmp;
            downAdjust(array,0,i);
            System.out.println(Arrays.toString(array));
        }
    }

    private static void downAdjust(int[] array, int parrentIndex, int length) {
        int tmp = array[parrentIndex];
        int childIndex = 2*parrentIndex + 1;
        while (childIndex < length){
            //最大堆
            if (childIndex+1 < length && array[childIndex+1] > array[childIndex]){
                childIndex++;
            }
            if (tmp >= array[childIndex]){
                break;
            }
            array[parrentIndex] = array[childIndex];
            parrentIndex = childIndex;
            childIndex = 2*parrentIndex+1;
        }
        array[parrentIndex] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {8,3,1,6,5,2,10,7};
        heapSort(array);
    }


}
