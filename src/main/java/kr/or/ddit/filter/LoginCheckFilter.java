package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.MemberVo;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(LoginCheckFilter.class);
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.debug("LoginCheckFilter.doFilter()");
		
		// session에 S_MEMBER 이름으로 속성이 존재하면 정상적으로 로그인한 케이스
		// 없으면 로그인 하지않은 상태로 접속 시도
		
		// GET,POST /login => S_MEMBER 속성이 없는게 당연
		
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI(); // 서버 실행 이후의 접속하는 uri 가져옴
		
		// login 경로로 요청하는 것은 사용자가 로그인을 시도하고 있는 것이므로
		// 세션 체크없이 servlet 혹은 다른 filter로 요청을 위임시켜준다.
		if(uri.equals("/login") || uri.endsWith(".js")|| uri.endsWith(".css") || uri.endsWith("map")){
			chain.doFilter(request, response); // 다음 filter로 넘김
		}
		else {
			HttpSession session = req.getSession();
			MemberVo S_MEMBER = (MemberVo)session.getAttribute("S_MEMBER"); // loginservlet의 저장된 속성 가져옴

			// 정상적으로 로그인이 될 경우
			if(S_MEMBER !=null) {
				chain.doFilter(request, response);
			}
			// 로그인 하지 않은 상태 (/login 화면으로 재요청 지시)
			else {
				HttpServletResponse resp = (HttpServletResponse)response;
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
