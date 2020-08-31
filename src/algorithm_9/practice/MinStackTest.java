package algorithm_9.practice;

import java.util.Stack;

/**
 * @ClassName MinStackTest
 * @Description 时间复杂度为O(1)的最小栈
 * 需要使用辅助栈，用于记录最小值，将当前值的最小值压入栈中，并记录前一个最小值
 * @Author 贺楚翔
 * @Date 2020-05-19 9:22
 * @Version 1.0
 **/
public class MinStackTest {

    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int element){
        mainStack.push(element);
        if (minStack.empty() || element <=minStack.peek()){
            minStack.push(element);
        }
    }

    public Integer pop(){
        if (mainStack.peek().equals(minStack.peek())){
            minStack.pop();
        }
        return mainStack.pop();
    }

    public int getMin() throws Exception {
        if (mainStack.empty()){
            throw new Exception("stack is empty!");
        }
        return minStack.peek();
    }

    public static void main(String[] args) throws Exception {
        MinStackTest minStackTest = new MinStackTest();
        minStackTest.push(10);
        minStackTest.push(9);
        minStackTest.push(7);
        minStackTest.push(3);
        minStackTest.push(8);
        minStackTest.push(5);
        System.out.println(minStackTest.getMin());
        System.out.println(minStackTest.mainStack);
        System.out.println(minStackTest.minStack);
        minStackTest.pop();
        minStackTest.pop();
        minStackTest.pop();
        System.out.println(minStackTest.getMin());
        System.out.println(minStackTest.mainStack);
        System.out.println(minStackTest.minStack);
    }
}
