package theSecondPackage_2;

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
    private int globalFlag;

    public int getGlobalFlag() {
        return globalFlag;
    }

    public void setGlobalFlag(int globalFlag) {
        this.globalFlag = globalFlag;
    }

    /**
    * 根据传参进入的标志位和游标判断，进行下一个线程的进行
    * @param flag    标志位
    * @param condition
    * @param loopNum  循环次数
    * @param nextCondition
    * @param next  指向下一个进程
    * @return void
    * @exception
    **/
    public void print(int flag,Condition condition,int loopNum,Condition nextCondition,int next){
        lock.lock();
        try
        {
                while (flag != globalFlag){
                    condition.await();
                }
                for (int k = 1; k <= loopNum; k++) {
                    System.out.println(Thread.currentThread().getName()+":"+k);
                }
                globalFlag = next;
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
        shareData.setGlobalFlag(1);
        new Thread(()->{
            for (int i = 0;i < 10; i++) {
                shareData.print(1,shareData.c1,5,shareData.c2,2);
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0;i < 10; i++) {
                shareData.print(2,shareData.c2,10,shareData.c3,3);
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0;i < 10; i++) {
                shareData.print(3,shareData.c3,15,shareData.c1,1);
            }
        },"C").start();


    }
}
