package com.todos.services;

import java.util.*;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todos.elasticrepository.TodosSearchRepository;
import com.todos.models.Todo;
import com.todos.repository.TodosRepository;

@Service
public class InjectDataService {

	@Autowired
	private TodosSearchRepository todosSearchRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(InjectDataService.class);
	
	@Autowired
	private TodosRepository todoRepository;

	public boolean insertData(String username, int size) {
		logger.info("Inside insertData method , username is " + username + " and size is " + size);
		try {
			List<Todo> todoList = new ArrayList<Todo>();
			for (int i = 1; i <= size; i++) {
				todoList.add(new Todo(null, username, "Injected Todo " + i, new Date(), false));
//				todoRepository.save(todoList.get(i-1));
			}
			logger.info("todolist is " + todoList);
			todoRepository.saveAll(todoList);
			todosSearchRepository.saveAll(todoList);
		} catch (Exception e) {
			System.out.println("Insertion not possible -> " + e.getMessage());
			return false;
		}
		return true;
	}
}
