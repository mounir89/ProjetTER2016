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
public class Exception_AbsenceValeur extends Exception{

    public Exception_AbsenceValeur(String message) {
        super("Des valeurs n�cessaires au calcul sont ind�finies ("+message+"). L'ex�cution du programme a �t� interrompue.");
    }
    
    @Override
    public String getMessage()
    {
        return "Des valeurs n�cessaires au calcul sont ind�finies. L'ex�cution du programme a �t� interrompue.";
    }
}
