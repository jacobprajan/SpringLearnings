package com.spring.dev.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.spring.dev.config.AppConfig;
import com.spring.dev.domain.Driver1;

public class AppMain
{
	public static void main(String args[])
	{
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		Driver1 driver1 = (Driver1) context.getBean("driver1");
		System.out.println("Driver1 Details : " + driver1);

	}
}
