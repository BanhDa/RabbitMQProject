/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.factory;

import com.mycompany.rabbitmq.config.Config;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Vantu
 */
public class RabbitMQConnectionFactory {
    
    private static final ConnectionFactory FACTORY = new ConnectionFactory();
    private static Connection CONNECTION = null;
    
    private static final RabbitMQConnectionFactory INSTANCE = new RabbitMQConnectionFactory();
    
    static {
        FACTORY.setHost(Config.RABBITMQ_HOST);
    }
    
    public static void connect() throws IOException, TimeoutException {
        if (CONNECTION != null) {
            CONNECTION.close();
        }
        CONNECTION = FACTORY.newConnection();
    }
    
    public static Connection getConnection() throws IOException, TimeoutException {
        if (CONNECTION == null) {
            connect();
        }
        return CONNECTION;
    }
    
    public static RabbitMQConnectionFactory getInstance() {
        return INSTANCE;
    }
    
    public Channel createChannel() throws IOException, TimeoutException {
        if (CONNECTION == null) {
            connect();
        }
        
        return null;
    }
    
}
