package com.spring.dev.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Bond
{

	// Without @Qualifier, Spring wont be able to decide which bean (Ferari or Mustang as both
	// implements Car) to choose for auto-wiring , and it throws exception.
	
	@Autowired
	@Qualifier("Mustang")
	private Car car;

	public void showCar()
	{
		car.getCarName();
	}
}
