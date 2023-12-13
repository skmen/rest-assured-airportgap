package airports;

import helper.ConfigReader;
import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class PostTokensTests extends BaseTest{


    @Test
    public void VerifyPostTokenReturnsValidToken(){
        RestAssured.given(getRequestSpec())
                .body(createPostBody(ConfigReader.getInstance().getEmail(), ConfigReader.getInstance().getPassword()))
                .post("/tokens")
                .then()
                .assertThat()
                .extract()
                .path("token")
                .equals(ConfigReader.getInstance().getToken());
    }
    @Test
    public void VerifyPostTokenReturns200Status(){
        RestAssured.given(getRequestSpec())
                .body(createPostBody(ConfigReader.getInstance().getEmail(), ConfigReader.getInstance().getPassword()))
                .post("/tokens")
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void VerifyPostTokenReturns401StatusWithInvalidPassword(){
        RestAssured.given(getRequestSpec())
                .body(createPostBody(ConfigReader.getInstance().getEmail(),"password"))
                .post("/tokens")
                .then()
                .assertThat().statusCode(401);
    }
    @Test
    public void VerifyPostTokenReturns401StatusWithInvalidEmail(){
        RestAssured.given(getRequestSpec())
                .body(createPostBody("abc@test.com", ConfigReader.getInstance().getPassword()))
                .post("/tokens")
                .then()
                .assertThat().statusCode(401);
    }
    private String createPostBody(String email, String password){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        return jsonObject.toString();
    }

}
