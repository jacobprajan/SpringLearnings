package com.spring.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import com.spring.dev.domain.HelloWorld;
import com.spring.dev.domain.HelloWorldImpl;

// @Configuration declares a class as a Spring Configuration class which can contain @Bean annotated 
// methods generating bean definitions managed by Spring container.

// @Description is a new annotation introduced in Spring 4 for providing a textual description of the bean which
// can be useful for monitoring purpose. In this particular example although, it is not making any difference.

@Configuration
public class HelloWorldConfig
{
	@Bean(name = "helloWorldBean")
	@Description("This is a sample HelloWorld Bean")
	public HelloWorld helloWorld()
	{
		return new HelloWorldImpl();
	}
}
