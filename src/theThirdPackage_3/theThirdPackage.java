package theThirdPackage_3;

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

        //byte的取值范围为-128~127，如果在这个取值范围内，自动装箱就不会创建新的对象，而是从常量池中获取，超过了byte取值范围就会再创建
        //新对象，创建的新对象在堆中，而常量池在栈中，地址不同，所以不同
        Integer a1 = 197;
        Integer a2 = 197;
        System.out.println(a1 == a2);

    }
}
