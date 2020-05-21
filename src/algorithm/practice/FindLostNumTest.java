package algorithm.practice;

import java.util.Arrays;

/**
 * @ClassName FindLostNumTest
 * @Description 假设一个无序数组中有若干正整数，范围为1~100，其中有98个整数出现了偶数次，只有2个整数出现了奇数次，
 * 如何找到这两个出现奇数次的整数。
 * 利用异或，和分治法
 * 全部整数异或结果为一个数，这个数二进制位数1，则表示两个奇数次数这位不同，以此分治
 * @Author 贺楚翔
 * @Date 2020-05-21 15:13
 * @Version 1.0
 **/
public class FindLostNumTest {

    public static int[] findLostNum(int[] array){
        int[] result = new int[2];
        int xorResult = 0;
        //第一次异或结果，记录两个奇数次数异或结果
        for (int i = 0; i < array.length; i++) {
            xorResult ^= array[i];
        }
        //如果异或结果为0，则输入数组有问题
        if (xorResult == 0){
            return null;
        }
        int index = 1;
        while (0 == (xorResult&index) ){
            index <<= 1;
        }

        //分治，根据二进制位将奇数次数分别分到两个结果中去
        for (int i = 0; i < array.length; i++) {
            if (0 == (array[i]&index)){
                result[0] ^= array[i];
            }else {
                result[1] ^= array[i];
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] array = {4,1,2,2,5,1,4,3};
        System.out.println(Arrays.toString(findLostNum(array)));

    }
}
