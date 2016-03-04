package com.spring.dev.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class AppMain {
     
    public static void main(String args[]){
        AbstractApplicationContext  context = new ClassPathXmlApplicationContext("app-config.xml");
        //Sets the active profiles
        context.getEnvironment().setActiveProfiles("Development");
        /*
         * Perform any logic here
         */

        
        context.close();
    }
 
}