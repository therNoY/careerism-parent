package pers.mihao.careerism.java.base.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import util.dto.Dog;

public class ListStream {

    static List<Dog> stringList = new ArrayList<>();

    static {
        stringList.add(new Dog("1"));
        stringList.add(new Dog("2"));
        stringList.add(new Dog("3"));
        stringList.add(new Dog("3"));
        stringList.add(new Dog("5"));
        stringList.add(new Dog("3"));
        stringList.add(new Dog("7"));
        stringList.add(new Dog());
    }

    public static void main(String[] args) {


        stringList.stream().collect(Collectors.groupingBy(s->s.getName()));
    }

}
