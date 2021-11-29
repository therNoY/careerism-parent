package pers.mihao.careerism.java.juc.juc_test.utils.countdown_test;

import java.util.concurrent.CountDownLatch;
import util.log.Logger;

/**
 * @Author mihao
 * @Date 2020/11/14 9:36
 */
public class CountDownTest {

    public static void main(String[] args) throws InterruptedException {

        int size = 10;


        Logger.info("开始");
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    Thread.sleep(finalI * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Logger.info("睡眠结束");
                countDownLatch.countDown();
            }).start();
        }
        System.out.println(countDownLatch.getCount());
        countDownLatch.await();
        System.out.println(countDownLatch.getCount());
        Logger.info("end");
    }

}
