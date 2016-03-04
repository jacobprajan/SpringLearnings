package com.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao
{

	// removed after hibernate implementation
	
	// private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;

	public UsersDao()
	{
		// System.out.println("Successfully loaded Offers DAO");
	}

	// removed after hibernate implementation
	/*
	@Autowired
	public void setDataSource(DataSource jdbc)
	{
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	*/
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	// Need to insert to users & authorities table. So if insertion fails in one
	// table, data should be rollbacked from other table also. Database should
	// be in previous state.
	// @Transactional is used for that purpose
	/*
	@Transactional
	public boolean create(User user)
	{

		// BeanPropertySqlParameterSource params = new
		// BeanPropertySqlParameterSource(
		// user);

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("username", user.getUsername());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("email", user.getEmail());
		params.addValue("name", user.getName());
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority());

		return jdbc
				.update("insert into users (username, name, password, email, enabled, authority ) values (:username, :name, :password, :email, :enabled, :authority)",
						params) == 1;

	}
	*/
	
	// using hibernate
	@Transactional
	public void create(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		getSession().save(user);		
	}
	
	/*
	public boolean exists(String username)
	{
		return jdbc.queryForObject(
				"select count(*) from users where username = :username",
				new MapSqlParameterSource("username", username), Integer.class) > 0;
	}
	*/
	
	// Refactored method
	
	public boolean exists(String username)
	{
		return getUser(username) != null;		
	}
	
	/*
	// using hibernate
	public boolean exists(String username)
	{
		Criteria crit = getSession().createCriteria(User.class);
		// "username" > means column name
		
		// crit.add(Restrictions.eq("username", username));  // method to query columns which are not primary keys
		
		crit.add(Restrictions.idEq(username));  // best method to query primary key columns
		
		User user = (User) crit.uniqueResult();
		return user != null;
	}
	*/
	/*
	public List<User> getAllUsers()
	{

		return jdbc
				.query("select * from users",
						BeanPropertyRowMapper.newInstance(User.class));
		
	}
	*/
	
	
	// using hibernate
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers()
	{
		// "User" is bean name... not table name
		return getSession().createQuery("from User").list();
	}

	public User getUser(String username)
	{
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));		
		return (User)crit.uniqueResult();
		
	}
}
