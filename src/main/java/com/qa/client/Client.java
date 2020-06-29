package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.apache.http.Header;

public class Client 
{
		// GET Method
		public void get(String url) throws ClientProtocolException, IOException
		{
			CloseableHttpClient httpClient =HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);  //to get the Request
			CloseableHttpResponse closeablehttpsResponse = httpClient.execute(httpGet); //to hit the URL.
			
			
			//To get the Status Code
			int statusCode =closeablehttpsResponse.getStatusLine().getStatusCode();
			System.out.println(" The Status Code--------->" + statusCode);
			
			
			//to get the Json String
			String responseString =EntityUtils.toString(closeablehttpsResponse.getEntity(), "UTF-8");
			JSONObject reponseOject = new JSONObject(responseString);
			
			System.out.println(" The JSON Response--------->"+ reponseOject);
			
			
			// to get the headers
			Header[] responseHeader = closeablehttpsResponse.getAllHeaders();
			HashMap<String, String> allheader = new HashMap<String, String>();
			for(Header header : responseHeader)
			{
				allheader.put(header.getName(), header.getValue());
			}
			
			System.out.println("The Header Array--------->" + allheader);
			
			
			
			
		}
}
