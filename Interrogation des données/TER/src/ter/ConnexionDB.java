/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

import java.sql.Connection;
import java.sql.DriverManager;
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
class ConnexionDB {
    
    private static Connection connection = null;
    
    static Connection getConnection()
    {
        if(connection!=null)
        {
            System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		   } catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			
                        return null;

		  }

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/atwebdb", "role_TER_user",
					"Admin123");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			
                        e.printStackTrace();
			
                        return null;

		}
                
                System.out.println("Connection established!");
                
        }

        return connection;
    }
   
}


