package bilian.tech.zhang.javastarter.queue;

import bilian.tech.zhang.javastarter.linkedlist.LinkedList;

public class LinkedListQueue<E> implements Queue<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    /**
     * 当链表中数据为空时，head 和 tail =  null
     */
    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null){
            tail = new Node(e);
            head = tail;
        }else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;

    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("cannot dequeue from an empty queue.");
        }

        Node node = head;
        head = head.next;
        if (head == null){  //只有一个元素，出队列。tail也要为空
            tail = null;
        }
        node.next = null;
        size --;
        return node.e;

    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("cannot dequeue from an empty queue.");
        }

        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListQueue top [ ");

       Node cur = head;
        while (cur != null){
            sb.append(cur);
            sb.append(" --> ");
            cur = cur.next;

        }

        sb.append("null");
        sb.append(" ] tail");

        return sb.toString();
    }

    public static void main(String[] args) {

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++){
            queue.enqueue(i);
            System.out.println(queue);
        }

        queue.dequeue();
        System.out.println(queue);
    }

}
