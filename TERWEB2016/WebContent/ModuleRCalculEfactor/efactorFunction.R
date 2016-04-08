#Calcul de l'efactor à 95% de confiance
efactorFunction=function(gluc.rate,gluc.yield,biomass.qty,solv.qty)
{	
    
	glucoseReleased.qty = (biomass.qty*gluc.rate*gluc.yield)
	
	enf=(((biomass.qty+solv.qty)-glucoseReleased.qty)/glucoseReleased.qty)
	
	return(enf)
}

