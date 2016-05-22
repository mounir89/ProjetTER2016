/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * Classe qui définit un objet représentant le rapport de l'étape de construction de la matrice de calcul globale.
 * Ce rapport pointe le fichier de calcul au format csv crée à partir de la matrice et liste les erreurs rencontrés.
 *
 */

public class Object_RapportCalculMatrice {
    
    private File fichierCalcule;
    private ArrayList<String> message;

    /**
    *
    * Constructeur paramétré d'un objet de la classe.
    * 
    * @param fichierCalcule : Fichier csv.
    * @param message : Liste de messages d'erreurs.
    *
    */
    
    public Object_RapportCalculMatrice(File fichierCalcule, ArrayList<String> message) {
        this.fichierCalcule = fichierCalcule;
        this.message = message;
    }
    
    /**
    *
    * Constructeur par défaut d'un objet de la classe.
    * 
    */
    
    public Object_RapportCalculMatrice() {
        this.fichierCalcule = null;
        this.message = null;
    }
    
    /**
    *
    * Fonction qui retourne le fichier de calcul csv.
    * @return obj:File.
    * 
    */
    
    public File getFichierCalcule() {
        return fichierCalcule;
    }

    /**
    *
    * Procédure qui modifie le fichier de calcul csv.
    * @param fichierCalcule : Nouveau fichier de calcul.
    * 
    */
    
    public void setFichierCalcule(File fichierCalcule) {
        this.fichierCalcule = fichierCalcule;
    }

    /**
    *
    * Fonction qui retourne la liste des messages d'erreurs.
    * @return obj:ArrayList.
    * 
    */
    
    public ArrayList<String> getMessage() {
        return message;
    }

    /**
    *
    * Procédure qui modifie la liste des mesages d'erreurs.
    * @param message : Nouvelle liste des messages d'erreurs.
    * 
    */
    
    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }
    
    
}
