package com.test.myapp.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/addcomment.do")
public class AddComment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CheckMember cm = new CheckMember();
		cm.check(req, resp);

		//할일
		//1. 데이터 가져오기(content, pseq)
		//2. DB 작업 > DAO 위임 > insert
		//3. 돌아가기 > view.do?seq=10
		
		//1.
		String pseq = req.getParameter("pseq"); //보고있던 글번호(= 작성중인 댓글의 부모 글번호)
		String content = req.getParameter("content");
		
		//2.
		BoardDAO dao = new BoardDAO();
		CommentDTO dto = new CommentDTO();
		
		HttpSession session = req.getSession();
		
		dto.setId(session.getAttribute("id").toString()); //댓글 작성자 아이디(= 로그인한 사람 세션)
		dto.setPseq(pseq);
		dto.setContent(content);
		
		int result = dao.addComment(dto);//1, 0		
		
		//3.
		if (result == 1) {
			resp.sendRedirect("/myapp/board/view.do?seq=" + pseq); //보고 있던 글번호를 가지고 돌아가기
		} else {
			
			resp.setCharacterEncoding("UTF-8");
			
			PrintWriter writer = resp.getWriter();			
			
			writer.print("<html>");
			writer.print("<body>");
			writer.print("<script>");
			writer.print("alert('댓글 쓰기 실패');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.print("</body>");
			writer.print("</html>");
			
			writer.close();
		}
		
	}

}