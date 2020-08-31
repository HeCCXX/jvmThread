package dataStruct_7;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @ClassName TreeTest
 * @Description 树
 * @Author 贺楚翔
 * @Date 2020-04-30 15:58
 * @Version 1.0
 **/
public class TreeTest {
    static class TreeNode{
        private int data;
        private TreeNode rightChild;
        private TreeNode leftChild;

        public TreeNode(int value) {
            this.data = value;
        }
    }

    static public TreeNode createBinaryTree(LinkedList<Integer> list){
        TreeNode node = null;
        if (list == null || list.isEmpty()){
            return null;
        }
        Integer data = list.removeFirst();
        if (data != null){
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(list);
            node.rightChild = createBinaryTree(list);
        }
        return node;
    }

    /**
    * 前序遍历递归完成
    * @param node
    * @return void
    * @exception
    **/
    public static void preOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        System.out.println(node.data);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    /**
    * 利用栈完成前序遍历
    * @param node
    * @return void
    * @exception
    **/
    public static void preOrderTraveralByStack(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = node;
        while (treeNode != null || !stack.isEmpty() ){
            //访问左孩子，并入栈，记录访问
            while (treeNode != null){
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            //如果没有左孩子，则弹出栈顶节点，访问右孩子
            if (!stack.isEmpty()){
                TreeNode pop = stack.pop();
                treeNode = pop.rightChild;
            }
        }
    }

    /**
    *
    * 利用栈中序遍历
    * @return void
    * @exception
    **/
    public static void midOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            while (node != null){
                    stack.push(node);
                    node = node.rightChild;
            }
            if (!stack.isEmpty()){
                TreeNode pop = stack.pop();
                node = pop.leftChild;
                System.out.println(pop.data);
            }
        }

    }

    /**
    * 利用栈完成后序遍历，主要需要解决判断右节点的存在
    * @param root
    * @return void
    * @exception
    **/
    public static void lastOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode flagNode = null;
        while (node != null || !stack.isEmpty()){
            while (node != null){
                stack.push(node);
                node = node.leftChild;
            }
            if (!stack.isEmpty()){
               if (stack.peek().rightChild == null || flagNode == stack.peek().rightChild)
               {
                   TreeNode pop = stack.pop();
                   System.out.println(pop.data);
                   flagNode = pop;

               }else if (stack.peek().rightChild != null){
                   node = stack.peek().rightChild;
               }
            }
        }

    }

    /**
    * 广度遍历，利用队列
    * @param root
    * @return void
    * @exception
    **/
    public static void levelOrderTraveral(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode treeNode = queue.removeFirst();
            System.out.println(treeNode.data);

            if (treeNode.leftChild != null){
                queue.add(treeNode.leftChild);
            }
            if (treeNode.rightChild != null){
                queue.add(treeNode.rightChild);
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> integers = new LinkedList<>(Arrays.asList(1, 2, 3, null, null, 10, null, 8,null,null, 6, null, 4));
        TreeNode binaryTree = createBinaryTree(integers);
        System.out.println("前序遍历：o");
        preOrderTraveral(binaryTree);
        System.out.println("栈实现前序遍历：");
        preOrderTraveralByStack(binaryTree);
        System.out.println("栈实现后续遍历：");
        lastOrderTraveralWithStack(binaryTree);
        System.out.println("队列实现广度遍历：");
        levelOrderTraveral(binaryTree);
    }
}
