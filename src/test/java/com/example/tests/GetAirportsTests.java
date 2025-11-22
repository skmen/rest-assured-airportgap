package com.example.tests;

import com.example.utils.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.example.api_services.AirportsService;

import java.io.File;

public class GetAirportsTests extends BaseTest{

    private AirportsService airportsService;

    @BeforeClass
    public void setup() {
        super.setup();
        airportsService = new AirportsService(reqSpec);
    }

    @Test
    public void VerifyValidGetAirportsPathReturns200() {
        airportsService.getAirports().then().assertThat().statusCode(200);
    }

    @Test
    public void VerifyInvalidGetAirportsPathReturns404() {
        airportsService.getAirportById("airportz")
                .then()
                .assertThat().statusCode(404);
    }

    @Test
    public void VerifyGetAirportsMatchesJSONSchema(){
        airportsService.getAirports().then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/data/airports.json")
                ));
    }

    @Test
    public void VerifyGetAirportsResponseHeaders(){
        airportsService.getAirports()
                .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().header("Content-Encoding", "gzip")
    }

    @Test
    public void VerifyGetAirportsResponseTimeUnder500ms(){
        airportsService.getAirports()
                .then()
                .assertThat().time(Matchers.lessThan(500L));
    }
}