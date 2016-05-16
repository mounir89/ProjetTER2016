/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

/**
*
* Classe qui d�finit un objet repr�sentant le rapport de l'�tape de construction d'un vecteur de calcul.
* Ce rapport porte sur le vecteur de calcul et un message d'erreur s'il y en a.
*
*/

public class Object_RapportCalculVecteur {
    
    private Object_VecteurCalcul vecteurCalcul;
    private String message;

    /**
    *
    * Constructeur param�tr� d'un objet de la classe.
    * 
    * @param vecteurCalcul : Vecteur de calcul.
    * @param message : Message d'erreur.
    *
    */
    
    public Object_RapportCalculVecteur(Object_VecteurCalcul vecteurCalcul, String message) {
        this.vecteurCalcul = vecteurCalcul;
        this.message = message;
    }
    
    /**
    *
    * Constructeur par d�faut d'un objet de la classe.
    * 
    */
    
    public Object_RapportCalculVecteur() {
        
        this.vecteurCalcul=null;
        this.message=null;
    }
    
    /**
    *
    * Fonction qui retourne le vecteur de calcul.
    * @return obj:Object_VecteurCalcul.
    * 
    */
    
    public Object_VecteurCalcul getVecteurCalcul() {
        return vecteurCalcul;
    }
    
    /**
    *
    * Fonction qui retourne le message d'erreur.
    * @return obj:String.
    * 
    */
    
    public String getMessage() {
        return message;
    }

    /**
    *
    * Proc�dure qui modifie le vecteur de calcul.
    * @param vecteurCalcul : Nouveau vecteur de calcul.
    * 
    */
    
    public void setVecteurCalcul(Object_VecteurCalcul vecteurCalcul) {
        this.vecteurCalcul = vecteurCalcul;
    }

    /**
    *
    * Proc�dure qui modifie le message d'erreur.
    * @param message : Nouveau message d'erreur.
    * 
    */
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
