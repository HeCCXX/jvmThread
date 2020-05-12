package algorithm;

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
    public static void main(String[] args) {
        int[] array = {8,3,1,6,5,2,10,7};
        countSort(array);
    }
}
