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
public class Exception_AbsenceExperienceBiomass extends Exception{

    public Exception_AbsenceExperienceBiomass() {
        super("Aucune expérience ne traite de la biomass sélectionnée.");
    }

    @Override
    public String getMessage()
    {
        return "Aucune expérience ne traite de la biomass sélectionnée.";
    }
}
