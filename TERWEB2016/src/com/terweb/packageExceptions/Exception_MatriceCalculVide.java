/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageExceptions;
/**
 *
 * @author proprietaire
 */
public class Exception_MatriceCalculVide extends Exception{
    
    public Exception_MatriceCalculVide() {
        super("La matrice de calcul est vide. L'exécution du programme a été interrompue.");
    }
    
    @Override
    public String getMessage()
    {
        return "La matrice de calcul est vide. L'exécution du programme a été interrompue.";
    }
    
}
