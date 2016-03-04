package com.spring.test.dao;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import com.spring.test.config.HibernateTestConfiguration;

/*

AbstractTransactionalTestNGSpringContextTests can (at some extent) be considered as JUnit equivalent of RunWith. 
This abstract class integrates Spring TestContext support in TestNG environment. It requires a class-level 
@ContextConfiguration in order to load ApplicationContext using XML configuration files or annotated 
@Configuration classes.

It also requires a datasource and a transactionManager to be defined in ApplicationContext in order to provide 
data-access support during testing. We have already defined both datasource & transactionManager in our 
@Configuration class.

Thanks to transaction support, by default a transaction will be started before each test, and then this 
transaction will be rolled back at the end of test. You may override the rollback behavior.

Look at setup method annotated with @BeforeMethod. Method annotated with @BeforeMethod is called before each 
test, so it is an ideal place to do something which is required before each test. In our case , we want the 
in-memory database to be clean and predefined sample data to be inserted before each test. We will do it right 
here.

Additionally, for DBUnit to connect to database in order to perform clean-insert, we have to provide a dataSource 
for it. That’s why we declared a dataSource here, which will be autowired with dataSource defined in 
HibernateTestConfiguration class.

As shown in above setUp method, firstly we create a connection to database using dataSource available( which 
will be test dataSource), and execute clean-insert on DB via DBUnit.

Notice the abstract method getDataSet above. This method will be implemented in our tests classes in order to 
provide the actual test data to be inserted before each test.

*/


@ContextConfiguration(classes =
{ HibernateTestConfiguration.class })
public abstract class EntityDaoImplTest extends
		AbstractTransactionalTestNGSpringContextTests
{

	@Autowired
	DataSource dataSource;

	@BeforeMethod
	public void setUp() throws Exception
	{
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(
				dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
	}

	protected abstract IDataSet getDataSet() throws Exception;

}
