package jucutils_6;

/**
 * @ClassName ThreadLocalTest
 * @Description ThreadLocal达到线程隔离机制，确保变量的安全性
 * 可以看到新建的线程都会去初始化一个ThreadLocal，ThreadLocal相当于一个附属变量带在每个线程上
 * @Author 贺楚翔
 * @Date 2020-04-28 15:00
 * @Version 1.0
 **/
public class ThreadLocalTest {
    private static ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);

    public int nextCount(){
        count.set(count.get()+1);
        return count.get();
    }

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+"count:"+threadLocalTest.nextCount());
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+"count:"+threadLocalTest.nextCount());
            }
        },"B").start();
    }
}
