package com.todos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todos.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("Select a from User a where a.username IN ?1")
	public User findByUsername(String username);
}
