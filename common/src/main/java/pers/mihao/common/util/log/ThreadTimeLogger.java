package pers.mihao.common.util.log;

import java.time.LocalDateTime;

public class ThreadTimeLogger extends AbstractLogger {

    @Override
    protected String beforePrint(String info) {
        String name = Thread.currentThread().getName();
        return "[ " + name + " ]" + " " + "[" + LocalDateTime.now() + "]" + info;
    }

}
