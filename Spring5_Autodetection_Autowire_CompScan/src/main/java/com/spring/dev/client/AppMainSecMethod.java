package com.spring.dev.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.dev.model.Employee;
import com.spring.dev.service.EmployeeService;

public class AppMainSecMethod
{

	public static void main(String args[])
	{
		// In case you decided to discard the configuration class AppConfig you can do so with following changes
		// AnnotationConfigApplicationContext.scan method performs a scan of all class in specified package[s], 
		// registering all the beans annotated with @Component (even @configuration itself is internally annotated 
		// with @component) in application context. A caveat here is that refresh must be called after 
		// scan in order to fully process the registered classes.
		
		// For XML based configuration , replace

		// AbstractApplicationContext  context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// with

		// AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
		
		// AbstractApplicationContext context = new
		// AnnotationConfigApplicationContext(AppConfig.class);
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.spring.dev");
		context.refresh();

		EmployeeService service = (EmployeeService) context
				.getBean("employeeService");

		/*
		 * Register employee using service
		 */
		Employee employee = new Employee();
		employee.setName("John Paul");
		service.registerEmployee(employee);

		context.close();
	}
}
