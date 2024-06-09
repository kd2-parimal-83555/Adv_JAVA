package com.sunbeam.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.CandidateDaoImpl;

public class DeleteCandidateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);
		try(CandidateDaoImpl candDao = new CandidateDaoImpl()){
			int count = candDao.deleteById(id);
			String msg = "Candidate Deleted:"+ count;
			req.setAttribute("message",msg);
			RequestDispatcher rd = req.getRequestDispatcher("result");
			rd.forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
}
