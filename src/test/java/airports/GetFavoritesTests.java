package airports;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class GetFavoritesTests extends BaseTest {

    @Test
    public void VerifyGetFavoritesReturns200Status(){
        RestAssured.given(getRequestSpec())
                .when().get("/favorites")
                .then()
                .assertThat().statusCode(200);
    }
}
