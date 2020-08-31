package algorithm_9.application;

import java.util.HashMap;

/**
 * @ClassName LRUCacheTest
 * @Description LRU算法，缓存,最近最少使用
 * @Author 贺楚翔
 * @Date 2020-05-21 16:20
 * @Version 1.0
 **/
public class LRUCacheTest {
    private Node head;
    private Node end;
    private int limit;
    private HashMap<String,Node> hashMap;

    public LRUCacheTest(int limit) {
        this.limit = limit;
        this.hashMap = new HashMap<>();
    }

    public String get(String key){
        Node node = hashMap.get(key);
        if (node == null){
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    public void put(String key,String value){
        Node node = hashMap.get(key);
        if (node == null){
            if (hashMap.size() >= limit){
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key,value);
            addNode(node);
            hashMap.put(key,node);
        }else {
            node.value = value;
            refreshNode(node);
        }
    }

    public void remove(String key){
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }

    /**
    * 插入一个节点
    * @param node
    * @return void
    * @exception
    **/
    private void addNode(Node node) {
        if (end != null){
            end.next = node;
            node.next = null;
            node.prev = end;
        }
        end = node;
        if (head == null){
            head = node;
        }
    }

    /**
    * 移除一个节点
    * @param node
    * @return java.lang.String
    * @exception
    **/
    private String removeNode(Node node) {
        if (node == head && node == end){
            head = null;
            end = null;
        }else if (node == end){
            end = end.prev;
            end.next = null;
        }else if (node == head){
            head = head.next;
            head.prev = null;
        }else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        return node.key;
    }

    /**
    * 刷新hash链表，移除后再插入到队列最后方
    * @param node
    * @return void
    * @exception
    **/
    private void refreshNode(Node node) {
        if (node == end){
            return;
        }
        removeNode(node);
        addNode(node);
    }

    class Node{
        private Node prev;
        private Node next;
        private String key;
        private String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCacheTest lruCacheTest = new LRUCacheTest(5);
        lruCacheTest.put("001","用户1信息");
        lruCacheTest.put("002","用户2信息");
        lruCacheTest.put("003","用户3信息");
        lruCacheTest.put("004","用户4信息");
        lruCacheTest.put("005","用户5信息");
        lruCacheTest.get("002");
        lruCacheTest.put("004","用户4信息update");
        lruCacheTest.put("006","用户6信息");
        System.out.println(lruCacheTest.get("001"));
        System.out.println(lruCacheTest.get("006"));
    }
}
