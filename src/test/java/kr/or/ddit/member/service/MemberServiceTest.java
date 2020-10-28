package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.model.MemberVo;

public class MemberServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
	private MemberServiceI memberService;
	
	@Before
	public void setup() {
		memberService = new MemberService();
		String userid = "lmh";
		memberService.deleteMember(userid);
	}

	@Test
	public void getMemberTest() {
		/*** Given ***/
		String userId = "brown";

		MemberVo answerMemberVo = new MemberVo();
		answerMemberVo.setUserid("brown");
		answerMemberVo.setPass("brownPass");

		/*** When ***/
		MemberVo memberVo = memberService.getMember(userId);

		/*** Then ***/
		assertEquals("brown", memberVo.getUserid());
		assertEquals(answerMemberVo.getUserid(), memberVo.getUserid());
	}
	
	@Test
	public void selectAllMemberTest() {
		/*** Given ***/
		
		/*** When ***/
		List<MemberVo> memberList = memberService.selectAllMember();
		
		/*** Then ***/
		assertEquals(15, memberList.size());
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1,7);
		
		/***When***/
		Map<String, Object> map = memberService.selectMemberPageList(pageVo);
		List<MemberVo> memberPageList = (List<MemberVo>)map.get("memberList");

		// 생성해야할 page 수 확인
		int pages = (int)map.get("pages"); 
		
		/***Then***/
		assertEquals(7, memberPageList.size());
		assertEquals(3, pages);
		
	}
	
	@Test
	public void localeListTest() {
		Locale[] locales = SimpleDateFormat.getAvailableLocales();
		for(Locale locale : locales) {
			logger.debug(locale.toString());
		}
	}
	
	@Test
	public void insertMemberTest() {
		/***Given***/
		MemberVo memberVo = new MemberVo("lmh", "pass", "이명호", "lmh", "대전 중구 중앙로 76", "영민빌딩 404호", "34940",
				"D:\\profile\\lmh.png", "lmh.png");
		
		/***When***/
		int insertCnt = memberService.insertMember(memberVo);

		/***Then***/
		assertEquals(1, insertCnt);
		
	}

}
