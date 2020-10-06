package kr.or.ddit.cookie;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieSplitTest {

	@Test
	public void test() {
		final Logger logger = LoggerFactory.getLogger(CookieSplitTest.class);
		/***Given***/
		String cookieString = "USERID=brown; REMEMBERME=Y; TEST=T; ";
		CookieSplit cookiesplit = new CookieSplit();

		/***When***/
		String result = cookiesplit.getCookieValue("USERID");
		logger.debug("{}".format(result));

		/***Then***/
		assertEquals(result, "brown");
	}

}
