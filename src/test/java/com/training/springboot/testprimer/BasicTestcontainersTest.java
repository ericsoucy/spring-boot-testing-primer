package com.training.springboot.testprimer;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class BasicTestcontainersTest {

  @Container
  public static PostgreSQLContainer container =
      new PostgreSQLContainer("postgres:14.2-alpine")
          .withUsername("duke")
          .withPassword("password")
          .withDatabaseName("test");

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
    registry.add("spring.datasource.password", container::getPassword);
    registry.add("spring.datasource.username", container::getUsername);
  }

  @Test
  void shouldStartPostgreSQLDatabase() {
    assertTrue(container.isRunning());
  }

  /*@Test
  void aTest() {
    Customer customer = new Customer("Afirstname", "Alastname");
    customerRepository.save(customer);
    System.out.println("in a test");
  }*/
}
