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
        super("Une erreur de cr�ation du fichier de calcul. L'ex�ution du programme a �t� interrompue.");
    }
    
    @Override
    public String getMessage()
    {
        return "Une erreur de cr�ation du fichier de calcul. L'ex�cution du programme a �t� interrompue.";
    }
    
}
