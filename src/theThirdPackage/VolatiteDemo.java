package theThirdPackage;

/**
 * @ClassName VolatiteDemo
 * @Description volatite关键字，当改变值不依赖当前值时，可以使用，实现数据的可见性。
 * @Author 贺楚翔
 * @Date 2020-03-18 14:53
 * @Version 1.0
 **/
class Data{
    volatile int number = 10;

    public synchronized void setNumber(int num){
        this.number = num;
    }
}
public class VolatiteDemo {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            try {
//                Thread.sleep(3000);
//                data.setNumber(100);
                data.number = 100;
                System.out.println(Thread.currentThread().getName()+data.number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        System.out.println(data.number);
        while (data.number == 10){

        }
        System.out.println("break while");
    }
}
