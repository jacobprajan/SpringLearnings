package com.spring.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;
import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.dao.Offer;
import com.spring.dao.OffersDao;
import com.spring.dao.User;
import com.spring.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations =
{ "classpath:com/spring/config/dao-context.xml",
		"classpath:com/spring/config/security-context.xml",
		"classpath:com/spring/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests
{
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("rexmathew", "Rex Mathew",
			"rexmathew@gmail.com", "hellohello", true, "ROLE_USER");
	private User user2 = new User("administrator", "Administrator",
			"administrator@gmail.com", "adminadmin", true, "ROLE_ADMIN");
	private User user3 = new User("richujohn", "Richu John",
			"richujohn@gmail.com", "liberator", true, "ROLE_USER");
	private User user4 = new User("johnhonai", "John Honai",
			"johnhonai@gmail.com", "terminator", false, "ROLE_USER");

	
	// this will run before every test case...
	@Before
	public void init()
	{
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");

	}

	/*
	 * 
	 * @Test public void testUsers() { // assertEquals("Dummy test", 1, 1);
	 * 
	 * User user = new User("rexmathew", "Rex Mathew", "rexmathew@gmail.com",
	 * "letmein", true, "ROLE_USER");
	 * 
	 * // assertTrue("User creation should return true.", //
	 * usersDao.create(user));
	 * 
	 * usersDao.create(user);
	 * 
	 * List<User> users = usersDao.getAllUsers();
	 * 
	 * assertEquals("Number of users should be equal to 1.", 1, users.size());
	 * 
	 * assertTrue("User should exists.", usersDao.exists(user.getUsername()));
	 * 
	 * assertEquals("Created user should be identical to retrieved user", user,
	 * users.get(0));
	 * 
	 * }
	 */

	// using hibernate

	@Test	
	public void testCreateRetrieve()
	{
		usersDao.create(user1);
		
		List<User> users1 = usersDao.getAllUsers();
		
		assertEquals("Number of users should be equal to 1.", 1, users1.size());
		
		assertEquals("Created user should be identical to retrieved user",
				user1, users1.get(0));
		
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		List<User> users2 = usersDao.getAllUsers();
		assertEquals("Number of users should be equal to 4.", 4, users2.size());
		
	}
	
	@Test
	public void testExists()
	{
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		
		assertTrue("User should exists.", usersDao.exists(user2.getUsername()));

		assertFalse("User should not exists.", usersDao.exists("dgfdfghfgh"));
	}
}
