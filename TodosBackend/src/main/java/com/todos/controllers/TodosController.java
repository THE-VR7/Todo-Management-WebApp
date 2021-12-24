package com.todos.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todos.elasticrepository.TodosSearchRepository;
import com.todos.models.Todo;
import com.todos.repository.TodosRepository;
import com.todos.services.TodoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodosController {

	@Autowired
	private TodoService todoService;

	@Autowired
	TodosRepository todoRepository;
	
	@Autowired
	private TodosSearchRepository todosSearchRepository;

	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoRepository.findByUsername(username);
//		 return todoService.findAll();
	}
	
	@GetMapping("/gettodosbypage/users/{username}/todos/{index}")
	public Page<Todo> getTodosByIndex(@PathVariable String username, @PathVariable int index)
	{
		return todoService.getTodosByIndex(username,index);
	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		return todoRepository.findById(id).get();
//		 return todoService.findById(id);
	}

	// DELETE /users/{username}/todos/{id}
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		todoRepository.deleteById(id);
		todosSearchRepository.deleteById(id);
		return ResponseEntity.noContent().build();
		// return ResponseEntity.notFound().build();
	}

	// Edit/Update a Todo
	// PUT /users/{user_name}/todos/{todo_id}
	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id,
			@RequestBody Todo todo) {

//		 Todo todoUpdated = todoService.save(todo);
		Todo todoUpdated = todoRepository.save(todo);
		todosSearchRepository.save(todo);

		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {

//		 Todo createdTodo = todoService.save(todo);
		todo.setUsername(username);
		Todo createdTodo = todoRepository.save(todo);
		todosSearchRepository.save(todo);

		// Location
		// Get current resource url
		/// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/search/{username}/{description}/{index}")
	public Page<Todo> searchTodosByIndex(@PathVariable String username, @PathVariable String description, @PathVariable int index)
	{
		return todoService.searchTodosForUser(username,description,index);
	}
	
	@GetMapping("/elasticsearch/{username}/{description}/{index}")
	public Page<Todo> elasticSearchTodosByIndex(@PathVariable String username, @PathVariable String description, @PathVariable int index)
	{
		return todoService.elasticSearchTodosForUser(username,description,index);
	}

}
