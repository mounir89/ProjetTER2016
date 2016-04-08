package com.terweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terweb.efactor.beans.AllVarTopicClass;
import com.terweb.exception.ClassCalculException;
import com.terweb.exception.ClassFileProblemException;

/**
 * Servlet implementation class AllVarTopic
 */
@WebServlet("/AllVarTopic")
public class AllVarTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllVarTopic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//All-Variation-Topic-Efactor
		
		AllVarTopicClass b =  new AllVarTopicClass(getServletContext().getRealPath("/").replaceAll("\\\\", "/"),"132RDFRADYHJ564");
		
			try {
				b.allVarTopicEfactor();
			} catch (ClassCalculException | ClassFileProblemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.getServletContext().getRequestDispatcher("/BestExperience.jsp").forward(request, response);	

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
