package com.spring.test.dao;

import java.math.BigDecimal;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.spring.dev.dao.EmployeeDao;
import com.spring.dev.model.Employee;

public class EmployeeDaoImplTest extends EntityDaoImplTest
{

	@Autowired
	EmployeeDao employeeDao;

	@Override
	protected IDataSet getDataSet() throws Exception
	{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader()
				.getResourceAsStream("Employee.xml"));
		return dataSet;
	}

	/*
	 * In case you need multiple datasets (mapping different tables) and you do
	 * prefer to keep them in separate XML's
	 * 
	 * @Override protected IDataSet getDataSet() throws Exception { IDataSet[]
	 * datasets = new IDataSet[] { new
	 * FlatXmlDataSet(this.getClass().getClassLoader
	 * ().getResourceAsStream("Employee.xml")), new
	 * FlatXmlDataSet(this.getClass(
	 * ).getClassLoader().getResourceAsStream("Benefits.xml")), new
	 * FlatXmlDataSet
	 * (this.getClass().getClassLoader().getResourceAsStream("Departements.xml"
	 * )) }; return new CompositeDataSet(datasets); }
	 */

	@Test
	public void findById()
	{
		Assert.assertNotNull(employeeDao.findById(1));
		Assert.assertNull(employeeDao.findById(3));
	}

	@Test
	public void saveEmployee()
	{
		employeeDao.saveEmployee(getSampleEmployee());
		Assert.assertEquals(employeeDao.findAllEmployees().size(), 3);
	}

	@Test
	public void deleteEmployeeBySsn()
	{
		employeeDao.deleteEmployeeBySsn("11111");
		Assert.assertEquals(employeeDao.findAllEmployees().size(), 1);
	}

	@Test
	public void deleteEmployeeByInvalidSsn()
	{
		employeeDao.deleteEmployeeBySsn("23423");
		Assert.assertEquals(employeeDao.findAllEmployees().size(), 2);
	}

	@Test
	public void findAllEmployees()
	{
		Assert.assertEquals(employeeDao.findAllEmployees().size(), 2);
	}

	@Test
	public void findEmployeeBySsn()
	{
		Assert.assertNotNull(employeeDao.findEmployeeBySsn("11111"));
		Assert.assertNull(employeeDao.findEmployeeBySsn("14545"));
	}

	public Employee getSampleEmployee()
	{
		Employee employee = new Employee();
		employee.setName("Karen");
		employee.setSsn("12345");
		employee.setSalary(new BigDecimal(10980));
		employee.setJoiningDate(new LocalDate());
		return employee;
	}

}


/*

Let’s take saveEmployee test case and understand how things happening here.

1) Before any of the test from the classes (which are extending EntityDaoImplTest) starts executing, Spring 
will load the text context from the configuration classes associated with @ContextConfiguration annotation & 
create the beans instances defined in those classes, thanks to AbstractTransactionalTestNGSpringContextTests. 
This will happen only once.
2) During @Bean instance creation, Spring will create the SessionFactory Bean which will be injected with 
dataSource bean (as defined in HibernateTestConfiguration class) based on database & hibernate properties. 
Look at the following property
properties.put("hibernate.hbm2ddl.auto", "create-drop");
Thanks to this hbm2ddl property, when the SessionFactory is created, the schema related to our Model classes 
will be validated and exported to database. That means Employee table will be created in H2 database.
3) Before the test start, @BeforeMethod will be called, which will instruct DBUnit to connect to database and 
perform clean-insert.It will insert 2 rows in Employee table (look at Employee.xml content)
4) Now the actual test case saveEmployee is about to start. Just before execution start, a transaction will be 
started. Method itself will run within this transaction. Once method finished it’s execution, transaction will 
be rolled back which is default setup. You can override this behavior by annotating the test method with 
@Rollback(true) annotation. It is defined in [org.springframework.test.annotation.Rollback]
4) Now the actual test case saveEmployee finally starts it’s execution. It will call employeeDao.saveEmployee
(getSampleEmployee()); which in-turn will insert the one pre-defined Employee object into H2 database using 
hibernate. This is the core logic of saveEmployee method anyway. After this operation there will be total 3 
rows in Employee table in H2 database. We will assert it for success/failure. Test completes.
5) For next test case, again @BeforeMethod will be called which will delete everything from table and re-insert 
two rows as defined in Employee.xml. Story continues…
6) When all our tests are done, session will be closed and schema will be dropped.



*/


