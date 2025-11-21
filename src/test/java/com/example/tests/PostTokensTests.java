package com.example.tests;

import com.example.utils.BaseTest;
import com.example.utils.ConfigReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.example.api_services.AuthService;

public class PostTokensTests extends BaseTest{

    private AuthService authService;

    @BeforeClass
    public void setup() {
        super.setup();
        authService = new AuthService(reqSpec);
    }

    @Test
    public void VerifyPostTokenReturnsValidToken(){
        authService.getAuthToken(ConfigReader.getInstance().getEmail(), ConfigReader.getInstance().getPassword())
                .then()
                .assertThat()
                .extract()
                .path("token")
                .equals(ConfigReader.getInstance().getToken());
    }
    @Test
    public void VerifyPostTokenReturns200Status(){
        authService.getAuthToken(ConfigReader.getInstance().getEmail(), ConfigReader.getInstance().getPassword())
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void VerifyPostTokenReturns401StatusWithInvalidPassword(){
        authService.getAuthToken(ConfigReader.getInstance().getEmail(),"password")
                .then()
                .assertThat().statusCode(401);
    }
    @Test
    public void VerifyPostTokenReturns401StatusWithInvalidEmail(){
        authService.getAuthToken("abc@test.com", ConfigReader.getInstance().getPassword())
                .then()
                .assertThat().statusCode(401);
    }
}
