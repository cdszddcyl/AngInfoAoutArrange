package com.anginfo.utils;

public class SystemUtils {
	
	public static boolean IS_OS_LINUX=false;
	static{
	    String os = System.getProperty("os.name");  
	    if(os.toLowerCase().startsWith("win")){  
	    	IS_OS_LINUX=false;
	    }  else{
	    	IS_OS_LINUX=true;
	    }
	}

}
