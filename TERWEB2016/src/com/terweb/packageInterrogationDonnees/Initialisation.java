/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.terweb.packageExceptions.Exception_BDDException;
import com.terweb.packageExceptions.Exception_SparqlConnexion;

/**
 *
 * Classe qui regroupe les fonctions sollicitées pour l'initialisation de l'application Web.
 * 
 */

public class Initialisation {
   
    
    /**
 	 * Fonction qui retourne la liste des Topics avec leurs définitions en termes d'opérations unitaires.
 	 * 
 	 * @return Obj:HashMap. 
 	 *  
 	 */
	
    public static HashMap<String,ArrayList<String>> initTopicOperation() throws Exception_BDDException, IOException, SQLException
    {
        
        return InterrogationBDD.getTopicOperation();
        
    }
    
    /**
 	 * Fonction qui retourne la liste des Relations n-aires avec leurs définitions en termes de quantités de matières.
 	 * 
 	 * @return Obj:HashMap. 
 	 *  
 	 */
    
    public static HashMap<String,ArrayList<String>> initRelationParameters(String path) throws IOException, Exception_SparqlConnexion
    {
        return InterrogationOntologie.getRelationParameters(path);
    }
    
    /**
 	 * Fonction qui retourne la liste des biomasses.
 	 * 
 	 * @return Obj:ArrayList. 
 	 *  
 	 */
    
    public static ArrayList<String> initBiomass(String path) throws IOException, Exception_SparqlConnexion
    {
        return InterrogationDataRDF.getBiomass(path);
    }
    
}
