/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author proprietaire
 */
class InterrogationBDD {
    
    static ArrayList<String> getTopicDocument(String topic)
    {

        ArrayList<String> listeDoc = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet result= null;
        
        try {
 
                conn = ConnexionDB.getConnection();
         
                    //Création d'un objet PrepareStatement
                    pst = conn.prepareStatement("select d.id_documents"
                      + " from documents d, topics t, ontology o"
                      + " where o.id_ontology=t.id_ontology"
                      + " and t.id_topic=d.id_topic"
                      + " and o.name='BIOREFINERY'"
                      + " and t.name= ?"); 
                    
                    //Edition du paramètre topic
                    pst.setString(1, topic);
                    
                    //L'objet ResultSet contient le résultat de la requête SQL
                    result = pst.executeQuery();      
                
                while(result.next()){
                    
                    listeDoc.add(result.getString(1));
                    
                }
         
            } catch (Exception e) {
                e.printStackTrace();
            }finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (result != null) {
                    result.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
            
        if(listeDoc.size()>0)
        {return listeDoc;}
            
        return null;
    }


    static HashMap<String,ArrayList<String>> getTopicOperation()
    {

        HashMap<String,ArrayList<String>> liste = new HashMap<>();
        Connection conn = null;
        Statement st = null;
        ResultSet result= null;
        
        String requete = "SELECT t.name, o.name_en" +
                          " FROM topics t, operations o, topicoperation r, ontology b" +
                          " WHERE r.id_topic=t.id_topic" +
                          " AND r.id_operations=o.id_operations" +
                          " AND t.id_ontology=b.id_ontology" +
                          " AND	UPPER(b.name)='BIOREFINERY'" +
                          " ORDER BY t.name, o.name_en";
        
        try {
 
                    conn = ConnexionDB.getConnection();
         
                    //Création d'un objet Statement
                    st=conn.createStatement();

                    //L'objet ResultSet contient le résultat de la requête SQL
                    result = st.executeQuery(requete);
                    
                while(result.next()){
              
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
         
            } catch (Exception e) {
                e.printStackTrace();
            }finally {

            try {
                 if (result != null) {
                    result.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
            
        return liste;
    }
    
}
