package concurentCAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicStampedTest
 * @Description 利用AtomicStampedReference解决ABA问题，如下，对AtomicInteger初始值为100的原子整形变量进行更新，AtomicInteger会成功执行CAS.
 * 而加上版本戳的
 * @Author 贺楚翔
 * @Date 2020-04-09 16:41
 * @Version 1.0
 **/
public class AtomicStampedTest {
    private static  AtomicInteger atomicNum = new AtomicInteger(100);
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            atomicNum.compareAndSet(100, 101);
            atomicNum.compareAndSet(101, 100);
        }, "A");

        Thread b = new Thread(() -> {
            try {
                Thread.sleep(1000);
                boolean b1 = atomicNum.compareAndSet(100, 101);
                System.out.println(b1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B");

        a.start();
        b.start();
        a.join();
        b.join();

    }
}
