/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.terweb.packageExceptions.*;

/**
 *
 * @author proprietaire
 */
public class Interrogation {
    
    
    private static String vBiomass = new String(); /*Paramètre d'entrée communiqué par l'interface*/
    
    private static HashMap<String, ArrayList<String>> vTopicOperations; /*Paramètre d'entrée communiqué par l'interface*/
    
    private static HashMap<String, ArrayList<String>> vRelationParametres; /*Paramètre d'entrée communiqué par l'interface*/
    
    private static HashMap<String, ArrayList<String>> vTopicDocs= new HashMap<>();
    
    private static ArrayList<Object_TIEG> vDocExp=new ArrayList<>();
    
    private static ArrayList<Object_VecteurCalcul> vVecteurCalculGlobal= new ArrayList<>();
    
    private static String vPathFile;
    
    private static String vPathRacine; //Chemin du dossier courant sur le serveur d'h�bergement
    
    private static File vFichierCalcul; 
    
    
                   
    //LoggerException.getLoggerException().log(Level.WARNING,null,ex);
    
    public static Object_RapportCalculMatrice initMatriceCalcul(String v_PathFile, String v_biomass, HashMap<String, ArrayList<String>> v_TopicOperations, HashMap<String, ArrayList<String>> v_RelationParametres ) throws Exception_AbsenceDocument, Exception_AbsenceExperienceBiomass, Exception_FichierCalcule, Exception_ParseException, IOException, Exception_BDDException, SQLException, Exception_SparqlConnexion, Exception_MatriceCalculVide
    {
        Object_RapportCalculMatrice rapport = new Object_RapportCalculMatrice();
         
        if((v_biomass!=null) && (v_TopicOperations.size()>0) && (v_RelationParametres.size()>0))
        {

        	vPathFile=v_PathFile; vBiomass=v_biomass; vTopicOperations=v_TopicOperations; vRelationParametres=v_RelationParametres;

            vPathRacine=v_PathFile.substring(0, v_PathFile.indexOf("DirectoryUsers"));
            
            /*Interroger la BDD pour construire pour chaque Topic sa liste de documents (vTopicDocs)*/

            for (String topic : vTopicOperations.keySet())
            {

                ArrayList<String> listDoc = InterrogationBDD.getTopicDocument(topic);

                if(listDoc!=null)
                {
                    vTopicDocs.put(topic, listDoc);
                }
            }
            
            /*Pour chaque Topic, pour chaque document vérifier si il existe une expérience sur ladite biomass*/
            /*Si !(vTopicDocs.size() > 0) ---> Exception_AbsenceDocument()*/
            /*Resultat : vDocExp*/

            recupererExperienceBiomass();
            
            /*Construire la matrice de calcul et renvoyer les messages d'absence de valeurs*/
            /*Si !(vDocExp.size() > 0) --> Exception_AbsenceExperienceBiomass*/
            /*Resultat : vVecteurCalculGlobal*/

            rapport.setMessage(construireMatriceCalcul());
            
            

            /*Transformer la matrice de calcul en fichier de sortie CSV exploitable avec R*/

            if(vVecteurCalculGlobal.size()>0)
            {            	
            	transformerMatriceCSV();
            }
            else
            {
                throw new Exception_MatriceCalculVide();
            }

            /*Retourner le fichier*/

            rapport.setFichierCalcule(vFichierCalcul);


        }
        else
        {
            throw new Exception_MatriceCalculVide();
        }
        
        /*Renvoyer le rapport*/
        return rapport;
    }
    
    
    private static void recupererExperienceBiomass() throws Exception_AbsenceDocument, IOException, Exception_SparqlConnexion
    {
    	
       if(vTopicDocs.size() > 0 )
       {
           for (String topic : vTopicDocs.keySet())
           {
               ArrayList<String> listDoc = vTopicDocs.get(topic);
               
               for(String idDoc : listDoc)
               {
            	   
            	   ArrayList<Object_TIEG> listeObj = InterrogationDataRDF.getDocumentExperience(vPathRacine,vBiomass,topic,idDoc);
                   
                   if(listeObj!=null)
                   {
                       vDocExp.addAll(listeObj);
                   }
               }
           }
    
       }
       else
       {
         
           //LoggerException.getLoggerException().log(Level.WARNING,null, new Exception_ParseException());

           throw new Exception_AbsenceDocument();

       }
       
    }
    
