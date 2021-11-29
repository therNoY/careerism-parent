package pers.mihao.careerism.java.juc.juc_test.thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import util.log.Logger;

/**
 * @Author mh32736
 * @Date 2021/9/16 13:43
 */
public class NewScheduledThreadPoolTest {

    static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    public static void main(String[] args) {
//        定时重复执行();

        延迟执行();

    }

    private static void 延迟执行() {
        scheduledExecutorService.schedule(()->{
            Logger.info("定时任务");
        }, 5, TimeUnit.SECONDS);
    }

    private static void 定时重复执行() {
        scheduledExecutorService.scheduleWithFixedDelay(()->{
            Logger.info("定时任务");
        }, 5000, 5000, TimeUnit.MILLISECONDS);
    }


}
