package com.authorize.setupService;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import dataProvider.Excel2Json;

public class UpdateRoleAPITest extends BaseSetupServiceApi {

	@Test(dataProvider = "getRequestJSON", dataProviderClass = Excel2Json.class)
	public void testUpdateRoleAPI(JSONObject requestJSON) throws JSONException {

		Response responseJSON = getAPIResponse(requestJSON, "updateRole", "POST");

		if (responseJSON.getStatusCode() == 200) {

			// Success Response should contain request Params
			Assert.assertEquals(responseJSON.jsonPath().getString("Params.ID"),
					requestJSON.getJSONObject("Params").getString("ID"), "ID not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getString("Params.oca"),
					requestJSON.getJSONObject("Params").getString("oca"), "oca not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getString("Params.Name"),
					requestJSON.getJSONObject("Params").getString("Name"), "Name not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getString("Params.Description"),
					requestJSON.getJSONObject("Params").getString("Description"), "Description not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getList("Params.LogicalRoles"),
					requestJSON.getJSONObject("Params").getJSONArray("LogicalRoles"), "LogicalRoles not found on Response Params");

			// Success Response Data Assertions
			Assert.assertEquals(responseJSON.jsonPath().getString("Data.ID"),
					requestJSON.getJSONObject("Params").getString("ID"), "ID not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getString("Data.oca"),
					requestJSON.getJSONObject("Params").getString("oca"), "oca not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getString("Data.Name"),
					requestJSON.getJSONObject("Params").getString("Name"), "Name not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getString("Data.Description"),
					requestJSON.getJSONObject("Params").getString("Description"), "Description not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getList("Data.LogicalRoles"),
					requestJSON.getJSONObject("Params").getJSONArray("LogicalRoles"), "LogicalRoles not found on Response Params");

		} else {

			// Failure Response should contain request Params
			Assert.assertEquals(responseJSON.jsonPath().getString("Params.ID"),
					requestJSON.getJSONObject("Params").getString("ID"), "ID not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getString("Params.oca"),
					requestJSON.getJSONObject("Params").getString("oca"), "oca not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getString("Params.Name"),
					requestJSON.getJSONObject("Params").getString("Name"), "Name not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getString("Params.Description"),
					requestJSON.getJSONObject("Params").getString("Description"), "Description not found on Response Params");
			Assert.assertEquals(responseJSON.jsonPath().getList("Params.LogicalRoles"),
					requestJSON.getJSONObject("Params").getJSONArray("LogicalRoles"), "LogicalRoles not found on Response Params");			

			// Failure Response Data should be null 
			Assert.assertNull(responseJSON.jsonPath().getString("Data"), "Response Data is not null");
			
			// Failure Response should have reason code
			Assert.assertNotNull(responseJSON.jsonPath().getString("Reasons.ReasonCode"));
		}

		System.out.println("**********Response from [updateRole]**********");
		System.out.println(responseJSON.getBody().asString());
		System.out.println();

	}

}
