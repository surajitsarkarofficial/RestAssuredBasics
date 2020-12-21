package com.qa.test;

import com.github.javafaker.Faker;
import com.qa.pojo.StudentPOJO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class ResponseLoggerTest extends BaseTest{

    @Test
    public void logResponseHeaders()
    {
        RestAssured.given()
                .when().contentType(ContentType.JSON)
                .get("/1")
                .then().log().headers().statusCode(200);

    }
    @Test
    public void logResponseStatus()
    {
        RestAssured.given()
                .when().contentType(ContentType.JSON).queryParams("limit",1)
                .pathParam("id",1)
                .get("{id}")
                .then().
                log().status().statusCode(200);

    }

    @Test
    public void logResponseBody()
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
                .body(student).post()
                .then()
                .log().body().
                        statusCode(201);

    }

    @Test
    public void logAllResponseDetails()
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
                .body(student).post()
                .then()
                .log().all()
                        .statusCode(201);

    }

    @Test
    public void logResponseDetialsIfValidationFails()
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
                .body(student).post()
                .then().
                log().ifValidationFails()
                .statusCode(200);

    }

}
