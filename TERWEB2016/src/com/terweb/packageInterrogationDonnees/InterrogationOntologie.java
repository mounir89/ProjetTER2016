/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.terweb.packageExceptions.Exception_SparqlConnexion;

/**
*
* Classe qui comporte les fonctions d'interogation de l'ontologie. 
*
*/

public class InterrogationOntologie {
    
    static String sparqlEndpoint = "http://pfl.grignon.inra.fr:3030/ontology/query";

    /*
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX owl: <http://www.w3.org/2002/07/owl#>
            PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
            PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
            PREFIX core: <http://opendata.inra.fr/resources/core#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>

            SELECT  DISTINCT ?relation_naire ?parametre
            WHERE { ?subject rdfs:subClassOf  core:Relation.
               ?subject skos:prefLabel "Unit operation relation"@en.
               ?operation rdfs:subClassOf ?subject.
               ?relation rdfs:subClassOf ?operation.
               BIND(strafter(str(?relation), "#") as ?relation_naire)
               ?relation rdfs:subClassOf ?restriction.
               ?restriction rdf:type owl:Restriction.
               ?restriction owl:onProperty core:hasImportantAccessConcept.
               ?restriction owl:someValuesFrom ?value.
			   BIND(strafter(str(?value), "#") as ?parametre)
			   ?value rdfs:subClassOf* domain:matter_quantity}
            ORDER BY ASC(?relation_naire) ASC(?parametre) 
    */
    
    /**
  	* Fonction qui r�cup�re la liste des relations n-aires d�finies en termes de quantit�s de mati�res.
  	* 
  	* @param path : Chemin des donn�es en cas d'une interrogation en local.
  	* @return Obj:HashMap
  	* @throws IOException, Exception_SparqlConnexion
  	* 
  	*/
    
    static HashMap<String,ArrayList<String>> getRelationParameters(String path) throws IOException, Exception_SparqlConnexion
    {
            String relation;
            
            HashMap<String,ArrayList<String>> resultat= new HashMap<>();
          
            String comNameQuery = 
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
"            PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
"            PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
"            PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
"            PREFIX core: <http://opendata.inra.fr/resources/core#>\n" +
"            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
"            PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>\n" +
"\n" +
"            SELECT  DISTINCT ?relation_naire ?parametre\n" +
"            WHERE { ?subject rdfs:subClassOf  core:Relation.\n" +
"               ?subject skos:prefLabel \"Unit operation relation\"@en.\n" +
"               ?operation rdfs:subClassOf ?subject.\n" +
"               ?relation rdfs:subClassOf ?operation.\n" +
"               BIND(strafter(str(?relation), \"#\") as ?relation_naire)\n" +
"               ?relation rdfs:subClassOf ?restriction.\n" +
"               ?restriction rdf:type owl:Restriction.\n" +
"			   OPTIONAL{\n" +
"					   ?restriction owl:onProperty core:hasImportantAccessConcept.\n" +
"					   ?restriction owl:someValuesFrom ?value.\n" +
"					   FILTER( ?value!=domain:biomass_quantity )\n" +
"					   BIND(strafter(str(?value), \"#\") as ?parametre)\n" +
"					   ?value rdfs:subClassOf* domain:matter_quantity}}\n" +
"            ORDER BY ASC(?relation_naire) ASC(?parametre)";

        
        /************************LOCAL MODE ***********************************/
        
        Path input = Paths.get(path+"DonneesLocales/Ontologie/", "biorefinery.owl");
        Model model = ModelFactory.createDefaultModel() ; 
        model.read(input.toUri().toString());
            
        //Model model= FileManager.get().loadModel(path+"DonneesLocales/Ontologie/biorefinery.owl");
         
        Query query = QueryFactory.create(comNameQuery);  
        
        QueryExecution qe = QueryExecutionFactory.create(query, model); 
         
        /************************************************************************/

        /**********************SPARQL ENDPOINT MODE*****************
        
        Query query = QueryFactory.create(comNameQuery);  
        
        QueryExecution qe = QueryExecutionFactory.sparqlService(sparqlEndpoint,query);
        
        ************************************************************************/

        try {
                ResultSet rs = qe.execSelect();
                
                while ( rs.hasNext() ) 
                {
                
                    QuerySolution solution=rs.next();
                    
                    relation=solution.getLiteral("relation_naire").getLexicalForm();
                    
                    if(resultat.keySet().contains(relation))
                    {
                        if(solution.getLiteral("parametre")!=null)
                        {
                            resultat.get(relation).add(solution.getLiteral("parametre").getLexicalForm());
                        }  
                    }
                    else
                    {
                        if(solution.getLiteral("parametre")!=null)
                        {
                            resultat.put(relation, new ArrayList<>(Arrays.asList(solution.getLiteral("parametre").getLexicalForm())));
                        }
                        
                    }

                }
       } 
        catch(Exception e) { 
           
           throw new com.terweb.packageExceptions.Exception_SparqlConnexion(e);
        }
        finally {
            qe.close();
        }
        	
        return resultat;
    }

}
