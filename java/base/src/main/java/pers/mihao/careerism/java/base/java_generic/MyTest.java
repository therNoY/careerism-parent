package pers.mihao.careerism.java.base.java_generic;

public class MyTest {

    public <T> void test (T t) {
        System.out.println(t.getClass().getName());
    }
}
