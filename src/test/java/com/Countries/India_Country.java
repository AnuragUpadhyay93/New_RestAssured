package com.Countries;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class India_Country {
  @Test
  public void India_Country() {
	  RestAssured.baseURI = "https://demo.spreecommerce.org";
		RequestSpecification httpRequest = RestAssured.given();
		// Response response = httpRequest.get();
		Response response = httpRequest.request(Method.GET, "/api/v2/storefront/countries/ind");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		// Status Code Validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is =>  " + statusCode);
		Assert.assertEquals(200, statusCode);
	      JsonPath jsonPathEvaluator = response.getBody().jsonPath();
		  String iso_name_act=jsonPathEvaluator.get("data.attributes.iso_name").toString();
		  Assert.assertEquals(iso_name_act, "INDIA");
		  
		  String name_act=jsonPathEvaluator.get("data.attributes.name").toString();
		  Assert.assertEquals(name_act, "India");
	  
  }
}
