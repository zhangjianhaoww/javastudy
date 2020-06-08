package bilian.tech.zhang.javastarter.tree;

/**
 * 两个节点的合并器
 *
 * @param <E>
 */
public interface Merger<E> {

    E merge(E val1, E val2);
}
