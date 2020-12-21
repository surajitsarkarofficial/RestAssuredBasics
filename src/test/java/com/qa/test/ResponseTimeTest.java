package com.qa.test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class ResponseTimeTest extends BaseTest{

    @Test
    public void logResponseTime()
    {
        long timeInMs = RestAssured.given()
                .queryParams("limit",10)
                .when()
                .get("/list")
                .time();
        System.out.println(timeInMs);

    }
    @Test
    public void logResponseTimeInSeconds()
    {
        long time = RestAssured.given()
                .queryParams("limit",10)
                .when()
                .get("/list")
                .timeIn(TimeUnit.SECONDS);
        System.out.println(time);

    }
    @Test
    public void validateResponseTime()
    {
        RestAssured.given()
                .queryParams("limit",10)
                .when()
                .get("/list")
                .then()
                .time(lessThan(1L),TimeUnit.SECONDS);
    }

    @Test
    public void validateResponseTimeUsinfResponseSpec()
    {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectResponseTime(lessThan(1L),TimeUnit.SECONDS);
        ResponseSpecification resSpec = responseSpecBuilder.build();
        RestAssured.given()
                .queryParams("limit",10)
                .when()
                .get("/list")
                .then()
                .spec(resSpec);
    }
}

