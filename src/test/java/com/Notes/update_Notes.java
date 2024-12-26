package com.Notes;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class update_Notes {
	 String outh_token;
	 String notes_id;
		
		@BeforeTest
		public void getToken() {

			RestAssured.baseURI = "https://practice.expandtesting.com";
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();
			requestParams.put("email", "anurag@xyz.com");
			requestParams.put("password", "1234567890");
			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			request.body(requestParams.toJSONString());
			// POST the Response
			Response response = request.request(Method.POST, "/notes/api/users/login");
			// Response response = request.request(Method.POST,"/spree_oauth/token");
			response.prettyPrint();
			int statusCode = response.getStatusCode();
			// System.out.println(response.asString());
			Assert.assertEquals(statusCode, 200);
			// To get the Token from JSON Response
			JsonPath jsonPathEvaluator = response.getBody().jsonPath();
			outh_token = jsonPathEvaluator.get("data.token").toString();
			System.out.println("oAuth Token is =>  " + outh_token);

			String success_msg = jsonPathEvaluator.get("message").toString();
			Assert.assertEquals(success_msg, "Login successful");

		}
		
		
		@Test(priority=1)
		public void createNotes() {
			

			RestAssured.baseURI = "https://practice.expandtesting.com";
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();
			requestParams.put("title", "TestNG_API");
			requestParams.put("description", "Done via RestAssured");
			requestParams.put("category", "Home");
			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			request.header("x-auth-token",outh_token);
			request.body(requestParams.toJSONString());
			// POST the Response
			Response response = request.request(Method.POST, "/notes/api/notes");
			// Response response = request.request(Method.POST,"/spree_oauth/token");
			response.prettyPrint();
			int statusCode = response.getStatusCode();
			// System.out.println(response.asString());
			Assert.assertEquals(statusCode, 200);
			// To get the Token from JSON Response
			JsonPath jsonPathEvaluator = response.getBody().jsonPath();
			String success_msg = jsonPathEvaluator.get("message").toString();
			Assert.assertEquals(success_msg, "Note successfully created");
			
			
			notes_id = jsonPathEvaluator.get("data.id").toString();
			System.out.println("notes id is =>  " + notes_id);
			

		}
		@Test(priority=2)
		public void updateNotes() {

			RestAssured.baseURI = "https://practice.expandtesting.com";
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();
			requestParams.put("title", "TestNG_API_updating");
			requestParams.put("description", "Done via RestAssured_updated");
			requestParams.put("category", "Personal");
			requestParams.put("completed", "true");
			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			request.header("x-auth-token",outh_token);
			request.body(requestParams.toJSONString());
			// POST the Response
			Response response = request.request(Method.PUT, "/notes/api/notes/"+ notes_id);
			// Response response = request.request(Method.POST,"/spree_oauth/token");
			response.prettyPrint();
			int statusCode = response.getStatusCode();
			// System.out.println(response.asString());
			Assert.assertEquals(statusCode, 200);
			// To get the Token from JSON Response
			JsonPath jsonPathEvaluator = response.getBody().jsonPath();
			String success_msg = jsonPathEvaluator.get("message").toString();
			Assert.assertEquals(success_msg, "Note successfully Updated");
			
			String act_title = jsonPathEvaluator.get("data.title").toString();
			Assert.assertEquals(act_title, "TestNG_API_updating");

			notes_id = jsonPathEvaluator.get("data.id").toString();
			System.out.println("notes id is =>  " + notes_id);
		}
		@Test(priority=3)
		public void deleteNotes() {

			RestAssured.baseURI = "https://practice.expandtesting.com";
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();
			requestParams.remove("id", notes_id);
			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			request.header("x-auth-token",outh_token);
			request.body(requestParams.toJSONString());
			// POST the Response
			Response response = request.request(Method.DELETE, "/notes/api/notes/"+ notes_id);
			// Response response = request.request(Method.POST,"/spree_oauth/token");
			response.prettyPrint();
			int statusCode = response.getStatusCode();
			// System.out.println(response.asString());
			Assert.assertEquals(statusCode, 200);
			// To get the Token from JSON Response
			JsonPath jsonPathEvaluator = response.getBody().jsonPath();
			String success_msg = jsonPathEvaluator.get("message").toString();
			Assert.assertEquals(success_msg, "Note successfully deleted");
			
		
}

}

