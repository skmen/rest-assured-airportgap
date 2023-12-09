package airports;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class GetAirportTests extends BaseTest{

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

    @Test
    public void GetAirportsReturns200Status() {
        Response resp = RestAssured.given(getRequestSpec())
                        .when().get("/airports")
                        .then()
                        .extract().response();

        Assert.assertEquals(200,resp.statusCode());
    }

    @Test
    public void GetAirportsReturns404Status() {
        Response resp = RestAssured.given(getRequestSpec())
                .when().get("/airportz")
                .then()
                .extract().response();

        Assert.assertEquals(404,resp.statusCode());
    }

    @Test
    public void GetAirportsValidateJsonSchema(){
        RestAssured.given(getRequestSpec())
                .when().get("/airports")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/airports.json")
                ));

    }

    @Test (dataProvider = "airport_ids")
    public void GetAirportValidateJsonSchema(String airportID){
        RestAssured.given(getRequestSpec())
                .when().get("/airports/"+airportID)
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/airport.json")
                ));
    }

    @Test (dataProvider = "airport_ids")
    public void GetAirportByIDReturns200Status(String airportID){
        Response resp = RestAssured.given(getRequestSpec())
                .when().get("/airports/"+airportID)
                .then()
                .extract().response();

        Assert.assertEquals(200, resp.statusCode());

    }


    @Test (dataProvider = "bad_data")
    public void GetAirportsByIDReturns404Status(String airportID){
        Response resp = RestAssured.given(getRequestSpec())
                .when().get("/airports/"+airportID)
                .then()
                .extract().response();

        Assert.assertEquals(404, resp.statusCode());

    }
}