package kr.or.ddit.login.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardServiceI;

@WebServlet("/login")
public class LoginServlet extends HttpServlet { // 로그인 서블렛 객체는 한 번 생성
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	
	// 서블렛 객체는 스프링에서 관리하는 영역이 아니므로 아래의 코드는 적용되지않음
	// 서블렛은 톰캣이 관리
	@Resource(name="boardService")
	private BoardServiceI boardService;
	
	@Override
	public void init() throws ServletException {
		// service 객체 생성
	}
	
	// login 화면을 클라이언트에게 응답으로 생성
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("logInServlet doGet");
		logger.debug("UNT_CD parameter : {}", request.getParameter("UNT_CD"));
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
