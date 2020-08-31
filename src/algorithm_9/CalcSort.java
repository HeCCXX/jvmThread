package algorithm_9;

import java.util.Arrays;

/**
 * @ClassName CalcSort
 * @Description 计数排序
 * @Author HCX
 * @Date 2020/05/12 20:58
 * @Version 1.0
 **/
public class CalcSort {
    private static void countSort(int[] array) {
        int max = array[0];
        for (int i = 1 ;i < array.length; i++) {
            if (array[i]>max){
                max = array[i];
            }
        }

        //利用数组下标计数出现的相同数字的个数
        int[] sortArray = new int[max + 1];
        for (int i = 0; i <= array.length - 1; i++) {
            sortArray[array[i]]++;
        }
        System.out.println(Arrays.toString(sortArray));

        int index = 0;
        int[] result = new int[array.length];
        //遍历数组下标及个数，输出有序数列
        for (int i = 0; i < sortArray.length ; i++) {
            for (int j = 0; j < sortArray[i]; j++) {
                result[index++] = i;
            }
        }
        System.out.println(Arrays.toString(result));
    }
    /**
    * 优化计数排序，最大值减去最小值，并利用累加之前的统计，最后从数组末尾进行遍历，当出现相同的数时，统计值指定排序
     * 是稳定的算法，不会破坏相同数的前后排序
     * 只适用于整数、且最大值与最小值相差较小
    * @param array
    * @return void
    * @exception
    **/
    private static void countSortBetter(int[] array) {

        int maxvalue = array[0];
        int minvalue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (maxvalue<array[i]){
                maxvalue = array[i];
            }
            if (minvalue>array[i]){
                minvalue = array[i];
            }
        }
        int size = maxvalue - minvalue;
        int[] sortArray = new int[size+1];
        for (int i = 0; i < array.length; i++) {
            sortArray[array[i]-minvalue]++;
        }
        for (int i = 1; i < sortArray.length; i++) {
            sortArray[i] += sortArray[i-1];
        }
        
        int[] result = new int[array.length];
        for (int i = array.length-1; i >=0 ; i--) {
            result[--sortArray[array[i]-minvalue]] = array[i];
//            sortArray[array[i]-minvalue]--;
        }
        System.out.println(Arrays.toString(result));
    }


    public static void main(String[] args) {
        int[] array = {8,3,1,6,5,2,10,7};
        int[] array2 = {98,93,91,96,95,92,100,97};
        countSort(array);
        countSortBetter(array2);
    }
}
