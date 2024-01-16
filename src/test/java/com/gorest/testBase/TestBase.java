package com.gorest.testBase;

import com.gorest.utils.PropertyReader;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;


public class TestBase extends TestUtils {
    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseURI");
        RestAssured.basePath = PropertyReader.getInstance().getProperty("basePath");
    }
}

