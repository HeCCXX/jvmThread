package algorithm_9.practice;

/**
 * @ClassName BigIntAddTest
 * @Description 两大整数相加
 * @Author 贺楚翔
 * @Date 2020-05-20 10:03
 * @Version 1.0
 **/
public class BigIntAddTest {

    public static String getSumOfBigNum(String num1,String num2){
        int maxLength = num1.length() > num2.length() ? num1.length():num2.length();
        int[] arrayA = new int[maxLength+1];
        for (int i = 0; i < num1.length(); i++) {
            //char 转 int  需要将char字符减去一个'0'的ASCII值
            arrayA[i] = num1.charAt(num1.length()-1-i) - '0';
        }
        int[] arrayB = new int[maxLength+1];
        for (int i = 0; i < num2.length(); i++) {
            arrayB[i] = num2.charAt(num2.length()-1-i) - '0';
        }
        int[] result = new int[maxLength+1];

        for (int i = 0; i < arrayA.length; i++) {
            int tmp = 0;
            tmp += result[i];
            tmp += arrayA[i];
            tmp += arrayB[i];
            if (tmp > 10){
                tmp = tmp - 10;
                result[i+1] = 1;
            }
            result[i] = tmp;
        }

         StringBuilder sb = new StringBuilder();
        //判断最高有效位
        boolean isFirst = false;
        for (int i = result.length-1; i >= 0 ; i--) {
            if (!isFirst){
                if (result[i] == 0){
                    continue;
                }
                isFirst = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(getSumOfBigNum("432551361231320","845621315421315"));
    }
}
