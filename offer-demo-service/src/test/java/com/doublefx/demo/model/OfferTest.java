package com.doublefx.demo.model;

import com.doublefx.demo.repository.OfferEntity;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class OfferTest {

    private static Instant getDate() {
        val instant = "2019-06-16T10:15:30Z";
        val clock = Clock.fixed(Instant.parse(instant), ZoneId.of("UTC"));

        return Instant.from(clock.instant());
    }

    private static final long ID = 0L;
    private static final String DESCRIPTION = "description";
    private static final Instant DATE = getDate();
    private static final BigDecimal PRICE = new BigDecimal("10.00");
    private static final boolean DISABLED = false;

    private Offer underTest;

    @BeforeEach
    void setUp() {
        underTest = Offer.of(ID, DESCRIPTION, PRICE, DATE, DISABLED);
    }

    @Test
    void fromEntity() {
        //GIVEN
        val entity = OfferEntity.of(DESCRIPTION, PRICE, DATE);

        //WHEN
        val actual = Offer.fromEntity(entity);

        //THEN
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(ID);
        assertThat(actual.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(actual.getPrice()).isEqualTo(PRICE);
        assertThat(actual.getExpiryDate()).isEqualTo(DATE);
    }

    @Test
    void toEntity() {
        //WHEN
        val actual = underTest.toEntity();

        //THEN
        assertThat(actual).isNotNull();
        assertThat(actual.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(actual.getExpiryDate()).isEqualTo(DATE);
    }

    @Test
    void equalsFalse() {
        //WHEN
        val actual = underTest.equals(Offer.of(1L, DESCRIPTION, PRICE, DATE, DISABLED));

        //THEN
        assertThat(actual).isFalse();
    }

    @Test
    void equalsTrue() {
        //WHEN
        val actual = underTest.equals(Offer.of(ID, DESCRIPTION, PRICE, DATE, true));

        //THEN
        assertThat(actual).isTrue();
    }

    @Test
    void hashCodeReturnsExpectedValue() {
        //GIVEN
        val expectedHashCode = Offer.of(ID, DESCRIPTION, PRICE, DATE, DISABLED).hashCode();

        //WHEN
        val actual = underTest.hashCode();

        //THEN
        assertThat(actual).isEqualTo(expectedHashCode);
    }

    @Test
    void toStringReturnsExpectedValue() {
        //GIVEN
        val expected = "Offer(id=0, description=description, price=10.00, expiryDate=2019-06-16T10:15:30Z, disabled=false)";

        //WHEN
        val actual = underTest.toString();

        //THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void of() {
        assertThat(underTest).isNotNull();
        assertThat(underTest.getId()).isEqualTo(ID);
        assertThat(underTest.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(underTest.getPrice()).isEqualTo(PRICE);
        assertThat(underTest.getExpiryDate()).isEqualTo(DATE);
    }
}