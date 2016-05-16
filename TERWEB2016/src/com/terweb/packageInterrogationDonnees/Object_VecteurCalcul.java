/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

/**
*
* Classe utilisée lors des traitements des données. Un objet de cette classe 
* représente un vecteur de la matrice de calcul globale, il stocke les informations suivantes :
* Topic : Identifiant-Document : Experience-Number : Somme des Qty de matières :
* Qty Biomasse :  GlucoseRate-Min :  GlucoseRate-Max : GlucoseYield-Min : GlucoseYield-Max :
* Document Reliability-Min : Document Reliability-Max.
*
*/

public class Object_VecteurCalcul {
    
    private String aTopic;
    
    private String aIdDoc;
    
    private String aExpN;
    
    private double aSomme;
    
    private double aBiomassQty;
    
    private double aGrMin;
    
    private double aGrMax;
    
    private double aGyMin;
    
    private double aGyMax;
    
    private double aReliabilityMin;
    
    private double aReliabilityMax;


    /**
    *
    * Constructeur paramétré d'un objet de la classe.
    * 
    * @param aTopic : Nom du topic.
    * @param aIdDoc : Identifiant du document.
    * @param aExpN : Numéro de l'expérience.
    * @param aSomme : Somme des quantités de matières.
    * @param aBiomassQty : Quatité de biomasse.
    * @param aGrMin : Glucose Rate Minimum.
    * @param aGrMax : Glucose Rate Minimum.
    * @param aGyMin : Glucose Yield Minimum.
    * @param aGyMax : Glucose Yield Maximum.
    * @param aReliabilityMin : Fiabilité Minimum du document.
    * @param aReliabilityMax : Fiabilité Maximum du document.
    *
    */
    
    public Object_VecteurCalcul(String aTopic, String aIdDoc, String aExpN, double aSomme, double aBiomassQty, double aGrMin, double aGrMax, double aGyMin, double aGyMax, double aReliabilityMin, double aReliabilityMax) {
        
        this.aTopic = aTopic;
        this.aIdDoc = aIdDoc;
        this.aExpN = aExpN;
        this.aSomme = aSomme;
        this.aBiomassQty = aBiomassQty;
        this.aGrMin = aGrMin;
        this.aGrMax = aGrMax;
        this.aGyMin = aGyMin;
        this.aGyMax = aGyMax;
        this.aReliabilityMin = aReliabilityMin;
        this.aReliabilityMax = aReliabilityMax;
        
    }

    /**
    *
    * Fonction qui retourne la fiabilité minimum.
    * @return obj:double.
    * 
    */
    
    public double getaReliabilityMin() {
        return aReliabilityMin;
    }
    
    /**
    *
    * Procédure qui modifie la fiabilité minimum.
    * @param aReliabilityMin : Nouvelle valeur de la fiabilité minimum.
    * 
    */
    
    public void setaReliabilityMin(double aReliabilityMin) {
        this.aReliabilityMin = aReliabilityMin;
    }
    
    /**
    *
    * Fonction qui retourne la fiabilité maximum.
    * @return obj:double.
    * 
    */
    
    public double getaReliabilityMax() {
        return aReliabilityMax;
    }
    
    /**
    *
    * Procédure qui modifie la fiabilité maximum.
    * @param aReliabilityMax : Nouvelle valeur de la fiabilité maximum.
    * 
    */
    
    public void setaReliabilityMax(double aReliabilityMax) {
        this.aReliabilityMax = aReliabilityMax;
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
    * @param aTopic : Nouveau nom du topic.
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
    * @param aIdDoc : Nouveau numéro d'expérience.
    * 
    */
    
    public void setaExpN(String aExpN) {
        this.aExpN = aExpN;
    }

    /**
    *
    * Fonction qui retourne la somme des quantités de matières.
    * @return obj:double.
    * 
    */
    
    public double getaSomme() {
        return aSomme;
    }
    
    /**
    *
    * Procédure qui modifie la somme des quantités de matières.
    * @param aSomme : Nouvelle valeur de la somme des quantités de matières.
    * 
    */
    
    public void setaSomme(double aSomme) {
        this.aSomme = aSomme;
    }
    
    /**
    *
    * Fonction qui retourne la quantité de biomasse.
    * @return obj:double.
    * 
    */
    public double getaBiomassQty() {
        return aBiomassQty;
    }
    
    /**
    *
    * Procédure qui modifie la quantité de biomasse.
    * @param aBiomassQty : Nouvelle quantité de biomasse.
    * 
    */
    
    public void setaBiomassQty(double aBiomassQty) {
        this.aBiomassQty = aBiomassQty;
    }
    
    /**
    *
    * Fonction qui retourne la valeur du Glucose Rate Minimum.
    * @return obj:double.
    * 
    */
    
    public double getaGrMin() {
        return aGrMin;
    }

    /**
    *
    * Procédure qui modifie la valeur du Glucose Rate Minimum.
    * @param aGrMin : Nouvelle valeur du Glucose Rate Minimum.
    * 
    */
    
    public void setaGrMin(double aGrMin) {
        this.aGrMin = aGrMin;
    }
    
    /**
    *
    * Fonction qui retourne la valeur du Glucose Rate Maximum.
    * @return obj:double.
    * 
    */
    
    public double getaGrMax() {
        return aGrMax;
    }
    
    /**
    *
    * Procédure qui modifie la valeur du Glucose Rate Maximum.
    * @param aGrMin : Nouvelle valeur du Glucose Rate Maximum.
    * 
    */
    
    public void setaGrMax(double aGrMax) {
        this.aGrMax = aGrMax;
    }
    
    /**
    *
    * Fonction qui retourne la valeur du Glucose Yield Minimum.
    * @return obj:double.
    * 
    */
    
    public double getaGyMin() {
        return aGyMin;
    }
    
    /**
    *
    * Procédure qui modifie la valeur du Glucose Yield Minimum.
    * @param aGyMin : Nouvelle valeur du Glucose Yield Minimum.
    * 
    */
    
    public void setaGyMin(double aGyMin) {
        this.aGyMin = aGyMin;
    }
    
    /**
    *
    * Fonction qui retourne la valeur du Glucose Yield Maximum.
    * @return obj:double.
    * 
    */
    
    public double getaGyMax() {
        return aGyMax;
    }
    
    /**
    *
    * Procédure qui modifie la valeur du Glucose Yield Maximum.
    * @param aGyMax : Nouvelle valeur du Glucose Yield Maximum.
    * 
    */
    
    public void setaGyMax(double aGyMax) {
        this.aGyMax = aGyMax;
    }
     
}
