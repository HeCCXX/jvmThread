package blockQueue;

import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BlockQueueTest
 * @Description 手写阻塞队列,定义对象数组
 * @Author 贺楚翔
 * @Date 2020-04-14 15:56
 * @Version 1.0
 **/
public class BlockQueueTest {
    final Object[] queue;

    private int count = 0;

    private int putIndex = 0;

    private int takeIndex = 0;

    private static Lock lock = new ReentrantLock();

    private static Condition FullCondition = lock.newCondition();

    private static Condition EmptyCondition = lock.newCondition();

    public BlockQueueTest(int capcity) {
        this.queue = new Object[capcity];
    }

    public Object take(){
        lock.lock();
        try
        {
            while(count == 0){
                EmptyCondition.await();
            }
            Object obj = queue[takeIndex];
            queue[takeIndex] = null;
            if(++takeIndex == queue.length){
                takeIndex = 0;
            }
            count--;
            FullCondition.signal();
            return obj;
        }catch (Exception e){
              e.printStackTrace();
        }finally {
             lock.unlock();
        }
        return null;
    }

    public void put(Object data){
        lock.lock();
        try
        {
            while (count == queue.length ){
                FullCondition.await();
            }
            System.out.println("put:"+String.valueOf(data));
            queue[putIndex++] = data;
            if (putIndex == queue.length){
                putIndex = 0;
            }
            count++;
            EmptyCondition.signal();
        }catch (Exception e){
              e.printStackTrace();
        }finally {
             lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockQueueTest blockQueueTest = new BlockQueueTest(10);
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 20, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1));
//            threadPoolExecutor.execute(()->{
//                Object take = blockQueueTest.take();
//                System.out.println(String.valueOf(take));
//            });
//            threadPoolExecutor.execute(()->{
//                blockQueueTest.put("hcx");
//            });
        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                blockQueueTest.put("data"+i);
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread().getName()+":take:"+blockQueueTest.take());
            }
        },"B").start();
    }


}
