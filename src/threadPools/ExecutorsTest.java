package threadPools;

import java.util.concurrent.*;

/**
 * @ClassName ExecutorsTest
 * @Description 利用Executors创建线程,但是不推荐使用该类创建线程池，因为该类创建的线程池会导致资源浪费
 * @Author 贺楚翔
 * @Date 2020-04-14 14:41
 * @Version 1.0
 **/
public class ExecutorsTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 16; i++) {
            Future<String> submit = executorService.submit(()->{
                return "bbb";
            }
            );
            System.out.println("active:"+ submit.get());
        }
        executorService.shutdown();
    }
}
