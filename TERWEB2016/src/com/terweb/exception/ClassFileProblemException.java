package com.terweb.exception;

public class ClassFileProblemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClassFileProblemException(){
		System.out.println("un probleme s'est produit , le fichier precalcul n'a été pas crée ");	
	}

}
