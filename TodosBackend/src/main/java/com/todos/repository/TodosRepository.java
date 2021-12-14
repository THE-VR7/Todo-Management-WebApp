package com.todos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.todos.models.Todo;

@Repository
public interface TodosRepository extends JpaRepository<Todo, Long> {
	List<Todo> findByUsername(String username);
	
	Page<Todo> findByUsername(String username,Pageable pageable);

	@Query("SELECT t FROM Todo t where t.username LIKE ?1 AND t.description LIKE ?2% ")
	Page<Todo> findByUsernameAndDescription(String username,String description, Pageable pageable);
}
