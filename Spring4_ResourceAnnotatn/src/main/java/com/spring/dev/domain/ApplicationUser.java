package com.spring.dev.domain;

import org.springframework.stereotype.Component;

// @Component indicates that an annotated class is a "component". Such classes are considered as 
// candidates for auto-detection when using annotation-based configuration and classpath scanning.

@Component("applicationUser")
public class ApplicationUser
{

	private String name = "defaultName";

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "ApplicationUser [name=" + name + "]";
	}
}