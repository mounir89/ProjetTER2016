package com.terweb.exception;

public class ClassFileProblemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Méthode permettant de déclencher une erreur si 
	 * le fichier contenant le glucose Yield et Rate à 95 de confiance n'est pas crée
	 */
	public ClassFileProblemException(){
		System.out.println("un probleme s'est produit , le fichier precalcul n'a été pas crée ");	
	}

}
