package theThirdPackage;

/**
 * @ClassName SyncVolatileDemo3
 * @Description TODO
 * @Author 贺楚翔
 * @Date 2020-04-07 15:55
 * @Version 1.0
 **/
public class SyncVolatileDemo3 {
    private  int num ;
//    private volatile  int num;

    synchronized int getNum(){
        return this.num;
    }

    void readAndWrite(){
        int temp = getNum();
        temp += 1;
        setNum(temp);
//        num++;
    }

    synchronized void setNum(int num){
        this.num = num;
    }

    public static void main(String[] args) throws InterruptedException {
        SyncVolatileDemo3 syncVolatileDemo3 = new SyncVolatileDemo3();

        Thread a = new Thread(() -> {
            int start = 0;
            while (start++ <10000){
                syncVolatileDemo3.readAndWrite();
            }
        }, "A");

        Thread b = new Thread(() -> {
            int start = 0;
            while (start++ <10000){
                syncVolatileDemo3.readAndWrite();
            }
        }, "B");

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println("Number:" + syncVolatileDemo3.num);
    }
}
