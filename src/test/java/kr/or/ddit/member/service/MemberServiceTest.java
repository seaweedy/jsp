package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.model.MemberVo;

public class MemberServiceTest {

	@Test
	public void getMemberTest() {
		/*** Given ***/
		MemberServiceI memberService = new MemberService();
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
		MemberServiceI memberService = new MemberService();
		
		/*** When ***/
		List<MemberVo> memberList = memberService.selectAllMember();
		
		/*** Then ***/
		assertEquals(15, memberList.size());
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		MemberServiceI memberService = new MemberService();
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
	public void selectMemberTotalCntTest() {
		/***Given***/
		MemberServiceI memberService = new MemberService();
		
		/***When***/
//		Map<String, Object> map = memberService.selectMemberPageList(paging);
//		
//		int totalCnt = memberService.selectMemberTotalCnt();
		
		/***Then***/
//		assertEquals(totalCnt, 15);
		
	}

}
