package algorithm.practice;

/**
 * @ClassName RemoveKDigisterTest
 * @Description 移除K个数字后，要求剩余的数字组成的数字最小
 * @Author 贺楚翔
 * @Date 2020-05-19 16:36
 * @Version 1.0
 **/
public class RemoveKDigisterTest {

    /**
    * 利用两个循环，substring，性能差，可以优化
    * @param num
    * @param k
    * @return java.lang.String
    * @exception
    **/
    public static String getRemoveK(String num,int k){
        String newNum = num;
        for (int i = 0; i < k; i++) {
            boolean isCut = false;
            for (int j = 0; j < newNum.length()-1; j++) {
                if (newNum.charAt(j) > newNum.charAt(j+1)){
                    newNum = newNum.substring(0,j)+newNum.substring(j+1,newNum.length());
                    isCut = true;
                    break;
                }
            }
            if (!isCut){
                newNum = newNum.substring(0,newNum.length()-1);
            }
            removeZero(newNum);
        }
        if (newNum.length() == 0){
            return "0";
        }
        return newNum;
    }

    /**
    * 优化，遍历字符串数字，如果前一个数字大于后一个数字，则移除较大数字
     * 利用栈实现元素的追溯，如果栈顶元素大于接下来的元素，移动下标，将栈顶抹除
    * @param num
    * @param k
    * @return java.lang.String
    * @exception
    **/
    public static String getRemoveKBetter(String num,int k){
        int newLength = num.length()-k;

        char[] stack = new char[newLength];
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (top > 0 && stack[top-1] > c && k >0){
                top -= 1;
                k -= 1;
            }
            stack[top++] = c;
        }
        int offset = 0;
        //去除栈中为0的元素
        while (offset < newLength && stack[offset] == '0'){
            offset++;
        }
        return offset == newLength?"0":new String(stack,offset,newLength);
    }

    private static void removeZero(String newNum) {
        for (int i = 0; i < newNum.length(); i++) {
            if (newNum.charAt(0)!='0'){
                break;
            }
            newNum = newNum.substring(1,newNum.length());
        }
    }

    public static void main(String[] args) {
        System.out.println(getRemoveK("1594521",3));
        System.out.println(getRemoveKBetter("1594521",3));
    }
}
