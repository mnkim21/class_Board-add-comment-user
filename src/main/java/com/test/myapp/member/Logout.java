package com.test.myapp.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout.do")
public class Logout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 로그아웃
		 1. 인증 티켓 제거
		 2. 부가 정보 제거
		 */
		
		HttpSession session = req.getSession();
		
		session.removeAttribute("id"); //로그아웃
		session.removeAttribute("name");
		session.removeAttribute("lv");
		session.removeAttribute("regdate");
		
		resp.sendRedirect("/myapp/index.do");

	}

}
