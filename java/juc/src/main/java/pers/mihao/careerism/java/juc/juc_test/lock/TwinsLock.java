package pers.mihao.careerism.java.juc.juc_test.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * 对共享锁简单实用同时有两个可以进入 参考Semaphore
 * @author hspcadmin
 */
public class TwinsLock implements Lock {

    static Sync sync = new Sync();

    static class Sync extends AbstractQueuedSynchronizer {

        Sync() {
            setState(2);
        }

        /**
         * 获取共享锁的时候会首先获取tryAcquireShared 如果返回的值大于0表示获取锁成功
         * @param reduceCount
         * @return
         */
        @Override
        protected int tryAcquireShared(int reduceCount) {
            int state = getState();
            int newState = state - reduceCount;
            if (newState >= 0 && compareAndSetState(state, newState)) {
                return newState;
            }
            return -1;
        }

        @Override
        protected boolean tryReleaseShared(int returnState) {
            int state = getState();
            int newCount = state + returnState;
            if (compareAndSetState(state, newCount)) {
                return true;
            }
            return false;
        }
    }


    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
