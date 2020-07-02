package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Utility.TestUtility;
import com.qa.base.TestBase;
import com.qa.client.Client;
import com.qa.data.PostUserPayload;


public class PostAPITest extends TestBase
{
	String serviceUrl;
	TestBase tBase;
	String requestUrl;
	String url;
	Client client ;
	CloseableHttpResponse closeablehttpsResponse;
	
	
	@BeforeMethod
	public void precondition()
	{
		tBase = new TestBase();
		serviceUrl =prop.getProperty("serviceurl");
		requestUrl = prop.getProperty("requestUrl");
		
		url = serviceUrl+requestUrl;
	}
	
	@Test
	public void postCall() throws JsonGenerationException, JsonMappingException, IOException
	{
		client = new Client();
		
		// to Create a header
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Content-Type", "application/json");
		
		//JackSon API
		 ObjectMapper mapper = new ObjectMapper();
		 PostUserPayload userPayload = new PostUserPayload("morpheus", "zion resident"); //Expected User.
		 
		 // Convert the Java Object to JSOn Object (MarShelling)
		 mapper.writeValue(new File("D:/CucumberWorkSpace/restapi/src/main/java/com/qa/data/user.json"),userPayload);
		 
		 
		 //Java Object to Json String
		 String PostUserPayloadString =mapper.writeValueAsString(userPayload);
		 System.out.println("The Post Payload used -------->"+PostUserPayloadString);
		 System.out.println();
		 
		 closeablehttpsResponse = client.post(url, PostUserPayloadString, hashMap);
		 
		 //validate the response from the API
		 //Validate the Status Code
		  int statusCode = closeablehttpsResponse.getStatusLine().getStatusCode();
		  Assert.assertEquals(statusCode, tBase.Response_Status_Code_201,"Status Code is not 200");
		  
		  
		  // to get the JSON Response
		  String ResponseJSONString =EntityUtils.toString(closeablehttpsResponse.getEntity(), "UTF-8");// tp convert the Response JSOn to Json String
		  JSONObject jObject = new JSONObject(ResponseJSONString);
		  System.out.println("The Response Json String is--------->" + ResponseJSONString);
		  System.out.println();
		  
		  //Validate the Response JSON
		  String name =TestUtility.getValueByJPath(jObject, "/name");
		  System.out.println("The value of name -------------->" + name);
		  Assert.assertEquals(name, "morpheus");
		  
		  String job =TestUtility.getValueByJPath(jObject, "/job");
		  System.out.println("The value of job -------------->" + job);
		  Assert.assertEquals(job, "zion resident");
		  
		  String id =TestUtility.getValueByJPath(jObject, "/id");
		  System.out.println("The value of id -------------->" + id);
		  //Assert.assertEquals(Integer.parseInt(id), expected);
		  
		  String createdAt =TestUtility.getValueByJPath(jObject, "/createdAt");
		  System.out.println("The value of createdAt -------------->" + createdAt);
		  
		  
		  // to Validate the Payload
		  // Converting the JSON Object to User Object(Unmarshelling)
		  
		   PostUserPayload payloadobject =mapper.readValue(ResponseJSONString, PostUserPayload.class);//actual users object
		   System.out.println("The Actual Payload Object------->"+ payloadobject);
		   System.out.println();
		   
		   Assert.assertTrue(payloadobject.getJob().equals(userPayload.getJob()));
		   Assert.assertTrue(payloadobject.getName().equals(payloadobject.getName()));
		   
		   
		   //to get the Headers
		   Header[] responseHeader = closeablehttpsResponse.getAllHeaders();
		   HashMap<String, String> hashMapOfHeader = new HashMap<String, String>();
		   for(Header header : responseHeader)
		   {
			   hashMapOfHeader.put(header.getName(), header.getValue());
		   }
		   
		   System.out.println("The Headers of the API Response--------->"+ hashMapOfHeader);
		   System.out.println();
		   
		   //Validate the Headers:
		   JSONObject jObjectHeader = new JSONObject(hashMapOfHeader);
		   
		   SoftAssert softAssert = new SoftAssert();
		   
		   String ContentType =TestUtility.getValueByJPath(jObjectHeader, "/Content-Type");
		   System.out.println("The Content-Type of the Header----------->"+ ContentType);
		   softAssert.assertEquals(ContentType, "application/json; charset=utf-8");
		   
		   String Connection =TestUtility.getValueByJPath(jObjectHeader, "/Connection");
		   System.out.println("The Connection of the Header----------->"+ Connection);
		   softAssert.assertEquals(Connection, "keep-alive");
		   
		   String XPoweredBy =TestUtility.getValueByJPath(jObjectHeader, "/X-Powered-By");
		   System.out.println("The X-Powered-By of the Header----------->"+ XPoweredBy);
		   softAssert.assertEquals(XPoweredBy, "Express");
		   
		   softAssert.assertAll();
		   	   	
	}

}

