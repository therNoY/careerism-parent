package pers.mihao.careerism.java.jvm.lock;

import org.openjdk.jol.info.ClassLayout;
import pers.mihao.common.util.log.Log;

/**
 * 测试 偏向锁和重量锁
 * 对象头信息查看说明 https://www.cnblogs.com/LemonFive/p/11246086.html 64位机器 markword 64bits 8 byts
 *
 * 通过代码运行结果
 * 1. 存在锁竞争的时候直接膨胀成重量锁 之后都不可偏向
 *
 * @Author mh32736
 * @Date 2021/8/5 9:16
 */
public class SynchronizedTest1 {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        SynchronizedTest a = new SynchronizedTest();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread1 locking");
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                    try {
                        //让线程晚点儿死亡，造成锁的竞争
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                    System.out.println("thread2 locking");
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.start();
        thread2.start();


        Thread.sleep(6000);
        System.out.println("所有锁释放主线程查看：\n"  + ClassLayout.parseInstance(a).toPrintable());
        synchronized (a){
            System.out.println("thread2 locking");
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
