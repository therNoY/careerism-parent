package pers.mihao.common.util.log;

/**
 * @Author mh32736
 * @Date 2021/11/26 14:27
 */
public interface Logger {

    /**
     * info
     * @param objs
     */
    void info(Object... objs);


    /**
     * error
     * @param objs
     */
    void error(Object... objs);

    /**
     * warn
     * @param objs
     */
    void warn(Object... objs);

}
