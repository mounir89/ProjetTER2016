/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageInterrogationDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import packageExceptions.Exception_BDDException;


/**
 *
 * @author proprietaire
 */
class ConnexionDB {
    
    private static Connection connection = null;
    
    static Connection getConnection() throws Exception_BDDException
    {
        if(connection==null)
        {
            System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		   } catch (ClassNotFoundException e) {
                       
                        //Logger.getLogger(Interrogation.class.getName()).log(Level.WARNING,null,new Exception_BDDException("Absence du driver pour se connecter à la base de donnéess."));

			throw new Exception_BDDException("Absence du driver pour se connecter à la base de donnéess.");

		  }

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/atwebdb","role_TER_user",
					"Admin123");

		} catch (SQLException e) {
 
                    throw new Exception_BDDException("Impossible d'établir la connexion avec la base de données.");

		}
                 
        }
        return connection;
    }
   
}


