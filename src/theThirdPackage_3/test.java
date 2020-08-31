package theThirdPackage_3;

/**
 * @ClassName test
 * @Description TODO
 * @Author 贺楚翔
 * @Date 2020-03-18 11:03
 * @Version 1.0
 **/
public class test {
    public static void main(String[] args) {
        String str1 = "hcx";
        String str2 = "hgg";
        String str3 = str1+str2;
        String str5 = "hcx" + "hgg";
        String str4 = "hcxhgg";

        String str6 = str1 + "hgg";
        System.out.println(str3 == str4);
        System.out.println(str4 == str5);
        System.out.println(str4 == str6);

    }
}
