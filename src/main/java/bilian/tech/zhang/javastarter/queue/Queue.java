package bilian.tech.zhang.javastarter.queue;

/**
 * queue
 *  队列 先进先出
 *
 *  广度优先遍历
 *
 * @param <E>
 */
public interface Queue<E> {

    /**
     * 插入
     *
     * @param e
     */
    void enqueue(E e);

    /**
     * 取出
     *
     * @return
     */
    E dequeue();

    /**
     * 获取队首元素
     *
     * @return
     */
    E getFront();

    /**
     * 队列的size
     *
     * @return
     */
    int getSize();

    /**
     * 队列是否为空
     *
     * @return
     */
    boolean isEmpty();
}
