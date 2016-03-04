package com.spring.test.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*

This class is exactly same as HibernateConfiguration class defined in src in Previous post. Only difference 
is that methods dataSource() & hibernateProperties() are implemented specific to Hibernate & H2 combination. 
I choose to make a separate test configuration class and not to pollute existing class in Sources with testing 
related stuff.
 
It does everything exactly same as the one in Sources folder: it creates a SessionFacoty using a dataSource 
which is configured to work with in-memory database H2. In order to make hibernate work with H2, we also need 
to specify the dialect being used [H2 Dialect].

This SessionFactory will be injected in our AbstractDao class defined in Previous post. And from then on, the 
actual DAO implementation classes [ EmployeeDaoImpl] will use this sessionFactory when running tests against them.

Additionally, we will be using DBUnit to clean-insert sample data in test database[H2] before each test case 
execution, in order to prepare database before each Dao method execution. This way we make sure that the tests 
method do not interfere with each other.

*/

/*
 * This class is same as real HibernateConfiguration class in sources.
 * Only difference is that method dataSource & hibernateProperties 
 * implementations are specific to Hibernate working with H2 database.
 */

@Configuration
@EnableTransactionManagement
@ComponentScan(
{ "com.spring.dev.dao" })
public class HibernateTestConfiguration
{

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[]
		{ "com.spring.dev.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource
				.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	private Properties hibernateProperties()
	{
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s)
	{
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}
