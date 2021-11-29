package pers.mihao.careerism.java.juc.juc_test.lock;

import java.util.concurrent.locks.Lock;
import util.ScanUtil;
import util.log.Logger;

public class TwinsLockTest {
    static Lock lock = new TwinsLock();

    public static void main(String[] args) {
//        inputTest();
        test1();
    }

    private static void inputTest() {
        ScanUtil.runInThread(s->{
            try {
                lock.lock();
            } catch (Exception e) {
                e.printStackTrace();
            }
            lock.unlock();

        });
    }

    private static void test1() {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
                Thread thread = new Thread(() -> {
                    try {
                        lock.lock();
                        Logger.info("处理事情....");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Logger.info("处理事情END....");
                        lock.unlock();
                    }
                });

            threads[i] = thread;
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }


}
