package com.todos.elasticrepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.todos.models.Todo;

@Repository
public interface TodosSearchRepository extends ElasticsearchRepository<Todo,Long> {

	@Query("SELECT t FROM Todo t where t.username LIKE ?1 AND t.description LIKE ?2% ")
	Page<Todo> findByUsernameAndDescription(String username,String description, Pageable pageable);
}
