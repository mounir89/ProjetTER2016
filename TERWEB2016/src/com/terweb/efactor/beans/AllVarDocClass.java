package com.terweb.efactor.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.rosuda.JRI.Rengine;









import com.opencsv.CSVWriter;
import com.terweb.exception.ClassCalculException;
import com.terweb.exception.ClassFileProblemException;

public class AllVarDocClass extends ClassAbstractCalculEfactor{

	private String pathCh ;
	private String userCurrentID;
	private BufferedReader  in;

	
	/**
	 * constructeur  
	 * @param pathCommun 
	 * @param userCurrentID
	 */
	public AllVarDocClass(String pathCommun,String userCurrentID){
		this.pathCh = pathCommun;
		this.userCurrentID = userCurrentID;
	}

	@Override
	/**
	 * Calcul de l'E-factor All  pour toutes les variations du document
	 * @return Path du fichier CSV de sortie
	 */
	public String allVarDocEfactor() throws ClassCalculException, ClassFileProblemException{
		
		String sfile = pathCh+"DirectoryUsers/"+userCurrentID+"/fileAllVarDocResultGraphic"+userCurrentID+".csv";
		
		try {
			
			String chm = super.preCalculEfactor(userCurrentID, pathCh);
			
		    if(chm!=null){
			String PathPreAll = this.CalculEfactorAll(chm);
			LinkedList<String> lineFile = new LinkedList<String>();
			LinkedList<String> lineTemp = new LinkedList<String>();
			in = new BufferedReader(new FileReader(PathPreAll));
			
			
			CSVWriter writer = new CSVWriter(new FileWriter(sfile));
				 
				 String line;
				 line =  in.readLine() ;
				 StringTokenizer tokenh = new StringTokenizer(line,",\"");
				 while (tokenh.hasMoreTokens()){
	        		 String cellValue = tokenh.nextToken().toString();
	        		 lineFile.add(cellValue);
	        	 }
				 lineFile.add("Efactor Average");
				 lineFile.add("Glucose Yield Average");
				 String[] arrayh = lineFile.toArray(new String[] {});
				 writer.writeNext(arrayh);
		         lineFile.clear();
		          
		         while (((line =  in.readLine()) != null)){
		                 StringTokenizer token = new StringTokenizer(line,",\"");
		            	 while (token.hasMoreTokens()){
		            		 String cellValue = token.nextToken().toString().trim();
		            		 if(lineFile.size()<=14){
		            			  lineFile.add(cellValue);
		            		 }else {
		            			  lineTemp.add(cellValue);
		            		 }
		            	 }
		            	 
		            	 if((lineTemp.size() == 15) && (lineFile.size() == 15)){
		            	   if(lineFile.get(1).toLowerCase().equals(lineTemp.get(1).toLowerCase())){
		            		 if(Integer.valueOf(lineFile.get(2)) == Integer.valueOf(lineTemp.get(2))){
		                       if(Double.valueOf(lineTemp.get(13)) < Double.valueOf(lineFile.get(13))){
		                    	 if(Double.valueOf(lineTemp.get(14)) > Double.valueOf(lineFile.get(14)) ){
			                    	  lineFile.set(14,lineTemp.get(14));
			                    	  lineFile.set(13,lineTemp.get(13));
			                    	  lineFile.set(8,lineTemp.get(8));
			                    	  lineFile.set(9,lineTemp.get(9));
			                    	  
			                    	  lineTemp.clear();
			                       }
		                    	 else {
		                    		   lineFile.set(13,lineTemp.get(13));
		                    		   lineFile.set(9,lineTemp.get(9));
		                    		   lineTemp.clear(); 
		                    	 }
		                       }
		                       else if(Double.valueOf(lineTemp.get(14)) > Double.valueOf(lineFile.get(14)) ){
		                    	   	   lineFile.set(14,lineTemp.get(14));
		                    	   	   lineFile.set(8,lineTemp.get(8));
		                    	       lineTemp.clear();
		                    		     
		                       }else{
		                    	       lineTemp.clear();
		                       }
		                       
		                    		 
		                     }else {
		                    	    	 lineFile.removeFirst();
				                    	 double eFactorAverage = (double)((Double.valueOf(lineFile.get(12))+Double.valueOf(lineFile.get(13)))/2) ;
				                	     lineFile.add(14, String.valueOf(eFactorAverage));
				                    	 
				                    	 double glucoseYieldAverage = (double)((Double.valueOf(lineFile.get(7))+Double.valueOf(lineFile.get(8)))/2) ;
				                    	 lineFile.add(15, String.valueOf(glucoseYieldAverage));
				                    	 
				                    	 String[] array = lineFile.toArray(new String[] {});
				                    	 writer.writeNext(array);
				                    	 lineFile.clear();
				                    	 lineFile.addAll(lineTemp);
				                    	 lineTemp.clear();
		                     }
		            		 
		                  }else{
		                	     lineFile.removeFirst();
		                    	 double eFactorAverage = (double)((Double.valueOf(lineFile.get(12))+Double.valueOf(lineFile.get(13)))/2) ;
		                	     lineFile.add(14, String.valueOf(eFactorAverage));
		                    	 
		                    	 double glucoseYieldAverage = (double)((Double.valueOf(lineFile.get(7))+Double.valueOf(lineFile.get(8)))/2) ;
		                    	 lineFile.add(15, String.valueOf(glucoseYieldAverage));
		                    	 
			                	 String[] array = lineFile.toArray(new String[] {});
			                 	 writer.writeNext(array);
		                    	 lineFile.clear(); 
		                    	 lineFile.addAll(lineTemp);
		                    	 lineTemp.clear();
		                    	 
		                  }
		               }
		            }
		         lineFile.removeFirst();
            	 double eFactorAverage = (double)((Double.valueOf(lineFile.get(12))+Double.valueOf(lineFile.get(13)))/2) ;
        	     lineFile.add(14, String.valueOf(eFactorAverage));
            	 
            	 double glucoseYieldAverage = (double)((Double.valueOf(lineFile.get(7))+Double.valueOf(lineFile.get(8)))/2) ;
            	 lineFile.add(15, String.valueOf(glucoseYieldAverage));
            	 
		         String[] array = lineFile.toArray(new String[] {});
	        	 writer.writeNext(array);
		         writer.close();
			
		    }else {
		    	  throw new ClassFileProblemException();
		     }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sfile;	
		
	}
	
    /**
     * Exécution des scripts R pour calculer l'E-factor 
     * à intervalle à 95 de confiance de chaque expérience
     * du document
     * @param pathfFile
     * @throws ClassCalculException 
     * @throws ClassNotFoundException 
     * @return Path du fichier CSV de sortie
     */
	
	public String CalculEfactorAll(String pathfFile) throws ClassCalculException {
		 
		  String fileR = pathCh+"ModuleRCalculEfactor/calculEfactor.R";
		  String fileFunc = pathCh+"ModuleRCalculEfactor/efactorFunction.R";
		  String fileOutResult =pathCh+"DirectoryUsers/"+userCurrentID+"/fileAllVarDocResultPreGraphic"+userCurrentID+".csv";
		  
		  
		  if(new File(fileOutResult).exists()){
			   return fileOutResult;
		  }
		  else {
			  Rengine re = ConnectionRClass.getConnection();
			  
			   if (!re.waitForR()) { 
				   throw new ClassCalculException();  
		       } 
		       else {
		    	   re.eval("source('"+fileR+"')");
				   re.eval(String.format("fileFunc <- '%s'",fileFunc));
			       re.eval(String.format("fileOutResult <- '%s'",fileOutResult));
			       re.eval(String.format("filecsv <- '%s'",pathfFile));
			       
			       re.eval("s<-CalculInterEfactor()");
			      
			       return fileOutResult;
		       }
			  
		  }	
	}
	
	public void allVarTopicEfactor() {}
	public void bestExpEfactor() {}

}
