/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.publisher;

import com.mycompany.rabbitmq.config.Config;
import com.mycompany.rabbitmq.entities.Request;
import com.mycompany.rabbitmq.util.JsonUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *
 * @author Vantu
 */
public class Publisher {
    
    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(Config.RABBITMQ_HOST);
            
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.queueDeclare(Config.QUEUE_NAME, false, false, false, null);
            
            for (int i = 0; i < 6000; i++) {
                Request request = new Request("request " + i);
                channel.basicPublish("", Config.QUEUE_NAME, null, JsonUtil.toJson(request).getBytes());
                Thread.sleep(10);
            }
            
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
