package theSecondPackage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionLoop05
 * @Description Lock和Condition
 * 备注：多线程之间按顺序调用，实现A->B->C
 *  * 三个线程启动，要求如下：
 *  *
 *  * AA打印5次，BB打印10次，CC打印15次
 *  * 接着
 *  * AA打印5次，BB打印10次，CC打印15次
 *  * 来10轮
 * @Author 贺楚翔
 * @Date 2020-03-11 15:22
 * @Version 1.0
 **/
class ShareData{
    private Lock lock = new ReentrantLock();
    public Condition c1 = lock.newCondition();
    public  Condition c2 = lock.newCondition();
    public Condition c3 = lock.newCondition();

    /**
    * 根据传参进入的标志位和游标判断，进行下一个线程的进行
    * @param flag
    * @param cusor
    * @param condition
    * @param loopNum
    * @param nextCondition
    * @return void
    * @exception
    **/
    public void print(int flag,int cusor,Condition condition,int loopNum,Condition nextCondition){
        lock.lock();
        try
        {
                while (flag != cusor){
                    condition.await();
                }
                for (int k = 1; k <= loopNum; k++) {
                    System.out.println(Thread.currentThread().getName()+":"+k);
                }
                nextCondition.signal();
        }catch (Exception e){
              e.printStackTrace();
        }finally {
             lock.unlock();
        }
    }

}
public class ConditionLoop05 {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            shareData.print(1,1,shareData.c1,5,shareData.c2);
        },"A").start();
        new Thread(()->{
            shareData.print(2,2,shareData.c2,10,shareData.c3);
        },"B").start();
        new Thread(()->{
            shareData.print(3,3,shareData.c3,15,shareData.c1);
        },"C").start();


    }
}
