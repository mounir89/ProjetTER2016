/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageInterrogationDonnees;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import packageExceptions.Exception_BDDException;
import packageExceptions.Exception_SparqlConnexion;

/**
 *
 * @author proprietaire
 */
class Initialisation {
   
    
    static HashMap<String,ArrayList<String>> initTopicOperation() throws Exception_BDDException, IOException, SQLException
    {
        
        return InterrogationBDD.getTopicOperation();
        
    }
    
    static HashMap<String,ArrayList<String>> initRelationParameters() throws IOException, Exception_SparqlConnexion
    {
        return InterrogationOntologie.getRelationParameters();
    }
    
    static ArrayList<String> initBiomass() throws IOException, Exception_SparqlConnexion
    {
        return InterrogationDataRDF.getBiomass();
    }
    
}
