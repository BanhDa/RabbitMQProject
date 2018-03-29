/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.subcriber;

import com.mycompany.rabbitmq.subcriber.processor.Processor;
import com.mycompany.rabbitmq.config.Config;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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
            
            channel.queueDeclare(Config.QUEUE_NAME, false, false, false, null);
            
            Processor processor = new Processor(channel, name);
            channel.basicConsume(Config.QUEUE_NAME, true, processor);
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
