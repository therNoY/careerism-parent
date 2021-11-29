package pers.mihao.common.util.log.print;

import java.io.PrintStream;

/**
 * @Author mh32736
 * @Date 2021/11/26 14:39
 */
public class PrinterDelegate implements Printer{

    Printer extPrinter;

    public PrinterDelegate(Printer printer) {
        extPrinter = printer;
    }

    @Override
    public void println(Object obj) {
        if (extPrinter != null) {
            extPrinter.println(obj == null ? "" : obj.toString());
        }
    }

    @Override
    public PrintStream getPrintStream() {
        return null;
    }
}
