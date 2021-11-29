package pers.mihao.careerism.java.jvm.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author mh32736
 * @Date 2021/8/5 9:26
 */
public class VolatileTest3 {


    static class Work {

        AtomicInteger atomicInteger = new AtomicInteger(0);

        volatile boolean isShutDown = false;

        void shutdown() {
            isShutDown = true;
            int a = atomicInteger.incrementAndGet();
            System.out.println("shutdown!" + a);
        }

        void doWork() {
            while (!isShutDown) {
                int a = atomicInteger.incrementAndGet();
                System.out.println("doWork" + a);
            }
        }
    }

    public static void main(String[] args) {
        Work work = new Work();

        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::shutdown).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
        new Thread(work::doWork).start();
    }
}
