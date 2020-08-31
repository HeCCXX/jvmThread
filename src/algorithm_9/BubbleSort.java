package algorithm_9;

import java.util.Arrays;

/**
 * @ClassName BubbleSort
 * @Description 冒泡算法
 * @Author 贺楚翔
 * @Date 2020-05-11 17:13
 * @Version 1.0
 **/
public class BubbleSort {

    /**
    * 冒泡算法基础版
    * @param array
    * @return void
    * @exception
    **/
    public static void bubbleSort(int[] array){
        for (int i = 0;i<array.length-1;i++){
            for (int j = 0; j < array.length-i-1; j++) {
                int tmp = 0;
                if (array[j] > array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }

    /**
    * 设置boolean flag进行判断
    * @param array
    * @return void
    * @exception
    **/
    public static void bubbleSortBetter(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                int tmp = 0;
                if (array[j]>array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    flag = false;
                }
            }
            if (flag){
                break;
            }
        }
    }

    /**
    * 设置边界，只交换到边界处
    * @param array
    * @return void
    * @exception
    **/
    public static void bubbleSortBetter2(int[] array){
        int lastChangeIndex = 0;
        int sortNum = array.length -1;
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < sortNum; j++) {
                int tmp = 0;
                if (array[j]>array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    flag = false;
                    lastChangeIndex = j;
                }
            }
            sortNum = lastChangeIndex;
            if (flag){
                break;
            }
        }
    }

    /**
    * 鸡尾酒排序，冒泡排序的另一种优化,i控制轮训次数，j控制遍历方向
    * @param array
    * @return void
    * @exception       
    **/
    public static void bubbleSortBetter3(int[] array){

        for (int i = 0; i < array.length/2; i++) {
            int tmp = 0;
            boolean flag = true;
            for (int j = i; j < array.length-i-1; j++) {
                if (array[j] > array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    flag = false;
                }
            }
            if (flag){
                break;
            }
            flag = true;
            for (int j = array.length-i-1; j > i ; j--) {
                    if (array[j-1] > array[j]){
                        tmp = array[j];
                        array[j] = array[j-1];
                        array[j-1] = tmp;
                        flag = false;
                    }
            }
            if (flag){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {8,3,1,6,5,2,10,7};
        int[] array2 = {2,3,4,5,6,7,1,8};
//        bubbleSort(array);
//        bubbleSortBetter(array);
//        bubbleSortBetter2(array);
        bubbleSortBetter3(array2);
        System.out.println(Arrays.toString(array2));
    }
}
