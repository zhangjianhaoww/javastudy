package bilian.tech.zhang.javastarter.tree;

import java.util.Arrays;

/**
 * 线段树
 *
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] array, Merger<E> merger){
        data = (E[])new Object[array.length];

        for (int i = 0; i < array.length; i++){
            data[i] = array[i];
        }

        tree = (E[]) new Object[4 * array.length];

        this.merger = merger;

        buildSegmentTree(0, 0, array.length - 1);
    }

    public E get(int index){
        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("index is illegal.");
        }
        return data[index];
    }


    public int getSize(){
        return data.length;
    }


    private int getLeftChild(int index){
        return 2 * index + 1;
    }

    private int getRightChild(int index){
        return 2 * index + 2;
    }

    private void buildSegmentTree(int treeIndex, int l, int r){

        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftChild = getLeftChild(treeIndex);
        int rightChild = getRightChild(treeIndex);

        int mid = l + (r - l) / 2;

        buildSegmentTree(leftChild, l, mid);
        buildSegmentTree(rightChild, mid + 1, r);

 //       tree[treeIndex] = tree[leftChild] + tree[rightChild]
        tree[treeIndex] = merger.merge(tree[leftChild], tree[rightChild]);
    }

    public E query(int queryL, int queryR){

        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR){
            throw new IllegalArgumentException("index queryL " + queryL + " or queryR " + queryR + " is illegal.");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR){

        if (l == queryL && r == queryR){
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = getLeftChild(treeIndex);
        int rightTreeIndex = getRightChild(treeIndex);

        if (queryL >= mid + 1){
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }

        if (queryR <= mid){
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

        return merger.merge(leftResult, rightResult);
    }

    public void set(int index, E e){

        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal");
        }
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e){

        if (l == r){
            tree[treeIndex] = e;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = getLeftChild(treeIndex);
        int rightTreeIndex = getRightChild(treeIndex);

        if (index >= mid + 1){
            set(rightTreeIndex, mid + 1, r, index, e);
        }
        else{
            set(leftTreeIndex, l, mid, index, e);
        }

        //左值或右值会有一个改变
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < tree.length; i ++){
            if (tree[i] != null){
                sb.append(tree[i]);
            }else{
                sb.append("null");
            }

            if (i != tree.length - 1){
                sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }


    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segTree = new SegmentTree<>(nums, Integer::sum);


        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));


    }
}
