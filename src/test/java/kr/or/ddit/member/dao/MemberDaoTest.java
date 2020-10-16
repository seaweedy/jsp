package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;

public class MemberDaoTest {
	@Test
	public void getMemberTest() {
		/***Given***/
		MemberDao memberDao = new MemberDao();
		String userId = "brown";
		
		MemberVo answerMemberVo = new MemberVo();
		answerMemberVo.setUserid("brown");
		answerMemberVo.setPass("brownPass");	

		/***When***/
		MemberVo memberVo = memberDao.getMember(userId);

		/***Then***/
//		assertEquals("brown", memberVo.getUserId());
//		assertEquals("passBrown", memberVo.getPassword());
		
		assertEquals(answerMemberVo.getUserid(), memberVo.getUserid());
	}
	
	@Test
	public void selectAllMemberTest() {
		/***Given***/
		MemberDao memberDao = new MemberDao();
		
		/***When***/
		List<MemberVo> memberList = memberDao.selectAllMember();
		
		/***Then***/
		assertEquals(memberList.size(), 15);
		// assertEquals("brown", memberList.get(0).getUserid());
		
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		MemberDao memberDao = new MemberDao();
		PageVo pageVo = new PageVo(1,5);
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		/***When***/
		List<MemberVo> memberPageList = memberDao.selectMemberPageList(sqlSession, pageVo);

		/***Then***/
		assertEquals(memberPageList.size(), 5);
		
	}
	
	@Test
	public void selectMemberTOtalCntTest() {
		/***Given***/
		MemberDao memberDao = new MemberDao();
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		/***When***/
		int totalCnt= memberDao.selectMemberTotalCnt(sqlSession);
		
		/***Then***/
		assertEquals(15, totalCnt);
		
	}
}
