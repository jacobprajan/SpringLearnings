package com.spring.dev.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.spring.dev.config.AppConfig;
import com.spring.dev.domain.Driver1;
import com.spring.dev.domain.Driver2;
import com.spring.dev.domain.Driver3;

public class AppMain
{
	public static void main(String args[])
	{
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		Driver1 driver1 = (Driver1) context.getBean("driver1");
		System.out.println("Driver1 Details : " + driver1);
		
		Driver2 driver2 = (Driver2) context.getBean("driver2");
		System.out.println("Driver2 Details : " + driver2);
		
		Driver3 driver3 = (Driver3) context.getBean("driver3");
		System.out.println("Driver3 Details : " + driver3);
	}
}
