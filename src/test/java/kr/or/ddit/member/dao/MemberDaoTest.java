package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;

public class MemberDaoTest {
	MemberDao memberDao;
	@Before
	public void setup() {
		memberDao = new MemberDao();
		String userid = "lmh";
		memberDao.deleteMember(userid);
	}
	
	@Test
	public void getMemberTest() {
		/*** Given ***/
		String userId = "apeach";

		MemberVo answerMemberVo = new MemberVo();
		answerMemberVo.setUserid("apeach");
		answerMemberVo.setPass("apeachPass");

		/*** When ***/
		MemberVo memberVo = memberDao.getMember(userId);

		/*** Then ***/
//		assertEquals("brown", memberVo.getUserId());
//		assertEquals("passBrown", memberVo.getPassword());

		assertEquals(answerMemberVo.getUserid(), memberVo.getUserid());
	}

	@Test
	public void selectAllMemberTest() {
		/*** Given ***/

		/*** When ***/
		List<MemberVo> memberList = memberDao.selectAllMember();

		/*** Then ***/
		assertEquals(memberList.size(), 17);
		// assertEquals("brown", memberList.get(0).getUserid());

	}

	@Test
	public void selectMemberPageListTest() {
		/*** Given ***/
		PageVo pageVo = new PageVo(1, 5);
		SqlSession sqlSession = MybatisUtil.getSqlSession();

		/*** When ***/
		List<MemberVo> memberPageList = memberDao.selectMemberPageList(sqlSession, pageVo);

		/*** Then ***/
		assertEquals(memberPageList.size(), 5);

	}

	@Test
	public void selectMemberTOtalCntTest() {
		/*** Given ***/
		SqlSession sqlSession = MybatisUtil.getSqlSession();

		/*** When ***/
		int totalCnt = memberDao.selectMemberTotalCnt(sqlSession);

		/*** Then ***/
		assertEquals(17, totalCnt);

	}

	@Test
	public void insertMemberTest() {
		/*** Given ***/
		MemberVo memberVo = new MemberVo("lmh", "pass", "이명호", "lmh", "대전 중구 중앙로 76", "영민빌딩 404호", "34940",
				"D:\\profile\\lmh.png", "lmh.png");

		/*** When ***/
		int insertCnt = memberDao.insertMember(memberVo);

		/*** Then ***/
		assertEquals(1, insertCnt);

	}
	
	@Test
	public void updateMemberTest() {
		/*** Given ***/
		MemberVo memberVo = new MemberVo("lss", "pass11", "이명호변경", "lmh", "대전 중구 중앙로 77", "영민빌딩 404호", "34940",
				"D:\\profile\\lmh.png", "lmh.png");
		
		/*** When ***/
		int updateCnt = memberDao.updateMember(memberVo);
		
		/*** Then ***/
		assertEquals(1, updateCnt);
		
	}
}
