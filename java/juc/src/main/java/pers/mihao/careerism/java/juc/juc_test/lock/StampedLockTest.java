package pers.mihao.careerism.java.juc.juc_test.lock;

import java.util.concurrent.locks.StampedLock;
import util.ScanUtil;
import util.log.Logger;

/**
 * 独写锁 为了优化readWrit 增加了乐观读 与readWritLock不同 读写锁只支持悲观读 意味着写锁不能再有读锁的时候进入 但是读锁可以多个共存
 * <p>
 * 而stamped获取锁的时候如果获取的写锁如果是乐观锁 不影响获取
 *
 * 可以用于对写操做不能有阻塞 读操作没有影响的情况
 *
 * @Author mh32736
 * @Date 2021/8/13 16:42
 */
public class StampedLockTest {

    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();

        final int[] i = {0};

        ScanUtil.runInThread(s -> {
            int v;
            switch (s) {
                case "1":
                    long version = stampedLock.tryOptimisticRead();
                    v = i[0];
                    System.out.println("乐观锁获取值" + i[0]);
                    if (stampedLock.validate(version)) {
                        Logger.info("获取锁成功" + v);
                    } else {
                        Logger.info("数据错误");
                    }
                    break;
                case "2":
                    stampedLock.asReadLock().lock();
                    v = i[0];
                    Logger.info("获取锁成功" + v);
                    stampedLock.asReadLock().unlock();
                    break;
                case "3":
                    stampedLock.asWriteLock().lock();
                    v = (i[0] = i[0] + 1);
                    Logger.info("获取锁成功" + v);
                    stampedLock.asWriteLock().unlock();
                    break;
                default:
                    break;
            }
        });
    }

}
