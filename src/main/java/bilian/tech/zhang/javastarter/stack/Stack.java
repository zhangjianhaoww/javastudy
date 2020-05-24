package bilian.tech.zhang.javastarter.stack;

public interface Stack<E> {

    /**
     * 入栈
     *
     * @param e
     */
    public void push(E e);

    /**
     * 出栈
     *
     * @return
     */
    public E pop();

    /**
     * 查询栈顶元素
     *
     * @return
     */
    public E peek();

    /**
     * 获取栈内元素个数
     *
     * @return
     */
    public int getSize();

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty();
}
