package com.authentication.authenticationService;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.response.Response;

import dataProvider.Excel2Json;

public class VerifyPasswordResetTokenAPITest extends BaseAuthenticationServiceApi {

	@Test(dataProvider = "getRequestJSON", dataProviderClass = Excel2Json.class)
	public void testVerifyPasswordResetTokenAPI(JSONObject requestJSON)
			throws JSONException, InterruptedException, JsonProcessingException {

		Response responseJSON = getAPIResponse(requestJSON, "verifyPasswordResetToken", "POST");

		if (responseJSON.getStatusCode() == 200) {

			Assert.assertEquals(responseJSON.jsonPath().getString("Params.ResetToken"),
					requestJSON.getJSONObject("Params").getString("ResetToken"), "ResetToken not found on Response Params");
			Assert.assertNull(responseJSON.jsonPath().getString("Data"), "Response Data is not null");
			
		} else {
			
			Assert.assertEquals(responseJSON.jsonPath().getString("Params.ResetToken"),
					requestJSON.getJSONObject("Params").getString("ResetToken"), "UserName not found on Response Params");
			Assert.assertNull(responseJSON.jsonPath().getString("Data"), "Response Data is not null");
			Assert.assertNotNull(responseJSON.jsonPath().getString("Reasons.ReasonCode"));
		}

		System.out.println("**********Response from [verifyPasswordResetToken]**********");
		System.out.println(responseJSON.getBody().asString());
		System.out.println();

	}

}
