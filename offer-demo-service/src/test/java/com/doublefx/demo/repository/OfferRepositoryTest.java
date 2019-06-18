package com.doublefx.demo.repository;

import lombok.val;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("small-size-test")
class OfferRepositoryTest {

	@Autowired
	private OfferRepository underTest;

	@Autowired
	private TestEntityManager entityManager;

	@BeforeAll
	static void beforeAll() {
		val instant = "2019-06-16T10:15:30Z";
		val clock = Clock.fixed(Instant.parse(instant), ZoneId.of("UTC"));

		new MockUp<Instant>() {
			@Mock
			public Instant now() {
				return clock.instant();
			}
		};
	}

	@Test
	void findAllEnabledWithExpiryDateAfter() {
		// GIVEN
		val enabled = OfferEntity.of("enabled entity", BigDecimal.ONE, Instant.now().plusSeconds(10));
		val expired = OfferEntity.of("expired entity", BigDecimal.ONE, Instant.now().minusSeconds(10));
		val disabled = OfferEntity.of("disabled entity", BigDecimal.ONE, Instant.now().plusSeconds(10));

		disabled.setDisabled(true);

		entityManager.persist(enabled);
		entityManager.persist(expired);
		entityManager.persist(disabled);

		//WHEN
		val offers = underTest.findByDisabledIsFalseAndExpiryDateIsAfter(Instant.now());

		//THEN
		assertThat(offers).isNotNull();
		assertThat(offers).size().isEqualTo(1);

		val offer = offers.get(0);
		assertThat(offer.getId()).isEqualTo(1);
	}
}