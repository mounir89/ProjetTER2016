package com.terweb.exception;

public class ClassCalculException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 * M�thode permettant d'affichier une erreur si 
	 * un probl�me de chargement de R se produit.
	 * 
	 */
	public ClassCalculException(){
		
		System.out.println("Impossible de charger R");
		
	}
	
	
	

}
