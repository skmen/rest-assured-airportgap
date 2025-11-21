package com.example.tests;

import com.example.utils.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.example.api_services.AirportsService;

import java.io.File;

public class GetAirportsByIdTests extends BaseTest{

    private AirportsService airportsService;

    @BeforeClass
    public void setup() {
        super.setup();
        airportsService = new AirportsService(reqSpec);
    }

    @DataProvider(name = "airport_ids")
    public Object[][] airportId(){
        return new Object[][]{{"HGU"}, {"LAE"}, {"POM"}, {"WWK"}, {"UAK"}, {"GOH"},
                {"SFJ"}, {"THU"}, {"AEY"}, {"EGS"}, {"HFN"}, {"HZK"}, {"IFJ"}, {"KEF"},
                {"PFJ"}, {"RKV"}, {"SIJ"}, {"VEY"}, {"YAM"}, {"YAY"}, {"YAZ"}, {"YBB"},
                {"YBC"}, {"YBG"}, {"YBK"}, {"YBL"}, {"YBR"}, {"YCB"}};
    }

    @DataProvider(name = "bad_data")
    public Object[][] badData(){
        return new Object[][]{{"/login"}, {"111"}, {"###"}, {"!!!"}, {"@@@"}, {"$$$"},
                {"%%%"}, {"^^^"}, {"&&&"}, {"admin"}};
    }

    @Test (dataProvider = "airport_ids")
    public void GetAirportByIDReturns200Status(String airportID){
        airportsService.getAirportById(airportID)
                .then()
                .assertThat().statusCode(200);
    }

    @Test (dataProvider = "bad_data")
    public void GetAirportsByIDReturns404Status(String airportID){
        airportsService.getAirportById(airportID)
                .then()
                .assertThat().statusCode(404);
    }
    @Test(dataProvider = "airport_ids")
    public void GetAirportValidateJsonSchema(String airportID){
        airportsService.getAirportById(airportID)
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/data/airport.json")
                ));
    }

    @Test (dataProvider = "airport_ids")
    public void VerifyGetAirportByIDResponseTimeUnder500ms(String airportID){
        airportsService.getAirportById(airportID)
                .then()
                .assertThat().time(Matchers.lessThan(500L));
    }
}
