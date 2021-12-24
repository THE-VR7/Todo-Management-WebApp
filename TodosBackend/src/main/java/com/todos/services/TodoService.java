package com.todos.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.todos.models.Todo;
import com.todos.repository.TodosRepository;
import com.todos.elasticrepository.TodosSearchRepository;

@Service
public class TodoService {

	@Autowired
	private TodosRepository todoRepository;
	
	@Autowired
	private TodosSearchRepository todosSearchRepository;
	
	
	private static List<Todo> todos = new ArrayList<>();
	private static long idCounter = 0;
	
	static {
		todos.add(new Todo(++idCounter, "in28minutes","Learn to Dance 2", new Date(), false ));
		todos.add(new Todo(++idCounter, "in28minutes","Learn about Microservices 2", new Date(), false ));
		todos.add(new Todo(++idCounter, "in28minutes","Learn about Angular", new Date(), false ));
	}
	
	public List<Todo> getTodos(){
		return todoRepository.findAll();
	}


	public List<Todo> findAll() {
		return todos;
	}
	
	public Todo save(Todo todo) {
		if(todo.getId()==-1 || todo.getId()==0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		
		if(todo==null) return null;
		
		if(todos.remove(todo)) {
			return todo;
		}
		
		return null;
	}

	public Todo findById(long id) {
		for(Todo todo:todos) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		
		return null;
	}


	public Page<Todo> getTodosByIndex(String username, int index) {
		Pageable pageable = PageRequest.of(index, 10,Sort.by("targetDate").descending());
		Page<Todo> page = todoRepository.findByUsername(username, pageable);
		return page;
	}

	public Page<Todo> searchTodosForUser(String username, String description, int index) {
		Pageable pageable = PageRequest.of(index, 10,Sort.by("targetDate").descending());
		Page<Todo> page = todoRepository.findByUsernameAndDescription(username, description, pageable);
		return page;
	}

	public Page<Todo> elasticSearchTodosForUser(String username, String description, int index) {
		Pageable pageable = PageRequest.of(index, 10,Sort.by("targetDate").descending());
		Page<Todo> page = todosSearchRepository.findByUsernameAndDescription(username, description, pageable);
		return page;
	}
}