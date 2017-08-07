/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Sharath Kulal
 */
@SpringBootApplication
public class Main {
    
    /*static {
        System.setProperty("server.port", "9010");
    }*/
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Spring Boot Started !!! ");
        
    }
}