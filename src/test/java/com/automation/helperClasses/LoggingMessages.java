package com.automation.helperClasses;

import org.apache.log4j.PropertyConfigurator;

public class LoggingMessages {
	
	static String configFilename = getWorkspaceLocation()+"\\src\\test\\resources\\Log4j.properties"; 	
	 
	public static void start() {
		PropertyConfigurator.configure(configFilename);
	}
	
	public static String getWorkspaceLocation() {
		return System.getProperty("user.dir");

	}

}
