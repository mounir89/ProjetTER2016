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
public class Exception_FichierCalcule extends Exception{

    public Exception_FichierCalcule() {
        super("Une erreur de création du fichier de calcul. L'exécution du programme a été interrompue.");
    }
    
    @Override
    public String getMessage()
    {
        return "Une erreur de création du fichier de calcul. L'exécution du programme a été interrompue.";
    }
    
}
