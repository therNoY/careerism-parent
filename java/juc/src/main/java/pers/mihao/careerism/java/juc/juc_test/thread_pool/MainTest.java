package pers.mihao.careerism.java.juc.juc_test.thread_pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MainTest {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(
            2,
            4,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            null,
            null);
        myThreadPool.execute(()->{
            System.out.println("1");
        });
    }
}
