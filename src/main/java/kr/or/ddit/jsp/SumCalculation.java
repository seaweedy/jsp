package kr.or.ddit.jsp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/sumCalculation")
public class SumCalculation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SumCalculation.class);
	
	// 입력화면 요청 처리(sumInput.jsp)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("jsp/sumInput.jsp").forward(request, response);
		// get방식으로 주소줄
		// form이 있는 jsp로 forwarding한다
		
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		
		// 폼에서 입력된 값을 getParameter로 받아온다.
		
		int sumResult = 0;
		for(int i = start ; i <= end; i ++) {
			sumResult +=i;
		}
		// 사이값을 구한다.
		logger.debug("sumResult : {}",sumResult);
		
		HttpSession session = request.getSession();
		session.setAttribute("sumResult",sumResult);
		// 세션 객체에 담는다.
		request.getRequestDispatcher("/jsp/sumResult.jsp").forward(request, response);
		// 값을 출력할jsp로 forwarding한다.
	}

}
