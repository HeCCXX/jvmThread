package theThirdPackage;

/**
 * @ClassName VolatiteDemo2
 * @Description volatile 复合操作原子性
 * 当进行volatile++这样的复合操作时，不能保证原子性，因为cpu执行执行volatile++是三行操作，volatile关键字不是阻塞算法，
 * 不保证其他线程不操作，从而导致结果出现错误。
 * @Author 贺楚翔
 * @Date 2020-04-07 15:31
 * @Version 1.0
 **/
public class VolatiteDemo2 {
    static volatile int num = 0;

    public static void main(String[] args) {
        VolatiteDemo2 volatiteDemo2 = new VolatiteDemo2();

        Thread a = new Thread(() -> {
            int start = 0;
            while (start++ < 100000) {
                num++;
                //加了输出语句会较少出现不正确例子
//                System.out.println(Thread.currentThread().getName() + ":" + num);
            }
        }, "A");

        Thread b = new Thread(() -> {
            int start = 0;
            while (start++ < 100000) {
                num++;
//                System.out.println(Thread.currentThread().getName() + ":" + num);
            }
        }, "B");

        a.start();
        b.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Number:"+ num);

    }

}
