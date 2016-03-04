package com.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component("offersDao")
@Transactional
public class OffersDao
{

	// removed after hibernate implementation
	
	// private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public OffersDao()
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
	
	/*
	 * public List<Offer> getOffers() {
	 * 
	 * return jdbc .query(
	 * "select * from offers, users where offers.username = users.username and users.enabled = true"
	 * , new OfferRowMapper()); }
	 */

	// using hibernate

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers()
	{
		Criteria cri = getSession().createCriteria(Offer.class);
		cri.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		return cri.list();
	}

	/*
	 * public List<Offer> getOffers(String username) {
	 * 
	 * return jdbc .query(
	 * "select * from offers, users where offers.username = users.username and users.enabled = true and offers.username =:username"
	 * , new MapSqlParameterSource("username", username), new OfferRowMapper());
	 * }
	 */

	// using hibernate
	@SuppressWarnings("unchecked")
	public List<Offer> getOffers(String username)
	{
		Criteria cri = getSession().createCriteria(Offer.class);

		cri.createAlias("user", "u");
		cri.add(Restrictions.eq("u.enabled", true));
		cri.add(Restrictions.eq("u.username", username));
		return cri.list();
	}

	/*
	 * public boolean update(Offer offer) { BeanPropertySqlParameterSource
	 * params = new BeanPropertySqlParameterSource( offer);
	 * 
	 * return jdbc.update("update offers set text=:text where id=:id", params)
	 * == 1; }
	 */

	/*
	 * // hibernate public void update(Offer offer) {
	 * getSession().update(offer); }
	 */

	/*
	 * public boolean create(Offer offer) {
	 * 
	 * BeanPropertySqlParameterSource params = new
	 * BeanPropertySqlParameterSource( offer);
	 * 
	 * return jdbc
	 * .update("insert into offers (username, text) values (:username, :text)",
	 * params) == 1; }
	 */

	// using hibernate
	/*
	 * public void create(Offer offer) { getSession().save(offer); }
	 */

	// refactoring create & update method
	public void saveOrUpdate(Offer offer)
	{
		getSession().saveOrUpdate(offer);
	}

	// this method is not using.. so commenting... check hibernate batch
	/*
	 * @Transactional public int[] create(List<Offer> offers) {
	 * 
	 * SqlParameterSource[] params = SqlParameterSourceUtils
	 * .createBatch(offers.toArray());
	 * 
	 * return jdbc .batchUpdate(
	 * "insert into offers (username, text) values (:username, :text)", params);
	 * }
	 */

	/*
	 * public boolean delete(int id) { MapSqlParameterSource params = new
	 * MapSqlParameterSource("id", id);
	 * 
	 * return jdbc.update("delete from offers where id=:id", params) == 1; }
	 */

	// using hibernate

	public boolean delete(int id)
	{
		// using HQL

		Query query = getSession()
				.createQuery("delete from Offer where id=:id");
		// "id" refers to placeholder .. its not field name
		query.setLong("id", id);
		return query.executeUpdate() == 1;

	}

	/*
	 * public Offer getOffer(int id) {
	 * 
	 * MapSqlParameterSource params = new MapSqlParameterSource();
	 * params.addValue("id", id);
	 * 
	 * return (Offer) jdbc .queryForObject(
	 * "select * from offers, users where offers.username=users.username and users.enabled=true and id=:id"
	 * , params, new OfferRowMapper()); }
	 */

	// using hibernate

	public Offer getOffer(int id)
	{

		Criteria cri = getSession().createCriteria(Offer.class);

		cri.createAlias("user", "u");
		cri.add(Restrictions.eq("u.enabled", true));
		cri.add(Restrictions.idEq(id));
		return (Offer) cri.uniqueResult();
	}

}
