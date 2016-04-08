package com.terweb.efactor.beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

import com.opencsv.CSVWriter;
import com.terweb.exception.ClassCalculException;
import com.terweb.exception.ClassFileProblemException;

public class AllVarTopicClass extends ClassAbstractCalculEfactor {

	private String pathCh ;
	private String userCurrentID;
	private BufferedReader  in;

	
	/**
	 * 
	 * @param pathCommun
	 * @param userCurrentID
	 */
	public AllVarTopicClass(String pathCommun,String userCurrentID){
		this.pathCh = pathCommun;
		this.userCurrentID = userCurrentID;
	}
	
	
	@Override
	public void allVarTopicEfactor() throws ClassCalculException, ClassFileProblemException {
		try {
			 AllVarDocClass alldoc = new AllVarDocClass(pathCh,userCurrentID);
		     String PathPreAll =alldoc.allVarDocEfactor();
		 
		     
		     
			 LinkedList<String> lineFile = new LinkedList<String>();
			 LinkedList<String> lineTemp = new LinkedList<String>();
			 in = new BufferedReader(new FileReader(PathPreAll));
			 
			 String sfile = pathCh+"DirectoryUsers/"+userCurrentID+"/fileAllVarTopicResultGraphic"+userCurrentID+".csv";
	         CSVWriter writer = new CSVWriter(new FileWriter(sfile));
			 String line;
			 int count =0;
			 line =  in.readLine() ;
			 StringTokenizer tokenh = new StringTokenizer(line,",\"");
			 while (tokenh.hasMoreTokens()){
	       		 String cellValue = tokenh.nextToken().toString();
	       		 lineFile.add(cellValue);
       	     }
			
			 String[] arrayh = lineFile.toArray(new String[] {});
			 writer.writeNext(arrayh);
	         lineFile.clear();
	          
	         while (((line =  in.readLine()) != null)){
	                 StringTokenizer token = new StringTokenizer(line,",\"");
	            	 while (token.hasMoreTokens()){
	            		 String cellValue = token.nextToken().toString().trim();
	            		 if(lineFile.size()<=15){
	            			  lineFile.add(cellValue);
	            		 }else {
	            			  lineTemp.add(cellValue);
	            		 }
	            		 
	            	 }
	            	 
	            	 if((lineTemp.size() == 16) && (lineFile.size() == 16)){
	            		
	            	   if(lineFile.get(0).toLowerCase().equals(lineTemp.get(0).toLowerCase())){
			            	Double resultMin = (Double.valueOf(lineFile.get(12)) + Double.valueOf(lineTemp.get(12)));
			            	
			            	lineFile.set(12, resultMin.toString());
			            	
			            	Double resultMax = (Double.valueOf(lineFile.get(13)) + Double.valueOf(lineTemp.get(13)));
			            	lineFile.set(13, resultMax.toString());
			            	
			            	Double resultMinGy = (Double.valueOf(lineFile.get(7)) + Double.valueOf(lineTemp.get(7)));
			            	lineFile.set(7, resultMinGy.toString());
			            	
			            	Double resultMaxGy = (Double.valueOf(lineFile.get(8)) + Double.valueOf(lineTemp.get(8)));
			            	lineFile.set(8, resultMaxGy .toString());
			            	
			            	lineTemp.clear();
			            	count++;
	            		 
	                  }else{
	                	    count = count+1;
	                	    
	                		Double resultAllMin = (Double)(Double.valueOf(lineFile.get(12))/count);
	                		lineFile.set(12, resultAllMin.toString());
			            	
			            	Double resultAllMax = (Double)(Double.valueOf(lineFile.get(13))/count);
			            	lineFile.set(13, resultAllMax.toString());
			            	
			            	Double resultMinGy = (Double)(Double.valueOf(lineFile.get(7))/count);
			            	lineFile.set(7, resultMinGy.toString());
			            	
			            	Double resultMaxGy = (Double)(Double.valueOf(lineFile.get(8))/count);
			            	lineFile.set(8, resultMaxGy .toString());
			            	
			            	count =0;
			            	
		                	String[] array = lineFile.toArray(new String[] {});
		                 	writer.writeNext(array);
	                    	lineFile.clear(); 
	                    	lineFile.addAll(lineTemp);
	                    	lineTemp.clear();
	                    	 
	                  }
	               }
	            }
	         count = count+1;
	         
     		 Double resultAllMin = (Double)(Double.valueOf(lineFile.get(12))/count);
     		 lineFile.set(12, resultAllMin.toString());
         	
          	 Double resultAllMax = (Double)(Double.valueOf(lineFile.get(13))/count);
         	 lineFile.set(13, resultAllMax.toString());
            	
	         String[] array = lineFile.toArray(new String[] {});
       	     writer.writeNext(array);
       	     lineFile.clear();
       	     count=0;
	         writer.close();
	   
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void bestExpEfactor() {}
	
	public String allVarDocEfactor() {
		return null;
	}

	


}
