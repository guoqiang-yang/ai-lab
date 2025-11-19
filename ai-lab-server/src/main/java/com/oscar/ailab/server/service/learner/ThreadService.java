package com.oscar.ailab.server.service.learner;

public interface ThreadService {

    void rawThread(int num);


    void syncThread();

    void reentrantLock();

    void readWriteLock();
}
