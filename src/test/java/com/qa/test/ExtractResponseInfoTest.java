package com.qa.test;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractResponseInfoTest extends BaseTest{
    /**
     * {
     *      *         "id": 3,
     *      *         "firstName": "Reece",
     *      *         "lastName": "Jason",
     *      *         "email": "tincidunt.dui@ultricessit.co.uk",
     *      *         "programme": "Computer Science",
     *      *         "courses": [
     *      *             "Calculus",
     *      *             "Algorithms",
     *      *             "Software Development",
     *      *             "Ethics"
     *      *         ]
     *      *     }
     */
    @Test
    public void extractResponseFromJSONObject()
    {
        RequestSpecification reqSpec = RestAssured.with();

        reqSpec.pathParam("id",1);
        Response response = reqSpec.get("{id}");
        response.prettyPrint();
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
        String courseName = validatableResponse.extract().path("courses[0]");
        System.out.println(courseName);
    }

    /**
     * [
     *     {
     *         "id": 3,
     *         "firstName": "Reece",
     *         "lastName": "Jason",
     *         "email": "tincidunt.dui@ultricessit.co.uk",
     *         "programme": "Computer Science",
     *         "courses": [
     *             "Calculus",
     *             "Algorithms",
     *             "Software Development",
     *             "Ethics"
     *         ]
     *     },
     *     {
     *         "id": 4,
     *         "firstName": "Orson",
     *         "lastName": "Armando",
     *         "email": "nascetur@conguea.com",
     *         "programme": "Computer Science",
     *         "courses": [
     *             "Calculus",
     *             "Algorithms",
     *             "Software Development",
     *             "Ethics"
     *         ]
     *     }
     * ]
     */
    @Test
    public void extractResponseFromJSONArray()
    {
        RequestSpecification reqSpec = RestAssured.with();

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("programme","Computer Science");
        params.put("limit",2);

        reqSpec.queryParams(params);
        Response response = reqSpec.get("/list");
        response.prettyPrint();
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
         List<HashMap<String,?>> studentObjects= validatableResponse.extract().path("$");

        System.out.println(studentObjects.size());

        System.out.println(((List<String>) studentObjects.get(0).get("courses")).get(0));




    }
}
