package airports;

import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetAirportTests extends BaseTest{

    private String baseURL = "https://airportgap.com/";

    @Test
    public void GetAirports() {
        RestAssured.given()
                .baseUri(baseURL)
                .when().get("/airports")
                .then()
                .statusCode(200);
    }

    @Test (dataProvider = "airport_ids")
    public void GetAirportsByID(String airportID){
        RestAssured.given(getRequestSpec())
                .when().get("/"+airportID)
                .then()
                .statusCode(200);

        System.out.println("Passed param:" + airportID);

    }
}