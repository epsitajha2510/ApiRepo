package com.qa.test;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import com.qa.Utility.TestUtility;
import com.qa.base.TestBase;
import com.qa.client.Client;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;


public class Test extends TestBase
{
	TestBase tBase;
	Client client;
	String url;
	String serviceUrl;
	String requestUrl;
	CloseableHttpResponse closeablehttpsResponse;
	
	@BeforeMethod
	public void precondition()
	{
		tBase = new TestBase();
		serviceUrl= prop.getProperty("serviceurl");
		requestUrl = prop.getProperty("requestUrl");
		 
		url = serviceUrl+requestUrl;
	}
	
	@org.testng.annotations.Test(priority=1)
	public void getAPItestWithoutHeaders() throws ClientProtocolException, IOException
	{
		client = new Client();
		closeablehttpsResponse =client.get(url);
		
		//To get the Status Code
		int statusCode =closeablehttpsResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code--------->" + statusCode);
		Assert.assertEquals(statusCode, Response_Status_Code_200,"Response Code is not 200");
		
		
		//To get the Json String
		//Single Object
		String responseString =EntityUtils.toString(closeablehttpsResponse.getEntity(), "UTF-8");
		JSONObject reponseObject = new JSONObject(responseString);
		System.out.println("The JSON Response--------->"+ reponseObject);
		
		//Single Value Assertion
		//PerPage
		String perPageValue =TestUtility.getValueByJPath(reponseObject, "/per_page");
		System.out.println("The per page Value is------>" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6," The Perpage value is not 6 ");
		
		
		//Total
		String totalValue =TestUtility.getValueByJPath(reponseObject, "/total");
		System.out.println("The Total value is------>"+ totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12," The Total Value is not 12");
		
		
		// to get the JSON Array
		String id =TestUtility.getValueByJPath(reponseObject, "/data[0]/id");
		String email =TestUtility.getValueByJPath(reponseObject, "/data[0]/email");
		String first_name =TestUtility.getValueByJPath(reponseObject, "/data[0]/first_name");
		String last_name =TestUtility.getValueByJPath(reponseObject, "/data[0]/last_name");
		String avatar =TestUtility.getValueByJPath(reponseObject, "/data[0]/avatar");
		System.out.println("id "+id);
		System.out.println("Email "+email);
		System.out.println("first_name "+first_name);
		System.out.println("last_name "+last_name);
		System.out.println("avatar "+avatar);
		
		
		//Assertion
		
		Assert.assertEquals(Integer.parseInt(id), 1);
		Assert.assertEquals(email, "george.bluth@reqres.in");
		Assert.assertEquals(first_name, "George");
		Assert.assertEquals(last_name, "Bluth");
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
		
		// to get the headers
		Header[] responseHeader = closeablehttpsResponse.getAllHeaders();
		HashMap<String, String> allheader = new HashMap<String, String>();
		for(Header header : responseHeader)
		{
			allheader.put(header.getName(), header.getValue());
		}
		
		System.out.println("The Header Array--------->" + allheader);
		System.out.println("__________________________________________________________________________________________");
		System.out.println();
	}
	
	
	
	@org.testng.annotations.Test(priority=2)
	public void getAPItestWithHeaders() throws ClientProtocolException, IOException
	{
		client = new Client();
		
		HashMap<String, String> hashMap1 = new HashMap<String, String>();
		hashMap1.put("Content-Type", "application/json");
		
		closeablehttpsResponse =client.get(url, hashMap1);
		
		//To get the Status Code
				int statusCode =closeablehttpsResponse.getStatusLine().getStatusCode();
				System.out.println("The Status Code--------->" + statusCode);
				Assert.assertEquals(statusCode, Response_Status_Code_200,"Response Code is not 200");
				
				
				//To get the Json String
				//Single Object
				String responseString =EntityUtils.toString(closeablehttpsResponse.getEntity(), "UTF-8");
				JSONObject reponseObject = new JSONObject(responseString);
				System.out.println("The JSON Response--------->"+ reponseObject);
				
				//Single Value Assertion
				//PerPage
				String perPageValue =TestUtility.getValueByJPath(reponseObject, "/per_page");
				System.out.println("The per page Value is------>" + perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6," The Perpage value is not 6 ");
				
				
				//Total
				String totalValue =TestUtility.getValueByJPath(reponseObject, "/total");
				System.out.println("The Total value is------>"+ totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12," The Total Value is not 12");
				
				
				// to get the JSON Array
				String id =TestUtility.getValueByJPath(reponseObject, "/data[0]/id");
				String email =TestUtility.getValueByJPath(reponseObject, "/data[0]/email");
				String first_name =TestUtility.getValueByJPath(reponseObject, "/data[0]/first_name");
				String last_name =TestUtility.getValueByJPath(reponseObject, "/data[0]/last_name");
				String avatar =TestUtility.getValueByJPath(reponseObject, "/data[0]/avatar");
				System.out.println("id "+id);
				System.out.println("Email "+email);
				System.out.println("first_name "+first_name);
				System.out.println("last_name "+last_name);
				System.out.println("avatar "+avatar);
				
				
				//Assertion
				
				Assert.assertEquals(Integer.parseInt(id), 1);
				Assert.assertEquals(email, "george.bluth@reqres.in");
				Assert.assertEquals(first_name, "George");
				Assert.assertEquals(last_name, "Bluth");
				Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");
				
				// to get the headers
				Header[] responseHeader = closeablehttpsResponse.getAllHeaders();
				HashMap<String, String> allheader = new HashMap<String, String>();
				for(Header header : responseHeader)
				{
					allheader.put(header.getName(), header.getValue());
				}
				
				System.out.println("The Header Array--------->" + allheader);
				System.out.println("__________________________________________________________________________________________");
				System.out.println();
	}
	
	
	
}
