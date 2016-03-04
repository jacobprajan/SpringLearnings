package com.spring.dev.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dev.domain.Application;

public class AppMainXml
{

	public static void main(String args[])
	{
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"app-config.xml");

		// Byname Autowiring
		Application application = (Application) context.getBean("application");
		System.out.println("Application Details : " + application);

	}

}