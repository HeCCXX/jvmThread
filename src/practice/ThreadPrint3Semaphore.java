package practice;

import java.util.concurrent.Semaphore;

/**
 * @ClassName ThreadPrint3Semaphore
 * @Description 利用工具类Semaphore实现循环轮流打印
 * @Author 贺楚翔
 * @Date 2020-04-23 16:30
 * @Version 1.0
 **/
public class ThreadPrint3Semaphore {
    public static final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private Semaphore semaphoreA = new Semaphore(1);
    public interfaceFunction interfaceFunction;
    private static volatile int index = 0;

    public ThreadPrint3Semaphore(ThreadPrint3Semaphore.interfaceFunction interfaceFunction) {
        this.interfaceFunction = interfaceFunction;
    }

    @FunctionalInterface
    interface interfaceFunction{
        void print(int n);
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
    void print(int flag) throws InterruptedException {
        while (true){
            semaphoreA.acquire();
            while(index <= array.length-1  && flag == checkNum(array[index])){
                if (index <= array.length-1 ){
                    interfaceFunction.print(array[index]);
                }else{
                    return;
                }
                index++;
            }
            semaphoreA.release();
            if (index > array.length-1){
                return;
            }
        }

    }
    public static void main(String[] args) {
        new Thread(()->{
            try {
                new ThreadPrint3Semaphore((n)-> System.out.println("A")).print(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                new ThreadPrint3Semaphore((n)-> System.out.println("B")).print(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{
            try {
                new ThreadPrint3Semaphore((n)-> System.out.println("C")).print(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
        new Thread(()->{
            try {
                new ThreadPrint3Semaphore((n)-> System.out.println(n)).print(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();
    }
}
