package com.Notes;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class All_Login extends Notes_TestData{
	@Test(dataProvider="All_Login")
	  public void Login_All(String email,String password, String exp_res) {
			RestAssured.baseURI = "https://practice.expandtesting.com";
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();
			requestParams.put("email",email);
			requestParams.put("password", password);
			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			request.body(requestParams.toJSONString());
			// POST the Response
			Response response = request.request(Method.POST, "/notes/api/users/login");
			// Response response = request.request(Method.POST,"/spree_oauth/token");
			response.prettyPrint();
			int statusCode = response.getStatusCode();
			// System.out.println(response.asString());
			Assert.assertEquals(statusCode, 400,200);
			// To get the Token from JSON Response
			JsonPath jsonPathEvaluator = response.getBody().jsonPath();
			String act_msg = jsonPathEvaluator.get("message").toString();
			Assert.assertEquals(act_msg, exp_res);

}
}
