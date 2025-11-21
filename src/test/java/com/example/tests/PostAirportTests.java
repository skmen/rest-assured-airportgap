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
        String[] toAirport = new String[]{"HGU","LAE", "POM", "WWK", "UAK", "GOH",
                "SFJ", "THU", "AEY", "EGS", "HFN", "HZK", "IFJ", "KEF",
                "PFJ", "RKV", "SIJ", "VEY", "YAM", "YAY", "YAZ", "YBB",
                "YBC", "YBG", "YBK", "YBL", "YBR", "YCB"};

        for (String s : toAirport) {
            if (fromAirport.equals(s)) continue;
            Response resp = airportsService.getAirportDistance(fromAirport, s);
            Assert.assertEquals(200, resp.statusCode());
        }
    }

    @Test
    public void VerifyPostAirportDistanceReturns422(){
        Response resp = airportsService.getAirportDistance("HGU", "ZZZ");
        Assert.assertEquals(422, resp.statusCode());
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
