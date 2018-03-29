/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.subcriber.processor;

import com.mycompany.rabbitmq.util.JsonUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;

/**
 *
 * @author Vantu
 */
public class Processor extends DefaultConsumer{
    
    private String name;
    
    public Processor(Channel channel, String name) {
        super(channel);
        this.name = name;
    }
    
    @Override
    public void handleDelivery(String consumerTag, Envelope evEnvelope, AMQP.BasicProperties properties, byte[] body) throws IOException{
        
        String message = new String(body, "UTF-8");
        System.out.println(name);
        System.out.println("receive message: " + message );
        long currentTime = System.currentTimeMillis();
        Long time = JsonUtil.getLongParam(message, "time");
        System.out.println("receive time : " + (currentTime - time));
        Task task = new Task(message);
        Executor.put(task);
    }
    
}
