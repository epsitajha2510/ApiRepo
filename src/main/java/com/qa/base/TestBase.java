package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase 
{
	
	protected Properties prop;
    public TestBase() {
		
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
