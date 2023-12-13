package airports;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class GetAirportsByIdTests extends BaseTest{

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

    RequestSpecification reqSpec;
    @BeforeMethod
    public void setup(){
        reqSpec = RestAssured.given(getRequestSpec());
    }

    @Test (dataProvider = "airport_ids")
    public void GetAirportByIDReturns200Status(String airportID){
        reqSpec.when().get("/airports/"+airportID)
                .then()
                .assertThat().statusCode(200);
    }

    @Test (dataProvider = "bad_data")
    public void GetAirportsByIDReturns404Status(String airportID){
        reqSpec.when().get("/airports/"+airportID)
                .then()
                .assertThat().statusCode(404);
    }
    @Test(dataProvider = "airport_ids")
    public void GetAirportValidateJsonSchema(String airportID){
        reqSpec.when().get("/airports/"+airportID)
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/airport.json")
                ));
    }

    @Test (dataProvider = "airport_ids")
    public void VerifyGetAirportByIDResponseTimeUnder500ms(String airportID){
        reqSpec.when().get("/airports/"+airportID)
                .then()
                .assertThat().time(Matchers.lessThan(500L));
    }
}
