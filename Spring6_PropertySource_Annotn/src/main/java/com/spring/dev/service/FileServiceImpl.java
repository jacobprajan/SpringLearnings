package com.spring.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service("fileService")
public class FileServiceImpl implements FileService
{

	@Value("${sourceLocation:g:/temp/input}")
	private String source;

	@Value("${destinationLocation:g:/temp/output}")
	private String destination;

	@Autowired
	private Environment environment;

	public void readValues()
	{
		System.out.println("Getting property via Spring Environment :"
				+ environment.getProperty("jdbc.driverClassName"));

		System.out.println("Source Location : " + source);
		System.out.println("Destination Location : " + destination);

	}

}

/*
 
First point to notice is Environment got auto-wired by Spring. Thanks to @PropertySoruce annotation , 
this Environment will get access to all the properties declared in specified .properties file. 
You can get the value of specif property using getProperty method. Several methods are defined 
in Environment interface.

Other interesting point here is @Value annotation. Format of value annotation is

@value("${key:default")
private String var;

Above declaration instruct Spring to find a property with key named ‘key’ (from .properties file e.g.) 
and assign it’s value to variable var.In case property ‘key’ not found, assign value ‘default’ to variable var.

Note that above ${…} placeholder will only be resolved when we have registered 
PropertySourcesPlaceholderConfigurer bean (which we have already done above) else the @Value annotation 
will always assign default values to variable var.
 
*/