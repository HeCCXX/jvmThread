package algorithm_9.application;

/**
 * @ClassName BitMapTest
 * @Description BitMap算法
 * @Author 贺楚翔
 * @Date 2020-05-21 15:52
 * @Version 1.0
 **/
public class BitMapTest {
    private long[] words;
    private int size;

    public BitMapTest(int size) {
        this.size = size;
        //相当于用几个64bit的long类型来构成对应位数的bitmap
        this.words = new long[getWordIndex(size-1)+1];
    }

    private int getWordIndex(int i) {
        //除以64,
        return i>>6;
    }

    public boolean getBit(int bitIndex){
        if (bitIndex < 0 || bitIndex > size -1){
            throw new IndexOutOfBoundsException("超过bitMap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        return (words[wordIndex]& (1 << bitIndex)) != 0;
    }

    public void setBit(int bitIndex){
        if (bitIndex < 0 || bitIndex > size -1){
            throw new IndexOutOfBoundsException("超过bitMap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        words[wordIndex] |= (1 << bitIndex);
    }


    public static void main(String[] args) {
        BitMapTest bitMap = new BitMapTest(128);
        bitMap.setBit(126);
        bitMap.setBit(7);
        System.out.println(bitMap.getBit(126));
        System.out.println(bitMap.getBit(78));
    }
}
