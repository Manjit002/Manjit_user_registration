package com.assignment.serviceimpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.Input.UserInput;
import com.assignment.model.User;
import com.assignment.repository.UserRepository;
import com.assignment.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserInput convertInputToObject(String userData) {
		UserInput userInput = new UserInput();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			userInput = objectMapper.readValue(userData, UserInput.class);
		} catch (IOException err) {
			err.printStackTrace();
		}
		return userInput;
	}

	@Override
	public User registerUser(String userData) {
		UserInput userInput = convertInputToObject(userData);
		User usr = userRepo.findByEmail(userInput.getEmail());
		if (usr == null) {
			User user = new User();
			user.setName(userInput.getName());
			user.setEmail(userInput.getEmail());
			user.setGender(userInput.getGender());
			user.setPassword(userInput.getPassword());
			User us = userRepo.save(user);
			System.out.println(us.getId());
			return us;
		} else
			return null;
	}

	@Override
	public List<User> getAllUser() {
		List<User> userList = userRepo.findAll();
		return userList;
	}

	@Override
	public User userLoginUsingEmailAndPassword(String email, String password) {
		User usr = userRepo.logInUserByEmailAndPassword(email, password);
		if (usr != null)
			return usr;
		else
			return null;
	}

	@Override
	public User deletUserByEmail(String email) {
		User usr = userRepo.findByEmail(email);
		if (usr != null) {
			userRepo.delete(usr);
			return usr;
		} else
			return null;
	}

}
