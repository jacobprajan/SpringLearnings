package com.spring.dev.domain;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

// @Component indicates that an annotated class is a "component". Such classes are considered as 
// candidates for auto-detection when using annotation-based configuration and classpath scanning.

@Component("application")
public class Application
{

	// In this code, Application’s user property is annotated with
	// @Resource(name=”applicationUser”). In this case, a bean with name
	// ‘applicationUser’ found in applicationContext will be injected here.
	
	@Resource(name = "applicationUser")
	private ApplicationUser user;

	@Override
	public String toString()
	{
		return "Application [user=" + user + "]";
	}
}
