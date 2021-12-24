package com.todos.loaders;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

import com.todos.elasticrepository.TodosSearchRepository;
import com.todos.models.Todo;
import com.todos.services.InjectDataService;
import com.todos.services.TodoService;

@Component
public class Loaders {
	
	@Autowired
	ElasticsearchOperations operations;
	
	@Autowired
	TodosSearchRepository todosSearchRepository;
	
	@Autowired
	TodoService todoService;
	
	private static final Logger logger = LoggerFactory.getLogger(Loaders.class);
	
	@PostConstruct
	@Transactional
	public void loadAll()
	{
		logger.info("Loading Data Started");
		todosSearchRepository.saveAll(getdata());
		logger.info("Loading Data Ended");
	}

	public List<Todo> getdata() {
		return todoService.getTodos();
	}
}
