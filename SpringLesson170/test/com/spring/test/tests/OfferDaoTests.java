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
public class OfferDaoTests
{
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private OffersDao offersDao;

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
	
	private Offer offer1 = new Offer(user1, "This is a test offer");
	private Offer offer2 = new Offer(user1, "This is another test offer");
	private Offer offer3 = new Offer(user2, "This is yet another test offer");
	private Offer offer4 = new Offer(user3, "This is a test offer once again");
	private Offer offer5 = new Offer(user3, "This is an interesting offer");
	private Offer offer6 = new Offer(user3, "This is just a test offer");
	private Offer offer7 = new Offer(user4, "This is a test offer for a user that is not enabled");
	

	@Before
	public void init()
	{
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");

	}
	
	
	@Test
	public void testCreateRetrieve()
	{
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		List<Offer> offers1 = offersDao.getOffers();
		assertEquals("Number of offers should be equal to 1.", 1, offers1.size());
		assertEquals("Created offer should be identical to retrieved offer",
				offer1, offers1.get(0));
		
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		List<Offer> offers2 = offersDao.getOffers();
		// one user is not enabled
		assertEquals("Number of offers should be equal to 6.", 6, offers2.size());
		
	}
	
	@Test
	public void testGetUsername()
	{
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		List<Offer> offers1 = offersDao.getOffers(user3.getUsername());
		assertEquals("Number of offers should be equal to 3.", 3, offers1.size());
		
		List<Offer> offers2 = offersDao.getOffers("fgdfgdfg");
		assertEquals("Number of offers should be equal to 0.", 0, offers2.size());
		
		List<Offer> offers3 = offersDao.getOffers(user1.getUsername());
		assertEquals("Number of offers should be equal to 2.", 2, offers3.size());
	}
	
	@Test
	public void testUpdate()
	{
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		offer3.setText("This offer has updated text.");
		offersDao.saveOrUpdate(offer3);
		
		Offer updated = offersDao.getOffer(offer3.getId());
		assertEquals("Updated offer should be identical to retrieved offer",
				offer3, updated);
		System.out.println(updated.getText());
	}
	
	@Test
	public void testDeleteOffer()
	{
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		Offer retrieved1 = offersDao.getOffer(offer2.getId());
		assertNotNull("Offer should not be null" , retrieved1);
		
		offersDao.delete(offer2.getId());

		Offer retrieved2 = offersDao.getOffer(offer2.getId()); 

		assertNull("Offer should be null" , retrieved2);
	}
	

	@Test
	public void testGetOfferById()
	{
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		Offer retrieved1 = offersDao.getOffer(offer2.getId());
		assertEquals("Offer by id should match offer from list",
				offer2, retrieved1);
		
		Offer retrieved2 = offersDao.getOffer(offer7.getId());
		assertNull("Offer should be null for disabled user" , retrieved2);
		
	}
	
	/*
	 
	@Test
	public void testCreateOffer()
	{

		User user = new User("rexmathew", "Rex Mathew", "rexmathew@gmail.com",
				"letmein", true, "ROLE_USER");
		
		//assertTrue("User creation should return true.", usersDao.create(user));
		
		usersDao.create(user);

		Offer offer = new Offer(user, "This is a test offer");
//		assertTrue("Offer creation should return true.",
//				offersDao.create(offer));
		
		offersDao.saveOrUpdate(offer);

		List<Offer> offers = offersDao.getOffers();
		assertEquals("Number of offers should be equal to 1.", 1, offers.size());

		assertEquals("Created offer should be identical to retrieved offer",
				offer, offers.get(0));

		// Get the offer with id filled in
		offer = offers.get(0);

		offer.setText("Updated offer text");
		//assertTrue("Offer updated should return true.", offersDao.update(offer));
		offersDao.saveOrUpdate(offer);

		Offer updated = offersDao.getOffer(offer.getId());

		assertEquals("Updated offer should be identical to retrieved offer",
				offer, updated);

		// Test get by id
		Offer offer2 = new Offer(user, "This is a test offer");
		
		//assertTrue("Offer creation should return true.",
		//		offersDao.create(offer2));
		
		offersDao.saveOrUpdate(offer2);
		
		List<Offer> userOffers = offersDao.getOffers(user.getUsername());
		assertEquals("Should be two offers for user", 2, userOffers.size());
		
		List<Offer> secondlist = offersDao.getOffers();
		
		for(Offer current : secondlist)
		{
			Offer retrived = offersDao.getOffer(current.getId());
			assertEquals("Offer by id should match offer from list",
					current, retrived);
		}
		
		
		// Test deletion
		offersDao.delete(offer.getId());

		List<Offer> empty = offersDao.getOffers();

		assertEquals("Offer lists should be empty", 1, empty.size());
	}
	
	*/

}
