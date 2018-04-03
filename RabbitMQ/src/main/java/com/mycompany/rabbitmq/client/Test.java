/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Vantu
 */
public class Test {
    
    public static void main(String[] args) {
        try {
            RPCClient client = new RPCClient();
            client.call("hello world");
            client.close();
        } catch (IOException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
