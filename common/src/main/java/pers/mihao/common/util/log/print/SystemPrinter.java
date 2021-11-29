package pers.mihao.common.util.log.print;

import java.io.PrintStream;

/**
 * @Author mh32736
 * @Date 2021/11/26 14:38
 */
public class SystemPrinter extends PrinterDelegate{

    public SystemPrinter() {
        super(null);
    }

    public SystemPrinter(Printer printer) {
        super(printer);
    }

    @Override
    public void println(Object obj) {
        super.println(obj);
        System.out.println(obj == null ? "" : obj.toString());
    }

    @Override
    public PrintStream getPrintStream() {
        return System.out;
    }
}
