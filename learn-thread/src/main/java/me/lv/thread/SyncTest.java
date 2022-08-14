package me.lv.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author plume
 * @date 2022/4/7 11:46
 */
@Slf4j
public class SyncTest {
    public static List<Thread> list = new ArrayList<>();
    public static Object lock = new Object();
    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                synchronized (lock) {
//                reentrantLock.lock();
                    log.info("thread executed");
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    reentrantLock.unlock();
                }
            }, "t" + i);
            list.add(thread);
        }

        synchronized (lock) {
//        reentrantLock.lock();
            for (Thread thread : list) {
                log.info("thread 启动 {}", thread.getName());
                thread.start();
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            reentrantLock.unlock();
        }
    }
}
