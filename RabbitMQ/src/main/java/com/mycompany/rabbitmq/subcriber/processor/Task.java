/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.subcriber.processor;

import com.mycompany.rabbitmq.dao.DAO;
import com.mycompany.rabbitmq.util.JsonUtil;

/**
 *
 * @author Vantu
 */
public class Task implements Runnable{
    String message;
        
    public Task(String message) {
        this.message = message;
    }
        
    @Override
    public void run() {
        System.out.println("queue size: " + Executor.getQueueSize());

        Long time = JsonUtil.getLongParam(message, "time");
        Long startTime = System.currentTimeMillis();
        DAO.save(message);
        long currentTime = System.currentTimeMillis();
        System.out.println("time save db : " + (currentTime - startTime));
        
        currentTime = System.currentTimeMillis();
        System.out.println("time : " + (currentTime - time));
        System.out.println("***************************");
        System.out.println("");
    }
}
