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
    public void GetAirportsByIDReturns200Status(String airportID){
        Response resp = RestAssured.given(getRequestSpec())
                .when().get("/airports/"+airportID)
                .then()
                .extract().response();

        Assert.assertEquals(200, resp.statusCode());

    }

    @DataProvider(name = "bad_data")
    public Object[][] airportId(){
        return new Object[][]{{"/login"}, {"111"}, {"###"}, {"!!!"}, {"@@@"}, {"$$$"},
                {"%%%"}, {"^^^"}, {"&&&"}, {"admin"}};
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