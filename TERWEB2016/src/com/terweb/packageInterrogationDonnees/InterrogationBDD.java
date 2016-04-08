/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import  com.terweb.packageExceptions.Exception_BDDException;
import com.terweb.packageLogger.LoggerException;

/**
 *
 * @author proprietaire
 */
class InterrogationBDD {
    
    static ArrayList<String> getTopicDocument(String topic) throws Exception_BDDException, SQLException
    {

        ArrayList<String> listeDoc = new ArrayList<>();
        Connection conn;
        PreparedStatement pst;
        ResultSet result;

        conn = ConnexionDB.getConnection();
  
        //Création d'un objet PrepareStatement
        pst = conn.prepareStatement("select d.id_documents"
                      + " from public.documents d, public.topics t, public.ontology o"
                      + " where o.id_ontology=t.id_ontology"
                      + " and t.id_topic=d.id_topic"
                      + " and o.name='BIOREFINERY'"
                      + " and t.name=?"); 
                    
        //Edition du paramètre topic
        pst.setString(1,topic);
                    
        //L'objet ResultSet contient le résultat de la requête SQL
        result = pst.executeQuery();      
                
        while(result.next()){
           
            listeDoc.add(result.getString(1));
                    
        }
         
        pst.close();

        result.close();
        

        if(listeDoc.size()>0)
        {            
            return listeDoc;
        }
            
        return null;
    }


    static HashMap<String,ArrayList<String>> getTopicOperation() throws IOException, SQLException, Exception_BDDException
    {

        HashMap<String,ArrayList<String>> liste = new HashMap<>();
        Connection conn;
        Statement st;
        ResultSet result;
        
        String requete = "SELECT t.name, o.name_en" +
                          " FROM topics t, operations o, topicoperation r, ontology b" +
                          " WHERE r.id_topic=t.id_topic" +
                          " AND r.id_operations=o.id_operations" +
                          " AND t.id_ontology=b.id_ontology" +
                          " AND	UPPER(b.name)='BIOREFINERY'" +
                          " ORDER BY t.name, o.name_en";
        
 
        conn = ConnexionDB.getConnection();
         
        //Création d'un objet Statement
        st=conn.createStatement();

        //L'objet ResultSet contient le résultat de la requête SQL
        result = st.executeQuery(requete);
                    
        while(result.next())
        {
              
                    if(liste.containsKey(result.getString(1)))
                    {
                        ArrayList<String> op=(ArrayList)liste.get(result.getString(1));
                        
                        op.add(result.getString(2));
                    }
                    else
                    {
                        liste.put(result.getString(1), new ArrayList<>(Arrays.asList(result.getString(2))));
                    }
        }

        st.close();

        result.close();
     
        return liste;
    }
    
}
