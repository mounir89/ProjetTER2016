#La conversion en intervalle à 95% de confiance
convertintconfiance=function(vec)
		  {
		    moyenne=mean(vec)
			ecart=moyenne-vec[1]
			vminconf = moyenne-(2*ecart)
			vmaxconf = moyenne+(2*ecart)
return(c(vminconf,vmaxconf))
}
