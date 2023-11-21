package com.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.Input.UserInput;
import com.assignment.model.User;

@Service
public interface UserService {
	public UserInput convertInputToObject(String data);

	public User registerUser(String userData);

	public List<User> getAllUser();

	public User userLoginUsingEmailAndPassword(String email, String password);

	public User deletUserByEmail(String email);
}
