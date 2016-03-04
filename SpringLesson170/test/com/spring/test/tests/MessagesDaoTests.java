package com.spring.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.dao.Message;
import com.spring.dao.MessagesDao;
import com.spring.dao.OffersDao;
import com.spring.dao.User;
import com.spring.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations =
{ "classpath:com/spring/config/dao-context.xml",
		"classpath:com/spring/config/security-context.xml",
		"classpath:com/spring/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessagesDaoTests
{
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private MessagesDao messagesDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("rexmathew", "Rex Mathew",
			"rexmathew@gmail.com", "hellohello", true, "ROLE_USER");
	private User user2 = new User("administrator", "Administrator",
			"administrator@gmail.com", "adminadmin", true, "ROLE_ADMIN");

	private Message message1 = new Message("Test Subject 1", "Test content 1",
			"Isaac Newton", "isaac@caveofprogramming.com", user1.getUsername());
	private Message message2 = new Message("Test Subject 2", "Test content 2",
			"Isaac Newton", "isaac@caveofprogramming.com", user1.getUsername());
	private Message message3 = new Message("Test Subject 3", "Test content 3",
			"Isaac Newton", "isaac@caveofprogramming.com", user2.getUsername());

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testSaveRetrieve() {
		usersDao.create(user1);
		usersDao.create(user2);
		
		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);
		
		List<Message> messages = messagesDao.getMessages(user1.getUsername());
		assertEquals(2, messages.size());
		
		messages = messagesDao.getMessages(user2.getUsername());
		assertEquals(1, messages.size());
	}
	
	@Test
	public void testRetrieveById() {
		usersDao.create(user1);
		usersDao.create(user2);
		
		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);
		
		List<Message> messages = messagesDao.getMessages(user1.getUsername());

		for(Message message: messages) {
			Message retrieved = messagesDao.getMessage(message.getId());
			assertEquals(message, retrieved);
		}
	}
	
	@Test
	public void testDelete() {
		usersDao.create(user1);
		usersDao.create(user2);
		
		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);
		
		List<Message> messages = messagesDao.getMessages(user1.getUsername());
		
		for(Message message: messages) {
			Message retrieved = messagesDao.getMessage(message.getId());
			assertEquals(message, retrieved);
		}
		
		Message toDelete = messages.get(1);
		
		assertNotNull("This message not deleted yet.", messagesDao.getMessage(toDelete.getId()));
		
		messagesDao.delete(toDelete.getId());
		
		assertNull("This message was deleted.", messagesDao.getMessage(toDelete.getId()));
	}
	
}
