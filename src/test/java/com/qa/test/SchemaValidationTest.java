package com.qa.test;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.File;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class SchemaValidationTest extends BaseTest{



    @Test
    public void jsonSchemaValidator()
    {
        File schemaFile = new File(
                System.getProperty("user.dir") + File.separator+
                        "src"+File.separator+"test"+File.separator+"resources"
                +File.separator+"studentResponseSchema.json"
        );

        RestAssured.given().pathParam("id",1)
                .when().get("{id}")
                .then().body(matchesJsonSchema(schemaFile));
    }
}
