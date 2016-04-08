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
public class Object_ProcessDescription {
    
    private String aOperation;
    
    private String aRelation;
    
    private String aBiomassQty;
    
    private String aParameter;
    
    private String aUnit;
    
    private float aValue;
   
    private float aGyMin;
    
    private float aGyMax;

    public Object_ProcessDescription(String aOperation, String aRelation, String aBiomassQty, String aParameter, String aUnit, float aValue, float aGyMin, float aGyMax) {
        
        this.aOperation = aOperation;
        this.aRelation = aRelation;
        this.aBiomassQty = aBiomassQty;
        this.aParameter = aParameter;
        this.aUnit = aUnit;
        this.aValue = aValue;
        this.aGyMin = aGyMin;
        this.aGyMax = aGyMax;
        
    }

    public String getaOperation() {
        return aOperation;
    }

    public void setaOperation(String aOperation) {
        this.aOperation = aOperation;
    }


    public String getaRelation() {
        return aRelation;
    }

    public void setaRelation(String aRelation) {
        this.aRelation = aRelation;
    }

    public String getaBiomassQty() {
        return aBiomassQty;
    }

    public void setaBiomassQty(String aBiomassQty) {
        this.aBiomassQty = aBiomassQty;
    }

    public String getaParameter() {
        return aParameter;
    }

    public void setaParameter(String aParameter) {
        this.aParameter = aParameter;
    }

    public String getaUnit() {
        return aUnit;
    }

    public void setaUnit(String aUnit) {
        this.aUnit = aUnit;
    }

    public float getaValue() {
        return aValue;
    }

    public void setaValue(float aValue) {
        this.aValue = aValue;
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
