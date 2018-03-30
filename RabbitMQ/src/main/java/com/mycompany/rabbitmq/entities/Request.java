/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.entities;

/**
 *
 * @author Vantu
 */
public class Request {
    
    private String request;
    private Long time;
    
    public Request(String request) {
        this.request = request;
        this.time = System.currentTimeMillis();
    }
    
    public String getRequest() {
        return request;
    }
    
    public void setRequest(String request) {
        this.request = request;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Request{" + "request=" + request + ", time=" + time + '}';
    }
    
}
