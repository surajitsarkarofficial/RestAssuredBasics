package com.qa.test;

import com.github.javafaker.Faker;
import com.qa.pojo.StudentPOJO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BasicMethodsTest extends BaseTest{



    @Test
    public void t1()
    {
        RequestSpecification reqSpec = RestAssured.with();

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("programme","Computer Science");
        params.put("limit",1);

        reqSpec.queryParams(params);
        Response response = reqSpec.get("list");
        response.prettyPrint();
        ValidatableResponse validatableResponse = response.then();

        validatableResponse.statusCode(200);
    }

    @Test
    public void t2()
    {
        RequestSpecification reqSpec = RestAssured.with();

        reqSpec.pathParam("id",1);
        //reqSpec.queryParam("programme","Computer Science");

        Response response = reqSpec.get("/{id}");
        response.prettyPrint();
        ValidatableResponse validatableResponse = response.then();

        validatableResponse.statusCode(200);
    }

    //@Test
    public void rs_POST()
    {
        String payload = "{\n" +
                "    \"firstName\": \"Murphy\",\n" +
                "    \"lastName\": \"Holmes\",\n" +
                "    \"email\": \"faucibus.orci.luctus8@Duisac.net\",\n" +
                "    \"programme\": \"Financial Analysis\",\n" +
                "    \"courses\": [\n" +
                "        \"Accounting\",\n" +
                "        \"Statistics\"\n" +
                "    ]\n" +
                "}";
        /*RestAssured.given().
                when().contentType(ContentType.JSON)
                    .body(payload)
                    .post()
                .then()
                    .statusCode(201);*/

        RequestSpecification rs = RestAssured.given();
        Response res = rs.contentType(ContentType.JSON).body(payload).post();
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(),201);
    }

    @Test
    public void test_POJO_POST()
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
                .then().statusCode(201);

    }

    @Test
    public void test_PUT()
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
                .body(student).put("/1")
                .then().statusCode(200);

    }

    @Test
    public void test_PATCH()
    {
        StudentPOJO student = new StudentPOJO();
        Faker faker = new Faker();


        student.setEmail(faker.internet().emailAddress());

        RestAssured.given().when().contentType(ContentType.JSON)
                .body(student).patch("/1")
                .then().statusCode(200);

    }

    @Test
    public void test_DELETE()
    {
        RestAssured.given().when().contentType(ContentType.JSON)
                .delete("/100")
                .then().statusCode(204);

    }



}
