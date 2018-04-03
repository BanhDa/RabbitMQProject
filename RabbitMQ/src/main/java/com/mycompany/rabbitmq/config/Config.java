/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.config;

/**
 *
 * @author Vantu
 */
public class Config {

    public static final String RABBITMQ_HOST = "localhost";
    
    public static final String QUEUE_NAME = "queue.test";
    public static final String RPC_QUEUE_NAME = "rpc_queue";
    
    public static final String RPC_EXCHANGE_NAME = "rpc_exchange";
    public static final String FANOUT_EXCHANGE_NAME = "fanout_exchange";
    public static final String DIRECT_EXCHANGE_NAME = "direct_exchange";
    public static final String HEADERS_EXCHANGE_NAME = "header_exchange";
    public static final String TOPIC_EXCHANGE_NAME = "topic_exchange";
}
