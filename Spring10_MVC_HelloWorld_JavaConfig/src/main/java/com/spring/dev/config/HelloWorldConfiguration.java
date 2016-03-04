package com.spring.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*

 This configuration class can be treated as a replacement of spring-servlet.xml as it contains all the 
 information required for component-scanning and view resolver.

 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spring.dev")
public class HelloWorldConfiguration
{
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

}

/*
 * 
 * @Configuration indicates that this class contains one or more bean methods
 * annotated with @Bean producing bean manageable by spring container. Above
 * Configuration class is equivalent to following XML counterpart:
 * 
 * @EnableWebMvc is equivalent to mvc:annotation-driven in XML. It enables
 * support for @Controller-annotated classes that use @RequestMapping to map
 * incoming requests to specific method.
 * 
 * @ComponentScan is equivalent to context:component-scan base-package="..."
 * providing with where to look for spring managed beans/classes.
 * 
 */
