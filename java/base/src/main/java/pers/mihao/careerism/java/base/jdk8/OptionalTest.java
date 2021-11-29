package pers.mihao.careerism.java.base.jdk8;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {

        String s = null;

        Map<String, String> map = new HashMap<>();

        /**
         * map & orELse
         * map 誓不为空的映射
         */
        // 如果不为空就执行里面的方法 为空返回222
        Optional.ofNullable(s).map((s1 -> s1 + "111")).orElse("222");
        s = "00";
        // 不为空执行里面的方法
        Optional.ofNullable(s).map((s1 -> s1 + "111")).orElse("222");

        s = null;

        System.out.println(Optional.ofNullable(s).flatMap((s1 -> {
            System.out.println(s1);
            return Optional.ofNullable(map.get(s1));
        })).orElse("null"));


        s = "111";

        System.out.println(Optional.ofNullable(s).flatMap((s1 -> {
            System.out.println(s1);
            return Optional.ofNullable(map.get(s1));
        })).orElse("null"));

        map.put(s, "aa");

        System.out.println(Optional.ofNullable(s).flatMap((s1 -> {
            System.out.println(s1);
            return Optional.ofNullable(map.get(s1));
        })).orElse("null"));


    }

    public void test1(){
        System.out.println("为空");
        String mess = null;
    }

}
