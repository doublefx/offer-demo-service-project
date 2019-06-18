package com.doublefx.demo.service;

import com.doublefx.demo.model.Offer;
import com.doublefx.demo.repository.OfferEntity;
import com.doublefx.demo.repository.OfferRepository;
import lombok.val;
import mockit.*;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.AbstractList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OfferServiceTest {

    @Injectable
    private OfferRepository offerRepository;

    @Mocked
    private OfferEntity offerEntity;

    @Mocked
    private Offer offer;

    @Tested
    private OfferService underTest;

    @Test
    void create() {
        // GIVEN
        new Expectations() {{
            offer.toEntity();
            result = offerEntity;
            times = 1;
        }};

        // WHEN
        underTest.create(offer);

        //THEN
        new Verifications() {{
            offerRepository.save(offerEntity);
            times = 1;
        }};
    }

    @Test
    void findAllEnabledNotExpired() {
        // GIVEN
        val instant = "2019-06-16T10:15:30Z";
        val clock = Clock.fixed(Instant.parse(instant), ZoneId.of("UTC"));

        new MockUp<Instant>() {
            @Mock
            public Instant now() {
                return clock.instant();
            }
        };

        // WHEN
        val result = underTest.findAllEnabledNotExpired();

        //THEN
        val now = Instant.from(clock.instant());
        new Verifications() {{
            offerRepository.findByDisabledIsFalseAndExpiryDateIsAfter(now);
            times = 1;
        }};
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(AbstractList.class);
    }

    @Test
    void disable() {
        //GIVEN
        new Expectations() {{
            offerRepository.findById(anyLong);
            result = Optional.of(offerEntity);
            times = 1;
        }};

        //WHEN
        underTest.disable(0L);

        //THEN
        new Verifications() {{
            offerEntity.setDisabled(true);
            times = 1;

            offerRepository.save(offerEntity);
            times = 1;
        }};
    }
}