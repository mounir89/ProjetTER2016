package com.terweb.efactor.beans;


import java.util.UUID;

public class IdentifierUserSession  {
	
	/**
	 * Génération d'un identifier unique
	 * pour chaque utilisteur de l'application
	 * @return Identifier unique pour l'utilisateur
	 */
   public static String generateIdentifierUserSession (){
	   
	   UUID uuid = UUID.randomUUID();
       String randomUUIDString = uuid.toString();

   return randomUUIDString;
	   
   }
}