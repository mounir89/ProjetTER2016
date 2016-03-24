/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import packageExceptions.*;
import packageLogger.LoggerException;

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
    
    
    static File initMatriceCalcul(String v_PathFile, String v_biomass, HashMap<String, ArrayList<String>> v_TopicOperations, HashMap<String, ArrayList<String>> v_RelationParametres ) throws Exception_AbsenceDocument, Exception_AbsenceExperienceBiomass, Exception_FichierCalcule, Exception_ParseException, IOException, Exception_BDDException, Exception_AbsenceValeur, SQLException
    {
        
        vPathFile=v_PathFile; vBiomass=v_biomass; vTopicOperations=v_TopicOperations; vRelationParametres=v_RelationParametres;
        
        /*Interroger la BDD pour construire pour chaque Topic sa liste de documents*/
        
        for (String topic : vTopicOperations.keySet())
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
    
    
    private static void recupererExperienceBiomass() throws Exception_AbsenceDocument, IOException
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
         
           LoggerException.getLoggerException().log(Level.WARNING,null, new Exception_ParseException());

           throw new Exception_AbsenceDocument();

       }
       
    }
    
    private static void construireMatriceCalcul() throws Exception_AbsenceExperienceBiomass, Exception_ParseException, Exception_AbsenceValeur, IOException {

        /*Pour chaque objetc : Object_TIEG de vDocExp construire le vecteur d'entrée au calul*/
        /*Le vecteur résultant sera placé sur : vVecteurCalculGlobal*/
        
      if(vDocExp!=null)
      {
              
          for(Object_TIEG obj : vDocExp)
          {
             try {   
               
                 vVecteurCalculGlobal.add(InterrogationDataRDF.getVecteurCalcul(obj, vTopicOperations, vRelationParametres));
                   
              } catch (Exception_ParseException | Exception_AbsenceValeur ex) {
               
                      LoggerException.getLoggerException().log(Level.WARNING,null,ex);
              }
          }

      }
      else
      {
          LoggerException.getLoggerException().log(Level.WARNING,null, new Exception_AbsenceExperienceBiomass());

          throw new Exception_AbsenceExperienceBiomass();
      }
        
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

                    vVecteurCalculGlobal.stream().forEach((vector) -> {

                        data.add(new String[]{vector.getaTopic(),vector.getaIdDoc(),vector.getaExpN(),Double.toString(vector.getaBiomassQty()),Double.toString(vector.getaSomme()),Double.toString(vector.getaGrMin()),Double.toString(vector.getaGrMax()),Double.toString(vector.getaGyMin()),Double.toString(vector.getaGyMax())});

                    });

                    writer.writeAll(data);
                }
                
            } catch (IOException ex) {
                
                LoggerException.getLoggerException().log(Level.WARNING,null,new Exception_FichierCalcule());

                throw new Exception_FichierCalcule();
            }
        
        
    }
 
}
