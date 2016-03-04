package com.spring.dev.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.spring.dev.config.AppConfig;
import com.spring.dev.domain.Application;

public class AppMainConfig
{

	public static void main(String args[])
	{
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		// Byname Autowiring
		Application application = (Application) context.getBean("application");
		System.out.println("Application Details : " + application);

	}

}