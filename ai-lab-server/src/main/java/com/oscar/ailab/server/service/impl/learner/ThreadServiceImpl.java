package com.oscar.ailab.server.service.impl.learner;

import com.alibaba.druid.support.json.JSONUtils;
import com.oscar.ailab.server.service.learner.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.*;

@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {


    @Override
    public void rawThread(int num) {
        try {
            log.info("I will Run a Thread");
//        Thread t = new MyThread("t");
//        Thread t = new Thread(new MyRunnable());
//        Thread t = new Thread(()->{log.info("Thread Run in Lambda");});

//            Thread t = new Thread() {
//                public void run() {
//                    mySleep(30L);
//                    log.info("thread run ...");
//                    log.info("thread end ...");
//                }
//            };
//            t.start(); //线程等待
//            mySleep(1L, "");
//            t.interrupt();
//            log.info("Thread Interrupt");
//            t.join(2);

            Thread addT = new AddThread();
            Thread decT = new DecThread();
            addT.start();
            decT.start();
            addT.join();
            decT.join();

            log.info(Counter.out());

            log.info("I Had Run a Thread");

        } catch (Exception e) {

        }
    }


    @Override
    public void syncThread() {
        TaskQueue task = new TaskQueue();
        Thread getT = new Thread() {
            public void run() {
                int deepSleep = 3;
                while(true) {
                    try {
                        String ret = task.getTask();
                        log.info("getTask value: {}", ret);
                    } catch (InterruptedException e) {

                    }
                }


            }
        };

        Thread addT = new Thread(() -> {
            for(int i=0; i<10; i++) {
                task.addTask(i+"");
                mySleep(Long.valueOf((int)(Math.random() * 100)), "addTask "+ i);
            }
        });


        addT.start();
        getT.start();
    }

    @Override
    public void reentrantLock() {
        int taskNum = 20;
        TaskQueue taskQueue = new TaskQueue();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i<taskNum/2; i++) {
            int tid = i;
            Thread getT = new Thread(() -> {
                while (true) {
                    try {
                        String ret = taskQueue.getTask2();
                        log.info("Get Task: tid: {}, task_ret: {}", tid, ret);
                    } catch (InterruptedException e) {

                    }
                }
            });
            getT.start();
            ts.add(getT);
        }

        try {
            Thread addT = new Thread(() -> {
                for (int j = 0; j < taskNum; j++) {
                    log.info("Set Task: val: {}", "task"+j);
                    taskQueue.addTask2("task" + j);
                    mySleep(Long.valueOf((int) (Math.random() * 100)), "addTask " + j);
                }
            });
            addT.start();
            addT.join();
        } catch (InterruptedException e) {

        }

        mySleep(3000L, "End");
        for(Thread t: ts) {
            t.interrupt();
        }
    }


    @Override
    public void readWriteLock() {
        int num = 10;
        ReadWriteLock readWriteLock = new ReadWriteLock();
        for (int i=0; i<num; i++) {
            int ii = i;
            Thread incT = new Thread(() -> {
                log.info("Inc: val {}", ii);
                readWriteLock.inc(ii);
            });

            Thread getT = new Thread(() -> {
                int[] ret = readWriteLock.get();
                log.info("Get: ret, {}", JSONUtils.toJSONString(ret));
            });

            incT.start();
            mySleep(10L, "aa");
            getT.start();
        }



    }

    private void mySleep(Long t, String flag) {
        try {
            Thread.sleep(t);
            log.info("My Sleep ... from: {}, time; {}", flag, t);
        } catch (InterruptedException e) {

        }
    }
}

@Slf4j
class StampedLockDemo {
    private final StampedLock lock = new StampedLock();
    private double x;
    private double y;

    public void move (double dx, double dy) {
        long stamp = lock.writeLock();
        try {
            x += dx;
            y += dy;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public double calDistance() {
        long stamp = lock.tryOptimisticRead();
        double cx = x;
        double cy = y;

        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                cx = x;
                cy = y;
            } finally {
                lock.unlock(stamp);
            }
        }
        return Math.sqrt(cx*cx + cy*cy);
    }
}

@Slf4j
class ReadWriteLock {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock rLock = rwLock.readLock();
    private final Lock wLock = rwLock.writeLock();
    private int[] counts = new int[10];

    public void inc(int index) {
        log.info("index: {}", index);
        wLock.lock();
        try {
            counts[index] = index;
        } finally {
            wLock.unlock();
        }
    }

    public int[] get() {
        rLock.lock();
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            rLock.unlock();
        }
    }
}

@Slf4j
class TaskQueue {
    Queue<String> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notifyAll();
    }

    public void addTask2(String s) {
        lock.lock();
        try {
            this.queue.add(s);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public synchronized String getTask() throws InterruptedException{

        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.remove();
    }

    public String getTask2() throws InterruptedException{
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }
}

@Slf4j
class MyRunnable implements Runnable {
    @Override
    public void run() {
        log.info("My Runnable Run");
    }
}

@Slf4j
class MyThread extends Thread {
    public volatile boolean running = true;
    private String f;
    MyThread(String f) {
        this.f = f;
    }

    @Override
    public void run() {
        int n = 0;
        while (n < 200) {
            n++;
            log.info("My Thread Run "+n);
        }
        log.info("My Thread End: "+n);
    }
}

@Slf4j
class AddThread extends Thread {
    public void run() {
        synchronized (Counter.lock) {
            for (int i=0; i<10000; i++) {
                Counter.c += 1;
            }
        }
//        log.info("Add: do");
//        for (int i=0; i<10000; i++) {
//            Counter.c += 1;
//        }
    }
}

@Slf4j
class DecThread extends Thread {
    public void run() {
        synchronized (Counter.lock) {
            for(int i=0; i<10000; i++) {
                Counter.c -= 1;
            }
        }
//        log.info("Dec: do");
//        for(int i=0; i<10000; i++) {
//            Counter.c -= 1;
//        }
    }
}


class Counter {
    public static final Object lock = new Object();
    public static int c = 100;

    public static String out() {
        return "Ret: "+c;
    }
}
