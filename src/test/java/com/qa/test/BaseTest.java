package com.qa.test;

import com.github.javafaker.Faker;
import com.qa.pojo.StudentPOJO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class BaseTest {

    @BeforeTest()
    public void bt()
    {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port=8085;
        RestAssured.basePath="/student";
    }

}
