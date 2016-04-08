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
import  com.terweb.packageExceptions.Exception_BDDException;
import  com.terweb.packageExceptions.Exception_SparqlConnexion;

/**
 *
 * @author proprietaire
 */
public class Initialisation {
   
    
   public static HashMap<String,ArrayList<String>> initTopicOperation() throws Exception_BDDException, IOException, SQLException
    {
        
        return InterrogationBDD.getTopicOperation();
        
    }
    
    public static HashMap<String,ArrayList<String>> initRelationParameters() throws IOException, Exception_SparqlConnexion
    {
        return InterrogationOntologie.getRelationParameters();
    }
    
    public static ArrayList<String> initBiomass() throws IOException, Exception_SparqlConnexion
    {
        return InterrogationDataRDF.getBiomass();
    }
    
}
