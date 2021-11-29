package pers.mihao.common.util.log;


import pers.mihao.common.util.log.print.Printer;
import pers.mihao.common.util.log.print.SystemPrinter;

public class AbstractLogger implements Logger {

    Printer printer = new SystemPrinter();

    @Override
    public void info(Object... objs) {
        println(getPrefix() + " [info] ", objs);
    }

    protected void println(String prefix, Object[] objs) {
        if (objs.length == 0) {
            return;
        }
        Object mainInfo = objs[0];
        int argsIndex = 1;
        if (mainInfo != null) {
            String mainInfoStr = prefix + mainInfo.toString();
            Object arg;
            while (mainInfoStr.contains("{}") && objs.length > argsIndex) {
                arg = objs[argsIndex++];
                mainInfoStr = mainInfoStr.replaceFirst("\\{\\}", arg == null ? "" : arg.toString());
            }
            printer.println(beforePrint(mainInfoStr));
        }
        for (int i = argsIndex; i < objs.length; i++) {
            if (objs[i] instanceof Throwable) {
                Throwable throwable = (Throwable) objs[i];
                throwable.printStackTrace(printer.getPrintStream());
            }
        }
    }

    protected String beforePrint(String info) {
        return info;
    }

    @Override
    public void error(Object... objs) {
        println(getPrefix() + " [error] ", objs);
    }

    @Override
    public void warn(Object... objs) {
        println(getPrefix() + " [warn] ", objs);
    }

    /**
     * 获取前缀
     *
     * @return
     */
    String getPrefix() {
        return "";
    }
}
