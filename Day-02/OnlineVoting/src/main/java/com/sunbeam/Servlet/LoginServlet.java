package com.sunbeam.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;
//@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	String email = req.getParameter("email");
	String passwd = req.getParameter("passwd");
	try(UserDao userDao = new UserDaoImpl()){
		User user = userDao.findByEmail(email);
		if(user != null && user.getPassword().equals(passwd)) {
			System.out.println("Login Successfull");
			resp.sendRedirect("candidate");
		}
		else {
			resp.setContentType("Text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Login Failed</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("Invalid email or password. <br/><br/>");
			out.println("<a href='index.html'>Login Again</a>");
			out.println("</body>");
			out.println("</html>");
		}
	}
	catch (Exception e) { 
		e.printStackTrace();
		throw new ServletException(e);
	}
	}
}
