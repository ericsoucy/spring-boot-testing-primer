package com.training.springboot.testprimer.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface CustomerRepository extends JpaRepository<Customer, Long> {
  @Query(
      value = "SELECT * " + "FROM customer " + "ORDER BY joined_at ASC " + "LIMIT 1",
      nativeQuery = true)
  Customer getEarlyBird();
}
