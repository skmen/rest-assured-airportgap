package com.example.api_services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class AirportsService {

    private final RequestSpecification reqSpec;

    public AirportsService(RequestSpecification reqSpec) {
        this.reqSpec = reqSpec;
    }

    public Response getAirports() {
        return RestAssured.given(reqSpec).when().get("/airports");
    }

    public Response getAirportById(String airportId) {
        return RestAssured.given(reqSpec).when().get("/airports/" + airportId);
    }

    public Response getAirportDistance(String from, String to) {
        return RestAssured.given(reqSpec)
                .body(createPostBody(from, to))
                .post("/airports/distance");
    }

    private String createPostBody(String fromAirport, String toAirport){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("from", fromAirport);
        jsonObject.put("to", toAirport);
        return jsonObject.toString();
    }
}
