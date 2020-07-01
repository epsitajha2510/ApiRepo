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
import java.util.Map;
import org.apache.http.entity.StringEntity;
import org.apache.http.Header;

public class Client 
{
		// GET Method without Headers
		public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
		{
			CloseableHttpClient httpClient =HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);  //to get the Request
			CloseableHttpResponse closeablehttpsResponse = httpClient.execute(httpGet); //to hit the URL.
			
			return closeablehttpsResponse;
		}
		
		
		// Get Method with Headers
		public CloseableHttpResponse get(String url, HashMap<String, String> hashMap) throws ClientProtocolException, IOException
		{
			CloseableHttpClient httpClient =HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);  //to get the Request
			
			for(Map.Entry<String, String> entry : hashMap.entrySet())
			{
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse closeablehttpsResponse = httpClient.execute(httpGet); //to hit the URL.
			
			return closeablehttpsResponse;
		}
}
