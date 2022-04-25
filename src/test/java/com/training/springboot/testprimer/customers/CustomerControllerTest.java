package com.training.springboot.testprimer.customers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @MockBean private CustomerService customerService;

  @Autowired private MockMvc mockMvc;

  @Test
  void shouldCreateMockMvc() {
    assertNotNull(mockMvc);
  }

  @Test
  void shouldReturnListOfCustomers() throws Exception {
    when(customerService.findAll()).thenReturn(List.of(new Customer("firstname", "lastname")));

    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/api/customers").header("correlation-id", "xx"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname", Matchers.is("firstname")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname", Matchers.is("lastname")));
    // .andExpect(MockMvcResultMatchers.header().string("correlation-id", Matchers.is("xx")));
  }

  @Test
  void shouldNotAllowUnauthenticatedUserToGetACustomer() throws Exception {

    when(customerService.getCustomerById(1L)).thenReturn(new Customer("firstname", "lastname"));

    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/api/customers/1").header("correlation-id", "xx"))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }

  @Test
  @WithMockUser
  void shouldAllowAuthenticatedUserToGetACustomer() throws Exception {

    when(customerService.getCustomerById(1L)).thenReturn(new Customer("firstname", "lastname"));

    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/api/customers/1").header("correlation-id", "xx"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.is("firstname")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.is("lastname")));
  }
}
