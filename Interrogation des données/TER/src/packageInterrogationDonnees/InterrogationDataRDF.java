/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageInterrogationDonnees;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import packageExceptions.*;
import packageLogger.LoggerException;

/**
 *
 * @author proprietaire
 */
class InterrogationDataRDF {
   
    static String sparqlEndpoint = "http://pfl.grignon.inra.fr:3030/annotation/query";
    
    static ArrayList<String> getBiomass() throws Exception_SparqlConnexion
    {
        ArrayList<String> resultat = new ArrayList<>();
        
        String comNameQuery=
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX onto: <http://opendata.inra.fr/resources/atWeb/annotation/>\n" +
                "PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                "\n" +
                "SELECT DISTINCT ?biomass\n" +
                "WHERE {\n" +
                "			?document onto:hasForID ?idDocument; a onto:Document.\n" +
                "  	                ?document onto:hasTable ?table.\n" +
                "  			?table dc:title ?tableTitle.\n" +
                "       		FILTER regex(?tableTitle, \"Biomass\", \"i\" )\n" +
                "	                ?table onto:hasForRow ?row.\n" +
                "	                ?row onto:hasForCell ?cell_biomass. \n" +
                "	                ?cell_biomass a domain:biomass.\n" +
                "	                ?cell_biomass onto:hasForFS/onto:hasForElement/rdf:type ?biomassURI\n" +
                "   			BIND(strafter(str(?biomassURI), \"#\") as ?biomass)\n" +
                "}\n" +
                "ORDER BY ASC(?biomass)";
        
        Query query = QueryFactory.create(comNameQuery);  
        
        QueryExecution qe = QueryExecutionFactory.sparqlService(sparqlEndpoint,query);

        try {
                ResultSet rs = qe.execSelect();
                
                while ( rs.hasNext() ) {
                
                QuerySolution solution=rs.next();
                
                resultat.add(solution.getLiteral("biomass").getLexicalForm()); 
            }
        } 
        catch(Exception e){ 
            
           throw new packageExceptions.Exception_SparqlConnexion(e);
    
        }
        finally {
            qe.close();
        }
        
        return resultat;
    }
    /*
        prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
        prefix owl: <http://www.w3.org/2002/07/owl#>
        PREFIX onto: <http://opendata.inra.fr/resources/atWeb/annotation/>
        PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>
        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
        PREFIX dc: <http://purl.org/dc/elements/1.1/>

        SELECT DISTINCT ?experience_number ?glucose_rate_min ?glucose_rate_max
        WHERE {
                ?document onto:hasForID ?idDocument; a onto:Document.
                FILTER(str(?idDocument)=?variable_idDocument)
                ?document onto:hasTable ?table.
                ?table dc:title ?tableTitle.
                FILTER regex(?tableTitle, "Biomass", "i" )
                ?table onto:hasForRow ?row.
                ?row onto:hasForCell ?cell_biomass. 
                ?cell_biomass a domain:biomass.
                ?cell_biomass onto:hasForFS/onto:hasForElement/rdf:type ?biomass
                FILTER(?biomass = ?variable_idDocument)
                ?row onto:hasForCell ?cell_expn.
                ?cell_expn a domain:experience_number.
                ?cell_expn onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?experience_number.
                ?row onto:hasForCell ?cell_gluc_rate.
                ?cell_gluc_rate a domain:glucose_rate.
                ?cell_gluc_rate onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMinKernel ?glucose_rate_min.
                ?cell_gluc_rate onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?glucose_rate_max
        }
        ORDER BY ASC(?idDocument) ASC(?experience_number)
    */
    static ArrayList<Object_TIEG> getDocumentExperience(String vBiomass, String topic, String idDoc) throws IOException, Exception_SparqlConnexion
    {
        ArrayList<Object_TIEG> resultat = new ArrayList<>();
        
        String comNameQuery=
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
   "        prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
   "        PREFIX onto: <http://opendata.inra.fr/resources/atWeb/annotation/>\n" +
   "        PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>\n" +
   "        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
   "        PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
   "        SELECT DISTINCT ?experience_number ?glucose_rate_min ?glucose_rate_max\n" +
   "        WHERE {\n" +
    "                ?document onto:hasForID ?idDocument; a onto:Document.\n" +
    "                FILTER(str(?idDocument)=\""+idDoc+"\")\n" +
    "                ?document onto:hasTable ?table.\n" +
    "                ?table dc:title ?tableTitle.\n" +
    "                FILTER regex(?tableTitle, \"Biomass\", \"i\" )\n" +
    "                ?table onto:hasForRow ?row.\n" +
    "                ?row onto:hasForCell ?cell_biomass. \n" +
    "                ?cell_biomass a domain:biomass.\n" +
    "                ?cell_biomass onto:hasForFS/onto:hasForElement/rdf:type ?biomass\n" +
    "                FILTER(?biomass = domain:"+vBiomass+")\n" +
    "                ?row onto:hasForCell ?cell_expn.\n" +
    "                ?cell_expn a domain:experience_number.\n" +
    "                ?cell_expn onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?experience_number.\n" +
    "                ?row onto:hasForCell ?cell_gluc_rate.\n" +
    "                ?cell_gluc_rate a domain:glucose_rate.\n" +
    "                ?cell_gluc_rate onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMinKernel ?glucose_rate_min.\n" +
    "                ?cell_gluc_rate onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?glucose_rate_max\n" +
    "        }\n" +
    "        ORDER BY ASC(?idDocument) ASC(?experience_number)";

        Query query = QueryFactory.create(comNameQuery);  
        
        QueryExecution qe = QueryExecutionFactory.sparqlService(sparqlEndpoint,query);

        try {
                ResultSet rs = qe.execSelect();
                
                while ( rs.hasNext() ) {
                
                QuerySolution solution=rs.next();
                
                resultat.add(new Object_TIEG(topic, idDoc,solution.getLiteral("experience_number").getLexicalForm(), 
                                                          solution.getLiteral("glucose_rate_min").getLexicalForm(),
                                                          solution.getLiteral("glucose_rate_max").getLexicalForm())); 
            }
        } 
        catch(Exception e){ 
            
           throw new packageExceptions.Exception_SparqlConnexion(e);
    
        }
        finally {
            qe.close();
        }
        
        return resultat;
    }
    
