package com.training.springboot.testprimer.customers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.ZonedDateTime;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
class CustomerDto {
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long id;

  private String firstname;
  private String lastname;

  public ZonedDateTime getJoinedAt() {
    return joinedAt;
  }

  public void setJoinedAt(ZonedDateTime joinedAt) {
    this.joinedAt = joinedAt;
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX")
  private ZonedDateTime joinedAt;

  CustomerDto() {}

  CustomerDto(String firstName, String lastname) {
    this.firstname = firstName;
    this.lastname = lastname;
  }

  CustomerDto(Long id, String firstName, String lastname, ZonedDateTime joinedAt) {
    this.id = id;
    this.firstname = firstName;
    this.lastname = lastname;
    this.joinedAt = joinedAt;
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
    CustomerDto customer = (CustomerDto) o;
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
