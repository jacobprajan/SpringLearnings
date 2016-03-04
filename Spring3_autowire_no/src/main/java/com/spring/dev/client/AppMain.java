package com.spring.dev.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dev.domain.Driver;

public class AppMain
{
	public static void main(String args[])
	{

		AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
		 
        //autowire=default
        Driver driver = (Driver)context.getBean("driver");
        System.out.println("Driver Details : "+driver);

	}
}
