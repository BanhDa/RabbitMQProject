/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.util;

/**
 *
 * @author Vantu
 */
public class Validator {
    
    public static boolean validateString(String arg) {
        return arg != null && !arg.trim().equals("");
    }
    
    public static boolean validateStrings(String... args) {
        
        for (String arg : args) {
            if (!validateString(arg)) {
                return false;
            }
        }
        
        return true;
    }
}
