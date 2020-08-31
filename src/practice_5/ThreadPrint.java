package practice_5;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ThreadPrint
 * @Description
 * 给定一个数组[1, 2, 3, 4, 5, 6, 7, 8, 9...., 15]，要求遍历数组，
 * 1、遇到可以同时被3和5整除的数字，打印C；
 * 2、遇到仅能被5整除的数字，打印B；
 * 3、遇到仅能被3整除的数字，打印A；
 * 4、其他打印数字本身；
 * 要求四个线程，每一个线程执行一个打印方法。
 * @Author 贺楚翔
 * @Date 2020-04-10 9:30
 * @Version 1.0
 **/
public class ThreadPrint {
    public static final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public volatile int arrayIndex = 0;

    public int checkNum(int num){
        if (num % 3 ==0 && num % 5 ==0){
            return 0;
        }else if (num % 3 == 0){
            return 1;
        }else if (num % 5 ==0){
            return 2;
        }else {
            return 3;
        }
    }

    public void printNum(int flag){
        while (true) {
            lock.lock();
            try {
                while (flag != checkNum(array[arrayIndex]) && arrayIndex < array.length-1){
                    condition.await();
                }
                if (arrayIndex < array.length-1){
                    if (flag == 0){
                        System.out.println("A");
                    }else if (flag ==1){
                        System.out.println("B");
                    }else if(flag == 2){
                        System.out.println("C");
                    }else {
                        System.out.println(array[arrayIndex]);
                    }
                }else{
                    return;
                }
                arrayIndex++;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPrint threadPrint = new ThreadPrint();
        new Thread(()->{
            threadPrint.printNum(0);
        },"A").start();
        new Thread(()->{
            threadPrint.printNum(1);
        },"B").start();
        new Thread(()->{
            threadPrint.printNum(2);
        },"C").start();
        new Thread(()->{
            threadPrint.printNum(3);
        },"D").start();
    }
}
