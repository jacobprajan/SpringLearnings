package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component("messagesDao")
@Transactional
public class MessagesDao
{

	// removed after hibernate implementation
	
	// private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public MessagesDao()
	{

	}

	// using hibernate

	@SuppressWarnings("unchecked")
	public List<Message> getMessages()
	{
		Criteria cri = getSession().createCriteria(Message.class);
		// cri.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		return cri.list();
	}

	// using hibernate
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username)
	{
		Criteria cri = getSession().createCriteria(Message.class);

		cri.add(Restrictions.eq("username", username));
		return cri.list();
	}

	
	// using hibernate
	
	public void saveOrUpdate(Message message)
	{
		System.out.println(message);
		getSession().saveOrUpdate(message);
	}

	// using hibernate

	public boolean delete(int id)
	{
		// using HQL

		Query query = getSession()
				.createQuery("delete from Message where id=:id");
		// "id" refers to placeholder .. its not field name
		query.setLong("id", id);
		return query.executeUpdate() == 1;

	}

	// using hibernate

	public Message getMessage(int id)
	{

		Criteria cri = getSession().createCriteria(Message.class);
		cri.add(Restrictions.idEq(id));
		return (Message) cri.uniqueResult();
	}

}
