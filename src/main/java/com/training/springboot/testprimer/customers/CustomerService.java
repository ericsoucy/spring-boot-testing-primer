package com.training.springboot.testprimer.customers;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  public Customer getCustomerById(Long id) {
    return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
  }
}
