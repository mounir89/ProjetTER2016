package com.terweb.efactor.beans;

import org.rosuda.JRI.Rengine;

public class ConnectionRClass {
       
	    private static Rengine connection;
	    /**
	     * 
	     * de la classe ConnectionClass
	     */
	    private ConnectionRClass() {
	    	
	    	connection = new Rengine (new String [] {"--vanilla"}, false, null);  
	        
	    }
	    /**
	     * Methode utilisant le pattern singleton pour obtenir
	     * qu'une seule instance de connexion R
	     * @return Instance R
	     */
	    public static synchronized Rengine getConnection() {
	        
	        if(connection == null){
	           
	          new ConnectionRClass();
	          
	        }
	    return connection;   
	  }  
	
}
