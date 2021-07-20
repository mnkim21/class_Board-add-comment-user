package com.test.myapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/template.do")
public class Template extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// http://localhost:8090/myapp/template.do -> 이클립스 대신 이 주소를 호출
		// http://localhost:8090/myapp/servlet/com.test.myapp.Template -> 올바른 상황

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/template.jsp");
		dispatcher.forward(req, resp);
	}

}