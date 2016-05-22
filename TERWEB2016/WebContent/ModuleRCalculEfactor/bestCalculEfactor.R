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
 
zmin=c()
zmax=c()
moyenneEfactor=c()
moyenneGlucoseYield=c()
CalculInterEfactor=function(){	
rm(list=ls())
source(pathOne())
library(tis) 

data=read.table(pathFileCsv(),sep=",",head=T)

for (ind in 1:nrow(data))
{
	
	biomass.qty=data[ind,4]
	solv.qty=data[ind,5]
    
	gr=c(data[ind,6],data[ind,7])
	
	gy=c(data[ind,8],data[ind,9])
	moyenneGlucoseYield[ind]=mean(gy)
	
	z=efactorFunction(gr,gy,biomass.qty,solv.qty)
	
	zmin[ind]=min(z)
	zmax[ind]=max(z)
	
	moyenneEfactor[ind]=mean(z)
	
	}
dataf=cbind(data,EfactorA95pourcent.Min=round(zmin,2),EfactorA95pourcent.Max=round(zmax,2),Efactor.Average=round(moyenneEfactor,2),Glucose.Yield.Average=round(moyenneGlucoseYield,2))
write.table(dataf,pathTwo(),sep=",")	
}
