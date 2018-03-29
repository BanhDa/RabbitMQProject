/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.subcriber;

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
    
    public Processor(Channel channel) {
        super(channel);
    }
    
    @Override
    public void handleDelivery(String consumerTag, Envelope evEnvelope, AMQP.BasicProperties properties, byte[] body) throws IOException{
        String message = new String(body, "UTF-8");
        System.out.println("receive message: " + message );
        System.out.println("consumerTag : " + consumerTag );
        System.out.println("evEnvelope : " + evEnvelope );
        System.out.println("BasicProperties : " + properties );
    }
}
