package com.terweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terweb.efactor.beans.AllVarDocClass;
import com.terweb.exception.ClassCalculException;
import com.terweb.exception.ClassFileProblemException;

/**
 * Servlet implementation class AllVarDoc
 */
@WebServlet("/AllVarDoc")
public class AllVarDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllVarDoc() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//All-Variation-Docucment-Efactor	
	
		AllVarDocClass b =  new AllVarDocClass(getServletContext().getRealPath("/").replaceAll("\\\\", "/"),"132RDFRADYHJ564");
	
	    try {
			b.allVarDocEfactor();
		} catch (ClassCalculException | ClassFileProblemException e) {
		
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
