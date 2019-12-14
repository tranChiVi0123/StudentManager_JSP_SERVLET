package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.bo.authBO;

/**
 * Servlet implementation class authController
 */
@WebServlet("/authController")
public class authController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public authController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordrp = "";
		if(request.getParameter("passwordrp")!=null) {
			passwordrp = request.getParameter("passwordrp");
		}
		HttpSession session = 	request.getSession(true);
		if(passwordrp.equals("")) {
			this.login(request,response,email,password);
		}else {
			if(passwordrp.equals(password)) {
				this.reigister(request,response,email,password);
			}else {
				session.setAttribute("status", 0);
				response.sendRedirect("index.jsp");	
			}
		}
	}
	private void reigister(HttpServletRequest request, HttpServletResponse response, String email, String password) throws IOException {
		authBO check = new authBO();
		HttpSession session = 	request.getSession(true);
		try {
			check.registerBO(email, password);
			session.setAttribute("status", 1);
			response.sendRedirect("index.jsp");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = 	request.getSession(true);
		session.removeAttribute("userCurrent");
		session.removeAttribute("role");
		response.sendRedirect("index.jsp");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response, String email,String password )throws ServletException, IOException {
		authBO check = new authBO();
		HttpSession session = 	request.getSession(true);
		try {
			if(check.checkLogin(email, password)) {
				//System.out.println("Login Successed");
				session.setAttribute("userCurrent", email);
				if(check.checkRole(email)) {
					session.setAttribute("role", "1");
				}else {
					session.setAttribute("role", "0");
				}
				session.setAttribute("status", 1);
				response.sendRedirect("index.jsp");
			}else {
				session.setAttribute("status", 0);
				response.sendRedirect("index.jsp");				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
