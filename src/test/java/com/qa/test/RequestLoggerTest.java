package com.qa.test;

import com.github.javafaker.Faker;
import com.qa.pojo.StudentPOJO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class RequestLoggerTest extends BaseTest{

    @Test
    public void logHeaders()
    {
        RestAssured.given().log().headers()
                .when().contentType(ContentType.JSON)
                .get("/1")
                .then().statusCode(200);

    }
    @Test
    public void logParams()
    {
        RestAssured.given().log().headers().log().params()
                .when().contentType(ContentType.JSON).queryParams("limit",1)
                .pathParam("id",1)
                .get("{id}")
                .then().statusCode(200);

    }

    @Test
    public void logReqBody()
    {
        StudentPOJO student = new StudentPOJO();
        Faker faker = new Faker();


        student.setFirstName(faker.name().firstName());
        student.setLastName(faker.name().lastName());
        student.setEmail(faker.internet().emailAddress());
        student.setProgramme("Computer Science");
        List<String> courseList = new LinkedList<String>();
        courseList.add("JAVA");
        courseList.add("SELENIUM");
        student.setCourses(courseList);

        RestAssured.given().when().contentType(ContentType.JSON)
                .log().body()
                .body(student).post()
                .then().statusCode(201);

    }

    @Test
    public void logAllDetails()
    {
        StudentPOJO student = new StudentPOJO();
        Faker faker = new Faker();


        student.setFirstName(faker.name().firstName());
        student.setLastName(faker.name().lastName());
        student.setEmail(faker.internet().emailAddress());
        student.setProgramme("Computer Science");
        List<String> courseList = new LinkedList<String>();
        courseList.add("JAVA");
        courseList.add("SELENIUM");
        student.setCourses(courseList);

        RestAssured.given().when().contentType(ContentType.JSON)
                .log().all()
                .body(student).post()
                .then().statusCode(201);

    }

    @Test
    public void logIfValidationFails()
    {
        StudentPOJO student = new StudentPOJO();
        Faker faker = new Faker();


        student.setFirstName(faker.name().firstName());
        student.setLastName(faker.name().lastName());
        student.setEmail(faker.internet().emailAddress());
        student.setProgramme("Computer Science");
        List<String> courseList = new LinkedList<String>();
        courseList.add("JAVA");
        courseList.add("SELENIUM");
        student.setCourses(courseList);

        RestAssured.given().when().contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body(student).post()
                .then().statusCode(200);

    }

}
