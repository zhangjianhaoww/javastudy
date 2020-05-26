package bilian.tech.zhang.javastarter.bst;

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

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {8, 5, 3, 9, 7};
        for (int num : nums){
            bst.add(num);
        }

        bst.preOrder();

        System.out.println("\n" + bst);
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


}