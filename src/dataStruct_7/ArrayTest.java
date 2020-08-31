package dataStruct_7;

/**
 * @ClassName test7
 * @Description 数组
 * @Author 贺楚翔
 * @Date 2020-04-29 16:06
 * @Version 1.0
 **/
public class ArrayTest {
    private int[] array;
    private int size;

    public ArrayTest(int capacity) {
        this.array = new int[capacity];
        size = 0;
    }

    public void insert(int index,int value){
        if (index < 0 ){
            throw new IndexOutOfBoundsException("chaojie");
        }
        if (index > array.length){
            resize();
        }

        for (int i = size - 1; i >= index ; i--) {
            array[i+1] = array[i];
        }
        array[index] = value;
        size++;
    }

    private void resize() {
        int[] large = new int[array.length * 2];
        System.arraycopy(array,0,large,0,array.length);
        array = large;
    }


    public static void main(String[] args) {
        ArrayTest test7 = new ArrayTest(5);
        test7.insert(1,1);
        test7.insert(2,1);
        test7.insert(3,1);
        test7.insert(2,2);
        for (int i : test7.array) {
            System.out.println(i);
        }
    }
}
