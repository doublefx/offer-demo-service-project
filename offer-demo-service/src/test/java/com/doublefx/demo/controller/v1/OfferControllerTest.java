package com.doublefx.demo.controller.v1;

import com.doublefx.demo.model.Offer;
import com.doublefx.demo.service.OfferService;
import lombok.val;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OfferControllerTest {

    @Mocked
    private Offer offer;

    @Injectable
    private OfferService offerService;

    @Tested
    OfferController underTest;

    @Test
    void create() {
        // WHEN
        underTest.create(offer);

        //THEN
        new Verifications() {{
            offerService.create(offer);
        }};
    }

    @Test
    void getOffers() {
        // WHEN
        underTest.getAll();

        //THEN
        new Verifications() {{
            val actual = offerService.findAllEnabledNotExpired();
            assertThat(actual).isNotNull();
        }};
    }

    @Test
    void disableOffer() {
        // WHEN
        underTest.disable(0L);

        //THEN
        new Verifications() {{
            offerService.disable(0L);
        }};
    }
}