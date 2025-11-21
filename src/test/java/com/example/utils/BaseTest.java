package com.example.utils;

import com.example.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification reqSpec;

    @BeforeClass
    public void setup() {
        reqSpec = new RequestSpecBuilder().setBaseUri(ConfigReader.getInstance().getBaseUrl())
                .setBasePath("/api")
                .addHeader("Authorization", "Bearer " + ConfigReader.getInstance().getToken())
                .setContentType(ContentType.JSON).build();
    }
}
