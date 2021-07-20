package com.test.myapp.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckMember {

	public void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			HttpSession session = req.getSession();
			
			//로그인 안한 사람
			if(session.getAttribute("id") == null || session.getAttribute("id").toString().equals("")) {
				
				resp.setCharacterEncoding("UTF-8");
				
				PrintWriter writer = resp.getWriter();
				
				writer.print("<html>");
				writer.print("<head>");
				writer.print("<meta charset='utf-8'>");
				writer.print("</head>");
				writer.print("<body>");
				writer.print("<script>");
				writer.print("alert('로그인 후 사용 가능합니다.');");
				writer.print("location.href='/myapp/board/login.do';");
				writer.print("</script>");
				writer.print("</body>");
				writer.print("</head>");
			
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
