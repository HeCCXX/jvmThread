package practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ThreadPrint2
 * @Description 利用接口函数做
 * 需要注意的是本次new 线程的时候是分别new的资源对象，所以它的成员变量需要时static，静态变量才会使4个对象使用相同变量
 * 接口函数写lambda，需要定义@FunctionalInterface，并且利用构造方法将lambda方法传入。
 * @Author 贺楚翔
 * @Date 2020-04-10 10:43
 * @Version 1.0
 **/
public class ThreadPrint2 {
    public static final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private static Lock lock = new ReentrantLock();
    private static   Condition condition = lock.newCondition();
    public static volatile int arrayIndex = 0;
    private PrintFunction printFunction;

    @FunctionalInterface
    interface PrintFunction{
        void print(int n);
    }

    public ThreadPrint2(PrintFunction printFunction) {
        this.printFunction = printFunction;
    }

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
                    printFunction.print(array[arrayIndex]);
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
        new Thread(()->{
            new ThreadPrint2((n)-> System.out.println("A")).printNum(0);
        },"A").start();
        new Thread(()->{
            new ThreadPrint2((n)-> System.out.println("B")).printNum(1);
        },"B").start();
        new Thread(()->{
            new ThreadPrint2((n)-> System.out.println("C")).printNum(2);
        },"C").start();
        new Thread(()->{
            new ThreadPrint2((n)-> System.out.println(n)).printNum(3);
        },"D").start();
    }

}
