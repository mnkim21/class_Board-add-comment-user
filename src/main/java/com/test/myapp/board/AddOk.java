package com.test.myapp.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/addok.do")
public class AddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 1. 데이터 가져오기(제목, 내용, 태그)
		 2. DB작업 -> DAO위임 -> insert
		 3. 결과 -> 후처리
		 */
		
		// req.setCharacterEncoding("UTF-8");
		
		
		// 1.
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String tag = req.getParameter("tag");
		
		// 2.
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		HttpSession session = req.getSession();
		dto.setId(session.getAttribute("id").toString());
		
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setTag(tag);
		
		int result = dao.add(dto);
		
		// 3.
		if (result == 1) {
			resp.sendRedirect("/myapp/board/list.do");
		} else {
			resp.sendRedirect("/myapp/board/add.do");
		}	
		
	}

}
