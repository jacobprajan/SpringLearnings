package com.spring.dev.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("driver2")
public class Driver2
{
	// @Autowired on Field
	
	@Autowired
	private License license;

	public License getLicense()
	{
		return license;
	}

	public void setLicense(License license)
	{
		this.license = license;
	}

	@Override
	public String toString()
	{
		return "Driver2 [license=" + license + "]";
	}
}