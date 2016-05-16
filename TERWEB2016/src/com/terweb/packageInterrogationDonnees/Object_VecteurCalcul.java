/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

/**
*
* Classe utilis�e lors des traitements des donn�es. Un objet de cette classe 
* repr�sente un vecteur de la matrice de calcul globale, il stocke les informations suivantes :
* Topic : Identifiant-Document : Experience-Number : Somme des Qty de mati�res :
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
    * Constructeur param�tr� d'un objet de la classe.
    * 
    * @param aTopic : Nom du topic.
    * @param aIdDoc : Identifiant du document.
    * @param aExpN : Num�ro de l'exp�rience.
    * @param aSomme : Somme des quantit�s de mati�res.
    * @param aBiomassQty : Quatit� de biomasse.
    * @param aGrMin : Glucose Rate Minimum.
    * @param aGrMax : Glucose Rate Minimum.
    * @param aGyMin : Glucose Yield Minimum.
    * @param aGyMax : Glucose Yield Maximum.
    * @param aReliabilityMin : Fiabilit� Minimum du document.
    * @param aReliabilityMax : Fiabilit� Maximum du document.
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
    * Fonction qui retourne la fiabilit� minimum.
    * @return obj:double.
    * 
    */
    
    public double getaReliabilityMin() {
        return aReliabilityMin;
    }
    
    /**
    *
    * Proc�dure qui modifie la fiabilit� minimum.
    * @param aReliabilityMin : Nouvelle valeur de la fiabilit� minimum.
    * 
    */
    
    public void setaReliabilityMin(double aReliabilityMin) {
        this.aReliabilityMin = aReliabilityMin;
    }
    
    /**
    *
    * Fonction qui retourne la fiabilit� maximum.
    * @return obj:double.
    * 
    */
    
    public double getaReliabilityMax() {
        return aReliabilityMax;
    }
    
    /**
    *
    * Proc�dure qui modifie la fiabilit� maximum.
    * @param aReliabilityMax : Nouvelle valeur de la fiabilit� maximum.
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
    * Proc�dure qui modifie le nom du topic.
    * @param aTopic : Nouveau nom du topic.
    * 
    */
    
    public void setaTopic(String aTopic) {
        this.aTopic = aTopic;
    }

    /**
    *
    * Fonction qui retourne le num�ro du document.
    * @return obj:String.
    * 
    */
    
    public String getaIdDoc() {
        return aIdDoc;
    }

    /**
    *
    * Proc�dure qui modifie le num�ro du document.
    * @param aIdDoc : Nouveau num�ro du document.
    * 
    */
    
    public void setaIdDoc(String aIdDoc) {
        this.aIdDoc = aIdDoc;
    }

    /**
    *
    * Fonction qui retourne le num�ro de l'exp�rience.
    * @return obj:String.
    * 
    */
    
    public String getaExpN() {
        return aExpN;
    }
    
    /**
    *
    * Proc�dure qui modifie le num�ro de l'exp�rience.
    * @param aIdDoc : Nouveau num�ro d'exp�rience.
    * 
    */
    
    public void setaExpN(String aExpN) {
        this.aExpN = aExpN;
    }

    /**
    *
    * Fonction qui retourne la somme des quantit�s de mati�res.
    * @return obj:double.
    * 
    */
    
    public double getaSomme() {
        return aSomme;
    }
    
    /**
    *
    * Proc�dure qui modifie la somme des quantit�s de mati�res.
    * @param aSomme : Nouvelle valeur de la somme des quantit�s de mati�res.
    * 
    */
    
    public void setaSomme(double aSomme) {
        this.aSomme = aSomme;
    }
    
    /**
    *
    * Fonction qui retourne la quantit� de biomasse.
    * @return obj:double.
    * 
    */
    public double getaBiomassQty() {
        return aBiomassQty;
    }
    
    /**
    *
    * Proc�dure qui modifie la quantit� de biomasse.
    * @param aBiomassQty : Nouvelle quantit� de biomasse.
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
    * Proc�dure qui modifie la valeur du Glucose Rate Minimum.
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
    * Proc�dure qui modifie la valeur du Glucose Rate Maximum.
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
    * Proc�dure qui modifie la valeur du Glucose Yield Minimum.
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
    * Proc�dure qui modifie la valeur du Glucose Yield Maximum.
    * @param aGyMax : Nouvelle valeur du Glucose Yield Maximum.
    * 
    */
    
    public void setaGyMax(double aGyMax) {
        this.aGyMax = aGyMax;
    }
     
}
