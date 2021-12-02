package pers.mihao.careerism.data_structures.util;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author mihao
 * @Date 2020/11/23 10:40
 */
public class ArraysUtil {

    public static void main(String[] args) {
        boolean[][] dp = new boolean[5][4];
        printArray(dp);
    }

    public static void printArray(boolean[][] booleans) {
        Arrays.stream(booleans).forEach(a->{
            System.out.println(Arrays.toString(a)+"\n");
        });
    }

    public static void printArray(int[][] booleans) {

    }

    public static void printArray(String[][] booleans) {

    }

    public static void printArray(Array array, int maxLength) {



    }

    static class Array {

        Object object;

        public Array(Object object) {
            this.object = object;
        }


        // 维度
        int getDimension() {
            return 0;
        }
    }


}
