package pers.mihao.careerism.java.juc.volatile_test;

public class VolatileTest {

    private static volatile int race = 0;

    public static void add() {
        race = race + 1;
    }


    public static void main(String[] args) {
        String s = "123";
        s.replace(" ", "%20");
    }
}
