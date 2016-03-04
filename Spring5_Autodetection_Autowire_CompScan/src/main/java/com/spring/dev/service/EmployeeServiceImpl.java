package com.spring.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dev.dao.EmployeeDao;
import com.spring.dev.model.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService
{

	@Autowired
	private DateService dateService;

	@Autowired
	private EmployeeDao employeeDao;

	public void registerEmployee(Employee employee)
	{
		employee.setAssessmentDate(dateService.getNextAssessmentDate());
		employeeDao.saveInDatabase(employee);
	}

}

/*

EmployeeService is our main service class.Notice that we have injected both DateService and EmployeeDao in this.
@Autowired on dateService property marks the DateService to be auto-wired by Spring’s dependency injection 
with the appropriate bean in Spring context. In our case, we have already declared a DateService bean 
using @Service, so that bean will be injected here. Similarly, employeeDao will be injected by EmployeeDao 
annotated with @Repository.

*/