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
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.terweb.packageExceptions.Exception_BDDException;
import com.terweb.packageExceptions.Exception_SparqlConnexion;

/**
 *
 * @author proprietaire
 */
public class UpdateBDD {
    
    static String sparqlEndpoint = "http://pfl.grignon.inra.fr:3030/annotation/query";
    
    /**
     * Procédure qui lance la mise à jour de la base de données à travers
     * un ensemble de traitements.
     * @throws Exception_BDDException, SQLException, Exception_SparqlConnexion
     */
    
    public static void execute() throws Exception_BDDException, SQLException, Exception_SparqlConnexion
    {
        
        //Pour chaque Topic ---> Traitements correspondants
        HashMap<String,ArrayList<String>> topicTreatment=new HashMap<>();
        
        //Pour chaque Topic ---> Ses documents
        HashMap<String,ArrayList<String>> topicDocument=getTopicDocument();
        
        //Pour chaque Topic -- Pour chauque document : rÃ©cupÃ©rer les traitements 
        //Construire la HashMap topicTreatment en Ã©liminant la redondance.
        if(topicDocument!=null)
        {
            for(String topic : topicDocument.keySet())
            {
                for(String idDoc : topicDocument.get(topic))
                {
                    ArrayList<String> treatments=getDocumentTreatments(idDoc);
                
                    if(treatments.size() > 0)
                    {
                         if(topicTreatment.containsKey(topic))
                         {
                             for(String treatment : treatments)
                             {
                                 if(!topicTreatment.get(topic).contains(treatment))
                                 {
                                     topicTreatment.get(topic).add(treatment);
                                 }
                             }

                         }
                         else
                         {
                                topicTreatment.put(topic, treatments);
                         }  
                    }
                }
               
                
            }
        }
        
        /****************************Traitement sur la base de donnÃ©es maintenant******************/
        
        if(topicTreatment.size()>0)
        {
            //Liste de tous les traitements qui figurent sur les donnÃ©es annotÃ©es
            ArrayList<String> allTreatments=getAllTreatment();
            
            if(allTreatments.size() > 0)
            {
                /*Mettre Ã  jour la table Operations*/
                rebuildTableOperation(allTreatments);
                
                /*Mettre Ã  jour la table topicoperation*/
                rebuildTableTopicOperation(topicTreatment);
            }
 
        }
        
    }
    
    /**
     * Fonction qui retourne une collection HashMap dont la clé est le nom du Topic
     * et la valeur associée est la liste des documents du topic.
     * 
     * @return obj:HashMap
     * @throws Exception_BDDException, SQLException
     * 
     */
    
    private static HashMap<String,ArrayList<String>> getTopicDocument() throws Exception_BDDException, SQLException
    {

        HashMap<String,ArrayList<String>> liste = new HashMap<>();
        
        Connection conn;
        Statement st;
        ResultSet result;

        conn = ConnexionDB.getConnection();

        //CrÃ©ation d'un objet Statement
        st = conn.createStatement();
        
        String query="select t.name, d.id_documents\n" +
                        "from public.documents d, public.topics t, public.ontology o\n" +
                        "where o.id_ontology=t.id_ontology\n" +
                        "and t.id_topic=d.id_topic\n" +
                        "and o.name='BIOREFINERY'\n" +
                        "ORDER BY (t.name,d.id_documents) ASC"; 
                    
        //L'objet ResultSet contient le résultat de la requête SQL
        result = st.executeQuery(query);
                
        while(result.next()){
           
            if(liste.containsKey(result.getString(1)))
            {
                liste.get(result.getString(1)).add(result.getString(2));
            }
            else
            {
                liste.put(result.getString(1), new ArrayList<>(Arrays.asList(result.getString(2))));
            }      
        }
         
        st.close();

        result.close();
        

        if(liste.size()>0)
        {            
            return liste;
        }
            
        return null;
    }
    
    /**
     * Procédure qui met à jour la table : public.topicoperation
     * Les données étant la liste des traitements (opérations) qui figurent sur les données annotées.
     * 
     * @param treatments : Liste des opérations unitaires.
     * @throws Exception_BDDException, SQLException
     */
    
