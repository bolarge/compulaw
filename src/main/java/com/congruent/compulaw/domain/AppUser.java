package com.congruent.compulaw.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AppUser extends org.springframework.security.core.userdetails.User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String lastname;
	private int age;

	public AppUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String lastname, int age) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.lastname = lastname;
		this.age = age;
	}

	public AppUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String lastname, int age) {
		super(username, password, authorities);
		this.lastname = lastname;
		this.age = age;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

}
