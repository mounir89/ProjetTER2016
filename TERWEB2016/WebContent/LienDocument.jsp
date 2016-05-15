<%@page import="com.terweb.packageInterrogationDonnees.LienDocument"%>
<%@page import="com.terweb.efactor.beans.IdentifierUserSession"%>
<%@page import="java.io.File"%>            
           <%
           
                int ndoc = Integer.valueOf(request.getParameter("numerodocument"));
           
		        String lien = LienDocument.genereLien(ndoc);
		        
		        out.println(lien);
		   		
		   		
		   %>
		   
		  