package com.spring.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dev.dao.EmployeeDao;
import com.spring.dev.model.Employee;

/*

Most interesting part is @Transactional which starts a transaction on each method start, and commits it 
on each method exit ( or rollback if method was failed due to an error). Note that since the transaction are 
on method scope, and inside method we are using DAO, DAO method will be executed within same transaction.

*/

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{

	@Autowired
	private EmployeeDao dao;

	public void saveEmployee(Employee employee)
	{
		dao.saveEmployee(employee);
	}

	public List<Employee> findAllEmployees()
	{
		return dao.findAllEmployees();
	}

	public void deleteEmployeeBySsn(String ssn)
	{
		dao.deleteEmployeeBySsn(ssn);
	}

	public Employee findBySsn(String ssn)
	{
		return dao.findBySsn(ssn);
	}

	public void updateEmployee(Employee employee)
	{
		dao.updateEmployee(employee);
	}
}