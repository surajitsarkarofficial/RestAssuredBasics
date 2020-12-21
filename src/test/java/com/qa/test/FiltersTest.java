package com.qa.test;

import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.PrintStream;
import java.io.StringWriter;

public class FiltersTest extends BaseTest{

    PrintStream reqCapture, resCapture, errCapture;
    StringWriter reqWritter,resWritter,errWritter;

    @BeforeMethod
    public void beforeMethod()
    {
        reqWritter=new StringWriter();
        reqCapture = new PrintStream(new WriterOutputStream(reqWritter),true);

        resWritter=new StringWriter();
        resCapture = new PrintStream(new WriterOutputStream(resWritter),true);

        errWritter=new StringWriter();
        errCapture = new PrintStream(new WriterOutputStream(errWritter),true);


    }

    @Test
    public void test1()
    {
        RestAssured.given()
                    .when()
                            .queryParams("limit",2)
                            .filter(new RequestLoggingFilter(reqCapture))
                            .filter(new ResponseLoggingFilter(resCapture))
                            .filter(new ErrorLoggingFilter(errCapture))
                        .get("/list")
                        .then().statusCode(200);
        System.out.println(reqWritter.toString());
        System.out.println(resWritter.toString());
        System.out.println(errWritter.toString());


    }

}