    private static void rebuildTableOperation(ArrayList<String> treatments) throws Exception_BDDException, SQLException
    {
        
        Connection conn;
        Statement st;

        conn = ConnexionDB.getConnection();
  
        //Création d'un objet PrepareStatement
        st = conn.createStatement();
        
        //requête SQL pour vider la table : public.topicoperation
        st.executeUpdate("DELETE FROM public.topicoperation");
        
        //requête SQL pour vider la table : public.operations
        st.executeUpdate("DELETE FROM public.operations");
        
        
        PreparedStatement pst= conn.prepareStatement("INSERT INTO public.operations(\n" +
                                        "            name_fr, name_en)\n" +
                                        "            VALUES (null, ?);"); 
        
        
        for(String treatment : treatments)
        {
            //Edition du paramètre name_en
            pst.setString(1,treatment);
        
            //Exécution de la requête SQL
            pst.executeUpdate();
        }
     
        st.close();
        
        pst.close();
        
    }
    
     /**
     * Procédure qui met à jour la table : public.topicoperation.
     * Les données étant les Topics reliés aux traitements correspondants.
     * 
     * @param topicTreatment : Liste des topics définis en termes d'opérations unitaires.
     * @throws Exception_BDDException, SQLException
     * 
     */
    
    private static void rebuildTableTopicOperation(HashMap<String,ArrayList<String>> topicTreatment) throws Exception_BDDException, SQLException
    {
        
        Connection conn;

        conn = ConnexionDB.getConnection();
          
        PreparedStatement pst= conn.prepareStatement("INSERT INTO public.topicoperation(id_topic, id_operations)\n" +
                                                    "SELECT t.id_topic, o.id_operations\n" +
                                                    "FROM topics t, operations o\n" +
                                                    "WHERE t.name= ?\n" +
                                                    "AND o.name_en= ?"); 
        
        for(String topic : topicTreatment.keySet())
        {
              for(String treatment : topicTreatment.get(topic))
                {
                     
                    //Edition du paramètre id_topic
                    pst.setString(1,topic);
                    
                    //Edition du paramètre id_operations
                    pst.setString(2,treatment);
                    
                    //Exécution de la requète SQL
                    pst.executeUpdate();
                }
        }
   
        pst.close();
        
    }
    
     /**
     * Fonction qui pour un document donnée, retourne la liste des traitements qu'il engendre.
     * 
     * @param idDocument : Identifiant du document.
     * @return obj:ArrayList
     * @throws Exception_SparqlConnexion
     */
    
    private static ArrayList<String> getDocumentTreatments(String idDocument) throws Exception_SparqlConnexion
    {
        ArrayList<String> resultat = new ArrayList<>();
        
        String comNameQuery=
                        "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
                        "PREFIX onto: <http://opendata.inra.fr/resources/atWeb/annotation/>\n" +
                        "PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                        "PREFIX ont: <http://purl.org/net/ns/ontology-annot#>\n" +
                        "\n" +
                        "SELECT  Distinct ?treatment\n" +
                        "WHERE {?document rdf:type onto:Document.\n" +
                        "  		?document onto:hasForID ?idDocument.\n" +
                        "               FILTER(str(?idDocument)=\""+idDocument+"\")\n" +
                        "  		?document onto:hasForOntology ?ontology.\n" +
                        " 		Filter(str(?ontology)=\"http://opendata.inra.fr/resources/BIORAF#\")\n" +
                        "  		?document onto:hasTable ?table.\n" +
                        "  		?table dc:title ?tableTitle.\n" +
                        "       	FILTER regex(?tableTitle, \"Process\", \"i\" )\n" +
                        "  	        ?table onto:hasForRow ?row.\n" +
                        "		?row onto:hasForRowNumber ?rowNumber.\n" +
                        "		?row onto:hasForCell ?cell_treatment.\n" +
                        "    	        ?cell_treatment rdf:type domain:treatment.\n" +
                        "  		?cell_treatment onto:hasForFS/onto:hasForElement/rdf:type ?treatment1.\n" +
                        "		BIND(strafter(str(?treatment1), \"#\") as ?treatment)\n" +
                        "}\n" +
                        "ORDER BY ASC(?treatment)";
        
                /************************LOCAL MODE ***********************************/
                 
        Model model= FileManager.get().loadModel("./RDF_DATA/annotations-atweb.ttl");
         
        Query query = QueryFactory.create(comNameQuery);  
        
        QueryExecution qe = QueryExecutionFactory.create(query, model); 
         
        /************************************************************************/

        /**********************SPARQL ENDPOINT MODE*****************
        
        Query query = QueryFactory.create(comNameQuery);  
        
        QueryExecution qe = QueryExecutionFactory.sparqlService(sparqlEndpoint,query);
        
        ************************************************************************/

        try {
                com.hp.hpl.jena.query.ResultSet rs = qe.execSelect();
                
                while ( rs.hasNext() ) {
                
                QuerySolution solution=rs.next();
                
                resultat.add(solution.getLiteral("treatment").getLexicalForm()); 
            }
        } 
        catch(Exception e){ 
            
           throw new Exception_SparqlConnexion(e);
    
        }
        finally {
            qe.close();
        }
        
        return resultat;
    }
    
