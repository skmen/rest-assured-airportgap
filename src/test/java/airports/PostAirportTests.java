package airports;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class PostAirportTests extends BaseTest{


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

        for(int i=0; i< toAirport.length;i++) {
            if(fromAirport.equals(toAirport[i]))continue;
            Response resp = RestAssured.given(getRequestSpec())
                    .body(createPostBody(fromAirport, toAirport[i]))
                    .post("/airports/distance");
            Assert.assertEquals(200, resp.statusCode());
        }
    }

    @Test
    public void VerifyPostAirportDistanceReturns422(){
        Response resp = RestAssured.given(getRequestSpec())
                .body(createPostBody("HGU","ZZZ"))
                .post("/airports/distance");

        Assert.assertEquals(422, resp.statusCode());
    }

    @Test
    public void PostAirportDistanceValidateJsonSchema(){
        RestAssured.given(getRequestSpec())
                .body(createPostBody("HGU","YBK"))
                .post("/airports/distance")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/airportdistance.json"))
                );

    }

    private String createPostBody(String fromAirport, String toAirport){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("from", fromAirport);
        jsonObject.put("to", toAirport);
        return jsonObject.toString();
    }

}
