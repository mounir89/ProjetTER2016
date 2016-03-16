/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import java.util.ArrayList;

/**
 *
 * @author proprietaire
 */
class InterrogationDataRDF {
    
    /*
        prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
        prefix owl: <http://www.w3.org/2002/07/owl#>
        PREFIX onto: <http://opendata.inra.fr/resources/atWeb/annotation/>
        PREFIX domain: <http://opendata.inra.fr/resources/BIORAF#>
        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
        PREFIX dc: <http://purl.org/dc/elements/1.1/>

        SELECT DISTINCT ?idDocument ?experience_number ?glucose_rate_min ?glucose_rate_max
        WHERE {
                ?document onto:hasForID ?idDocument; a onto:Document.
                FILTER(str(?idDocument)="***********?*************")
                ?document onto:hasTable ?table.
                ?table dc:title ?tableTitle.
                FILTER regex(?tableTitle, "Biomass", "i" )
                ?table onto:hasForRow ?row.
                ?row onto:hasForCell ?cell_biomass. 
                ?cell_biomass a domain:biomass.
                ?cell_biomass onto:hasForFS/onto:hasForElement/rdf:type ?biomass
                FILTER(?biomass = "**************?****************")
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
    static ArrayList<Object_TIEG> getDocumentExperience(String vBiomass, String topic, String idDoc)
    {
        return null;
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
    
    static Object_VecteurCalcul getVecteurCalcul(Object_TIEG objTIEG)
    {
        return null;
    }
}
