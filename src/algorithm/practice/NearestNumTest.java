package algorithm.practice;

import java.util.Arrays;

/**
 * @ClassName NearestNumTest
 * @Description 全排序找到最近大于原数的最近的数
 * @Author 贺楚翔
 * @Date 2020-05-19 14:02
 * @Version 1.0
 **/
public class NearestNumTest {

    public static int[] getNearestNumber(int[] array){
        int index = getNumChange(array);
        if (index == 0){
            return null;
        }
        int[] arraycopy = Arrays.copyOf(array, array.length);
        exchangeNum(arraycopy,index);
        reverse(arraycopy,index);
        return arraycopy;
    }

    private static void reverse(int[] arraycopy,int index) {
        for (int i = index, j = arraycopy.length-1; i < j; i++,j--) {
            int tmp = arraycopy[i];
            arraycopy[i] = arraycopy[j];
            arraycopy[j] = tmp;
        }
    }

    private static void exchangeNum(int[] arraycopy, int index) {
        int min = arraycopy[index-1];
        for (int i = arraycopy.length-1; i > 0; i--) {
            if (min < arraycopy[i]){
                arraycopy[index - 1] = arraycopy[i];
                arraycopy[i] = min;
                break;
            }
        }
    }

    private static int getNumChange(int[] array) {
        for (int i = array.length-1; i > 0; i--) {
            if (array[i] > array[i-1]){
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] num = {1,2,3,4,5};
        for (int i = 0; i < 10; i++) {
            num = getNearestNumber(num);
            System.out.println(Arrays.toString(num));

        }
    }
}
