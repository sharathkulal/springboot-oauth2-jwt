/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.oauth2;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Sharath Kulal
 */
@SpringBootApplication
public class Main {
    
    private final static Logger LOG = Logger.getLogger(Main.class);
    
    /*static {
        System.setProperty("server.port", "9010");
    }*/
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        LOG.info("Spring Boot Oauth Started !!! ");
    }
}