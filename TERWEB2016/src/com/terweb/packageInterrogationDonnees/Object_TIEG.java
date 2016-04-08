/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageInterrogationDonnees;

/**
 *
 * @author proprietaire
 */
public class Object_TIEG {
    
    private String aTopic;
    
    private String aIdDoc;
    
    private String aExpN;
    
    private String aGrMin;
    
    private String aGrMax;

    public Object_TIEG(String aTopic, String aIdDoc, String aExpN, String aGrMin, String aGrMax) {
        
        this.aTopic = aTopic;
        this.aIdDoc = aIdDoc;
        this.aExpN=aExpN;
        this.aGrMin = aGrMin;
        this.aGrMax = aGrMax;
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

    public String getaGrMin() {
        return aGrMin;
    }

    public void setaGrMin(String aGrMin) {
        this.aGrMin = aGrMin;
    }

    public String getaGrMax() {
        return aGrMax;
    }

    public void setaGrMax(String aGrMax) {
        this.aGrMax = aGrMax;
    }
    
    
}
