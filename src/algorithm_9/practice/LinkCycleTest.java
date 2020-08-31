package algorithm_9.practice;

/**
 * @ClassName LinkCycleTest
 * @Description 链表有环判断
 * @Author 贺楚翔
 * @Date 2020-05-18 16:29
 * @Version 1.0
 **/
public class LinkCycleTest {

    public static boolean isCycle(Node link){
        Node p1 = link;
        Node p2 = link;
        while (p2!=null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2){
                return true;
            }
        }
        return false;
    }

    /**
    * 计算环的长度，第一次相遇后，count计算到下一次相遇间的移动次数，当相遇则cycleNum加1，表示第几次相遇
     * 环长 = 速度差 * 前进移动次数
    * @param link 链表
    * @return int
    * @exception
    **/
    public static int CycleLength(Node link){
        Node p1 = link;
        Node p2 = link;
        int count = 0;
        int cycleNum = 0;
        while (p2!=null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2){
                cycleNum++;
            }
            if (cycleNum == 1){
                count++;
            }
            if (cycleNum == 2){
                return count;
            }
        }
        return -1;
    }

    /**
    * 求环的交汇点，根据第一次相遇后，将p1节点置为首节点，然后两个指针分别都是前进1，当再次相遇的节点便是交汇点
    * @param link
    * @return algorithm.practice.LinkCycleTest.Node
    * @exception
    **/
    public static Node nodeOfCycle(Node link){
        Node p1 = link;
        Node p2 = link;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2){
                p1 = link;
                while (p2 != null && p2.next != null){
                    p1 = p1.next;
                    p2 = p2.next;
                    if (p1 == p2){
                        return p1;
                    }
                }
            }
        }
        return null;
    }

    public static class Node{
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node4;

        System.out.println("是否有环："+isCycle(node1));
        System.out.println("环的长度："+CycleLength(node1));
        System.out.println("环的出入节点："+nodeOfCycle(node1).data);
    }
}
