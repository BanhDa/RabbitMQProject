/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.subcriber;

/**
 *
 * @author Vantu
 */
public class Test {
    
    public static void main(String[] args) {        
        for (int i = 1; i < 2; i++) {
            Subcriber subcriber = new Subcriber("Processor " + i + i + i+ i+ i+ i+ i+ i+ i+ i+ i+ i+ i+ i+ i+ i+ i + " :");
            Thread thread = new Thread(subcriber);
            thread.run();
        }
    }
}
