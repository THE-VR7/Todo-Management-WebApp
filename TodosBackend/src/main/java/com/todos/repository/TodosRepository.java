package com.todos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todos.models.Todo;

@Repository
public interface TodosRepository extends JpaRepository<Todo, Long> {
	List<Todo> findByUsername(String username);
}
