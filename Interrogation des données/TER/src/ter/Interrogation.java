/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import packageExceptions.Exception_AbsenceDocumentBiomass;
import packageExceptions.Exception_AbsenceExperienceBiomass;
import packageExceptions.Exception_AbsenceValeur;

/**
 *
 * @author proprietaire
 */
class Interrogation {
    
    
    private static String vBiomass = new String(); /*Paramètre d'entrée communiqué par l'interface*/
    
    private static HashMap<String, ArrayList<String>> vTopicOperations; /*Paramètre d'entrée communiqué par l'interface*/
    
    private static HashMap<String, ArrayList<String>> vRelationParametres; /*Paramètre d'entrée communiqué par l'interface*/
    
    private static HashMap<String, ArrayList<String>> vTopicDocs = null;
    
    private static ArrayList<Object_TIEG> vDocExp = null;
    
    private static ArrayList<Object_VecteurCalcul> vVecteurCalculGlobal = null;
    
    private static String vPathFile;
    
    private static File vFichierCalcul; 
    
    
    static File initMatriceCalcul(String v_PathFile, String v_biomass, HashMap<String, ArrayList<String>> v_TopicOperations, HashMap<String, ArrayList<String>> v_RelationParametres ) throws Exception_AbsenceDocumentBiomass, Exception_AbsenceExperienceBiomass
    {
        
        vPathFile=v_PathFile; vBiomass=v_biomass; vTopicOperations=v_TopicOperations; vRelationParametres=v_RelationParametres;
        
        /*Interroger la BDD pour construire pour chaque Topic sa liste de documents*/
        
        for (String topic : vTopicDocs.keySet())
        {
            ArrayList<String> listDoc = InterrogationBDD.getTopicDocument(topic);
            
            if(listDoc==null)
            {
                vTopicDocs.put(topic, listDoc);
            }
        }
        
        /*Pour chaque Topic, pour chaque document vérifier si il existe une expérience sur ladite biomass*/
        
        recupererExperienceBiomass();
        
        /*Construire la matrice de calcul*/
        
        construireMatriceCalcul();
        
        /*Transformer la matrice de calcul en fichier de sortie CSV exploitable avec R*/
        
        transformerMatriceCSV();
        
        /*Retourner le fichier*/
        
        return vFichierCalcul;
    }
    
    
    static void recupererExperienceBiomass() throws packageExceptions.Exception_AbsenceDocumentBiomass
    {
        
       if(vTopicDocs!=null)
       {
                for (String topic : vTopicDocs.keySet()) 
                {
                    ArrayList<String> listDoc = vTopicDocs.get(topic);

                    for(String idDoc : listDoc)
                    {
                        ArrayList<Object_TIEG> listeObj = InterrogationDataRDF.getDocumentExperience(vBiomass,topic,idDoc);

                        if(listeObj!=null)
                        {
                            vDocExp.addAll(listeObj);
                        }
                    }
                }  
       }
       else
       {
           throw new Exception_AbsenceDocumentBiomass();
       }
       
    }
    
    static void construireMatriceCalcul() throws Exception_AbsenceExperienceBiomass
    {
        
        /*Pour chaque objetc : Object_TIEG de vDocExp construire le vecteur d'entrée au calul*/
        /*Le vecteur résultant sera placé sur : vVecteurCalculGlobal*/
      if(vDocExp!=null)
      {
        for(Object_TIEG objTIEG : vDocExp)
        {
            vVecteurCalculGlobal.add(InterrogationDataRDF.getVecteurCalcul(objTIEG));
        }
      }
      else
      {
          throw new Exception_AbsenceExperienceBiomass();
      }
        
    }
    
    static void transformerMatriceCSV()
    {
        
        
        try {
            
            if((vFichierCalcul=new File(vPathFile+"/CalculeR.csv")).exists())
            {
                vFichierCalcul.delete();
            }
            
            vFichierCalcul.createNewFile();
        
        } catch (IOException e) {
            
            e.printStackTrace(); /*Traitement à rajouter*/
        }
        
    }
    
    
    
}
