package com.terweb.exception;

public class ClassCalculException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 * Méthode permettant d'affichier une erreur si 
	 * un problème de chargement de R se produit.
	 * @return
	 */
	public ClassCalculException(){
		
		System.out.println("Impossible de charger R");
		
	}
	
	
	

}
