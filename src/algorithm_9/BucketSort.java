package algorithm_9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @ClassName BucketSort
 * @Description 桶排序
 * @Author HCX
 * @Date 2020/05/13 21:11
 * @Version 1.0
 **/
public class BucketSort {

    private static void bucketSort(double[] array){
        int num = array.length;
        double maxvalue = array[0];
        double minvalue = array[0];
        for (int i = 1; i < num; i++) {
            if (maxvalue < array[i]){
                maxvalue = array[i];
            }
            if (minvalue> array[i]){
                minvalue = array[i];
            }
        }

        double d = maxvalue - minvalue;
        ArrayList<LinkedList<Double>> linkedLists = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            linkedLists.add(new LinkedList<Double>());
        }

        //将数字分发到不同的桶中，根据最大、最小值限定每个桶的区间范围
        for (int i = 0; i < array.length; i++) {
           int index = (int) ((array[i]-minvalue)*(num-1)/d);
           linkedLists.get(index).add(array[i]);
        }

        for (int i = 0; i < linkedLists.size(); i++) {
            //底层采用归并排序
            Collections.sort(linkedLists.get(i));
        }

        double[] result = new double[array.length];
        int index = 0;
        for (LinkedList<Double> linkedList : linkedLists) {
            for (Double aDouble : linkedList) {
                result[index++]=aDouble;
            }
        }
        System.out.println(Arrays.toString(result));

    }
    public static void main(String[] args) {
        double[] array = {4.12, 6.333, 0.3521, 3.0, 2.7412, 8.66, 10.99};
        bucketSort(array);

    }
}
