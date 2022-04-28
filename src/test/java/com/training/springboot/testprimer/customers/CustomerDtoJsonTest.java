package com.training.springboot.testprimer.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class CustomerDtoJsonTest {

  @Autowired private JacksonTester<CustomerDto> json;

  @Test
  public void testSerialization() throws Exception {
    ZonedDateTime testdate = ZonedDateTime.now();
    CustomerDto customerDto = new CustomerDto(200L, "Duke", "Java", testdate);
    JsonContent<CustomerDto> result = this.json.write(customerDto);
    assertThat(result).hasJsonPathStringValue("$.firstname");
    assertThat(result).extractingJsonPathStringValue("$.firstname").isEqualTo("Duke");
    assertThat(result).extractingJsonPathStringValue("$.lastname").isEqualTo("Java");

    assertThat(result)
        .extractingJsonPathStringValue("$.joinedat")
        .isEqualTo(testdate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
  }

  @Test
  public void testDeserialize() throws Exception {
    String jsonContent =
        "{\"firstname\":\"Mike\", \"lastname\": \"Meyer\","
            + " \"joinedat\":\"2022-04-27T21:40:12.1085049-04:00\","
            + " \"id\": 42}";
    CustomerDto result = this.json.parse(jsonContent).getObject();
    assertThat(result.getFirstname()).isEqualTo("Mike");
    assertThat(result.getLastname()).isEqualTo("Meyer");
    assertThat(result.getJoinedAt()).isEqualTo(ZonedDateTime.now());
    assertThat(result.getId()).isEqualTo(42L);
  }
}
