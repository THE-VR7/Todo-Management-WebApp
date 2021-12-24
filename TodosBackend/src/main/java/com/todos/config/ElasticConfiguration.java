package com.todos.config;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.todos.elasticrepository")
@EnableJpaRepositories(basePackages = "com.todos.repository")
public class ElasticConfiguration {

	@Bean
	public RestHighLevelClient client() {
		ClientConfiguration configuration = ClientConfiguration.builder()
												.connectedTo("localhost:9200")
												.build();
		return RestClients.create(configuration).rest();
	}
	
	@Bean
	public ElasticsearchOperations elasticsearchTemplate()
	{
		return new ElasticsearchRestTemplate(client());
	}
}
