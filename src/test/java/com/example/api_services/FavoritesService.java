package com.example.api_services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FavoritesService {

    private final RequestSpecification reqSpec;

    public FavoritesService(RequestSpecification reqSpec) {
        this.reqSpec = reqSpec;
    }

    public Response getFavorites() {
        return RestAssured.given(reqSpec).when().get("/favorites");
    }
}
