/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.subcriber;

import com.mycompany.rabbitmq.dao.DAO;

/**
 *
 * @author Vantu
 */
public class Test {
    
    public static void main(String[] args) {
        DAO.init();
        for (int i = 0; i < 1; i++) {
            Subcriber subcriber = new Subcriber("Processor " + i + " :");
            Thread thread = new Thread(subcriber);
            thread.run();
        }
    }
}
