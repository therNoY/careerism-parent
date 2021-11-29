package pers.mihao.careerism.java.base.map.entrySet;

import design_patterns.creater_type.prototype.Dog;
import java.util.Map;
import java_base.map.MyHashMap;

/**
 * @Author mihao
 * @Date 2021/7/5 16:51
 */
public class TestEntrySet {

    public static void main(String[] args) {
        MyHashMap<String, Dog> map = new MyHashMap<>(5);
        int length = 20;
        for (int i = 0; i < length; i++) {
            Dog dog = new Dog("大黄" + i, 1, i);
            map.put(dog.getName(), dog);
        }

        for (Map.Entry<String, Dog> entry : map.entrySet()) {
            map.remove(entry.getKey());
        }

    }

}
