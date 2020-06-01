package bilian.tech.zhang.javastarter.bst;

import bilian.tech.zhang.javastarter.stack.ArrayStack;

import java.util.LinkedList;
import java.util.Queue;

public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }


    private Node root;
    private int size;


    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }


    /*
     * 添加元素
     */
    public void add(E e){
        if (root == null){
            root = new Node(e);
            size ++;
        }else{

           root =  add(root, e);
        }
    }

    private void add0(Node node, E e){

        if (e.equals(node.e)){
            return;
        }
        if (e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size ++;
            return;
        }
        if (e.compareTo(node.e) > 0 && node.right == null){
            node.right = new Node(e);
            size ++;
            return;
        }
        if (e.compareTo(node.e) < 0){
            add0(node.left, e);
        }else{
            add0(node.right, e);
        }
    }

    private Node add(Node node, E e){
        if (node == null){
            size ++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0){
            node.left = add(node.left, e);
        }else if (e.compareTo(node.e) > 0){  //相等时直接不操作
            node.right = add(node.right, e);
        }

        return node;
    }


    /*
    查询
     */
    public boolean contains(E e){

        if (root == null){
            return false;
        }
        return contains(root, e);
    }

    public boolean contains(Node node, E e){

        if (node == null){
            return false;
        }

        if (e.compareTo(node.e) == 0){
            return  true;
        }
        else if (e.compareTo(node.e) < 0){
            return  contains(node.left, e);
        }
        else{
            return  contains(node.right, e);
        }

    }

    /*
     * 遍历
     */

    //前序遍历
    public void preOrder(){

        preOrder(root);
    }

    private void preOrder(Node node){
        if (node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //前序遍历的非递归实现  需要将节点记录到栈中
    public void preOrder01(){
        if (root == null){
            return;
        }

        ArrayStack<Node> stacks = new ArrayStack<>();
        stacks.push(root);

        while (!stacks.isEmpty()){

            Node node = stacks.pop();

            System.out.println(node.e);

            if (node.right != null){
                stacks.push(node.right);
            }
            if (node.left != null){
                stacks.push(node.left);
            }
        }

    }

    //中序遍历的方式产生的数据都是有序的
    public void inOrder(){
        inOrder(root);
    }


    private void inOrder(Node node){
        if (node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder(){

        postOrder(root);
    }
    public void postOrder(Node node){
        if (node == null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();

        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null){
                q.add(cur.left);
            }
            if (cur.right != null){
                q.add(cur.right);
            }
        }
    }

    /**
     * 删除最小值和最大值
     * 不一定是子节点
     *
     *
     */

    //寻找最小元素
    public E minimum(){
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    //寻找最大元素
    public E maximum(){
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }
        return maximum(node.right);
    }


    /**
     * 递归删除最小值
     *
     * @return
     */
    public E removeMin(){
        E ret = minimum();

        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;    //删除操作
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }



    public E removeMax(){
        E ret = maximum();

        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;    //删除操作
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e){

    }

    private Node remove(Node node, E e){

        if (node == null){
            return null;
        }
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;

        }else{ //e == node.e{
            if (node.left == null){ //left == null && right == null || left == null
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            //待删除节点的左右子树均不为空的情况
            //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            //用这个节点替代删除节点的位置
            else{
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);  //size --
                successor.left = node.left;
                node.left = null;
                node.right = null;

                return successor;

            }
        }

    }

    /**
     * 通过前序遍历的方式编写 toString()
     * 
     * @return
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);

        return sb.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder sb) {

        if (node == null){
           sb.append(generateDepthString(depth) + " null\n");

        }else{
            sb.append(generateDepthString(depth) + " " + node.e + "\n");
            generateBSTString(node.left, depth + 1, sb);
            generateBSTString(node.right, depth + 1, sb);
        }
    }

    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i ++){
            sb.append("--> ");
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {8, 5, 3, 9, 7};
        for (int num : nums){
            bst.add(num);
        }

        bst.preOrder();

        System.out.println("\n" + bst);

        bst.inOrder();

        System.out.println();
        bst.postOrder();

        System.out.println();
        bst.preOrder01();

        System.out.println();
        bst.levelOrder();


        System.out.println();
        bst.removeMin();
        bst.levelOrder();


        System.out.println();
        bst.removeMax();
        bst.levelOrder();
    }



}
