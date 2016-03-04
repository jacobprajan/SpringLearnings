package com.spring.dev.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "APP_USER")
public class User
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name = "SSO_ID", unique = true, nullable = false)
	private String ssoId;

	@NotEmpty
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@NotEmpty
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@NotEmpty
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@NotEmpty
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "APP_USER_USER_PROFILE", 
	joinColumns = { @JoinColumn(name = "USER_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getSsoId()
	{
		return ssoId;
	}

	public void setSsoId(String ssoId)
	{
		this.ssoId = ssoId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Set<UserProfile> getUserProfiles()
	{
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles)
	{
		this.userProfiles = userProfiles;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ssoId == null)
		{
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}

}



/*

Look at how userProfiles property is annotated with ManyToMany.

@ManyToMany indicates that there is a Many-to-Many relationship between User and UserProfile. A User can have 
several profiles [ USER, ADMIN, DBA] while a profile can belong to several users. @JoinTable indicates that 
there is a link table which joins two tables using foreign key constraints to their primary keys. 
This annotation is mainly used on the owning side of the relationship. joinColumns refers to the column name 
of owning side(ID of USER), and inverseJoinColumns refers to the column of inverse side of relationship(ID 
of USER_PROFILE). Primary key of this joined table is combination of USER_ID & USER_PROFILE_ID.

Lazy Loading:
Pay special attention to fetch = FetchType.LAZY. Here we are informing hibernate to lazy load the userProfile 
collection. It’s also the default behavior. With this setup, a query to load the collection will be fired only 
when it is first accessed. It’s a good way to avoid fetching all connected object graph which is an expensive 
operation. When you are in transaction/active session, and will try to access collection, hibernate will fire 
separate select to fetch them.

But if you are outside active session (session closed/no transaction :you are in JSP e.g.), and tried to access 
the collection, you will meet your nemesis : org.hibernate.LazyInitializationException – could not initialize 
proxy – no Session. To avoid it, you need to initialize the collection on demand by calling Hibernate.initialize
(user.getUserProfiles()); within an active session [you know the DAO method you were in, before coming all the 
way to view, you may call this initialize in that method.]

Also note that we are not using any cascade. It is because a userprofile is not dependent of user, and can live 
independently.


One important remark : In case of *Many* association, always override hashcode and equals method which are 
looked by hibernate when holding entities into collections.



*/
