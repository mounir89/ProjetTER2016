package com.terweb.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.terweb.efactor.beans.AllVarDocClass;
import com.terweb.efactor.beans.AllVarTopicClass;
import com.terweb.efactor.beans.BestExperienceClass;
import com.terweb.efactor.beans.ParametreClass;
import com.terweb.exception.ClassCalculException;
import com.terweb.exception.ClassFileProblemException;
import com.terweb.packageExceptions.Exception_AbsenceDocument;
import com.terweb.packageExceptions.Exception_AbsenceExperienceBiomass;
import com.terweb.packageExceptions.Exception_BDDException;
import com.terweb.packageExceptions.Exception_FichierCalcule;
import com.terweb.packageExceptions.Exception_ParseException;
import com.terweb.packageExceptions.Exception_SparqlConnexion;
import com.terweb.packageInterrogationDonnees.Initialisation;
import com.terweb.packageInterrogationDonnees.Interrogation;
public class PagePrincipal extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
   /**
    * @see HttpServlet#HttpServlet()
    */
   public PagePrincipal() {
       super();
       
       
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		
		try {
			
			ArrayList<String> biomass = Initialisation.initBiomass();
			HashMap<String, ArrayList<String>> topics = Initialisation.initTopicOperation();
			HashMap<String, ArrayList<String>> relations = Initialisation.initRelationParameters();
		
		    
			request.setAttribute("biomass",biomass);
			
			request.setAttribute("topics",topics);
			
			request.setAttribute("relations",relations);
			
            if((!biomass.isEmpty())&&(!topics.isEmpty())&&(!relations.isEmpty())){
            	this.getServletContext().getRequestDispatcher( "/pageprincipal.jsp" ).forward( request, response );
            }
			
		
		} catch (Exception_SparqlConnexion  |Exception_BDDException |SQLException e) {
			e.printStackTrace();
		} 
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        String json = "";
	        if(br != null){
	            json = br.readLine();
	        }
	        
	        ObjectMapper mapper = new ObjectMapper();
	        
	        ParametreClass parametres = mapper.readValue(json, ParametreClass.class);
	        
	        try {
	        	
	        	String pathUser=getServletContext().getRealPath("/").replaceAll("\\\\", "/")+"DirectoryUsers/"+parametres.getUserID();
	        	
	        	Interrogation.initMatriceCalcul(pathUser,parametres.getBiomass(),parametres.getTopics(),parametres.getRelations());
			
	        
	        } catch (Exception_AbsenceDocument
					| Exception_AbsenceExperienceBiomass
					| Exception_FichierCalcule | Exception_ParseException
					| Exception_BDDException | SQLException
					| Exception_SparqlConnexion e) {
			
				e.printStackTrace();
			}
	       
	        /*************** calcul en R ******************/
	        
	        System.out.println("-------- experience -----\t"+parametres.getExperience());
	        
	        
	        if(parametres.getExperience().equalsIgnoreCase("Efactor Best Experience")){
	        	System.out.println("experience efactor -----------");
	        	
	        	BestExperienceClass b =  new BestExperienceClass(getServletContext().getRealPath("/").replaceAll("\\\\", "/"),parametres.getUserID());
	    		
	    		try {
	    			
	    			b.bestExpEfactor();
	    			
	    		} catch (ClassCalculException | ClassFileProblemException e) {
	    			e.printStackTrace();
	    		}
	    		
	    		
	        }else if(parametres.getExperience().equalsIgnoreCase("Efactor All Variation Document")){
	        	
	        
	        	AllVarDocClass b =  new AllVarDocClass(getServletContext().getRealPath("/").replaceAll("\\\\", "/"), parametres.getUserID());
				
			    try {
			    	
					b.allVarDocEfactor();
					
				} catch (ClassCalculException | ClassFileProblemException e) {
					e.printStackTrace();
				}
			    
			    
			    
	        }else if(parametres.getExperience().equalsIgnoreCase("Efactor All Variation Topic")){
	        	
	        	
	        	AllVarTopicClass b =  new AllVarTopicClass(getServletContext().getRealPath("/").replaceAll("\\\\", "/"),parametres.getUserID());
	    		
				try {
					
					b.allVarTopicEfactor();
					
				} catch (ClassCalculException | ClassFileProblemException e) {
					e.printStackTrace();
				}
	        }
			
	        response.setContentType("application/json");  
	        mapper.writeValue(response.getOutputStream(), "OK");
	}
}
