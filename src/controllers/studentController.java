package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.bean.Student;
import models.bo.studentsBO;

/**
 * Servlet implementation class studentController
 */
@WebServlet("/studentController")
public class studentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	studentsBO svBO = new studentsBO();
    public studentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idUpdate = request.getParameter("idUpdate");
		String idRemove = request.getParameter("idRemove");
		HttpSession session = 	request.getSession(true);
		if(idUpdate!=null) {
			RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
			rd.forward(request, response);
		}else{
			try {
				svBO.deleteStudentBO(Integer.parseInt(idRemove));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("status", 1);
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = 	request.getSession(true);
		int id = Integer.parseInt(request.getParameter("id"));
		String name =  new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		String falculty = request.getParameter("falculty");
		String classOf = request.getParameter("classOf");
		Date birthDay = null;
		String action = request.getParameter("submit");
		//System.out.println(request.getParameter("submit"));
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		try {
			birthDay = formater.parse(request.getParameter("birthDay"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean gentle = Boolean.parseBoolean(request.getParameter("male"));
		Float score = Float.parseFloat(request.getParameter("score"));
		Student student = new Student(id,name,falculty,classOf,birthDay,gentle,score);
		try {
			if(action.toUpperCase().equals("ADD")) {
				svBO.insertStudentBO(student);
			}else {
				svBO.updateStudentBO(student);
			}
			session.setAttribute("status", 1);
			response.sendRedirect("index.jsp");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			session.setAttribute("status", 0);
			response.sendRedirect("detail.jsp");
			e.printStackTrace();
		}
	}

}
