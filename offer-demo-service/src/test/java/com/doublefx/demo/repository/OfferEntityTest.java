package com.doublefx.demo.repository;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class OfferEntityTest {

    private static final String DESCRIPTION = "description";
    private static final Instant DATE = getDate();
    private static final BigDecimal PRICE = new BigDecimal("10.00");

    private OfferEntity underTest;

    private static Instant getDate() {
        val instant = "2019-06-16T10:15:30Z";
        val clock = Clock.fixed(Instant.parse(instant), ZoneId.of("UTC"));

        return Instant.from(clock.instant());
    }

    @BeforeEach
    void setUp() {
        underTest = OfferEntity.of(DESCRIPTION, PRICE, DATE);
    }

    @Test
    void equalsFalse() {
        //GIVEN
        val entity = new OfferEntity(1L, DESCRIPTION, PRICE, DATE, null);

        //WHEN
        val actual = underTest.equals(entity);

        //THEN
        assertThat(actual).isFalse();
    }

    @Test
    void equalsTrue() {
        //GIVEN
        val entity = OfferEntity.of(DESCRIPTION, PRICE, DATE);

        //WHEN
        val actual = underTest.equals(entity);

        //THEN
        assertThat(actual).isTrue();
    }

    @Test
    void hashCodeReturnsExpectedValue() {
        //GIVEN
        val expected = OfferEntity.of(DESCRIPTION, PRICE, DATE);
        val expectedHashCode = expected.hashCode();

        //WHEN
        val actual = underTest.hashCode();

        //THEN
        assertThat(actual).isEqualTo(expectedHashCode);
    }

    @Test
    void toStringReturnsExpectedValue() {
        //GIVEN
        val expected = "OfferEntity(id=0, description=description, price=10.00, expiryDate=2019-06-16T10:15:30Z, disabled=false)";

        //WHEN
        val actual = underTest.toString();

        //THEN
        assertThat(actual).isEqualTo(expected);
    }
}