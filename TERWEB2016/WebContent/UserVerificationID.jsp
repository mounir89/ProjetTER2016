<%@page import="com.terweb.efactor.beans.IdentifierUserSession"%>
<%@page import="java.io.File"%>            
           <%
		        String userID = IdentifierUserSession.generateIdentifierUserSession();
		   		String pathDirectoryUser =getServletContext().getRealPath("/").replaceAll("\\\\", "/")+"DirectoryUsers/"+userID;
		   		new File(pathDirectoryUser).mkdir();
		   		out.println(userID);
		   %>
		   
		  