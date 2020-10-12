package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVo;

public class MemberService implements MemberServiceI {

	@Override
	public MemberVo getMember(String userId) {
		MemberDaoI memberDao = new MemberDao();
		return memberDao.getMember(userId);
	}

	@Override
	public List<MemberVo> selsectAllmember() {
		MemberDaoI memberDao = new MemberDao();
		return memberDao.selectAllMember();
	}
	
	

}
