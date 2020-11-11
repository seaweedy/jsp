package kr.or.ddit.member.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;


public class MemberDaoTest extends ModelTestConfig {
	private static final Logger logger = LoggerFactory.getLogger(MemberDaoTest.class);

	@Resource(name="MemberDao")
	private MemberDaoI memberDao;
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
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
		/***Given***/
		

		/***When***/
		List<MemberVo> memberList = memberDao.selectAllMember();

		/***Then***/
		assertEquals(16, memberList.size());
//		assertTrue(memberList.size() == 16);
	}
	
	@Test
	public void selectMemberPageListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 20);

		/***When***/
		memberDao.selectMemberPageList(pageVo);

		/***Then***/
		logger.debug("selectMemberPageList count : {} ", (memberDao.selectMemberPageList(pageVo)).size());
		assertEquals(16, (memberDao.selectMemberPageList(pageVo)).size());
		
	}
	
	@Test
	public void selectMemberTotalCntTest() {
		/***Given***/
		
		/***When***/
		memberDao.selectMemberTotalCnt();
		
		/***Then***/
		assertEquals(16, memberDao.selectMemberTotalCnt());
		
	}
	
	@Test
	public void insertMemberTest() {
		/***Given***/
		MemberVo memberVo = new MemberVo("test", "testPass", "testNm", "testAlias", "testAddr1", "testAddr2", "1234", "", "");
		
		/***When***/
		int insertCnt = memberDao.insertMember(memberVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
		
	}
	
	@Test
	public void updateMemberTest() {
		/***Given***/
		MemberVo memberVo = new MemberVo("ddit", "dditPass", "dditNm", "dditeAlias", "dditAddr1", "dditAddr2", "4321", "", "");
		
		/***When***/
		int updateCnt = memberDao.updateMember(memberVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
		
	}

}














