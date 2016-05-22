package com.terweb.packageInterrogationDonnees;

import com.terweb.packageExceptions.Exception_SparqlConnexion;


    /**
   	* 
   	* Classe qui regroupe des méthodes utilisées pour récupérer l'URI d'une table : Process Description sur @WEB
   	* à partir de l'identifiant du document scientifique correspendant.
   	* 
   	* 
   	*/

public class LienDocument {

    /**
   	* 
   	* Fonction qui retourne l'URI de la table annotée (Process Description) d'un document donné.
   	* 
   	* @param path : Chemin des données en cas d'une interrogation en local.
   	* @param idDocument : Identifiant du document.
   	* @return Obj:String
   	* @throws Exception_SparqlConnexion
   	* 
   	*/
	
    public static String genereLien(String path, String idDocument) throws Exception_SparqlConnexion{
		
    	int idTable = Integer.parseInt(InterrogationDataRDF.getTableID(path,idDocument));
    	
    	String lien=getLien(idDocument, idTable);
    	
		return lien;
	}
    
    /**
   	* 
   	* Fonction qui génère l'URI de la table annotée (Process Description) d'un document donné.
   	* 
   	* @param path : Chemin des données en cas d'une interrogation en local.
   	* @param idDocument : Identifiant de la table.
   	* @return Obj:String
   	* 
   	*/
    
    private static String getLien(String idDocument, int idItem)
    {

    	String serial=Integer.toString(idItem * idItem - idItem, 8);
    	
    	String lien="http://pfl.grignon.inra.fr/atWeb/TableServlet?viewTable="+idItem+"&idDoc="+idDocument+"&id="+serial;
 
    	return lien;
    }
}
