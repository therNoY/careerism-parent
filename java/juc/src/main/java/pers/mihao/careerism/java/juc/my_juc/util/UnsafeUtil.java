package pers.mihao.careerism.java.juc.my_juc.util;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeUtil {

    public static Unsafe getUnsafe() {
        try {
            Field unsafeStaticField =
                    Unsafe.class.getDeclaredField("theUnsafe");
            unsafeStaticField.setAccessible(true);
            return (Unsafe) unsafeStaticField.get(Unsafe.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
