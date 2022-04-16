package com.training.springboot.testprimer.customers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
class Customer {
  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
  private String firstname;
  private String lastname;

  Customer() {}

  Customer(String firstName, String lastname) {
    this.firstname = firstName;
    this.lastname = lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return Objects.equals(id, customer.id)
        && Objects.equals(firstname, customer.firstname)
        && Objects.equals(lastname, customer.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "Customer{"
        + "id="
        + id
        + ", firstName='"
        + firstname
        + '\''
        + ", lastName='"
        + lastname
        + '\''
        + '}';
  }
}
