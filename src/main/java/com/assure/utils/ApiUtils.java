package com.assure.utils;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
//import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Vipin Alias Neo De Van
 */
public class ApiUtils {
	// Global Setup Variables
	public static String path;
	public static String jsonPathTerm;

	// Sets Base URI
	public static void setBaseURI() {
		RestAssured.baseURI = PropertyManager.getInstance().getBaseUrl();
	}

	
	// Sets base path
	public static void setBasePath(String basePathTerm) {
		RestAssured.basePath = basePathTerm;
	}

	// Reset Base URI (after test)
	public static void resetBaseURI() {
		RestAssured.baseURI = null;
	}

	// Reset base path
	public static void resetBasePath() {
		RestAssured.basePath = null;
	}

	// Sets ContentType
	public static void setContentType(ContentType Type) {
		given().contentType(Type);
	}

	
	public static Response getResponseBody() {
		return given().accept("application/json").contentType(ContentType.JSON).queryParam("catalogue", false).when().get("/v1/Categories/6327/Details.json");
	}

	// Returns response
	public static Response getResponse() {
		return get();
	}
	
	public static String getJsonPath(Response res) {
		String json = res.asString();
		return json;
	}
	

}