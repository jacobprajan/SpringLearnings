package com.spring.dev.domain;

import org.springframework.stereotype.Component;

@Component
public class License {
 
    private String number="123456ABC";
 
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