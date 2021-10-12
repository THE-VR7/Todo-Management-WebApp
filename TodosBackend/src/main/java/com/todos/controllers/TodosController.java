package com.todos.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoRepository.findByUsername(username);
//		 return todoService.findAll();
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

		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {

//		 Todo createdTodo = todoService.save(todo);
		todo.setUsername(username);
		Todo createdTodo = todoRepository.save(todo);

		// Location
		// Get current resource url
		/// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
