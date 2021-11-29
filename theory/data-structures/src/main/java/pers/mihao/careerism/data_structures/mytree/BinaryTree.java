package pers.mihao.careerism.data_structures.mytree;

/**
 * BST 二叉树
 *
 * @Author mihao
 * @Date 2021/3/29 11:25
 */
public class BinaryTree<E> extends AbstractTree<E> {

    class BinaryNode extends Node {

        BinaryNode left;
        BinaryNode right;

        public BinaryNode(E value) {
        }
    }
}
