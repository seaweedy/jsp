package kr.or.ddit.cookie;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieSplit {
	private static final Logger logger = LoggerFactory.getLogger(CookieSplit.class);
	
	// cookieString 문자열 변수에 담긴 ㄱ밧은
	// 쿠키이름1=쿠키값1; 쿠키이름2=쿠키값; ... 형태로 구성됨
	// 구성된 쿠키 이름과 값은 상황에 따라 변경 될 수 있음
	private String cookieString = "USERID=brown; REMEMBERME=Y; TEST=T; ";

	public static void main(String[] args) {
		CookieSplit cookiesplit = new CookieSplit();
		logger.debug(cookiesplit.getCookieValue("USERID")); // 기대되는 값 brown
		logger.debug(cookiesplit.getCookieValue("TEST")); // T
		logger.debug(cookiesplit.getCookieValue("XXXX")); // Y
	}
	
	// cookieString 필드를 분석하여 메서드 인자로 넘어온 cookieName에 해당하는 쿠키가 있을 경우
	// 해당 쿠키의 값을 반환하는 메서드
	public String getCookieValue(String cookieName) {
		String[] cookies = cookieString.split("; ");
		String[] cookie;
		String result = "";
		for(int i = 0; i < cookies.length; i++) {
			cookie = cookies[i].split("=");
			if(cookie[0].equals(cookieName)) {
				result = cookie[1];
			}
		}
		return result;
	}
}
