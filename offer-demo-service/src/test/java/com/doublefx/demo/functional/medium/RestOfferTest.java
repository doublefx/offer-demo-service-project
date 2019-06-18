package com.doublefx.demo.functional.medium;

import com.doublefx.demo.functional.medium.steps.StepBasedTest;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

class RestOfferTest extends StepBasedTest {
    private static final String JSON_OFFER = "json/offer/";

    @Test
    void shouldSaveAndRetrieveOffers() throws Exception {
        //GIVEN
        val base = JSON_OFFER + "shouldSaveAndRetrieveOffers/";

        val expectedOffers = readFile(base + "expectedOffers.json");
        val activeOffer = readFile(base + "activeOffer.json");
        val expiredOffer = readFile(base + "expiredOffer.json");

        offerServiceSteps.create(activeOffer);
        offerServiceSteps.create(expiredOffer);

        //WHEN
        val all = offerServiceSteps.getAll();

        //THEN
        assertEquals(expectedOffers, all, true);
    }

    @Test
    void shouldDisableOffers() throws Exception {
        //GIVEN
        val base = JSON_OFFER + "shouldDisableOffer/";

        val expectedOffers = readFile(base + "expectedOffers.json");
        val offer1 = readFile(base + "offer1.json");

        offerServiceSteps.create(offer1);

        //WHEN
        offerServiceSteps.disable(3L);

        //THEN
        val all = offerServiceSteps.getAll();
        assertEquals(expectedOffers, all, true);
    }
}
