package kr.or.ddit.cookie;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieSplitTest {

	@Test
	public void getCookieValueSuccessTest() {
		/***Given***/
		CookieSplit cookiesplit = new CookieSplit();
		final Logger logger = LoggerFactory.getLogger(CookieSplitTest.class); // 로그객체

		/***When***/
		String result = cookiesplit.getCookieValue("REMEMBERME"); // 예시되는 값 입력
		logger.debug("{}".format(result)); // 로그확인

		/***Then***/
		assertEquals(result, "Y"); // 기대한 값과 동일한지 확인
		assertNotNull(result); // 기대한 값이 널이 ㅇ아닌지
	}
	
	@Test
	public void getCookieValueFailureTest() {
		/***Given***/
		CookieSplit cookiesplit = new CookieSplit();
		
		/***When***/
		String result = cookiesplit.getCookieValue("PASSWORD"); // 예시되는 값 입력
		
		/***Then***/
		assertEquals(result, ""); // 기대한 값과 동일한지 확인
	}

}
