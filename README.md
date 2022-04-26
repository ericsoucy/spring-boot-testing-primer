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

## Spring Boot Test Slice Annotations Exercise #6

## Testing the Web Layer Exercise #7

<https://rieckpil.de/spring-boot-test-slices-overview-and-usage/>
<https://github.com/rieckpil/blog-tutorials/tree/master/spring-boot-test-slice-annotations>
<https://rieckpil.de/guide-to-testing-spring-boot-applications-with-mockmvc>

**Setup MockMvc to Test Your Spring MVC @Controller and @RestController in Isolation**
<https://youtu.be/sxWNJJ4dKJo>

**Perform HTTP GET and POST Requests MockMvc to Test a @RestController**
<https://youtu.be/Aasp0mWT3Ac>

**Test Protected (Spring Security) Controller Endpoints With MockMvc**
<https://www.youtube.com/watch?v=oLtXe1wgSC8&feature=emb_imp_woyt>

**Use MockMvc to Test a Spring MVC Thymeleaf View @Controller Endpoint**
<https://www.youtube.com/watch?v=d7TDoGSZCoc>

## Testing the Persistence Layer Exercise 8

<https://rieckpil.de/test-your-spring-boot-jpa-persistence-layer-with-datajpatest/>
**for tests focus on non-trivial handcrafted queries**

**Introduction to @DataJpaTest and Pitfalls of In-Memory Databases For Testing**
<https://www.youtube.com/watch?v=DwBgx30ZWVc>

**Test a Native Query of Your Spring Data JPA Repository With @DataJpaTest**
<https://www.youtube.com/watch?v=EPxII6TeqTQ>

**Replacing the Default In-Memory Database of @DataJpaTest Using Testcontainers**
<https://www.youtube.com/watch?v=2fPDw0PVbso>

**Short And Concise Test Setup With @DataJpaTest and Testcontainers**
<https://www.youtube.com/watch?v=zFkTA95w0oo>

```bash
@DataJpaTest(properties = {
        "spring.test.database.replace=NONE",
        "spring.datasource.url=jdbc:tc:postgresql:12:///springboot"
})
// no need to autowire datasource
// no need to autowire entitymanager
@Test
@Sql("/scripts/SOME_SCRIPT.sql")  // to be executed before the test
void testSomeStuff(){}
```