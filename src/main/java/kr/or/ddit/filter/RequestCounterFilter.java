package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestCounterFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(RequestCounterFilter.class);
	Map<String, Integer> requestCounterMap;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("RequestcounterFilter.init()");
		requestCounterMap = new HashMap<String, Integer>();
		// request, session, application
		
		ServletContext sc = filterConfig.getServletContext(); // httpServlet으로 변환
		sc.setAttribute("requestCounterMap", requestCounterMap);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("RequestcounterFilter.doFilter()");

		HttpServletRequest req = (HttpServletRequest)request;
		logger.debug("uri : {} ",req.getRequestURI());
		
		// uri 별 요청 횟수
		// /memberList 12
		// /jstl/jstl_fmt_date.jsp 20
//		map 객체에서 uri에 해당하는 요청이 있느지 확인
//		없으면 값을 1로 해서 새로운 Key로 추가
//		있으면 기존 값에서 1을 더해 값을 수정
		Integer value = requestCounterMap.get(req.getRequestURI());
		
		// 해당 uri로 최초요청
		if(value == null) {
			requestCounterMap.put(req.getRequestURI(), 1);
		}
		// 해당 uri요청이 존재할 경우
		else {
			requestCounterMap.put(req.getRequestURI(), value +1);
		}
		
		// 어떤 자료구조를 쓰면 좋을까?
		// 등록된 다른 필터로 요청을 위임
		// 더 이상 등록된 Filter가 없을 경우 요청을 처리할 서블릿 / jsp으로 요청을 전달
		
		// 전처리 : 요청이 서블릿으로 가기전에 실행되는 부분
		logger.debug("RequestCounterFilter 전처리 부분 - chain.doFilter 호출전");
		
		chain.doFilter(request, response); // servlet 처리
		
		logger.debug("RequestCounterFilter 후처리 부분 - chain.doFilter 호출후");
		
		// 후처리 : servlet 응답성성 후 응답이 web으로 넘어가는 단계
		
	}

	@Override
	public void destroy() {
		
	}

}
