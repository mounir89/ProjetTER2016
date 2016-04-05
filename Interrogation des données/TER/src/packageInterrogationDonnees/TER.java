/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageInterrogationDonnees;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import packageExceptions.Exception_AbsenceDocument;
import packageExceptions.Exception_AbsenceExperienceBiomass;
import packageExceptions.Exception_BDDException;
import packageExceptions.Exception_FichierCalcule;
import packageExceptions.Exception_ParseException;
import packageExceptions.Exception_SparqlConnexion;
import packageLogger.LoggerException;
import packageInterrogationDonnees.Initialisation;

/**
 *
 * @author proprietaire
 */
public class TER {

    /**
     * @param args the command line arguments
     */
    
    private static ArrayList<Object_VecteurCalcul> vVecteurCalculGlobal=new ArrayList<>();
            
    public static void main(String[] args) throws Exception_FichierCalcule, Exception_SparqlConnexion, IOException, Exception_AbsenceExperienceBiomass {
        // TODO code application logic here
        
        /*ArrayList<String> listeDoc=ConnexionDB.getDocumentTopic("Bioref-PM");
        
        System.out.println(listeDoc);
        
        ArrayList<String> list=new ArrayList<>();
        list.add("op1");
        list.add("op2");
        list.add("op3");
        
        HashMap<String,ArrayList<String>> map=new HashMap<>();
        
        map.put("UPM",list);
        
        map.put("UPM-UFM",list);
        
        OperationJSON.writeTopicOperation(map);
        
        OperationJSON.readTopicOperation(new File("./JSON/TopicOperations.json"));*/
        
        //System.out.println(ConnexionDB.getTopicOperation());
        
        /*vVecteurCalculGlobal.add(new Object_VecteurCalcul("UPM","1227","1", 0, 0, 0, 0, 0, 0));
        vVecteurCalculGlobal.add(new Object_VecteurCalcul("UPM-UFM","1227","1", 0, 0, 0, 0, 0, 0));
        vVecteurCalculGlobal.add(new Object_VecteurCalcul("UPM","1227","1", 0, 0, 0, 0, 0, 0));
        vVecteurCalculGlobal.add(new Object_VecteurCalcul("UPM","1228","1", 0, 0, 0, 0, 0, 0));
        vVecteurCalculGlobal.add(new Object_VecteurCalcul("UPM","1227","1", 0, 0, 0, 0, 0, 0));
        vVecteurCalculGlobal.add(new Object_VecteurCalcul("UPM","1227","1", 0, 0, 0, 0, 0, 0));
        
        transformerMatriceCSV();*/
        
       /* ArrayList<Object_TIEG> result = InterrogationDataRDF.getDocumentExperience("rice_straw","UPM","1225");
        
        for(Object_TIEG obj : result)
        {
            System.out.println(obj.getaIdDoc()+" "+obj.getaExpN()+" "+obj.getaGrMin()+" "+obj.getaGrMax());
        }*/
       
       /*System.out.println(InterrogationOntologie.initRelationParameters());*/
       
       /*String v_path=".";
       
       String v_biomass="rice_straw";
       
       HashMap<String,ArrayList<String>> v_TopicOperations = new HashMap<>();
       
       HashMap<String,ArrayList<String>> v_RelationOperations = new HashMap<>();
              
       v_RelationOperations.put("enzymatic_hydrolysis_solid_constituent_output_state_relation", new ArrayList<>(Arrays.asList("buffer_liquid_quantity")));
       v_RelationOperations.put("milling_liquor_quantity_output_relation", new ArrayList<>(Arrays.asList("water_quantity")));
       v_RelationOperations.put("washing_and_separation_solid_quantity_output_relation", new ArrayList<>(Arrays.asList("acid_quantity")));
       

       
       v_TopicOperations.put("Bioref-PM", new ArrayList<>(Arrays.asList("ball_milling", "cutting_milling", "enzymatic_hydrolysis_treatment","dry_ball_milling","drying")));
       v_TopicOperations.put("Bioref-PM-UFM", new ArrayList<>(Arrays.asList("ball_milling", "cutting_milling", "enzymatic_hydrolysis_treatment","dry_ball_milling","drying")));
       
        try 
        {
            
            Object_RapportCalculMatrice rapport=Interrogation.initMatriceCalcul(v_path, v_biomass, v_TopicOperations, v_RelationOperations);
            
            if(rapport.getMessage()!=null)
            {
                System.out.println("les messages d'absence de valeurs :\n"+rapport.getMessage());
            }
            
        } catch (Exception_AbsenceDocument | Exception_AbsenceExperienceBiomass | Exception_FichierCalcule | Exception_ParseException | IOException | Exception_BDDException | SQLException | Exception_SparqlConnexion ex) 
        {
            LoggerException.getLoggerException().log(Level.WARNING,null,ex);
        }*/
        
        /*try {
            
            ArrayList<String> listDoc = InterrogationBDD.getTopicDocument("Bioref-PM");
            System.out.println("contenu:"+listDoc);
            
        } catch (Exception_BDDException | SQLException e) {
                        System.err.println(e.toString());

        }*/
        
        System.out.println(Initialisation.initBiomass());

    }
    
    private static void transformerMatriceCSV() throws Exception_FichierCalcule
    {
        File vFichierCalcul;
        
        try {
            
            if((vFichierCalcul=new File("CalculeR.csv")).exists())
            {
                vFichierCalcul.delete();
            }
            
            vFichierCalcul.createNewFile();
            
            try (CSVWriter writer = new CSVWriter(new FileWriter("CalculeR.csv"))) 
            {
                List<String[]> data = new ArrayList<>();
                
                vVecteurCalculGlobal.stream().forEach((vector) -> {
                    
                    data.add(new String[]{vector.getaTopic(),vector.getaIdDoc(),vector.getaExpN(),Double.toString(vector.getaSomme()),Double.toString(vector.getaBiomassQty()),Double.toString(vector.getaGrMin()),Double.toString(vector.getaGrMax()),Double.toString(vector.getaGyMin()),Double.toString(vector.getaGyMax())});
                
                });

                writer.writeAll(data);
            }
            } catch (IOException e) {

                throw new Exception_FichierCalcule();
            }
        
        
    }
}
