package com.terweb.efactor.beans;


import java.util.UUID;

public class IdentifierUserSession  {
	/**
	 * 
	 * @return
	 */
   public static String generateIdentifierUserSession (){
	   
	   // Creation  UUID (Universally unique identifier)
	   
       UUID uuid = UUID.randomUUID();
       String randomUUIDString = uuid.toString();

   return randomUUIDString;
	   
   }
}