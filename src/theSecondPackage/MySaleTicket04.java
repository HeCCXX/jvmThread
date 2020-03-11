package theSecondPackage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MySaleTicket04
 * @Description 题目：现在两个线程，可以操作初始值为零的一个变量，
 *  * 实现一个线程对该变量加1，一个线程对该变量减1，
 *  * 实现交替，来10轮，变量初始值为零。
 *
 *     1    高聚低合前提下，线程操作资源类
 *     2   判断/干活/通知
 *     3   防止虚假唤醒
 *
 *    知识小总结 = 多线程编程套路+while判断+新版写法
 * @Author 贺楚翔
 * @Date 2020-03-11 14:18
 * @Version 1.0
 **/
class Ticket{
    private int ticket = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try
        {
            while (ticket !=0){
                condition.await();
            }
            ticket++;
            System.out.println(Thread.currentThread().getName()+":"+ticket);
            condition.signalAll();
        }catch (Exception e){
              e.printStackTrace();
        }finally {
             lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try
        {
            while(ticket==0){
                condition.await();
            }
            ticket--;
            System.out.println(Thread.currentThread().getName()+":"+ticket);
            condition.signalAll();
        }catch (Exception e){
              e.printStackTrace();
        }finally {
             lock.unlock();
        }
    }
    /**
    * 利用syncchronized关键字实现例程
    * @param null
    * @return
    * @exception
    **/
//    public synchronized void decrement2() throws InterruptedException {
//        while(ticket==0){
//            this.wait();
//        }
//        ticket--;
//        this.notifyAll();
//    }
}
public class MySaleTicket04 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.decrement();
            }
        },"A").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                ticket.increment();
            }
        },"B").start();
    }
}