    /*
    
    prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    prefix owl: <http://www.w3.org/2002/07/owl#>
    PREFIX onto: <http://opendata.inra.fr/resources/atWeb/annotation/>
    PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>
    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX dc: <http://purl.org/dc/elements/1.1/>
    PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
    PREFIX ont: <http://purl.org/net/ns/ontology-annot#>

    SELECT  Distinct ?treatment ?relation ?biomass_quantity ?biomass_unit ?parametre ?unit ?value ?gy_plus ?gy_min
    WHERE {?document rdf:type onto:Document.
  	   ?document onto:hasForID ?idDocument.
           FILTER(str(?idDocument)="**********************?************************")
  	   ?document onto:hasForOntology ?ontology.
 	   Filter(str(?ontology)="http://opendata.inra.fr/resources/BIORAF#")
		
  	   ?document onto:hasTable ?table.
           ?table dc:title ?tableTitle.
       	   FILTER regex(?tableTitle, "Process", "i" )
		
  	   ?table onto:hasForRow ?row.
		
	   ?row onto:hasForRowNumber ?rowNumber.
		
	   ?row onto:hasForCell ?cell_expn.
    	   ?cell_expn rdf:type domain:experience_number.
  	   ?cell_expn onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?experience_number.
	   FILTER(str(?experience_number)="*******************?***********************")
				
	   ?row onto:hasForRelation/rdf:type ?row_relation. 
	   BIND(strafter(str(?row_relation), "#") as ?relation)
		
	   ?row onto:hasForCell ?cell_treatment.
    	   ?cell_treatment rdf:type domain:treatment.
           ?cell_treatment onto:hasForFS/onto:hasForElement/rdf:type ?treatment1.
           BIND(strafter(str(?treatment1), "#") as ?treatment)
  
	   ?row onto:hasForCell ?cell_bioqty.
    	   ?cell_bioqty rdf:type domain:biomass_quantity.
  	   ?cell_bioqty onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?biomass_quantity.
  	   ?cell_bioqty onto:hasForFS/onto:hasForUnit ?biomass_unit1.
	   BIND(strafter(str(?biomass_unit1), "#") as ?biomass_unit)
  
    	   ?row onto:hasForCell ?cell_substance.
    	   ?cell_substance rdf:type ?cellType.
	   FILTER( ?cellType != onto:Cell && ?cellType != domain:biomass_quantity && ?cellType != domain:glucose_yield)
           BIND(strafter(str(?cellType), "#") as ?parametre)
           ?cell_substance onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?value.
           ?cell_substance onto:hasForFS/onto:hasForUnit ?unit1
           BIND(strafter(str(?unit1), "#") as ?unit)
  
  	   OPTIONAL{
			?row onto:hasForCell ?cell_gy.
			?cell_gy rdf:type domain:glucose_yield.
			?cell_gy onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?gy_plus.
			?cell_gy onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMinKernel ?gy_min.		
	    }
    }
    ORDER BY ASC(?rowNumber) ASC(?relation)
    
    */
    
