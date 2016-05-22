/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

/**
 *
 * Classe utilisée lors des traitements des données. Un objet de cette classe stocke les informations suivantes :
 * Topic : Identifiant-Document : Experience-Number : GlucoseRate-Min : GlucoseRate-Max.
 *
 */

public class Object_TIEG {
    
    private String aTopic;
    
    private String aIdDoc;
    
    private String aExpN;
    
    private String aGrMin;
    
    private String aGrMax;


    /**
    *
    * Constructeur paramétré d'un objet de la classe.
    * 
    * @param aTopic : Nom du topic.
    * @param aIdDoc : Identifiant du document.
    * @param aExpN : Numéro de l'expérience.
    * @param aGrMin : Glucose Rate Minimum.
    * @param aGrMax : Glucose Rate Maximum.
    *
    */
    
    public Object_TIEG(String aTopic, String aIdDoc, String aExpN, String aGrMin, String aGrMax) {
        
        this.aTopic = aTopic;
        this.aIdDoc = aIdDoc;
        this.aExpN=aExpN;
        this.aGrMin = aGrMin;
        this.aGrMax = aGrMax;
    }

    /**
    *
    * Fonction qui retourne le nom du topic.
    * @return obj:String.
    * 
    */
    
    public String getaTopic() {
        return aTopic;
    }

    /**
    *
    * Procédure qui modifie le nom du topic.
    * @param aTopic : Nom du nouveau topic.
    * 
    */
    
    public void setaTopic(String aTopic) {
        this.aTopic = aTopic;
    }


    /**
    *
    * Fonction qui retourne le numéro du document.
    * @return obj:String.
    * 
    */
    
    public String getaIdDoc() {
        return aIdDoc;
    }

    /**
    *
    * Procédure qui modifie le numéro du document.
    * @param aIdDoc : Nouveau numéro du document.
    * 
    */
    
    public void setaIdDoc(String aIdDoc) {
        this.aIdDoc = aIdDoc;
    }

    /**
    *
    * Fonction qui retourne le numéro de l'expérience.
    * @return obj:String.
    * 
    */
    
    public String getaExpN() {
        return aExpN;
    }

    /**
    *
    * Procédure qui modifie le numéro de l'expérience.
    * @param aExpN : Nouveau numéro de l'expérience.
    * 
    */
    
    public void setaExpN(String aExpN) {
        this.aExpN = aExpN;
    }

    /**
    *
    * Fonction qui retourne la valeur du Glucose Rate Minimum.
    * @return obj:String.
    * 
    */
    
    public String getaGrMin() {
        return aGrMin;
    }

    /**
    *
    * Procédure qui modifie la valeur du Glucose Rate Minimum.
    * @param aGrMin : Nouvelle valeur du Glucose Rate Minimum.
    * 
    */
    
    public void setaGrMin(String aGrMin) {
        this.aGrMin = aGrMin;
    }

    /**
    *
    * Fonction qui retourne la valeur du Glucose Rate Maximum.
    * @return obj:String.
    * 
    */
    
    public String getaGrMax() {
        return aGrMax;
    }
    
    /**
    *
    * Procédure qui modifie la valeur du Glucose Rate Maximum.
    * @param aGrMax : Nouvelle valeur du Glucose Rate Maximum.
    * 
    */
    
    public void setaGrMax(String aGrMax) {
        this.aGrMax = aGrMax;
    }
    
    
}
