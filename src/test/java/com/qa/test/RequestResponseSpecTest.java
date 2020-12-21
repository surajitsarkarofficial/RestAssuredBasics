package com.qa.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RequestResponseSpecTest extends BaseTest{

    static RequestSpecification reqSpec;
    static ResponseSpecification resSpec;
    static RequestSpecBuilder reqSpecBuilder;
    static ResponseSpecBuilder resSpecBuilder;

    @BeforeClass
    public void beforeClass()
    {
        reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.setContentType(ContentType.JSON);
        reqSpecBuilder.addQueryParam("limit",1);
        reqSpec = reqSpecBuilder.build();

        resSpecBuilder=new ResponseSpecBuilder();
        resSpecBuilder.expectHeader("Content-Type","application/json; charset=utf-8");
        resSpecBuilder.expectStatusCode(200);

    }

    @Test
    public void t1()
    {
        RestAssured.given()
                .when()
                    .spec(reqSpec)
                    .get("/list")
                .then();
    }


}
