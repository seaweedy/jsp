package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimesTableServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		
		int dan = 9;
		int gob = 9;
		writer.println("<html>");
		writer.println("	<head></head>");
		writer.println("	<body>");
		writer.println("		<table border ='1'>");
		for(int i = 2; i<= dan; i++) {
			writer.println("			<tr>"); // tr은 한 개의 행
			for(int j = 1; j<= gob; j++) {
				writer.println("			<td>"+i + " * " + j  + " = " + i*j + "</td>"); // td 한 개의 열
			}
			writer.println("			</tr>");
		}
		writer.println("		</table>");
		writer.println("	</body>");
		writer.println("</html>");
		writer.flush();
		writer.close();
	}

}
