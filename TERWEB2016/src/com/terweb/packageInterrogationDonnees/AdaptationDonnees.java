/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

import java.math.BigDecimal;
import java.util.HashMap;
import com.terweb.packageExceptions.Exception_ParseException;

/**
 * Classe qui regroupe des fonctions de conversion et d'adaptation des donn�es
 * 
 */
public class AdaptationDonnees {
    
	/**
	 * Fonction qui convertit un String en Double.
	 * 
	 * @param valeur : chaine de caract�re � convertir en double.
	 * @return valeur Double. 
	 * @throws Exception_ParseException
	 *  
	 */
	
    static double conversionStringVersDouble(String valeur) throws Exception_ParseException
    { 
        double valeur_retour=0;
        
        try {
            
            valeur_retour=Double.parseDouble(valeur);
            
        } catch (Exception e) {
            
            throw new Exception_ParseException();
        }
        
        return valeur_retour;

    }
    
    /**
	 * Fonction qui cast la pr�cision d'un Double.
	 * 
	 * @param expn : valeur � caster.
	 * @param fraction : pr�cion.
	 * @return valeur cast�e. 
	 *  
	 */
    
    static double doubleFractionPrecision(double expn, int fraction)
    {
       BigDecimal b = new BigDecimal(expn).setScale(fraction,BigDecimal.ROUND_HALF_UP);
       
       return b.doubleValue(); 
    }
    
    /**
 	 * Fonction qui convertit l'unit� d'une quantit� de mati�re en kilogramme.
 	 * 
 	 * @param unite : unit� actuelle.
 	 * @param valeur : quantit�.
 	 * @return quantit� convertie en kilogramme. 
 	 *  
 	 */
    
    static double conversionUnite(String unite, String valeur) throws Exception_ParseException
    {
        HashMap<String,Integer> vecteurConversion = new HashMap<>();
        
        double valeur_retour=0;
        
        vecteurConversion.put("Milliliter", 1000);
        vecteurConversion.put("Liter", 1);
        vecteurConversion.put("Milligram", 1000000);
        vecteurConversion.put("Gram", 1000);
        vecteurConversion.put("Kilogram", 1);
        
        try {
            
            valeur_retour=conversionStringVersDouble(valeur)/(vecteurConversion.get(unite));
            
        } catch (Exception e) {
            
            throw new Exception_ParseException();
        }
        
        return valeur_retour;
        
    }
}
