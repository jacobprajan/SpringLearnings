package com.spring.dev.domain;

public class Driver
{
	private License license;

	public void setLicense(License license)
	{
		this.license = license;
	}

	public License getLicense()
	{
		return license;
	}

	@Override
	public String toString()
	{
		return "Driver [license=" + license + "]";
	}
}
