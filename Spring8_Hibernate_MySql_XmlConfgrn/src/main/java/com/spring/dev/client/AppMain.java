package com.spring.dev.client;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dev.model.Employee;
import com.spring.dev.service.EmployeeService;

public class AppMain
{
	public static void main(String args[])
	{
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"hibernate-cfg.xml");

	
		EmployeeService service = (EmployeeService) context
				.getBean("employeeService");

		/*
		 * Create Employee1
		 */
		Employee employee1 = new Employee();
		employee1.setName("Han Yenn");
		employee1.setJoiningDate(new LocalDate(2010, 10, 10));
		employee1.setSalary(new BigDecimal(10000));
		employee1.setSsn("ssn00000001");

		/*
		 * Create Employee2
		 */
		Employee employee2 = new Employee();
		employee2.setName("Dan Thomas");
		employee2.setJoiningDate(new LocalDate(2012, 11, 11));
		employee2.setSalary(new BigDecimal(20000));
		employee2.setSsn("ssn00000002");

		/*
		 * Persist both Employees
		 */
		service.saveEmployee(employee1);
		service.saveEmployee(employee2);

		/*
		 * Get all employees list from database
		 */
		List<Employee> employees = service.findAllEmployees();
		for (Employee emp : employees)
		{
			System.out.println(emp);
		}

		/*
		 * delete an employee
		 */
		service.deleteEmployeeBySsn("ssn00000002");

		/*
		 * update an employee
		 */

		Employee employee = service.findBySsn("ssn00000001");
		employee.setSalary(new BigDecimal(50000));
		service.updateEmployee(employee);

		/*
		 * Get all employees list from database
		 */
		List<Employee> employeeList = service.findAllEmployees();
		for (Employee emp : employeeList)
		{
			System.out.println(emp);
		}

		context.close();
	}
}

/*
 * 
 * Notice how we have set the active profiles to be used on runtime.
 * context.scan("package") scans the mentioned package and registers all the
 * 
 * @Component but when it encounters the @Profile annotation on a
 * bean/configuration, it compare the profile value with the one supplied in
 * environment. If the value matches, it registers that bean else it skips it.
 * In our case it’s the DevDatabaseConfig dataDource which will be injected in
 * AppConfig.dataSource.
 */
