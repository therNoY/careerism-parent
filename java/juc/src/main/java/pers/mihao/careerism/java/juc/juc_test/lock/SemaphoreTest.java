package pers.mihao.careerism.java.juc.juc_test.lock;

import java.util.concurrent.Semaphore;
import util.ScanUtil;

/**
 * 信号 支持定义共享次数的共享锁
 * @Author mh32736
 * @Date 2021/8/12 16:12
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        ScanUtil.runInThread(s->{
            if (s.equals("1")) {
                try {
                    semaphore.acquire(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release(1);

            }
        });
    }

}
