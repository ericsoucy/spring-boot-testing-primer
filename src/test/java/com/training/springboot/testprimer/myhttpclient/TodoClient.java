package com.training.springboot.testprimer.myhttpclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TodoClient {
  private final RestTemplate restTemplate;

  public TodoClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate =
        restTemplateBuilder.rootUri("https://jsonplaceholder.typicode.com").build(); // todos
  }

  public Todo getTodo(Long id) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Void> entity = new HttpEntity<>(headers);
    return this.restTemplate
        .exchange("/todos/{id}", HttpMethod.GET, entity, Todo.class, id)
        .getBody();
  }
}
