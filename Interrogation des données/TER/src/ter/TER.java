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
import java.util.ArrayList;
import java.util.List;
import packageExceptions.Exception_FichierCalcule;
import packageExceptions.Exception_SparqlConnexion;

/**
 *
 * @author proprietaire
 */
public class TER {

    /**
     * @param args the command line arguments
     */
    
    private static ArrayList<Object_VecteurCalcul> vVecteurCalculGlobal=new ArrayList<>();
            
    public static void main(String[] args) throws Exception_FichierCalcule, Exception_SparqlConnexion, IOException {
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
       
       System.out.println(InterrogationOntologie.initRelationParameters());
        
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
