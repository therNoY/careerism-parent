package pers.mihao.careerism.data_structures.mytree;

import java.util.Comparator;

/**
 * 数的基本操做 不一定是二叉数
 *
 * @param <T>
 * @author hspcadmin
 */
public abstract class AbstractTree<T> implements Tree<T> {

    /**
     * 根节点
     */
    Node root = null;

    /**
     * 大小
     */
    int size = 0;

    Comparator<T> comparator = null;

    /**
     * 默认叶子节点的数量
     */
    int defaultLeafNum = 2;

    @Override
    public void insert(T t) {
        Node node = newNode(t);
    }


    public int compareNode(T t1, T t2) {
        if (comparator != null) {
            return comparator.compare(t1, t2);
        } else if (t1 instanceof Comparable) {
            Comparable c1 = (Comparable) t1;
            Comparable c2 = (Comparable) t2;
            return c1.compareTo(c2);
        }
        throw new RuntimeException("不能比较");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(T t) {

    }

    @Override
    public void show() {
        printNode(root);
    }

    public void printNode(Node node) {
        System.out.print(node.value);
        if (node.nodes != null) {
            for (Node n : root.nodes) {
                printNode(n);
            }
        }
    }

    @Override
    public T search(T t) {
        return null;
    }

    static class Node<T> {
        T value;
        Node[] nodes;
    }

    Node newNode(T t) {
        Node node = new Node();
        node.value = t;
        node.nodes = new Node[defaultLeafNum];
        return node;
    }


    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }
}
