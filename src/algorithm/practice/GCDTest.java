package algorithm.practice;

/**
 * @ClassName GCDTest
 * @Description 求两个整数的最大公约数
 * 1、辗转相除法
 * 2、更相减损法
 * @Author 贺楚翔
 * @Date 2020-05-19 10:01
 * @Version 1.0
 **/
public class GCDTest {

    /**
    * 辗转相除法：两个整数的最大公约数等于 两数的余数与较小数的公约数
    * @param a
    * @param b
    * @return int
    * @exception
    **/
    public static int getGreaterCommonDivisior(int a,int b){
        int big = a>b?a:b;
        int small = a<b?a:b;
        if (big%small == 0){
            return small;
        }
        return getGreaterCommonDivisior(big%small,small);
    }

    /**
    * 更相减损法，两整数的最大公约数等于较大数减去较小数的结果与较小数的公约数
    * @param a
    * @param b
    * @return int
    * @exception
    **/
    public static int getGreaterCommonDivisorv2(int a,int b){
        if (a == b){
            return a;
        }
        int big = a>b?a:b;
        int small = a<b?a:b;
        return getGreaterCommonDivisorv2(big-small,small);
    }

    /**
    * 辗转相除法和更相减损法相结合，解决取模的性能差问题，也解决更相减损法两数相差差距较大问题
    * 当 a,b都是偶数，gcd(a,b) = 2 * gcd(a/2,b/2)
     * 当a为偶数，b为奇数时，gcd(a,b) = gcd(a/2,b)
     * 当a为奇数，b为偶数时，gcd(a,b) = gcd(a,b/2)
     * 当a，b都为奇数时，gcd(a,b) = gcd(a-b,b),计算结果a-b一定为偶数
    * @param a
    * @param b
    * @return int
    * @exception
    **/
    public static int gcd(int a,int b){
        if (a == b){
            return a;
        }
        if ((a&1)==0 && (b&1)==0){
            return gcd(a>>1,b>>1)<<1;
        }else if ((a&1)==0 && (b&1)!=0){
            return gcd(a>>1,b);
        }else if ((a&1)!=0 && (b&1)==0){
            return gcd(a,b>>1);
        }else {
            int big = a > b? a: b;
            int small = a < b? a: b;
            return gcd(big-small,small);
        }
    }
    public static void main(String[] args) {
        System.out.println("最大公约数："+getGreaterCommonDivisior(100,80));
        System.out.println("最大公约数："+getGreaterCommonDivisorv2(100,80));
        System.out.println("最大公约数："+gcd(100,80));

    }
}
