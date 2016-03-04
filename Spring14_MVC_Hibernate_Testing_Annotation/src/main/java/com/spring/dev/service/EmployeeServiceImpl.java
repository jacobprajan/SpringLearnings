package com.spring.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dev.dao.EmployeeDao;
import com.spring.dev.model.Employee;

/*
 * Most interesting part above is @Transactional which starts a transaction on each method start, and commits 
 * it on each method exit ( or rollback if method was failed due to an error). Note that since the transaction 
 * are on method scope, and inside method we are using DAO, DAO method will be executed within same transaction.
 *  
 */

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{

	@Autowired
	private EmployeeDao dao;

	public Employee findById(int id)
	{
		return dao.findById(id);
	}

	public void saveEmployee(Employee employee)
	{
		dao.saveEmployee(employee);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	public void updateEmployee(Employee employee)
	{
		Employee entity = dao.findById(employee.getId());
		if (entity != null)
		{
			entity.setName(employee.getName());
			entity.setJoiningDate(employee.getJoiningDate());
			entity.setSalary(employee.getSalary());
			entity.setSsn(employee.getSsn());
		}
	}

	public void deleteEmployeeBySsn(String ssn)
	{
		dao.deleteEmployeeBySsn(ssn);
	}

	public List<Employee> findAllEmployees()
	{
		return dao.findAllEmployees();
	}

	public Employee findEmployeeBySsn(String ssn)
	{
		return dao.findEmployeeBySsn(ssn);
	}

	public boolean isEmployeeSsnUnique(Integer id, String ssn)
	{
		Employee employee = findEmployeeBySsn(ssn);
		return (employee == null || ((id != null) && (employee.getId() == id)));
	}

}