package bilian.tech.zhang.javastarter.map;

import bilian.tech.zhang.javastarter.bst.BST;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node{
        public K key;
        public V value;

        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }


    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }


    @Override
    public void add(K key, V value) {

        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){
        if (node == null){
            size ++;
            return new Node(key, value);
        }

        if (node.key.compareTo(key) == 0){
            node.value = value;
        }else if (node.key.compareTo(key) < 0){
            node.right = add(node.right, key, value);
        }else if (node.key.compareTo(key) > 0){
            node.left = add(node.left, key, value);
        }

        return node;
    }

    private Node getNode(Node node, K key){

        if (node == null){
            return null;
        }

        if (key.compareTo(node.key) == 0){
            return node;
        }else if (key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }else{ //key.compareTo(node.key) > 0
            return getNode(node.right, key);
        }
    }


    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null){
            remove(root, key);
            return node.value;
        }
        return null;
    }



    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
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

    private Node remove(Node node, K key){

        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
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



    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {

        Node node = getNode(root, key);
        if (node == null){
            return null;
        }else{
            return node.value;
        }
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