    private static ArrayList<String> construireMatriceCalcul() throws Exception_AbsenceExperienceBiomass, Exception_ParseException,IOException, Exception_SparqlConnexion {

        /*Pour chaque objetc : Object_TIEG de vDocExp construire le vecteur d'entrée au calul*/
        /*Le vecteur résultant sera placé sur : vVecteurCalculGlobal*/
        /*En cas de valeurs manquantes, le vecteur sera null, un message indiquant l'emplacement du manque ser transmis à la servlet*/
      
      Object_RapportCalculVecteur rapport;
          
      ArrayList<String> message= new ArrayList<>();
      
      if(vDocExp.size() > 0)
      {
              
          for(Object_TIEG obj : vDocExp)
          {
             try {   

                 if((rapport=InterrogationDataRDF.getVecteurCalcul(vPathRacine, obj, vTopicOperations, vRelationParametres)).getVecteurCalcul()!=null)
                 {
                    vVecteurCalculGlobal.add(rapport.getVecteurCalcul());
                 }
                 else
                 {
                   if(rapport.getMessage()!=null)
                   {
                      message.add(rapport.getMessage());
                   }
                   
                 }
                   
              } catch (Exception_ParseException ex) {
     
                      throw ex;
              }
          }
      }
      else
      {
          throw new Exception_AbsenceExperienceBiomass();
      }
        
      return message;
      
    }
    
    private static void transformerMatriceCSV() throws Exception_FichierCalcule, IOException
    {

        try {
            
                if((vFichierCalcul=new File(vPathFile+"/CalculeR.csv")).exists())
                {
                    vFichierCalcul.delete();
                }

                vFichierCalcul.createNewFile();
            
                try (CSVWriter writer = new CSVWriter(new FileWriter(vPathFile+"/CalculeR.csv"))) 
                {
                    List<String[]> data = new ArrayList<>();
                    
                    //On ajoute le header
                    
                    data.add(new String[]{"TOPIC","IDDOCUMENT","EXPERIENCE_NUMBER",
                                              "BIOMASS_QTY",
                                              "QUANTITY_MATTER",
                                              "GLUCOSE_RATE_MIN","GLUCOSE_RATE_MAX",
                                              "GLUCOSE_YIELD_MIN","GLUCOSE_YIELD_MAX",
                                              "RELIABILITY_MIN","RELIABILITY_MAX"});

                    for( Object_VecteurCalcul vector : vVecteurCalculGlobal){
                    	
                        data.add(new String[]{vector.getaTopic(),vector.getaIdDoc(),vector.getaExpN(),
                                              Double.toString(vector.getaBiomassQty()),
                                              Double.toString(AdaptationDonnees.doubleFractionPrecision(vector.getaSomme(),4)),
                                              Double.toString(AdaptationDonnees.doubleFractionPrecision(vector.getaGrMin()/100,4)),Double.toString(AdaptationDonnees.doubleFractionPrecision(vector.getaGrMax()/100,4)), //On divise par 100 pour ramener le % en KG
                                              Double.toString(AdaptationDonnees.doubleFractionPrecision(vector.getaGyMin()/100,4)),Double.toString(AdaptationDonnees.doubleFractionPrecision(vector.getaGyMax()/100,4)), //On divise par 100 pour ramener le % en KG
                                              Double.toString(AdaptationDonnees.doubleFractionPrecision(vector.getaReliabilityMin()/5,2)),Double.toString(AdaptationDonnees.doubleFractionPrecision(vector.getaReliabilityMax()/5,2))}); //On divise la Reliability par 5 pour la normanliser sur une echelle de 0-->1

                    }

                    writer.writeAll(data);
                }
                
            } catch (IOException ex) {
 
                throw new Exception_FichierCalcule();
            }
        
        
    }
 
}
