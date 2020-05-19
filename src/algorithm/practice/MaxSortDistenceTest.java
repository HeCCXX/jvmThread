package algorithm.practice;

/**
 * @ClassName MaxSortDistenceTest
 * @Description 求一个无序整数数组排序后，相邻两数的最大差
 * @Author 贺楚翔
 * @Date 2020-05-19 10:57
 * @Version 1.0
 **/
public class MaxSortDistenceTest {

    /**
    * 利用桶排序的原理将数组中元素分到不同范围桶中，然后根据桶的最大值和最小值差值计算得到最大相邻差
    * @param array
    * @return int
    * @exception
    **/
    public static int getMaxSortDistance(int[] array){
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i]>max){
                max = array[i];
            }
            if (array[i]<min){
                min = array[i];
            }
        }
        int d = max - min;
        if (d == 0){
            return 0;
        }
        //初始化桶
        int bucketNum = array.length;
        Bucket[] buckets = new Bucket[array.length];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }

        //遍历数组，将元素分别分发到不同范围的桶中
        for (int i = 0; i < array.length; i++) {
            int index = (array[i] - min) * (bucketNum - 1) / d;
            if (buckets[index].min == null || buckets[index].min > array[i]){
                buckets[index].min = array[i];
            }
            if (buckets[index].max == null || buckets[index].max < array[i]){
                buckets[index].max = array[i];
            }
        }

        //利用桶之间的最小值-最大值，最后求出便是相邻最大差
        Bucket leftBucket = buckets[0];
        int distance = 0;
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i].min == null){
                continue;
            }
            if (buckets[i].min - leftBucket.max > distance){
                distance = buckets[i].min - leftBucket.max;
            }
            leftBucket = buckets[i];
        }
        return distance;
    }

    private static class Bucket{
        private Integer max;
        private Integer min;
    }
    public static void main(String[] args) {
        int[] array = {2,6,3,4,5,10,9};
        System.out.println(getMaxSortDistance(array));
    }
}