    /**
     * Fonction qui retrourne la liste des traitements figurant sur les données annotées.
     * 
     * @return obj:ArrayList
     * @throws Exception_SparqlConnexion
     */
    
    private static ArrayList<String> getAllTreatment() throws Exception_SparqlConnexion
    {
        ArrayList<String> resultat = new ArrayList<>();
        
        String comNameQuery=
                        "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
                        "PREFIX onto: <http://opendata.inra.fr/resources/atWeb/annotation/>\n" +
                        "PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                        "PREFIX ont: <http://purl.org/net/ns/ontology-annot#>\n" +
                        "\n" +
                        "SELECT  Distinct ?treatment\n" +
                        "WHERE {?document rdf:type onto:Document.\n" +
                        "  		?document onto:hasForID ?idDocument.\n" +
                        "  		?document onto:hasForOntology ?ontology.\n" +
                        " 		Filter(str(?ontology)=\"http://opendata.inra.fr/resources/BIORAF#\")\n" +
                        "  		?document onto:hasTable ?table.\n" +
                        "  		?table dc:title ?tableTitle.\n" +
                        "       	FILTER regex(?tableTitle, \"Process\", \"i\" )\n" +
                        "  	        ?table onto:hasForRow ?row.\n" +
                        "		?row onto:hasForRowNumber ?rowNumber.\n" +
                        "		?row onto:hasForCell ?cell_treatment.\n" +
                        "    	        ?cell_treatment rdf:type domain:treatment.\n" +
                        "  		?cell_treatment onto:hasForFS/onto:hasForElement/rdf:type ?treatment1.\n" +
                        "		BIND(strafter(str(?treatment1), \"#\") as ?treatment)\n" +
                        "}\n" +
                        "ORDER BY ASC(?treatment)";
        
                /************************LOCAL MODE ***********************************/
                 
        Model model= FileManager.get().loadModel("./RDF_DATA/annotations-atweb.ttl");
         
        Query query = QueryFactory.create(comNameQuery);  
        
        QueryExecution qe = QueryExecutionFactory.create(query, model); 
         
        /************************************************************************/

        /**********************SPARQL ENDPOINT MODE*****************
        
        Query query = QueryFactory.create(comNameQuery);  
        
        QueryExecution qe = QueryExecutionFactory.sparqlService(sparqlEndpoint,query);
        
        ************************************************************************/

        try {
                com.hp.hpl.jena.query.ResultSet rs = qe.execSelect();
                
                while ( rs.hasNext() ) {
                
                QuerySolution solution=rs.next();
                
                resultat.add(solution.getLiteral("treatment").getLexicalForm()); 
            }
        } 
        catch(Exception e){ 
            
           throw new Exception_SparqlConnexion(e);
    
        }
        finally {
            qe.close();
        }
        
        return resultat;
    }
    
}