    static Object_RapportCalculVecteur getVecteurCalcul(Object_TIEG objTIEG, HashMap<String, ArrayList<String>> vTopicOperations, HashMap<String, ArrayList<String>> vRelationParametres) throws Exception_ParseException,IOException, Exception_SparqlConnexion
    {
        String treatment,relation,biomass_quantity,biomass_unit,parametre,unit,value;
        
        String gy_min=null, gy_plus=null; 
        
        double somme=0; double gy_p=0; double gy_m=0; double reliability_min=0; double reliability_max=0;
        
        Object_RapportCalculVecteur rapport=new Object_RapportCalculVecteur();
        
        Object_VecteurCalcul vecteur=null;
        
        String message=null;
        
        String comNameQuery="prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "    prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "    PREFIX onto: <http://opendata.inra.fr/resources/atWeb/annotation/>\n" +
        "    PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>\n" +
        "    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "    PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
        "    PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
        "    PREFIX ont: <http://purl.org/net/ns/ontology-annot#>\n" +
        "\n" +
        "    SELECT  Distinct ?treatment ?relation ?biomass_quantity ?biomass_unit ?parametre ?unit ?value ?gy_plus ?gy_min ?reliability_min ?reliability_max\n" +
        "    WHERE {?document rdf:type onto:Document.\n" +
        "  	   ?document onto:hasForID ?idDocument.\n" +
        "          FILTER(str(?idDocument)=\""+objTIEG.getaIdDoc()+"\")\n" +
        "  	   ?document onto:hasForOntology ?ontology.\n" +
        " 	   Filter(str(?ontology)=\"http://opendata.inra.fr/resources/BIORAF#\")\n" +
        "\n" +
        "          ?document onto:hasForMinReliability ?reliability_min."+
        "          ?document onto:hasForMaxReliability ?reliability_max."+
        "\n" +
        "  	   ?document onto:hasTable ?table.\n" +
        "          ?table dc:title ?tableTitle.\n" +
        "          FILTER regex(?tableTitle, \"Process\", \"i\" )\n" +
        "\n" +
        "  	   ?table onto:hasForRow ?row.\n" +
        "		\n" +
        "	   ?row onto:hasForRowNumber ?rowNumber.\n" +
        "		\n" +
        "	   ?row onto:hasForCell ?cell_expn.\n" +
        "    	   ?cell_expn rdf:type domain:experience_number.\n" +
        "  	   ?cell_expn onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?experience_number.\n" +
        "	   FILTER(str(?experience_number)=\""+objTIEG.getaExpN()+"\")\n" +
        "\n" +
        "	   ?row onto:hasForRelation/rdf:type ?row_relation. \n" +
        "	   BIND(strafter(str(?row_relation), \"#\") as ?relation)\n" +
        "\n" +
        "	   ?row onto:hasForCell ?cell_treatment.\n" +
        "    	   ?cell_treatment rdf:type domain:treatment.\n" +
        "           ?cell_treatment onto:hasForFS/onto:hasForElement/rdf:type ?treatment1.\n" +
        "           BIND(strafter(str(?treatment1), \"#\") as ?treatment)\n" +
        "\n" +
        "	   ?row onto:hasForCell ?cell_bioqty.\n" +
        "    	   ?cell_bioqty rdf:type domain:biomass_quantity.\n" +
        "  	   ?cell_bioqty onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?biomass_quantity.\n" +
        "  	   ?cell_bioqty onto:hasForFS/onto:hasForUnit ?biomass_unit1.\n" +
        "	   BIND(strafter(str(?biomass_unit1), \"#\") as ?biomass_unit)\n" +
        "\n" +
        "    	   ?row onto:hasForCell ?cell_substance.\n" +
        "    	   ?cell_substance rdf:type ?cellType.\n" +
        "	   FILTER( ?cellType != onto:Cell && ?cellType != domain:biomass_quantity && ?cellType != domain:glucose_yield)\n" +
        "          BIND(strafter(str(?cellType), \"#\") as ?parametre)\n" +
        "          ?cell_substance onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?value.\n" +
        "          ?cell_substance onto:hasForFS/onto:hasForUnit ?unit1\n" +
        "          BIND(strafter(str(?unit1), \"#\") as ?unit)\n" +
        "\n" +                
        "  	   OPTIONAL{\n" +
        "			?row onto:hasForCell ?cell_gy.\n" +
        "			?cell_gy rdf:type domain:glucose_yield.\n" +
        "			?cell_gy onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMaxKernel ?gy_plus.\n" +
        "			?cell_gy onto:hasForFS/onto:hasForFuzzyElement/onto:hasForMinKernel ?gy_min.\n" +
        "	    }\n" +
        "    }\n" +
        "    ORDER BY ASC(?rowNumber) ASC(?relation)";
 
        
        Query query = QueryFactory.create(comNameQuery);  
        
        QueryExecution qe = QueryExecutionFactory.sparqlService(sparqlEndpoint,query);

        
        try {
                ResultSet rs = qe.execSelect();
                
                while ( rs.hasNext() ) {
                
                QuerySolution solution=rs.next();
                
                treatment=solution.getLiteral("treatment").getLexicalForm();
                relation=solution.getLiteral("relation").getLexicalForm();
                biomass_quantity=solution.getLiteral("biomass_quantity").getLexicalForm();
                biomass_unit=solution.getLiteral("biomass_unit").getLexicalForm();
                parametre=solution.getLiteral("parametre").getLexicalForm();
                unit=solution.getLiteral("unit").getLexicalForm();
                value=solution.getLiteral("value").getLexicalForm();
                
                if(solution.getLiteral("gy_min")!=null)
                {
                    gy_min=solution.getLiteral("gy_min").getLexicalForm();
                }
                
                if(solution.getLiteral("gy_plus")!=null)
                {
                    gy_plus=solution.getLiteral("gy_plus").getLexicalForm();
                }
                
                reliability_min=Double.parseDouble(solution.getLiteral("reliability_min").getLexicalForm());
                reliability_max=Double.parseDouble(solution.getLiteral("reliability_max").getLexicalForm());
                
                
                /*Vérifier que le traitement a été choisi dans la définition du Topic*/
                if(vTopicOperations.get(objTIEG.getaTopic()).contains(treatment))
                {

                    /*Vérifier que la relation et le paramètre ont été choisis*/
                    if(vRelationParametres.containsKey(relation) && vRelationParametres.get(relation).contains(parametre))
                    {
                        if(value.equals("inf")|| (gy_min!=null && gy_min.equals("inf")) || (gy_plus!=null && gy_plus.equals("inf")))
                        {
                             message="Valeur manquante::"+
                                     "Topic-"+objTIEG.getaTopic()+"::"+
                                     "IDDoc-"+objTIEG.getaIdDoc()+"::"+
                                     "Experience Number-"+objTIEG.getaExpN()+"::"+
                                     "Treatment-"+treatment+"::"+
                                     "Relation-"+relation+"::"+
                                     "Parameter-"+parametre+"::"+
                                     "Glucose Yield(-)-"+gy_min+"::"+
                                     "Glucose Yield(+)-"+gy_plus;
                             
                             LoggerException.getLoggerException().log(Level.WARNING,null, new Exception_AbsenceValeur(message));
                             
                             //Sortir du traitement
                             rapport.setMessage(message);
                             
                             return rapport;
                        }
                        else
                        {

                            if(biomass_quantity.equals("0.000e+0") || biomass_quantity.equals("inf"))
                            {                    
                               somme+=AdaptationDonnees.conversionUnite(unit, value);
                            }
                            else
                            {
                                somme+=AdaptationDonnees.conversionUnite(unit, value)/AdaptationDonnees.conversionUnite(biomass_unit, biomass_quantity);
                            }

                            /*Si Glucose Yield n'est pas défini ???*/

                            if(gy_min!=null && gy_m==0)
                            {
                                gy_m=AdaptationDonnees.conversionStringVersDouble(gy_min);
                            }

                            if(gy_plus!=null && gy_p==0)
                            {
                                gy_p=AdaptationDonnees.conversionStringVersDouble(gy_plus);
                            }

                            vecteur=new Object_VecteurCalcul(objTIEG.getaTopic(),objTIEG.getaIdDoc(), objTIEG.getaExpN(),somme,1, AdaptationDonnees.conversionStringVersDouble(objTIEG.getaGrMin()), AdaptationDonnees.conversionStringVersDouble(objTIEG.getaGrMax()), gy_m, gy_p, reliability_min, reliability_max);

                        }

                    }

                }
            }
        } 
        catch(IOException | Exception_ParseException e) { 
            
            switch (e.getClass().toString()) {
                case "Exception_ParseException":
                    throw new Exception_ParseException();
                case "IOException":
                    throw e;
                default:
                    throw new Exception_SparqlConnexion(e);
     
            }
                
        }
        finally {
            qe.close();
        }
        
        //mise à jour du rendu du rapport
        rapport.setVecteurCalcul(vecteur);
        
        return rapport;
    }
}
