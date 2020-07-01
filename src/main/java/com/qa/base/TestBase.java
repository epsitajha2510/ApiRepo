package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase 
{
	
	protected Properties prop;
	
	public int Response_Status_Code_200 = 200;
	public int Response_Status_Code_300 = 300;
	public int Response_Status_Code_404 = 404;
	public int Response_Status_Code_505 = 200;
	
    public TestBase() 
    {
		
    	{
			 try {
				 prop = new Properties();
				 FileInputStream fis= new FileInputStream("D:\\CucumberWorkSpace\\restapi\\src\\main\\java\\com\\qa\\config\\config.properties");
				 prop.load(fis);
			 	}
			 catch (IOException e) {
				// TODO: handle exception
			}	 
		 }
	}
}
