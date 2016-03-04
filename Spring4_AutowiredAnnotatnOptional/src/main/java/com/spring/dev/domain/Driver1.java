package com.spring.dev.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("driver1")
public class Driver1
{
	
	// By default, @Autowired annotation makes sure that field is indeed autowired. 
	// In case autowiring is not successful, Spring will throw an exception. 
	// There are times however when you want to make autowiring optional. 
	// Setting @Autowired required attribute to ‘false’ will make this field optional for autowiring 
	// and Spring will skip it(remain null) if dependency not found.
	
	@Autowired(required = false)
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
		return "Driver [license=" + license + "]";
	}
}
