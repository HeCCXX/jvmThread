package concurentCAS_8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName AtomicStampedTest
 * @Description 利用AtomicStampedReference解决ABA问题，如下，对AtomicInteger初始值为100的原子整形变量进行更新，AtomicInteger会成功执行CAS.
 * 而加上版本戳的AtomicStampedReference将会执行失败。
 * 例程如下：对atomicInteger类型的数据进行CAS后，结果正确，但存在ABA问题；
 * 对AtomicStampedReference初始化的pair，每次CAS后改变版本戳，比如自增1.但在RT2线程中，首先去获取版本戳，然后sleep2秒，此时另外线程如果改变或未改变
 * 时间戳，将对结果产生影响。若未改动，则输出true，若改动，则输出false，有一定概率，若不延时，将解决ABA问题。
 * @Author 贺楚翔
 * @Date 2020-04-09 16:41
 * @Version 1.0
 **/
public class AtomicStampedTest {
    private static  AtomicInteger atomicNum = new AtomicInteger(100);
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100,0);
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            atomicNum.compareAndSet(100, 101);
            atomicNum.compareAndSet(101, 100);
        }, "A");

        Thread b = new Thread(() -> {
            try {
                Thread.sleep(1000);
                boolean b1 = atomicNum.compareAndSet(100, 101);
                System.out.println(Thread.currentThread().getName()+b1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B");

        a.start();
        b.start();
        a.join();
        b.join();

        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp()+1);
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp()+1);
        },"RT1").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b1 = atomicStampedReference.compareAndSet(100, 101, stamp,
                    stamp + 1);
            System.out.println(Thread.currentThread().getName()+b1);
        }, "RT2").start();

    }
}
