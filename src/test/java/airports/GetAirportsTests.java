package airports;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;

public class GetAirportsTests extends BaseTest{


    Response resp;
    @BeforeMethod
    public void setup(){
        resp = RestAssured.given(getRequestSpec()).when().get("/airports");
    }
    @Test
    public void VerifyValidGetAirportsPathReturns200() {
        resp.then().assertThat().statusCode(200);
    }

    @Test
    public void VerifyInvalidGetAirportsPathReturns404() {
        RestAssured.given(getRequestSpec())
                .when().get("/airportz")
                .then()
                .assertThat().statusCode(404);
    }

    @Test
    public void VerifyGetAirportsMatchesJSONSchema(){
        resp.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/airports.json")
                ));
    }

    @Test
    public void VerifyGetAirportsResponseHeaders(){
        RestAssured.given(getRequestSpec())
                .when().get("/airports")
                .then()
                .assertThat().contentType(ContentType.JSON)
                .assertThat().header("Content-Encoding", "gzip")
                .assertThat().header("Server","cloudflare");
    }

    @Test
    public void VerifyGetAirportsResponseTimeUnder500ms(){
        RestAssured.given(getRequestSpec())
                .when().get("/airports")
                .then()
                .assertThat().time(Matchers.lessThan(500L));
    }


}