package com.qa.test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class MockAPITest {

    private static int PORT = 8085;
    private static String HOST="localhost";
    private static String BasePATH = "student";

    WireMockServer server = new WireMockServer(PORT);

    @BeforeClass
    public void beforeClass()
    {
        server.start();
        WireMock.configureFor(HOST,PORT);

    }


    @Test
    public void mockTestSimpleGet()
    {
        WireMock.stubFor(WireMock.get("/student/list")
                .willReturn(new ResponseDefinitionBuilder().withStatus(200))
        );
        RestAssured.given().log().all()
                .when().get("http://localhost:8085/student/list").then().log().all().statusCode(200);
        System.out.println("Mock completed..");
    }

    @Test
    public void mockTestGetWithPathParam()
    {
        WireMock.stubFor(WireMock.get("/student/1")
                .willReturn(new ResponseDefinitionBuilder().withStatus(200))
        );
        RestAssured.given().contentType(ContentType.JSON)
                .when().pathParam("id",1)
                .get("http://localhost:8085/student/{id}").then().log().all().statusCode(200);
        System.out.println("Mock completed..");
    }

    @Test
    public void mockTestGetWithBody()
    {
        WireMock.stubFor(WireMock.get("/student/1")
                .willReturn(new ResponseDefinitionBuilder().withStatus(200).withHeader("Content-Type","application/json")
                        .withBody("" +
                        "{\"id\":1,\"firstName\":\"Vernon\",\"lastName\":\"Harper\",\"email\":\"egestas.rhoncus.Proin@massaQuisqueporttitor.org\",\"programme\":\"Financial Analysis\",\"courses\":[\"Accounting\",\"Statistics\"]}"))
        );
        RestAssured.given().log().all()
                .when().pathParam("id",1)
                .get("http://localhost:8085/student/{id}")
                .then().log().all().statusCode(200)
                .body(
                        "programme", equalTo("Financial Analysis"),
                        "courses", hasItem("Statistics")
                );
        System.out.println("Mock Body completed..");
    }

    @AfterTest
    public void tearDown()
    {
        if(server!=null || server.isRunning())
        {
            server.stop();
            server=null;
        }
    }





}
