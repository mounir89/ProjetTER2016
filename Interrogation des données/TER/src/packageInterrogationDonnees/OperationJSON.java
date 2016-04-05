/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageInterrogationDonnees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author proprietaire
 */
class OperationJSON {
    
    static boolean createDirectoryJSON(String directoryPath)
    {
        File directory = new File(directoryPath);
        
        boolean mkdir = true;
        
        if (!directory.exists())
        {
            mkdir = directory.mkdir();
        }
        
        return mkdir;
     }
    
    static boolean writeTopicOperation(HashMap<String,ArrayList<String>> liste)
    {
        boolean result = true;
        
        FileOutputStream fileStream=null;
        
        OutputStreamWriter writer=null;
        
        if(createDirectoryJSON("./JSON"))
        {
            try
            {
                //Suppression et Création du fichier JSON
                File file = new File("./JSON/TopicOperations.json");

                if (file.exists()){

                        if(!file.delete())
                        {
                            return false;
                        }
                } 

                if(!file.createNewFile())
                {
                    return false;
                }
                
                fileStream = new FileOutputStream(file);
                
                writer = new OutputStreamWriter(fileStream, "UTF-8");

                    //Définition du vecteur qui va comportre tous les objets JSON
                    JSONArray allVector=new JSONArray();
                    
                    for(Entry<String,ArrayList<String>> entry : liste.entrySet()) {
                        
                        String topic = entry.getKey();
                        
                        ArrayList<String> operations = entry.getValue();
                        
                        /*création d'un objet JSON*/
                        JSONObject obj = new JSONObject();
                        
                        /*création d'un attribut liste*/
                        JSONArray oplist = new JSONArray();
                        
                        Iterator itr=operations.iterator();
                        
                        while(itr.hasNext())
                        {
                            oplist.put(itr.next().toString());
                        }
                        
                        obj.put("Topic", topic);
                        
                        obj.put("operations", oplist);
                        
                        allVector.put(obj);
                        
                    }
                    
                    writer.write(allVector.toString());
                    
                    writer.flush();
                    

             }catch(IOException | JSONException e){
                 
                 result=false; //Un problème d'exécution docn le contenu peut être corrompu
                 
                 e.printStackTrace(); 
                 
             }finally
             {
                 try {
                     
                      if (writer != null) 
                        {
                            writer.close();
                        }
                      
                     if (fileStream != null) 
                        {
                            fileStream.close();
                        }
                     
                 } catch (Exception e) {
                     e.printStackTrace(); 
                 }
                
             }
            
        }else
        {
            return false; //Dossier introuvable et ne peut pas être crée.
        }
        
        return result;
    }
    
    static void readTopicOperation(File jsonFile)
    {
       try {
                JSONArray array = new JSONArray(getDataJSON(jsonFile));

                for(int index = 0; index < array.length(); ++index) 
                {
                    JSONObject jsonObject = array.getJSONObject(index);

                    String topic = (String) jsonObject.get("Topic");

                    System.out.println("topic: "+topic);

                    JSONArray operations = (JSONArray) jsonObject.get("operations");

                    for(int i=0;i<operations.length();i++)
                    {
                        String operation = operations.getString(i);  // get jsonObject @ i position 

                        System.out.println("operation: "+operation);
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
  
        
    }

    static String getDataJSON(File jsonFile)
    {
        String jsonData = "";
		
        BufferedReader br = null;
		
        try {
			String line= null;
                        StringBuilder buf = new StringBuilder();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile), "UTF-8"));
			while ((line = br.readLine()) != null) 
                        {
                            buf.append(line);		
                        }
                        jsonData=buf.toString();
                        
	} catch (IOException e) {
			e.printStackTrace();
	} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
	}
        
        return jsonData;
    }
}
