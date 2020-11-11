package kr.or.ddit.member.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.member.model.MemberVo;


public class MemberDaoTest extends ModelTestConfig {

	@Resource(name="MemberDao")
	private MemberDaoI memberDao;
	
	@Test
	public void selectAllMemberTest() {
		/***Given***/
		

		/***When***/
		List<MemberVo> memberList = memberDao.selectAllMember();

		/***Then***/
		assertTrue(memberList.size() > 13);
	}

}
