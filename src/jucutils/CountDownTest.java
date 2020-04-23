package jucutils;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownTest
 * @Description juc并发工具类CountDownLatch 测试
 * 定义一个count为5的CountDownLatch，当运行完的线程数达到五个，就释放所有等待线程，便执行等待后面的输出
 * @Author 贺楚翔
 * @Date 2020-04-23 13:43
 * @Version 1.0
 **/
public class CountDownTest {
    public static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("Boss在等人开会，需要"+countDownLatch.getCount()+"人才开会！");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("人齐了，准备开会！");
        },"BOSS").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println("会议室进来第"+Thread.currentThread().getName()+"人");
                countDownLatch.countDown();
            },"Thread-"+String.valueOf(i)).start();
        }
    }
}
