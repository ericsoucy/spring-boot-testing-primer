package com.training.springboot.testprimer;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class GenericTestcontainersTest {

  @Container
  static GenericContainer<?> container =
      new GenericContainer(DockerImageName.parse("httpd:2.4.53"))
          .withExposedPorts(80)
          .withClasspathResourceMapping("static", "/usr/local/apache2/htdocs/", BindMode.READ_ONLY)
          .waitingFor(Wait.forHttp("/").forStatusCode(200));

  @Test
  void shouldStartCustomContainer() {
    assertTrue(container.isRunning());
  }
}
