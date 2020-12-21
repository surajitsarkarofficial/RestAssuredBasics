package com.qa.test;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class FileDownloadTest {


    /**
     * We have a expected file in src/tes/resources
     * We are fetching its length in bytes
     * Then we are hitting the url to download the file
     * and fetching its length in bytes from the response
     * and then comparing the size
     */
    @Test
    public void fileDownloadTest()
    {
        File file = new File(
                System.getProperty("user.dir")+File.separator+"src"+
                        File.separator+"test"+File.separator+"resources"+File.separator
                +"geckodriver-v0.28.0-win32.zip"
        );

        int expectedSize = (int) file.length();


        byte[] actualValue = RestAssured.given()
                    .when()
                .get("https://github.com/mozilla/geckodriver/releases/download/v0.28.0/geckodriver-v0.28.0-win32.zip")
                .then().log().status().log().headers().extract().asByteArray();

        Assert.assertEquals(actualValue.length,expectedSize);

    }
}
