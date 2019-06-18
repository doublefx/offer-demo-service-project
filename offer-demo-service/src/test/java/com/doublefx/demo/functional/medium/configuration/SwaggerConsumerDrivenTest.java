package com.doublefx.demo.functional.medium.configuration;

import com.doublefx.demo.functional.medium.common.BaseFunctionalTest;
import io.github.robwin.swagger.test.SwaggerAssertions;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.FileNotFoundException;

class SwaggerConsumerDrivenTest extends BaseFunctionalTest {

    @LocalServerPort
    private int randomPort;

    @Test
    void validateThatImplementationSatisfiesConsumerSpecificationV1() throws FileNotFoundException {
        val designFirstSwagger = getResource("yaml/swagger-v1.yaml");
        SwaggerAssertions.assertThat("http://localhost:" + randomPort + "/v2/api-docs?group=v1")
                .satisfiesContract(designFirstSwagger.getAbsolutePath());
    }
}
