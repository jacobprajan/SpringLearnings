package com.spring.dev.domain;

import org.springframework.stereotype.Component;

//@Component
// The above annotation is commented to make license property null in Driver1.java

public class License {
 
    private String number;
 
    public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	@Override
    public String toString() {
        return "License [number=" + number + "]";
    }
    
}