package algorithm.practice;

import java.util.Stack;

/**
 * @ClassName QueueByStack
 * @Description 利用栈实现队列,两个栈分别完成入队和出队
 * @Author 贺楚翔
 * @Date 2020-05-19 13:24
 * @Version 1.0
 **/
public class QueueByStackTest {

    private  Stack<Integer> stackA = new Stack<>();
    private  Stack<Integer> stackB = new Stack<>();

    public  void enQueue(int element){
        stackA.push(element);
    }

    public  Integer deQueue(){
        if (stackB.isEmpty()){
            if (stackA.isEmpty()){
                return null;
            }
            transfer(stackA);
        }
        return stackB.pop();
    }

    private  void transfer(Stack<Integer> stackA) {
        while (!stackA.isEmpty()){
            stackB.push(stackA.pop());
        }
    }

    public static void main(String[] args) {
        QueueByStackTest queue = new QueueByStackTest();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);
        System.out.println(queue.deQueue());
        queue.enQueue(7);
        System.out.println(queue.deQueue());
    }
}
