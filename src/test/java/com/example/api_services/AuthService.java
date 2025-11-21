package com.example.api_services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class AuthService {

    private final RequestSpecification reqSpec;

    public AuthService(RequestSpecification reqSpec) {
        this.reqSpec = reqSpec;
    }

    public Response getAuthToken(String email, String password) {
        return RestAssured.given(reqSpec)
                .body(createPostBody(email, password))
                .post("/tokens");
    }

    private String createPostBody(String email, String password){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        return jsonObject.toString();
    }
}
