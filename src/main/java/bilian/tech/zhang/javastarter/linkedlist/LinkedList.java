package bilian.tech.zhang.javastarter.linkedlist;

public class LinkedList<E> {

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


    private Node dummyHead;

    private int size;

    public LinkedList(){
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //在链表头添加元素e
    public void addFirst(E e){

//        Node node = new Node(e);
//        node.next = head;
//        head = node;

       add(0, e);
    }

    /**
     * 指定索引，添加（0-based） 比较少用
     *
     *  创建新节点node
     * 找到该索引的前一个节点 prev (当原链表为空时，需要判断)(添加虚拟头节点（dummyHead）后不需要考虑为空)
     * node.next = prev.next
     * prev.next = node
     *
     */

    public void add(int index, E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed, Illegal index.");
        }

//        if (index == 0){
//            addFirst(e);
//            return;
//        }

        Node prev = dummyHead;


        for (int i = 0; i < index; i++){
            prev = prev.next;
        }

//        Node data = new Node(e);
//        data.next = prev.next;
//        prev.next = data;
        prev.next = new Node(e, prev.next);

        size ++;
    }


    public void addLast(E e){
        add(size, e);
    }


    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed, Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++){
            cur = cur.next;
        }

        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed, Illegal index.");
        }

        Node cur = dummyHead.next;

        for (int i = 0; i < index; i ++){
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 包含
     *
     * @param e
     * @return
     */
    public boolean contains(E e){

        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed, Illegal index.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i ++){
            prev = prev.next;
        }

        Node cur = prev.next;
        prev.next = cur.next;
        cur.next = null;

        size --;

        return cur.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList [ ");

        Node cur = dummyHead.next;
        while (cur != null){
            sb.append(cur);
            sb.append(" --> ");
            cur = cur.next;

        }

        sb.append("null");
        sb.append(" ]");

        return sb.toString();
    }

    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++){

            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 66);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
