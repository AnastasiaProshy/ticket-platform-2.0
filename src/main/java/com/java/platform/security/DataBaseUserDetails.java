package com.java.platform.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.java.platform.model.Role;
import com.java.platform.model.User;

public class DataBaseUserDetails implements UserDetails {
	
	private final Integer id;
	private final String username;
	private final String password;
	private final Set<GrantedAuthority> authorities;
	
	public DataBaseUserDetails(User user)
	{
		this.id = user.getId();
		this.username = user.getUsername();
		this.password =user.getPassword();
		
		authorities = new HashSet<GrantedAuthority>();
		
		for (Role role: user.getRoles())
		{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}
	
	@Override
	public boolean isEnabled()
	{
		return true;
	}
	
}
