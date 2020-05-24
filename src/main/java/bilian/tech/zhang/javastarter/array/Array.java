package bilian.tech.zhang.javastarter.array;

import java.util.ArrayList;

/**
 * 增、删、改、查
 * 泛型
 *动态数组（自动增加容量）
 */
public class Array<E> {

    private final static int DEFAULT_LENGTH = 20;

    private E[] data;
    private int size;

    public Array(int capacity){
        //java 不支持直接创建泛型数组
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array(){
        this(DEFAULT_LENGTH);
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }


    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * O(1)
     * @param e
     */
    public void addLast(E e){

        add(size, e);
    }

    /**
     * O(n)
     */
    public void addFirst(E e){
        add(0, e);
    }

    /**
     * 与index的值有关 O(n)
     *
     * @param index
     * @param e
     */
    public void add(int index, E e){

        if (index <0 || index > size){
            throw new IllegalArgumentException("Add fail, require index >= 0 and index <= size.");
        }

        if (size == data.length){
//            throw new IllegalArgumentException("Add fail, array size is full.");
            resize(2 * data.length);

        }


        for (int i = size-1; i>= index; i--){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append("Array: size = " + size + ", capacity = " + data.length + "\n");
        res.append('[');
        for (int i = 0; i < size; i++){
            res.append(data[i]);
            if (i != size-1){
                res.append(", ");
            }
        }

        res.append(']');

        return res.toString();
    }

    /**
     * O(1)
     *
     * @param index
     * @return
     */
    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("get fail, require index >= 0 and index < size.");
        }
        return data[index];
    }

    public E getLast(){

        return get(size - 1);
    }

    public E getFirst(){

        return get(0);
    }


    /**
     * O(1)
     *
     * @param index
     * @param e
     */
    public void set(int index, E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("get fail, require index >= 0 and index < size.");
        }
        data[index] = e;
    }


    /**
     * O(n)
     *
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0; i < size; i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }


    /**
     * O(n)
     *
     * @param e
     * @return
     */
    public int find(E e){
        for (int i = 0; i < size; i++){
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * O(n)
     *
     * @param index
     * @return
     */
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("get fail, require index >= 0 and index < size.");
        }

        E ret = data[index];
        for (int i = index; i <size - 1; i++){
            data[i] = data[i + 1];
        }
        size --;
        data[size] = null;

        if (size <= data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }

        return ret;
    }

    /**
     * O(n)
     *
     * 数组中重复的话，只删除第一个
     *
     * @param e
     */
    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }


    /**
     * O(n)
     *
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * O(1)
     *
     * @return
     */
    public E  removeLast(){
        return remove(size - 1);
    }


    //removeAllElement
    //getAllElement



    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++){
            newData[i] = data[i];
        }

        data = newData;
    }

}
