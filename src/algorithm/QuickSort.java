package algorithm;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @ClassName QuickSort
 * @Description 快速排序
 * @Author HCX
 * @Date 2020/05/11 21:42
 * @Version 1.0
 **/
public class QuickSort {

    public static void quickSort1(int[] array,int startIndex,int endIndex){
        if (startIndex >= endIndex){
            return;
        }
        int pivot = partition2(array,startIndex,endIndex);
        quickSort1(array,startIndex,pivot-1);
        quickSort1(array,pivot+1,endIndex);
    }

    /**
    * 利用栈替换递归的方式
    * @param array
    * @param startIndex
    * @param endIndex
    * @return void
    * @exception
    **/
    public static void quickSortByStack(int[] array,int startIndex,int endIndex){
        Stack<Map<String, Integer>> stack = new Stack<>();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex",startIndex);
        map.put("endIndex",endIndex);
        stack.push(map);

        while (!stack.isEmpty()){
            Map<String, Integer> pop = stack.pop();
            int partition = partition(array, pop.get("startIndex"), pop.get("endIndex"));

            if (partition - 1 > pop.get("startIndex")){
                HashMap<String, Integer> map1 = new HashMap<>();
                map1.put("startIndex",startIndex);
                map1.put("endIndex",partition-1);
                stack.push(map1);
            }
            if (pop.get("endIndex")>partition+1){
                HashMap<String, Integer> map1 = new HashMap<>();
                map1.put("startIndex",partition+1);
                map1.put("endIndex",endIndex);
                stack.push(map1);
            }
        }
    }

    /**
    * 双向循环，左右遍历
    * @param array
    * @param startIndex
    * @param endIndex
    * @return int
    * @exception
    **/
    private static int partition(int[] array,int startIndex,int endIndex) {
        int pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right){
            //必须先从右边开始遍历，要不然记录的位置会加1
            while (left<right && array[right]>pivot){
                right--;
            }
            while (left<right && array[left]<=pivot){
                left++;
            }
            if (left < right){
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
            }
        }
        array[startIndex] = array[left];
        array[left] = pivot;
        System.out.println(Arrays.toString(array));
        return left;
    }

    /**
    * 单边循环遍历，mark标记负责记录大于pivot和小于pivot交换的位置
    * @param array
    * @param startIndex
    * @param endIndex
    * @return int
    * @exception
    **/
    private static int partition2(int[] array,int startIndex,int endIndex) {
        int pivot = array[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (array[i]<pivot){
                mark++;
                int tmp = array[i];
                array[i] = array[mark];
                array[mark] = tmp;
            }
        }
        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    public static void main(String[] args) {
        int[] array = {8,3,1,6,5,2,10,7};
//        quickSort1(array,0,array.length-1);
        quickSortByStack(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
