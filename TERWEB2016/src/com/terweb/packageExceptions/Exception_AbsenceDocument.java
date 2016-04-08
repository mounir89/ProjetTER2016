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
public class Exception_AbsenceDocument extends Exception{

    public Exception_AbsenceDocument() {
        super("Aucun document ne figure dans le(s) topic(s) choisi(s).");
    }
    
    @Override
    public String getMessage()
    {
        return "Aucun document ne figure dans le(s) topic(s) choisi(s).";
    }
    
}
