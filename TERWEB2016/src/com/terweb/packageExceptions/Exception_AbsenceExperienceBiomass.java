/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.terweb.packageExceptions;
/**
 *
 * @author proprietaire
 */
public class Exception_AbsenceExperienceBiomass extends Exception{

    public Exception_AbsenceExperienceBiomass() {
        super("Aucune exp�rience ne traite de la biomass s�lectionn�e.");
    }

    @Override
    public String getMessage()
    {
        return "Aucune exp�rience ne traite de la biomass s�lectionn�e.";
    }
}
