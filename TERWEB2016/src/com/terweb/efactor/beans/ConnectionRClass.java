package com.terweb.efactor.beans;

import org.rosuda.JRI.Rengine;

public class ConnectionRClass {
       
	    private static Rengine connection;
	    
	    private ConnectionRClass() {
	    	
	    	connection = new Rengine (new String [] {"--vanilla"}, false, null);  
	        
	    }
	    
	    public static synchronized Rengine getConnection() {
	        
	        if(connection == null){
	            
	          new ConnectionRClass();
	          
	        }
	    return connection;   
	  }  
	
}
