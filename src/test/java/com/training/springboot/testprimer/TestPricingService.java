package com.training.springboot.testprimer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestPricingService {

  @Mock private ProductVerifier productVerifier;

  @Test
  void shouldReturnSmallPriceIfCompetitorHasInStock() {

    when(productVerifier.isCurrentlyInStockOfCompetitor("iPod")).thenReturn(true);

    PricingService classUnderTest = new PricingService(productVerifier);

    assertEquals(new BigDecimal("99.99"), classUnderTest.calculatePrice("iPod"));

    // assertNotEquals(new BigDecimal("99.99"),classUnderTest.calculatePrice("iPhone"));
  }

  @Test
  void shouldReturnBigPriceIfCompetitorHasNotInStock() {
    when(productVerifier.isCurrentlyInStockOfCompetitor("iPhone")).thenReturn(false);

    PricingService classUnderTest = new PricingService(productVerifier);
    assertNotEquals(new BigDecimal("99.99"), classUnderTest.calculatePrice("iPhone"));
  }
}
