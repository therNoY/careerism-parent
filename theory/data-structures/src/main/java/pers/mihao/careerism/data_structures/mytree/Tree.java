package pers.mihao.careerism.data_structures.mytree;


/**
 * 树的基本抽象类
 * @author hspcadmin
 */
public interface Tree<T> {

    void insert(T t);

    void remove(T t);

    void show();

    T search(T t);

    int size();
}
