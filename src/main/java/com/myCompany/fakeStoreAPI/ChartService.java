package com.myCompany.fakeStoreAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class ChartService {
    String baseURL = "https://fakestoreapi.com";
    private HashMap<String, String> collections;
    private Response response;
    private RequestSpecification httpRequest;

    public ChartService() {
        RestAssured.baseURI = baseURL;
        httpRequest = RestAssured.given();
    }

    public ChartService setAllCart() {
        response = httpRequest.request(Method.GET, "/carts" );
        return this;
    }

    public ChartService setSingleCart(String numId) {
        response = httpRequest.request(Method.GET, "/carts/" + numId);
        return this;
    }

    public ChartService setQueryParams(String quaryParam1, String valueParam1) {
        response =  response = httpRequest.queryParam(quaryParam1, valueParam1).request(Method.GET, "/carts");
        return this;
    }

    public ChartService setQueryParams(String quaryParam1, String valueParam1,String quaryParam2, String valueParam2) {
        response =  response = httpRequest.queryParam(quaryParam1, valueParam1).queryParam(quaryParam2, valueParam2).request(Method.GET, "/carts");
        return this;
    }

    public ChartService setCartBaseOnUser(String userId) {
        response = httpRequest.request(Method.GET, "/carts/user/" + userId);
        return this;
    }

    public ChartService createCart(JSONObject product) {
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(product.toJSONString());
        response = httpRequest.post("/carts");
        return this;
    }


    public ResponseBody getResponseBody() {
        return response.getBody();
    }

    public Object getResponseStatusline() {
        return response.getStatusLine();
    }

    public JsonPath asJSON() {
        return response.jsonPath();
    }



}
