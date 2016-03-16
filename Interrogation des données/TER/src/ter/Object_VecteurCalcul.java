/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ter;

/**
 *
 * @author proprietaire
 */
public class Object_VecteurCalcul {
    
    private String aTopic;
    
    private String aIdDoc;
    
    private String aExpN;
    
    private String aSomme;
    
    private String aBiomassQty;
    
    private float aGrMin;
    
    private float aGrMax;
    
    private float aGyMin;
    
    private float aGyMax;

    public Object_VecteurCalcul(String aTopic, String aIdDoc, String aExpN, String aSomme, String aBiomassQty, float aGrMin, float aGrMax, float aGyMin, float aGyMax) {
        
        this.aTopic = aTopic;
        this.aIdDoc = aIdDoc;
        this.aExpN = aExpN;
        this.aSomme = aSomme;
        this.aBiomassQty = aBiomassQty;
        this.aGrMin = aGrMin;
        this.aGrMax = aGrMax;
        this.aGyMin = aGyMin;
        this.aGyMax = aGyMax;
        
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

    public String getaSomme() {
        return aSomme;
    }

    public void setaSomme(String aSomme) {
        this.aSomme = aSomme;
    }

    public String getaBiomassQty() {
        return aBiomassQty;
    }

    public void setaBiomassQty(String aBiomassQty) {
        this.aBiomassQty = aBiomassQty;
    }

    public float getaGrMin() {
        return aGrMin;
    }

    public void setaGrMin(float aGrMin) {
        this.aGrMin = aGrMin;
    }

    public float getaGrMax() {
        return aGrMax;
    }

    public void setaGrMax(float aGrMax) {
        this.aGrMax = aGrMax;
    }

    public float getaGyMin() {
        return aGyMin;
    }

    public void setaGyMin(float aGyMin) {
        this.aGyMin = aGyMin;
    }

    public float getaGyMax() {
        return aGyMax;
    }

    public void setaGyMax(float aGyMax) {
        this.aGyMax = aGyMax;
    }
     
}
