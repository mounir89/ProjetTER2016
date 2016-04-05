/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageExceptions;

/**
 *
 * @author proprietaire
 */
public class Exception_SparqlConnexion extends Exception{
    
    public Exception_SparqlConnexion() {
        super("Une erreur de connexion avec le sparql endpoint. L'exécution du programme a été interrompue.");
    }

    public Exception_SparqlConnexion(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage()
    {
        return "Une erreur de connexion avec le sparql endpoint. L'exécution du programme a été interrompue.";
    }

}
