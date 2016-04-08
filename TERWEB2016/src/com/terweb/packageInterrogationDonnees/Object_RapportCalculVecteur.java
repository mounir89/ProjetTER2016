/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

/**
 *
 * @author proprietaire
 */
public class Object_RapportCalculVecteur {
    
    private Object_VecteurCalcul vecteurCalcul;
    private String message;

    public Object_RapportCalculVecteur(Object_VecteurCalcul vecteurCalcul, String message) {
        this.vecteurCalcul = vecteurCalcul;
        this.message = message;
    }

    public Object_RapportCalculVecteur() {
        
        this.vecteurCalcul=null;
        this.message=null;
    }
    
    
    public Object_VecteurCalcul getVecteurCalcul() {
        return vecteurCalcul;
    }

    public String getMessage() {
        return message;
    }

    public void setVecteurCalcul(Object_VecteurCalcul vecteurCalcul) {
        this.vecteurCalcul = vecteurCalcul;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
