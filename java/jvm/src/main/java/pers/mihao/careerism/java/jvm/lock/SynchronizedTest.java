package pers.mihao.careerism.java.jvm.lock;

import org.openjdk.jol.info.ClassLayout;
import pers.mihao.common.util.log.Log;

/**
 * 测试 偏向锁和轻量锁
 * 对象头信息查看说明 https://www.cnblogs.com/LemonFive/p/11246086.html 64位机器 markword 64bits 8 byts
 *
 * 通过代码运行结果
 * 1. 对象初始创建锁状态为可以偏向 **0101 未偏向
 * 2. 有线程对对象加锁的时候锁状态未 偏向 **0101 已经偏向 且同一个线程多次加锁都不会改变偏向结果
 * 3. 当锁状态为偏向锁 锁被释放仍然是偏向锁 **0101
 * 4. 当有其他线程对偏向锁加锁的时候会膨胀成轻量锁 ** 0000
 * 5. 其他线程对轻量锁释放后为无锁不可偏向 ** 0001
 * 6. 不可偏向锁的对象再次加锁会直接成为轻量锁 ** 0000
 *
 * @Author mh32736
 * @Date 2021/8/5 9:16
 */
public class SynchronizedTest {

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(5000);
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        String info;

//        info = ClassLayout.parseInstance(synchronizedTest).toPrintable();
//        Log.info("无锁状态\n" + info);

        info = ClassLayout.parseInstance(synchronizedTest).toPrintable();
        Log.info("延迟5s查看锁可偏向\n" + info);

        synchronized (synchronizedTest) {
            info = ClassLayout.parseInstance(synchronizedTest).toPrintable();
            Log.info("加锁一次查看\n" + info);
        }

        info = ClassLayout.parseInstance(synchronizedTest).toPrintable();
        Log.info("主线程释放锁\n" + info);

        synchronized (synchronizedTest) {
            info = ClassLayout.parseInstance(synchronizedTest).toPrintable();
            Log.info("加锁两次查看\n" + info);
        }

        info = ClassLayout.parseInstance(synchronizedTest).toPrintable();
        Log.info("主线程释放锁两次\n" + info);

        Thread thread1 = new Thread(() -> {
            synchronized (synchronizedTest) {
                Log.info("线程1加锁 偏向锁改成轻量锁" + ClassLayout.parseInstance(synchronizedTest).toPrintable());
            }
        });

        thread1.start();
        thread1.join();

        info = ClassLayout.parseInstance(synchronizedTest).toPrintable();
        Log.info("线程1释放后查看\n" + info);

        synchronized (synchronizedTest) {
            info = ClassLayout.parseInstance(synchronizedTest).toPrintable();
            Log.info("主线程再次加锁查看\n" + info);
        }



    }

}
