#Calcul à 95% de confiance (GlucoseYield et GlucoseRate)
#Recuperation des chemins 
pathOne=function(){
return(fileFunc)
}
pathTwo=function(){
return(fileOutResult)
}
pathFileCsv=function(){
return(filecsv)
}
#declaration tableau 
zmin=c()
zmax=c()
CalculInterEfactor=function(){	
rm(list=ls())
source(pathOne())
library(tis) # donne lintegrate(estx,estz,xint=estx)
# lire variables
data=read.table(pathFileCsv(),sep=",",head=T)
#boucler sur lignes de donnees
for (ind in 1:nrow(data))
{
	#affecter parametres
	biomass.qty=data[ind,4]
	solv.qty=data[ind,5]
    #GlucoseRate à 95%
	gr=c(data[ind,6],data[ind,7])
	#GlucoseYield à 95%
	gy=c(data[ind,8],data[ind,9])
	#calculer impact factor : resultat = vecteur qui represente un echantillon simule de la VA enfactor
	z=efactorFunction(gr,gy,biomass.qty,solv.qty)
	if(z[1]>z[2]){
	zmin[ind]=z[2]
	zmax[ind]=z[1]
	}else if(z[1]<=z[2]){
	zmin[ind]=z[1]
	zmax[ind]=z[2]
	}
	}#fin de la boucle sur les lignes 
dataf=cbind(data,EfactorA95pourcent.Min=round(zmin,2),EfactorA95pourcent.Max=round(zmax,2))
write.table(dataf,pathTwo(),sep=",")	
}
