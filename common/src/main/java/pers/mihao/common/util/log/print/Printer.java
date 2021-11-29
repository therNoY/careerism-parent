package pers.mihao.common.util.log.print;

import java.io.PrintStream;

/**
 * @Author mh32736
 * @Date 2021/11/26 14:37
 */
public interface Printer {

    /**
     * 打印
     * @param obj
     */
    void println(Object obj);

    /**
     * 获取流
     * @return
     */
    PrintStream getPrintStream();

}
