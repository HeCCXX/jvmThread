package jucutils_6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @ClassName ExchangerTest
 * @Description Exchanger工具类，两个线程或多线程交换数据
 * 以下以消费者-生产者为例，生产者生产数据后和消费者交换数据
 * @Author 贺楚翔
 * @Date 2020-04-26 16:19
 * @Version 1.0
 **/
public class ExchangerTest {
    private  Exchanger exchanger;

    private List<String> buffer;

    public ExchangerTest(Exchanger exchanger, List<String> buffer) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    static class Provider implements Runnable{
        private  Exchanger exchanger;

        private List<String> buffer;

        public Provider(Exchanger exchanger, List<String> buffer) {
            this.exchanger = exchanger;
            this.buffer = buffer;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("生产者第"+i+"次提供");
                for (int j = 0; j < 3; j++) {
                    System.out.println("生产者装入"+ i + "-------"+j);
                    buffer.add("buffer:"+i+"----"+j);
                }
                System.out.println("生产者装满，等待与消费者交换..");
                try {
                    exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{
        private  Exchanger exchanger;

        private List<String> buffer;

        public Consumer(Exchanger exchanger, List<String> buffer) {
            this.exchanger = exchanger;
            this.buffer = buffer;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者第"+i + "次消费");
                for (int j = 0; j < 3; j++) {
                    System.out.println("消费："+buffer.get(0));
                    buffer.remove(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> buffer = new ArrayList<>();
        Exchanger<List<String>> listExchanger = new Exchanger<>();
        ExchangerTest exchangerTest = new ExchangerTest(listExchanger,buffer);
//        new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                System.out.println("生产者第"+i+"次提供");
//                for (int j = 0; j < 3; j++) {
//                    System.out.println("生产者装入"+ i + "-------"+j);
//                    exchangerTest.buffer.add("buffer:"+i+"----"+j);
//                }
//                System.out.println("生产者装满，等待与消费者交换..");
//                try {
//                    exchangerTest.exchanger.exchange(exchangerTest.buffer);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },"A").start();
//
//        new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                try {
//                    exchangerTest.exchanger.exchange(exchangerTest.buffer);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("消费者第"+i + "次消费");
//                for (int j = 0; j < 3; j++) {
//                    System.out.println("消费："+exchangerTest.buffer.get(0));
//                    exchangerTest.buffer.remove(0);
//                }
//            }
//        },"B").start();

        new Thread(new Provider(listExchanger,buffer)).start();

        new Thread(new Consumer(listExchanger,buffer)).start();

    }
}
