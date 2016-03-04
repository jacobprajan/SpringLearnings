package com.spring.dev.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.spring.dev.config.AppConfig;
import com.spring.dev.model.Employee;
import com.spring.dev.service.EmployeeService;

public class AppMain
{
	public static void main(String args[])
	{
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		EmployeeService service = (EmployeeService) context
				.getBean("employeeService");

		/*
		 * Register employee using service
		 */
		Employee employee = new Employee();
		employee.setName("Danny Theys");
		service.registerEmployee(employee);

		context.close();
	}
}
