/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageInterrogationDonnees;

/**
 *
 * @author proprietaire
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

    public double getaReliabilityMin() {
        return aReliabilityMin;
    }

    public void setaReliabilityMin(double aReliabilityMin) {
        this.aReliabilityMin = aReliabilityMin;
    }

    public double getaReliabilityMax() {
        return aReliabilityMax;
    }

    public void setaReliabilityMax(double aReliabilityMax) {
        this.aReliabilityMax = aReliabilityMax;
    }

    
    public String getaTopic() {
        return aTopic;
    }

    public void setaTopic(String aTopic) {
        this.aTopic = aTopic;
    }

    public String getaIdDoc() {
        return aIdDoc;
    }

    public void setaIdDoc(String aIdDoc) {
        this.aIdDoc = aIdDoc;
    }

    public String getaExpN() {
        return aExpN;
    }

    public void setaExpN(String aExpN) {
        this.aExpN = aExpN;
    }

    public double getaSomme() {
        return aSomme;
    }

    public void setaSomme(double aSomme) {
        this.aSomme = aSomme;
    }

    public double getaBiomassQty() {
        return aBiomassQty;
    }

    public void setaBiomassQty(double aBiomassQty) {
        this.aBiomassQty = aBiomassQty;
    }

    public double getaGrMin() {
        return aGrMin;
    }

    public void setaGrMin(double aGrMin) {
        this.aGrMin = aGrMin;
    }

    public double getaGrMax() {
        return aGrMax;
    }

    public void setaGrMax(double aGrMax) {
        this.aGrMax = aGrMax;
    }

    public double getaGyMin() {
        return aGyMin;
    }

    public void setaGyMin(double aGyMin) {
        this.aGyMin = aGyMin;
    }

    public double getaGyMax() {
        return aGyMax;
    }

    public void setaGyMax(double aGyMax) {
        this.aGyMax = aGyMax;
    }
     
}
