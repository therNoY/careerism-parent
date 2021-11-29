package pers.mihao.careerism.java.juc.juc_test.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author mh32736
 * @Date 2021/9/2 16:32
 */
public class MyThreadPoolPlus implements Executor {

    private int coreSize;
    private int maxSize;
    private BlockingQueue<Runnable> blockingQueue;
    private ThreadFactory threadFactory;
    private RejectedExecutionHandler rejectedExecutionHandler;

    private List<Worker> workerList;


    public MyThreadPoolPlus(int coreSize, int maxSize) {
        this(coreSize, maxSize, new LinkedBlockingQueue());
    }

    public MyThreadPoolPlus(int coreSize, int maxSize, BlockingQueue blockingQueue) {
        this(coreSize, maxSize, blockingQueue, new DefaultThreadFactor(), new DefRejectedExecutionHandler());
    }

    public MyThreadPoolPlus(int coreSize, int maxSize, BlockingQueue blockingQueue, ThreadFactory threadFactory,
        RejectedExecutionHandler rejectedExecutionHandler) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.blockingQueue = blockingQueue;
        this.threadFactory = threadFactory;
        this.rejectedExecutionHandler = rejectedExecutionHandler;
    }

    @Override
    public void execute(Runnable command) {
        Worker worker = getWorker();
        if (worker == null) {
            boolean offerRes = blockingQueue.offer(command);
            if (!offerRes && getExtWorker(command) == null) {
                rejectedExecutionHandler.rejectedExecution(command, null);
            }
        } else {
            worker.doWork(command);
        }
    }

    private Worker getExtWorker(Runnable command) {
        if (workerList == null) {
            workerList = new ArrayList<>();
        }
        if (workerList.size() < coreSize) {
            Worker worker = new Worker();
            worker.doWork(command);
            workerList.add(worker);
            return worker;
        }
        return null;
    }

    private Worker getWorker() {
        if (workerList == null) {
            workerList = new ArrayList<>();
        }
        if (workerList.size() < coreSize) {
            Worker worker = new Worker();
            workerList.add(worker);
            return worker;
        }
        return null;
    }

    static class DefaultThreadFactor implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            return null;
        }
    }

    class Worker extends Thread {

        Runnable task;

        @Override
        public void run() {
            task.run();
        }

        public void doWork(Runnable runnable) {
            this.task = runnable;
            this.start();
        }
    }

    static class DefRejectedExecutionHandler implements RejectedExecutionHandler {


        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }


    public static void main(String[] args) {
        Executor executor = new MyThreadPoolPlus(2, 4);
        executor.execute(() -> {

        });
    }
}
