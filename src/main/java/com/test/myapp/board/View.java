package com.test.myapp.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/view.do")
public class View extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 1. 데이터 가져오기(seq)
		 2. DB작업 > DAO 위임 > select where seq
		 3. BoardDTO반환 > JSP 호출 + 전달
		 */
		
		HttpSession session = req.getSession()
;		
		// 1.
		String seq = req.getParameter("seq");
		String column = req.getParameter("column");
		String search = req.getParameter("search");
		
		// 2.
		BoardDAO dao = new BoardDAO();
		
		// 2.3 조회수 증가하기
		if(session.getAttribute("read") != null && session.getAttribute("read").toString().equals("n")) {
			
			dao.updateReadcount(seq);
			
			session.setAttribute("read", "y");
		}
		
		BoardDTO dto = dao.get(seq);
		
		// 2.5
		String subject = dto.getSubject();
		String content = dto.getContent();
		
		
		// 무조건 글 제목과 내용에 들어있는 <scrip>태그는 비활성화
		subject = subject.replace("<script", "&lt;script").replace("</script>", "&lt;/script&gt;");
		dto.setSubject(subject);
		
		content = content.replace("<script", "&lt;script").replace("</script>", "&lt;/script&gt;");
		dto.setContent(content);
		
		
		// 글 내용에 태그 적용 안되게 하기
		if (dto.getTag().equals("n")) {
			// <b> -> &lt;b&gt;
			content = content.replace("<", "&lt;").replace(">", "&gt;");
			dto.setContent(content);
		}
		
		// 글 내용의 개행문자 처리/ 엔터를 <br>태그로 변경하기
		// content = dto.getContent();
		content = content.replace("\r\n", "<br>");
		dto.setContent(content);
		
		// 내용으로 검색 중일 때 검색어 부각시키기
		if(column != null && search != null & column.equals("content")) {
			content = content.replace(search, "<span style='color: tomato; background-color: yellow;'>" + search + "</span>");
			dto.setContent(content);
		}
		
		
		// 2.7
		// - 현재 보고 있는 글에 달린 댓글 목록 가져오기
		ArrayList<CommentDTO> clist = dao.listComment(seq); //현재 글번호(= 댓글의 부모글번호)
		
		
		
		// 3.
		req.setAttribute("dto", dto);
		req.setAttribute("column", column);
		req.setAttribute("search", search);
		req.setAttribute("clist", clist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);
	}

}
