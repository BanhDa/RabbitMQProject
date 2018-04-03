/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.subcriber;

import com.mycompany.rabbitmq.subcriber.processor.Processor;
import com.mycompany.rabbitmq.config.Config;
import com.mycompany.rabbitmq.util.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Vantu
 */
public class Subcriber implements Runnable{
    
    private String name;
    
    public Subcriber(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(Config.RABBITMQ_HOST);
            
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.queueDeclare(Config.RPC_QUEUE_NAME, false, false, false, null);
            channel.basicQos(1);
            
//            channel.exchangeDeclare(Config.RPC_EXCHANGE_NAME, Constant.EXCHANGE.TOPIC);
//            
//            channel.queueDeclareNoWait(Config.RPC_QUEUE_NAME, false, false, false, null);
//            channel.queueBind(Config.RPC_QUEUE_NAME, Config.RPC_EXCHANGE_NAME, "request");
            
            Processor processor = new Processor(channel, name);
            channel.basicConsume(Config.RPC_QUEUE_NAME, true, processor);
            while (true) {
                synchronized(processor) {
                    try {
                        processor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        
    }
}
