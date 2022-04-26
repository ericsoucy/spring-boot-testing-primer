package com.training.springboot.testprimer.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CustomerRepositoryTest {

  @Autowired private EntityManager entityManager;

  @Autowired private DataSource dataSource;

  @Autowired private CustomerRepository customerRepository;

  @Test
  void contextLoads() {
    assertNotNull(entityManager);
    assertNotNull(dataSource);
  }

  @Test
  void shouldStoreAndRetrieveCustomer() {
    Customer customer = customerRepository.saveAndFlush(createCustomer("ffff", "llll"));
    assertNotNull(customerRepository.findById(customer.getId()));
  }

  @Test
  @Sql("/scripts/data.sql")
  void shouldGetEarliestJoined() {
    Customer result = customerRepository.getEarlyBird();
    assertNotNull(result);
    assertEquals(44, result.getId());
  }

  private Customer createCustomer(String firstname, String lastname) {
    Customer customer = new Customer();
    customer.setFirstname(firstname);
    customer.setLastname(lastname);
    return customer;
  }
}
