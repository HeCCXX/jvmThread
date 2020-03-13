package theThirdPackage;

/**
 * @ClassName theThirdPackage.theThirdPackage
 * @Description 堆和栈例子
 * @Author 贺楚翔
 * @Date 2020-03-13 17:10
 * @Version 1.0
 *
 * 8中基本类型：int  short  byte  float  double  boolean  char long  字面量存放在栈
 * 包装类   String   Integer   Double   Long
 **/
public class theThirdPackage {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        Integer c = new Integer(1);
        Integer d = new Integer(1);
        System.out.println(a==b);
        System.out.println(c == d);
    }
}
