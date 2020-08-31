package threadPools_4;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolTest
 * @Description 线程池运行国过程测试，原理学习
 * @Author 贺楚翔
 * @Date 2020-04-13 16:22
 * @Version 1.0
 **/
public class ThreadPoolTest {
    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(5);
        //丢弃策略，多余的丢弃，不报错
        ThreadPoolExecutor.DiscardPolicy handler = new ThreadPoolExecutor.DiscardPolicy();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue,handler);
        for (int i = 0; i < 16; i++) {
            poolExecutor.execute(()->{
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("线程池中活跃的线程数："+ poolExecutor.getPoolSize());
            if (queue.size() > 0){
                System.out.println("-------------------任务队列阻塞的线程数"+queue.size());
            }
        }
        poolExecutor.shutdown();
    }
}
