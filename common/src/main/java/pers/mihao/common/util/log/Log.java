package pers.mihao.common.util.log;

public class Log {

    private static Logger logger;

    static {
        logger = new ThreadTimeLogger();
    }

    public static void info(Object... objs) {
        logger.info(objs);
    }

    public static void error(Object... objs) {
        logger.error(objs);
    }

    public static void main(String[] args) {
        Log.info("123{}", 45, new RuntimeException());
    }
}
