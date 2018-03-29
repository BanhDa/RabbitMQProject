/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.subcriber.processor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Vantu
 */
public class Executor {
    private static final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue(40);
    private static final ThreadPoolExecutor EXECUTORS = new ThreadPoolExecutor(4, 100, 60, TimeUnit.SECONDS, queue);
    
    public static int getQueueSize() {
        return queue.size();
    }
    
    public static void put(Runnable runnable) {
        System.out.println("number thread run:" + EXECUTORS.getActiveCount());
        System.out.println("number thread in pool:" + EXECUTORS.getPoolSize());
        System.out.println("number thread have schedule:" + EXECUTORS.getTaskCount());
        if (runnable != null) {
            EXECUTORS.execute(runnable);
        }
    }
}
