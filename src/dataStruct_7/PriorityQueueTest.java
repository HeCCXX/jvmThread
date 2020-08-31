package dataStruct_7;

import java.util.Arrays;

/**
 * @ClassName PriorityQueueTest
 * @Description 优先级队列
 * @Author 贺楚翔
 * @Date 2020-05-11 15:34
 * @Version 1.0
 **/
public class PriorityQueueTest {

    private  int[] queue;
    private  int size;

    public PriorityQueueTest() {
        queue = new int[32];
    }

    /**
    * 进队，上浮调整插入元素
    * @param value
    * @return void
    * @exception
    **/
    public  void enQueue(int value){
        if (size >= queue.length){
            resize();
        }
        queue[size++] = value;
        upAdjust(queue);

    }

    private void upAdjust(int[] queue) {
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1)/2;
        int tmp = queue[childIndex];
        while (childIndex > 0 && tmp < queue[parentIndex]){
            queue[childIndex] = queue[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex -1)/2;
        }
        queue[childIndex] = tmp;
    }

    public void resize() {
        int size = this.size * 2;
        queue = Arrays.copyOf(queue,size);
    }

    public int deQueue() throws Exception {
        if (size <= 0){
            throw new Exception("this queue is empty!");
        }
        int head = queue[0];
        queue[0] = queue[--size];
        downAdjust(queue,0,size);
        return head;
    }

    private void downAdjust(int[] queue, int parentIndex, int size) {
        int tmp  = queue[parentIndex];
        int childIndex = 2 * parentIndex +1;
        while (childIndex < size){
            if (childIndex + 1 < size && queue[childIndex+1] < queue[childIndex]){
                childIndex++;
            }
            if (tmp < queue[childIndex]){
                break;
            }
            queue[parentIndex] = queue[childIndex];
            parentIndex = childIndex;
            childIndex = 2*parentIndex +1;
        }
        queue[childIndex] = tmp;
    }

    public static void main(String[] args) throws Exception {
        //优先级队列
        PriorityQueueTest priorityQueue = new PriorityQueueTest();
        priorityQueue.enQueue(1);
        priorityQueue.enQueue(7);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(8);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(3);
        System.out.println(Arrays.toString(priorityQueue.queue));
        System.out.println("出队元素："+ priorityQueue.deQueue());
        System.out.println("出队元素："+ priorityQueue.deQueue());
    }
}
