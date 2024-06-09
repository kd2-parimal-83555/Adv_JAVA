package com.sunbeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
//@WebServlet("/vote")
public class VoteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String candidateId = req.getParameter("candidate");
		int id = Integer.parseInt(candidateId);
		try(CandidateDaoImpl candDao = new CandidateDaoImpl()){
			candDao.incrementVote(id);
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		resp.setContentType("TExt/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
				out.println("<head>");
	   	    	out.println("<title>Voted</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("Your vote is registerd successfully. <br/><br/>");
				out.println("<a href='logout'>Sign Out</a>");
				out.println("</body>");
				out.println("</html>");
	
	}
	
}
