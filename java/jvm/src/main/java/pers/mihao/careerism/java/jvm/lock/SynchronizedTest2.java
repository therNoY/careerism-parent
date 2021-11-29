package pers.mihao.careerism.java.jvm.lock;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.jol.info.ClassLayout;
import pers.mihao.common.util.log.Log;

/**
 * 测试重偏向 对象头信息查看说明 https://www.cnblogs.com/LemonFive/p/11246086.html 64位机器 markword 64bits 8 byts
 * <p>
 * 批量冲偏向参数是 默认20 修改如下
 * <p>
 * -XX:BiasedLockingBulkRebiasThreshold=2
 * <p>
 * 当一个类的对象被偏向 20此 JVM会考虑偏向的对象可能错误 会重偏向
 * -XX:BiasedLockingBulkRevokeThreshold=2
 * <p>
 * 当重偏向或者升级为轻量锁的对象多的时候 会批量撤销 不会冲偏向 直接升级轻量锁 ，后续新建的对象都是不可偏向
 *
 * @Author mh32736
 * @Date 2021/8/5 9:16
 */
public class SynchronizedTest2 {

    public static void main(String[] args) throws Exception {
        //延时产生可偏向对象
        Thread.sleep(5000);

        //创造10个偏向线程t1的偏向锁
        List<SynchronizedTest> listA = new ArrayList<>();
        ClassLayout.parseInstance(listA).toPrintable();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                SynchronizedTest a = new SynchronizedTest();
                synchronized (a) {
                    listA.add(a);
                }
            }
            Log.info("打印t1线程，list中第10个对象的对象头：");
            Log.info((ClassLayout.parseInstance(listA.get(9)).toPrintable()));
            try {
                //为了防止JVM线程复用，在创建完对象后，保持线程t1状态为存活
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        //睡眠3s钟保证线程t1创建对象完成
        Thread.sleep(3000);
        //创建线程t2竞争线程t1中已经退出同步块的锁
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 8; i++) {
                SynchronizedTest a = listA.get(i);
                synchronized (a) {
                    Log.info("第" + (i + 1) + "次重偏向结果");
                    Log.info((ClassLayout.parseInstance(a).toPrintable()));
                }
            }
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        Thread.sleep(3000);
        for (int i = 0; i < 10; i++) {
            SynchronizedTest a = listA.get(i);
            Log.info("第" + (i + 1) + "个对象头信息");
            Log.info((ClassLayout.parseInstance(a).toPrintable()));
        }


        Thread t3 = new Thread(() -> {
            for (int i = 6; i < 9; i++) {
                SynchronizedTest a = listA.get(i);
                synchronized (a) {
                    Log.info("第" + (i + 1) + "个对象头信息");
                    Log.info((ClassLayout.parseInstance(a).toPrintable()));
                }
            }
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();

        Thread.sleep(3000);

        for (int i = 0; i < 5; i++) {
            SynchronizedTest a = new SynchronizedTest();
            synchronized (a) {
                listA.add(a);
            }
        }

        for (int i = 0; i < 15; i++) {
            SynchronizedTest a = listA.get(i);
            Log.info("第" + (i + 1) + "个对象头信息");
            Log.info((ClassLayout.parseInstance(a).toPrintable()));
        }
    }

}
