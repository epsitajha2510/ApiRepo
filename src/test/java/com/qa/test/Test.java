package com.qa.test;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import com.qa.base.TestBase;
import com.qa.client.Client;


public class Test extends TestBase
{
	TestBase tBase;
	Client client;
	String url;
	String serviceUrl;
	String requestUrl;
	
	@BeforeMethod
	public void precondition()
	{
		tBase = new TestBase();
		serviceUrl= prop.getProperty("serviceurl");
		requestUrl = prop.getProperty("requestUrl");
		 
		url = serviceUrl+requestUrl;
	}
	
	@org.testng.annotations.Test
	public void getAPItest() throws ClientProtocolException, IOException
	{
		client = new Client();
		client.get(url);
	}
	
	
	
}
