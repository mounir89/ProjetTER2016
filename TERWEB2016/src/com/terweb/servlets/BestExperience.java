package com.terweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terweb.efactor.beans.BestExperienceClass;
import com.terweb.exception.ClassCalculException;
import com.terweb.exception.ClassFileProblemException;

/**
 * Servlet implementation class BestExperience
 */
@WebServlet("/BestExperience")
public class BestExperience extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BestExperience() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BestExperienceClass b =  new BestExperienceClass(getServletContext().getRealPath("/").replaceAll("\\\\", "/"),"132RDFRADYHJ564");
		
		try {
			b.bestExpEfactor();
		} catch (ClassCalculException | ClassFileProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		//request.setAttribute("efactor", m);
		//this.getServletContext().getRequestDispatcher("/WEB-INFO/efactor.jsp").forward(request, response);	
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
