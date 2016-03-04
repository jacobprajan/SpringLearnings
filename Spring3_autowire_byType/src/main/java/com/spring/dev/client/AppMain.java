package com.spring.dev.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dev.domain.Employee;

public class AppMain
{
	public static void main(String args[])
	{

		AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
		 
        //autowire=byType
        Employee employee = (Employee)context.getBean("employee");
        System.out.println("Employee Details : "+employee);

	}
}
