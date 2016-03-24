/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import com.hp.hpl.jena.query.*;

/**
 *
 * @author proprietaire
 */
public class TestEndPoint {
    
  String sparqlEndpoint = "http://pfl.grignon.inra.fr:3030/annotation/query";

  public TestEndPoint() {
   
  org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
  
  String variable_biomass="domain:rice_straw";
  
  String comNameQuery = 
        " prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
"        prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
"        PREFIX onto: <http://opendata.inra.fr/resources/atWeb/annotation/>\n" +
"        PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>\n" +
"        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
"        PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
"        SELECT DISTINCT ?idDocument ?experience_number ?glucose_rate_min ?glucose_rate_max" +
"        WHERE {" +
"                ?document onto:hasForID ?idDocument; a onto:Document." +
"                ?document onto:hasTable ?table." +
"                ?table dc:title ?tableTitle." +
"                FILTER regex(?tableTitle, \"Biomass\", \"i\" )" +
"                ?table onto:hasForRow ?row." +
"                ?row onto:hasForCell ?cell_biomass." +
"                ?cell_biomass a domain:biomass." +
"                ?cell_biomass onto:hasForFS/onto:hasForElement/rdf:type "+variable_biomass+"."+
"                ?row onto:hasForCell ?cell_expn." +
"                ?cell_expn a domain:experience_number." +
"                ?cell_expn onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?experience_number." +
"                ?row onto:hasForCell ?cell_gluc_rate." +
"                ?cell_gluc_rate a domain:glucose_rate." +
"                ?cell_gluc_rate onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMinKernel ?glucose_rate_min." +
"                ?cell_gluc_rate onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?glucose_rate_max" +
"        }" +
"        ORDER BY ASC(?idDocument) ASC(?experience_number)";

        //ParameterizedSparqlString queryStr = new ParameterizedSparqlString(comNameQuery);
        
        Query query = QueryFactory.create(comNameQuery);  
        
        QueryExecution qe = QueryExecutionFactory.sparqlService(sparqlEndpoint,query);

        try {
            ResultSet rs = qe.execSelect();
            while ( rs.hasNext() ) {
                
                System.out.println(rs.next().getLiteral("idDocument").getLexicalForm());
                
                //System.out.println(ResultSetFormatter.asText(rs));
            }
       } 
        catch(Exception e) { 
            System.out.println(e.getMessage());
        }
        finally {
            qe.close();
        }
  }

  public static void main(String[] args) {
    new TestEndPoint();
  }
}
