package dataStruct;

/**
 * @ClassName LinkedListTest
 * @Description 链表
 * @Author 贺楚翔
 * @Date 2020-04-29 16:52
 * @Version 1.0
 **/
public class LinkedListTest {
    private Node head;
    private Node last;
    private int size;
    static class Node{
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public LinkedListTest() {
        this.size = 0;
    }

    public void insert(int value,int index){

        if (index < 0){
            throw  new IndexOutOfBoundsException("不能为负数。");
        }
        Node node = new Node(value);
        if (size == 0){
            head = node;
            last = node;
        }else if (index == 0){
            node.next = head;
            head = node;
        }else if (size == index || index >size){
            last.next = node;
            last = node;
        }else {
            Node prev = head;
            for (int i = 0; i < index -1; i++) {
                prev = head.next;
            }
            node.next = prev.next;
            prev.next = node;
        }
        size++;
    }

    public static void main(String[] args) {
        LinkedListTest linkedListTest = new LinkedListTest();
        linkedListTest.insert(1,0);
        linkedListTest.insert(1,1);
        linkedListTest.insert(1,2);
        linkedListTest.insert(1,3);

        linkedListTest.insert(2,1);
        while(linkedListTest.head != null){
            System.out.println(linkedListTest.head.data);
            linkedListTest.head = linkedListTest.head.next;
        }
    }

}
