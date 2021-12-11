package pers.mihao.careerism.data_structures.util;

import java.lang.reflect.Method;

/**
 * @Author mh32736
 * @Date 2021/12/8 17:19
 */
public class RunUtil {

    public static void run(Class clazz, Object... args) {
        Method[] methods = clazz.getDeclaredMethods();
        try {
            Object clazzObj = clazz.newInstance();
            Object res = methods[0].invoke(clazzObj, args);
            System.out.println("执行方法" + methods[0].getName() + "结果：" + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
