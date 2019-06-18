package com.doublefx.demo.configuration;

import com.doublefx.demo.controller.v1.OfferController;
import com.doublefx.demo.model.Offer;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = OfferController.class)
public class SwaggerConfig {

    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String CONTACT = "Frederic Thomas";
    private static final String GITHUB = "https://github.com/doublefx";
    private static final String EMAIL = "fthomas@apache.org";
    private static final String LICENSE = "Apache License Version 2.0";
    private static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";
    private static final String TITLE = "Offers REST API";
    private static final String DESCRIPTION = "RESTful API for Offers";

    private final TypeResolver typeResolver;

    public SwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .license(LICENSE)
                .licenseUrl(LICENSE_URL)
                .version(SWAGGER_API_VERSION)
                .contact(new Contact(CONTACT, GITHUB, EMAIL))
                .build();
    }

    @Bean
    public Docket offersApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("v1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.doublefx.demo.controller.v1"))
                .paths(PathSelectors.any())
                .build()
                .additionalModels(typeResolver.resolve(Offer.class));
    }
}
