package com.spring.dev.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.spring.dev.config.AppConfig;
import com.spring.dev.domain.Bond;

public class AppMain
{
	public static void main(String args[])
	{
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		Bond bond = (Bond) context.getBean("bond");
		bond.showCar();
	}
}
