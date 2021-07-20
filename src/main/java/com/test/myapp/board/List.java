package com.test.myapp.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/list.do")
public class List extends HttpServlet {
	
	/*
	 GET or POST
	 - 페이지를 요청할때 어느때 GET요청이고 어느때 POST요청인지?
	 1. GET
	 	- 브라우저 주소창을 URL을 입력해 요청할때
	 	- <a> 이동시
	 	- location.href 이동 시
	 	- resp.sendRedirect() 이동 시
	 	- <form method="GET">
	 2. POST
	 	- <form method="POST">
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPostGet(req, resp);
		
		// http://localhost:8090/myapp/board/list.do?column=subject&search=%EB%82%B4%EC%9A%A9
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPostGet(req, resp);
		
		// http://localhost:8090/myapp/board/list.do
	}

	private void doPostGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 list.do
		 1. 목록보기(게시판의 시작 페이지 역할)
		 	- select
		 2. 검색결과보기(검색 버튼을 눌러서 호출
		 	- select where
		 */
		
		String column = req.getParameter("column");
		String search = req.getParameter("search");
		
		String isSearch = "n";
		
//		System.out.println("column: " + column);
//		System.out.println("search: " + search);
		
		if (column != null && search != null && !column.equals("") && !search.equals("")) {
			isSearch = "y";
		}
		
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("column", column);
		map.put("search", search);
		map.put("isSearch", isSearch);
		

		/*
		 할 일
		 1. DB작업 > DAO 위임 > select
		 2. ArrayList<BoardDTO> 반환
		 3. JSP 호출 + 2번 전달
		 */
		
		// 1.
		BoardDAO dao = new BoardDAO();
		
		// 2.
		ArrayList<BoardDTO> list = dao.list(map);
		
		// 2.5
		/*
		 날짜 가공 업무 방법
		 1. SQL > 나중에 시분초 필요 > 선택x
		 2. DAO > 나중에 시분초 필요 + DAO에서 데이터 가공 안한다. 순수 데이터 입출력 전담. > 선택x
		 3. Servlet => 보통 데이터 조작, 가공은 Servlet이 담당
		 4. JSP > 출력만 전담 > 선택x
		 */
		for (BoardDTO dto : list) {
		
			// 날짜 > 가공
			String regdate = dto.getRegdate();
			regdate = regdate.substring(0, 10);
			dto.setRegdate(regdate);
			
			// 제목이 길면 자르기
			String subject = dto.getSubject();
			
			// 무조건 글 제목과 내용에 들어있는 <scrip>태그는 비활성화
			subject = subject.replace("<script", "&lt;script").replace("</script>", "&lt;/script&gt;");
			dto.setSubject(subject);
			
			if(subject.length() > 30) {
				subject = subject.substring(0, 30) + "...";
				dto.setSubject(subject);
			}
			
			// 제목으로 검색 중일 때 검색어를 부각시키기
			// "자유 게시판 입니다." > 검색어(게시판) > "자유 <span style="color: tomato; background-color: yellow;>게시판</span> 입니다."
			if(isSearch.equals("y") && column.equals("subject")) {
				subject = subject.replace(search, "<span style='color: tomato; background-color: yellow;'>" + search + "</span>");
				dto.setSubject(subject);
			}
			
			
			
		}
		
		
		// 새로고침에 의한 조회 수 증가 방지 티켓
		HttpSession session = req.getSession();
		
		session.setAttribute("read", "n");
		
		
		// 3.
		req.setAttribute("list", list);
		req.setAttribute("map", map); //*****
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);
	}

}
