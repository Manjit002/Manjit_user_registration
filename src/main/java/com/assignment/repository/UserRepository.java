package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assignment.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "select user from User user where user.email =:email and user.password =:password")
	User logInUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	User findByEmail(String email);
}
