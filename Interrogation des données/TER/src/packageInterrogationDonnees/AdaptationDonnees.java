/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageInterrogationDonnees;

import java.math.BigDecimal;
import java.util.HashMap;
import packageExceptions.Exception_ParseException;

/**
 *
 * @author proprietaire
 */
class AdaptationDonnees {
    
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
    
    static double doubleFractionPrecision(double expn, int fraction)
    {
       BigDecimal b = new BigDecimal(expn).setScale(fraction,BigDecimal.ROUND_HALF_UP);
       
       return b.doubleValue(); 
    }
    
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
