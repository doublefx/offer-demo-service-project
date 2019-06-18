package com.doublefx.demo.functional.medium.steps;

import com.doublefx.demo.functional.medium.common.BaseFunctionalTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

public abstract class StepBasedTest extends BaseFunctionalTest {

    @Autowired
    protected OfferServiceSteps offerServiceSteps;

    @TestConfiguration
    @ComponentScan("com.doublefx.demo.functional.medium.steps")
    public static class StepsConfiguration {
    }
}
