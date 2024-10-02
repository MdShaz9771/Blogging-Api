package com.mdshaz.blog.blog_rest_api1.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "about")
	private String about;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<Role>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<Comment>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Like> likes = new HashSet<Like>();
	
	public Long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getAbout()
	{
		return about;
	}
	
	public void setAbout(String about)
	{
		this.about = about;
	}

	public Set<Role> getRole()
	{
		return roles;
	}

	public void setRole(Set<Role> roles)
	{
		this.roles = roles;
	}

	public Set<Comment> getComments()
	{
		return comments;
	}

	public void setComments(Set<Comment> comments)
	{
		this.comments = comments;
	}

	public Set<Like> getLikes()
	{
		return likes;
	}

	public void setLikes(Set<Like> likes)
	{
		this.likes = likes;
	}

	public User(long id, String name, String email, String password,String about)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about=about;
	}
	public User(String name, String email, String password,String about)
	{
		this.name = name;
		this.email = email;
		this.password = password;
		this.about=about;
	}
	public User()
	{
	}

}
