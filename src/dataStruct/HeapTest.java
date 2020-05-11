package dataStruct;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @ClassName HeapTest
 * @Description 二叉堆
 * @Author 贺楚翔
 * @Date 2020-05-11 13:37
 * @Version 1.0
 **/
public class HeapTest {



    private static void buildHeap(int[] array) {
        for (int i = (array.length-2)/2; i >= 0; i--) {
            downAdjust(array,i,array.length);
        }

    }

    /**
    * 下沉，构建最小二叉堆
    * @param array
    * @param parentIndex
    * @param length
    * @return void
    * @exception
    **/
    private static void downAdjust(int[] array, int parentIndex, int length) {
        int tmp = array[parentIndex];
        int childIndex = 2 * parentIndex +1;
        while (childIndex < length){
            if (childIndex + 1 < length && array[childIndex+1] < array[childIndex]){
                childIndex++;
            }
            if(tmp < array[childIndex]){
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }
        array[parentIndex] = tmp;
    }

    /**
    * 上浮，适用于插入节点后的自我调整
    * @param array
    * @return void
    * @exception
    **/
    private static void upAdjust(int[] array){
        int childIndex = array.length -1;
        int parentIndex = (childIndex - 1)/2;
        int tmp = array[childIndex];
        while (childIndex > 0){
            if (tmp < array[parentIndex]){
                array[childIndex] = array[parentIndex];
                childIndex = parentIndex;
                parentIndex = (childIndex -1)/2;
            }else {
                break;
            }
        }
        array[childIndex] = tmp;
    }



    public static void main(String[] args) throws Exception {
        int[] array = {7, 1, 3, 10, 2, 8, 4, 6, 5, 9};
        buildHeap(array);
        System.out.println(Arrays.toString(array));

        int[] arrays = {1,2,3,4,5,6,7,8,9,0};
        upAdjust(arrays);
        System.out.println(Arrays.toString(arrays));


    }

}
