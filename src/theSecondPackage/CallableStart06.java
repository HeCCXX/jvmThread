package theSecondPackage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableStart06
 * @Description 实现多线程的方法，4种
 * 1、继承 thread
 * 2、实现runable
 * 3、实现callable
 * 4、线程池
 * @Author 贺楚翔
 * @Date 2020-03-11 16:26
 * @Version 1.0
 **/

class  MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call comeing in ...");
        return 1;
    }
}
public class CallableStart06 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread());
        new Thread(futureTask,"A").start();

        Integer o = futureTask.get();
        System.out.println(o.getClass().getName());

    }
}
