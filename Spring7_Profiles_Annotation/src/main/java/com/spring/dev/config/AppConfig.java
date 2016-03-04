package com.spring.dev.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring.dev")
public class AppConfig
{

	@Autowired
	public DataSource dataSource;

}

/*

Spring configuration class are the ones annotated with @Configuration. These classes contains methods 
annotated with @Bean. These @Bean annotated methods generates beans managed by Spring container.

Above Configuration just have one property to be auto-wired. Now what we will show here is that this 
dataSource bean can be injected with different beans on different environment (MySQL dataSource on 
Development & ORACLE for Production e.g.).

*/