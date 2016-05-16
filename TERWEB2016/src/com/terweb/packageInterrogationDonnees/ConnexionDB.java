/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.terweb.packageExceptions.Exception_BDDException;


/**
 *
 * Classe qui assure la connexion avec la base de données PostgreSQL.
 * 
 */

public class ConnexionDB {
    
    private static Connection connection = null;
    
    /**
 	 * Fonction qui crée une connexion à la base de données PostgreSQL.
 	 * 
 	 * @return Obj:Connection. 
 	 *  
 	 */
    
    static Connection getConnection() throws Exception_BDDException
    {
        if(connection==null)
        {
            System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		   } catch (ClassNotFoundException e) {
                       
                        //Logger.getLogger(Interrogation.class.getName()).log(Level.WARNING,null,new Exception_BDDException("Absence du driver pour se connecter Ã  la base de donnÃ©ess."));

			throw new Exception_BDDException("Absence du driver pour se connecter Ã  la base de donnÃ©ess.");

		  }

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/atwebdb","role_TER_user",
					"Admin123");

		} catch (SQLException e) {
 
                    throw new Exception_BDDException("Impossible d'Ã©tablir la connexion avec la base de donnÃ©es.");

		}
                 
        }
        return connection;
    }
   
}


