package com.example.jwt.demojwt.resource;

import com.example.jwt.demojwt.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserResource {
	private Long id;
	private String login;
	private String name;
	private String password;
	
	public UserResource(User u)
	{
		this.setName(u.getName());
		this.setLogin(u.getLogin());
		this.setId(u.getId());
	}
	
	public User toModel(UserResource u)
	{
		return User.builder().name(u.name).login(u.login).password(u.password).build();
	}	
}
