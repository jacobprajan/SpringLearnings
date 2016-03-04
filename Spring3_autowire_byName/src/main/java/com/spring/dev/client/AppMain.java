package com.spring.dev.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dev.domain.Application;

public class AppMain
{
	public static void main(String args[])
	{

		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"app-config.xml");

		// autowire=byName
		Application application = (Application) context.getBean("application");
		System.out.println("Application Details : " + application);

	}
}
