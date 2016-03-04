package com.spring.dev.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("driver3")
public class Driver3
{

	private License license;

	// @Autowired on Constructor
	
	@Autowired
	public Driver3(License license)
	{
		this.license = license;
	}

	@Override
	public String toString()
	{
		return "Driver3 [license=" + license + "]";
	}
}