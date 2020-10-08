package kr.or.ddit.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
       
	// login 화면을 클라이언트에게 응답으로 생성
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	// login 화면에서 사용자가 보낸 아이디, 비밀번호를 사용하여 로그인 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		logger.debug("userId : {}, password : {}",userId, password);
		
		MemberServiceI memberService = new MemberService();
		MemberVo memberVo = memberService.getMember(userId);
		
		// DB에 등록된 회원이 없는 경우 (로그인 페이지) / 비밀번호가 일치하지 않는 경우 (로그인 페이지)
		if(memberVo == null || !memberVo.getPassword().equals(password) ) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		// 비밀번호가 일치하는 경우 (메인페이지 이동)
		else if(memberVo.getPassword().equals(password)) {
			request.getSession().setAttribute("S_MEMBER", memberVo); // 세션을 들고와서 값을 설정
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		
		// 쿠키정보
//		Cookie[] cookies = request.getCookies();
//		for(Cookie cookie : cookies) {
//			logger.debug("name : {}, value : {}",cookie.getName(), cookie.getValue());
//		}
//		
//		Cookie cookie = new Cookie("SERVERCOOKIE","COOKIEVALUE");
//		cookie.setMaxAge(60*60*24);
//		
//		response.addCookie(cookie); // 응답할 때 웹브라우저(클라이언트)에서 쿠키를 생성
	}

}
