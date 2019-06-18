package com.doublefx.demo.functional.medium.steps;

import io.restassured.http.ContentType;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Component
public class OfferServiceSteps implements ApplicationListener<WebServerInitializedEvent> {

    private int servicePort;

    public void create(String body) {
        given().port(servicePort).body(body).contentType(ContentType.JSON)
                .when().put("offer/create")
                .then().statusCode(200);
    }

    public String getAll() {
        return given().port(servicePort)
                .when().get("offer/getAll")
                .then().statusCode(200).contentType(ContentType.JSON).extract().asString();
    }

    public void disable(Long offerId) {
        given().port(servicePort)
                .when().get("offer/disable?offerId=" + offerId)
                .then().statusCode(200);
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.servicePort = webServerInitializedEvent.getWebServer().getPort();
    }
}
