package com.training.springboot.testprimer;

import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DOMDifferenceEngine;
import org.xmlunit.diff.DifferenceEngine;

import javax.xml.transform.Source;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class InvestigateLibrariesTest {

  @Test
  void verifyMockito() {
    List<String> mockedList = mock(List.class);
    mockedList.add("one");
    mockedList.clear();

    verify(mockedList).add("one");
    verify(mockedList).clear();
  }

  @Test
  void VerificationInOrderMockito() {
    // A. Single mock whose methods must be invoked in a particular order
    List<String> singleMock = mock(List.class);

    // using a single mock
    singleMock.add("was added first");
    singleMock.add("was added second");

    // create an inOrder verifier for a single mock
    InOrder inOrder = inOrder(singleMock);

    // following will make sure that add is first called with "was added first", then with "was
    // added second"
    inOrder.verify(singleMock).add("was added first");
    inOrder.verify(singleMock).add("was added second");

    // B. Multiple mocks that must be used in a particular order
    List<String> firstMock = mock(List.class);
    List<String> secondMock = mock(List.class);

    // using mocks
    firstMock.add("was called first");
    secondMock.add("was called second");

    // create inOrder object passing any mocks that need to be verified in order
    InOrder inOrder2 = inOrder(firstMock, secondMock);

    // following will make sure that firstMock was called before secondMock
    inOrder2.verify(firstMock).add("was called first");
    inOrder2.verify(secondMock).add("was called second");
  }

  @Test
  void VerifyingNumberOfInvocationsMockito() {
    LinkedList<String> mockedList = mock(LinkedList.class);
    mockedList.add("once");

    mockedList.add("twice");
    mockedList.add("twice");

    mockedList.add("three times");
    mockedList.add("three times");
    mockedList.add("three times");

    // following two verifications work exactly the same - times(1) is used by default
    verify(mockedList).add("once");
    verify(mockedList, times(1)).add("once");

    // exact number of invocations verification
    verify(mockedList, times(2)).add("twice");
    verify(mockedList, times(3)).add("three times");

    // verification using never(). never() is an alias to times(0)
    verify(mockedList, never()).add("never happened");

    // verification using atLeast()/atMost()
    verify(mockedList, atMostOnce()).add("once");
    verify(mockedList, atLeastOnce()).add("three times");
    verify(mockedList, atLeast(2)).add("three times");
    verify(mockedList, atMost(5)).add("three times");
  }

  @Test
  void argumentsMatcherMockito() {
    LinkedList<String> mockedList = mock(LinkedList.class);
    when(mockedList.get(anyInt())).thenReturn("element");

    assertThat(mockedList.get(999)).isEqualTo("element");
    verify(mockedList).get(anyInt());

    mockedList.add("somestring");
    verify(mockedList).add(argThat(someString -> someString.length() > 5));
  }

  @Test
  void stubMockito() {
    LinkedList<String> mockedList = mock(LinkedList.class);

    when(mockedList.get(0)).thenReturn("first");

    assertThat(mockedList.get(0)).isEqualTo("first");
  }

  @Test
  void helloWorldJUnit5() {
    assertEquals("duke", "DUKE".toLowerCase());
  }

  @Test
  void helloWorldAssertJ() {
    assertThat("DUKE".toLowerCase()).isEqualTo("duke");
  }

  @Test
  void helloWorldHamcrest() {
    MatcherAssert.assertThat("duke", equalTo("DUKE".toLowerCase()));
  }

  @Test
  void helloWorldJsonAssert() throws Exception {
    String result = "{name: 'duke', age: 42}";
    JSONAssert.assertEquals("{name: 'duke'}", result, false);
  }

  @Test
  void helloWorldJsonPath() {
    String result = "{\"age\":\"42\", \"name\": \"duke\", \"tags\":[\"java\", \"jdk\"]}";

    assertEquals(2, JsonPath.parse(result).read("$.tags.length()", Long.class));
    assertEquals("duke", JsonPath.parse(result).read("$.name", String.class));
  }

  @Test
  void helloWorldXmlUnit() {
    Source expected = Input.fromString("<invoices></invoices>").build();
    Source actual = Input.fromString("<customers></customers>").build();

    DifferenceEngine diff = new DOMDifferenceEngine();

    diff.addDifferenceListener(
        (comparison, outcome) -> Assertions.fail("XML documents are not similar: " + comparison));

    assertThrows(AssertionError.class, () -> diff.compare(expected, actual));
  }
}
