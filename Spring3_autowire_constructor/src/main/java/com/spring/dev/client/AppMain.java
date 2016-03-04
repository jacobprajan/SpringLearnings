package com.spring.dev.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dev.domain.Performer;

public class AppMain
{
	public static void main(String args[])
	{

		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"app-config.xml");

		// autowire=constructor
		Performer performer = (Performer) context.getBean("performer");
		System.out.println("Performer Details : " + performer);

	}
}
