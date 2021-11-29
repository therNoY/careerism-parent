package pers.mihao.careerism.java.juc.juc_test.thread_pool;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {


        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(100);
            System.out.println("执行");
            return UUID.randomUUID().toString();
        });

        futureTask.run();
        System.out.println(futureTask.get());

        Thread.sleep(Integer.MAX_VALUE);
    }
}
