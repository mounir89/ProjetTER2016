#Calcul à 95% de confiance (GlucoseYield et GlucoseRate)
#Recuperation des chemins 
pathOne=function(){
return(convertinteric)
}
pathTwo=function(){
return(fileOutResult)
}
pathFileCsv=function(){
return(filecsv)
}
#declaration tableau 
mgrmin=c()
mgrmax=c()
mgymin=c()
mgymax=c()
numdoc=c()
biomass=c()
solv=c()
topic=c()
numexperience=c()
fiabilitemin=c()
fiabilitemax=c()
fiabiliteMoyenne=c()
CalculInterGYGR=function(){	
rm(list=ls())
source(pathOne())
library(tis) # donne lintegrate(estx,estz,xint=estx)
# lire variables
data=read.table(pathFileCsv(),sep=",",head=T)
#boucler sur lignes de donnees
	for (ind in 1:nrow(data))
	{
	topicc=data[ind,1]
	topic[ind]=as.character(topicc)
	#numero de document
	numdoc[ind]=data[ind,2]
	#numero experience
	numexperience[ind]=data[ind,3]
	#quantité du biomass
	biomass[ind]=data[ind,4]
	#quantité du Solvent
	solv[ind]=data[ind,5]
	#GlucoseRate à 95%
	gr=c(data[ind,6],data[ind,7])
	res=convertintconfiance(gr)
	mgrmin[ind]=res[1]
	mgrmax[ind]=res[2]
	#GlucoseYield à 95%
	gy=c(data[ind,8],data[ind,9])
	res=convertintconfiance(gy)
	mgymin[ind]=res[1]
	mgymax[ind]=res[2]
	fiabilitemin[ind]=data[ind,10]
	fiabilitemax[ind]=data[ind,11]
	fiabilite=c(data[ind,10],data[ind,11])
	fiabiliteMoyenne[ind]=mean(fiabilite)
	}#fin de la boucle sur les lignes 
dataf=cbind(Topic=topic,Numdoc=numdoc,NumExperience=numexperience,Biomass.Qty=biomass,Material.Qty=solv,Glucose.rate.Min=round(mgrmin,2),Glucose.rate.Max=round(mgrmax,2),Glucose.yields.Min=round(mgymin,2),Glucose.yields.Max=round(mgymax,2),Reliability_min=fiabilitemin,Reliability_max=fiabilitemax,ReliabilityAverage=fiabiliteMoyenne)
	write.table(dataf,pathTwo(),sep=",")
	
}

	
	
