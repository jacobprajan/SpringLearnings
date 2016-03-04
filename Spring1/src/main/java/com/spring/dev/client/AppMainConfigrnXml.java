package com.spring.dev.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dev.domain.HelloWorld;

public class AppMainConfigrnXml
{
	public static void main(String args[])
	{
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"helloworld-config.xml");
		HelloWorld bean = (HelloWorld) context.getBean("helloWorldBean");
		bean.sayHello("Spring 4");
		context.close();

	}
}
