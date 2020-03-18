package theThirdPackage;

import java.util.Random;

/**
 * @ClassName OOM
 * @Description OOM  jvm调参设置
 * -Xmx最大  默认为物理内存的1/4
 * -Xms 最小  初始分配大小，默认为物理内存的1/64
 * -XX：+PrintGCDetails  输出详细的GC日志
 * @Author 贺楚翔
 * @Date 2020-03-17 11:01
 * @Version 1.0
 **/
public class OOM {

    private  byte[] bigSize = new byte[1*1024*1024];
    Object instance = null;
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
//        String str = "hcx";
//        str += "hcx";
//        String str1 = "hcxhcx";
//        String str2 = "hcxhcx";
//        System.out.println(str.equals(str1));
//        System.out.println(str1 == str2);
        //输出运行过程 堆最大和初始分配大小
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"Mb");
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"Mb");
        //vm option   -Xmx4m -Xms4m -XX:+PrintGCDetails
        byte[] bytes = new byte[4 * 1024 * 1024];
        String str = "hcx";
        while(true){
            str += new Random().nextInt(888888888)+new Random().nextInt(999999999);
        }

        /**
        * 引用计数法：每次对对象赋值时均要维护引用计数器，jvm的实现一般不采用这种方法
        * @param args
        * @return void
        * @exception
        **/
        /*OOM objectB = new OOM();
        OOM objectA = new OOM();
        objectA.instance = objectB;
        objectB.instance = objectA;
        objectA = null;
        objectB = null;

        System.gc();*/
    }
}
