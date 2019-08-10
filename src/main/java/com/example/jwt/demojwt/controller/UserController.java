package com.example.jwt.demojwt.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.demojwt.domain.User;
import com.example.jwt.demojwt.repository.UserRepository;
import com.example.jwt.demojwt.resource.UserResource;
import com.example.jwt.demojwt.service.RegisterLog;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RegisterLog registerLog;

	@RequestMapping({ "/user" })
	public List<UserResource> get() {
		registerLog.info("Busca de Usuários", "firstPage");
		List<User> users = userRepository.findAll();
		registerLog.info("Result: {}", "firstPage", users.size());

		return users.stream().map(p -> new UserResource(p)).collect(Collectors.toList());
	}

	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public UserResource save(@RequestBody UserResource userResource) {
		registerLog.info("Novo Usuário: {}, {}", "save", userResource.getName(), userResource.getLogin());
		User user = userRepository.save(new UserResource().toModel(userResource));
		registerLog.info("Usuário Salvo com sucesso!", "firstPage");
		
		return new UserResource(user);
	}
}
