package bilian.tech.zhang.javastarter.queue;

import java.util.Objects;

/**
 * 通过定义 tail 和 front 来记录数据在数组中的开始和结束节点，循环插入数据
 * tail == front 表示表为空
 * ( tail + 1 ) % data.length == front 表示表已满，这时再创建更大容量的数组
 *
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size; // tail - front || tail + capacity - front

    public LoopQueue(int capacity) {
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(20);
    }

    public int getCapacity(){
        //会有一个浪费掉，来判断数组是否已满

        return data.length - 1;
    }
    @Override
    public void enqueue(E e) {

        if ((tail + 1) % data.length == front){
            resize(getCapacity() * 2);
        }

        data[tail] = e;

        tail = (tail + 1) % data.length;

        size ++;
    }


    @Override
    public E dequeue() {
        //查询的时候表可能为空
        if (isEmpty()){
            throw new IllegalArgumentException("can not dequeue from an empty queue.");
        }

        E ret = data[front];

        data[front] = null;
        front = (front + 1) % data.length; //循环队列
        size --;

        if (size * 4 <= getCapacity() && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }

        return ret;
    }

    @Override
    public E getFront() {
        //查询的时候表可能为空
        if (isEmpty()){
            throw new IllegalArgumentException("can not dequeue from an empty queue.");
        }

        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 扩容或缩容
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity + 1]; //需要浪费一个进行判断是否已满
//
//        int newLocation = 0;
//        for (int i = front; i % data.length != tail; i++){
//
//            newData[newLocation] = data[i % data.length];
//            newLocation ++;
//        }
//        front = 0;
//        tail = size;

        for(int i = 0; i < size; i ++){
            newData[i] = data[(i + front) % data.length];
        }
        front = 0;
        tail = size;


        data = newData;
    }


    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append("Queue: size = " + size + ", capacity = " + getCapacity() + "\n");
        res.append("front [");
        for (int i = 0; i < size; i++){
            res.append(data[(i + front) % data.length]);
            if (i != size - 1){
                res.append(", ");
            }
        }

        res.append("] tail");

        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>(2);
        for (int i = 0; i < 5; i ++){
            queue.enqueue(i);
            System.out.println(queue);
        }

        queue.dequeue();
        System.out.println(queue);

        System.out.println(queue.getCapacity());
    }
}
