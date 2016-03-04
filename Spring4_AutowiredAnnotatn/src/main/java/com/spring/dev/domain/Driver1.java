package com.spring.dev.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("driver1")
public class Driver1
{

	private License license;
	
	public License getLicense()
	{
		return license;
	}

	// @Autowired on Setter method
	
	@Autowired
	public void setLicense(License license)
	{
		this.license = license;
	}

	@Override
	public String toString()
	{
		return "Driver1 [license=" + license + "]";
	}

}
