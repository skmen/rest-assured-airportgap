package com.example.tests;

import com.example.utils.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.example.api_services.AirportsService;

import java.io.File;

public class PostAirportTests extends BaseTest{

    private AirportsService airportsService;

    @BeforeClass
    public void setup() {
        super.setup();
        airportsService = new AirportsService(reqSpec);
    }

    @DataProvider(name = "airport_ids")
    public Object[][] airportId(){
        return new Object[][]{{"HGU"}, {"LAE"}, {"POM"}, {"WWK"}, {"UAK"}};
    }

    @Test(dataProvider = "airport_ids")
    public void VerifyPostAirportDistanceReturns200(String fromAirport){
        Response resp = airportsService.getAirportDistance(fromAirport, "YBK");
        Assert.assertEquals(resp.statusCode(), 200);
    }

    @Test
    public void VerifyPostAirportDistanceReturns422(){
        Response resp = airportsService.getAirportDistance("HGU", "ZZZ");
        Assert.assertEquals(resp.statusCode(), 422);
    }

    @Test
    public void PostAirportDistanceValidateJsonSchema(){
        airportsService.getAirportDistance("HGU", "YBK")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/data/airportdistance.json"))
                );
    }
}
