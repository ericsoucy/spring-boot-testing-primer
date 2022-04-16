# Testing Spring Boot Applications primer

<https://rieckpil.de/guide-to-testing-with-spring-boot-starter-test/>

<https://rieckpil.de/maven-setup-for-testing-java-applications/>

## Exercise 3 Testing Application with maven

```bash
// mvn run unit test only
mvn surefire:test

// mvn run integration test
mvn failsafe:integration-test
//or
mvn verify
```

excellent JUnit 5 crash course as part of the freeCodeCamp
<https://www.youtube.com/watch?v=flpmSXVTqBI>

Furthermore, the official user guide of JUnit 5 (don't forget to bookmark it) also introduces every feature with great
hands-on examples.
<https://junit.org/junit5/docs/current/user-guide/>
<https://rieckpil.de/guide-to-testing-with-spring-boot-starter-test/>

The main workflow when using Mockito for our tests is usually the following:

- Create mocks for collaborators of our class under test (e.g., using @Mock)
- Stub the behavior of the mocks as they'll otherwise return a default value (when().thenReturn())
- (Optionally) Verify the interaction of our mocks (verify())

The **four golden rules** of Mockito should always guide our decisions about when and what to mock:

- Do not mock types you don't own
- Don't mock values objects
- Don't mock everything
- Show some love with your tests

<https://rieckpil.de/spring-boot-unit-and-integration-testing-overview/>

## Exercise #5

```bash
curl http://localhost:8080/api/customers

```