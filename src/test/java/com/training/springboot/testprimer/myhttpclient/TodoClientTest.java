package com.training.springboot.testprimer.myhttpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(TodoClient.class)
class TodoClientTest {

  @Autowired private TodoClient todoClient;

  @Autowired private ObjectMapper mapper;

  @Autowired private MockRestServiceServer mockRestServiceServer;

  @Test
  void todoClientSuccessfullyReturnsTodo() throws Exception {
     String json = """
       {
        "userId": 10,
        "id": 200,
        "title": "ipsam aperiam voluptates qui",
        "completed": false
        }
     """;

     this.mockRestServiceServer
             .expect(requestTo("/todos/200"))
             .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
     Todo result = todoClient.getTodo(200L);
     assertNotNull(result);

  }

  // you can also use the injected ObjectMapper to create a valid JSON string
  @Test
  void todoClientSuccessfullyReturnsTodoTwoHundred() throws Exception {
    String json =this.mapper.writeValueAsString(new Todo(10L,200L,"ipsam aperiam voluptates qui",false));
    this.mockRestServiceServer
            .expect(requestTo("/todos/200"))
            .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
    Todo result = todoClient.getTodo(200L);

    assertEquals(200L, result.getId());
  }

  @Test
  void todoClientThrowsExceptionWhenNoTodoIsFound() {
    this.mockRestServiceServer.expect(requestTo("/todos/201"))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));

    assertThrows(HttpClientErrorException.class, () -> todoClient.getTodo(201L));
  }
}
