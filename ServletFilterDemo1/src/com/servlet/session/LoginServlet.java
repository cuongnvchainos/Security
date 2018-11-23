package com.servlet.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String userID="admin";
	private final String password="password"; 

    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("user");
		String pass=request.getParameter("pwd");
		String sessionID=null;
		
		if(userID.equals(user)&&password.equals(pass)) {
			HttpSession session = request.getSession();
			session.setAttribute("user","Test");
			session.setMaxInactiveInterval(30*60);
			Cookie username = new Cookie("user", user);
			username.setMaxAge(30*60);
			response.addCookie(username);
			Cookie[] cookies = request.getCookies();
			PrintWriter out = response.getWriter();
			if(cookies !=null){
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
					//generate token use sessionID
				}
				}
			
			
				
		
			response.sendRedirect("LoginSuccess.jsp");		
		}else {
			RequestDispatcher rq = request.getRequestDispatcher("Login.html");
			PrintWriter out = response.getWriter();
			out.println("Username or password error");
		}
	}

}
