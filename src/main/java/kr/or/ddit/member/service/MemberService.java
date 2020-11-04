package kr.or.ddit.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.repository.MemberDao;
import kr.or.ddit.member.repository.MemberDaoI;

@Service("MemberService")
public class MemberService implements MemberServiceI  {
	@Resource(name="MemberDao")
	private MemberDaoI memberDao;
	
	public MemberService(){
//		memberDao = new MemberDao();
	}

	@Override
	public MemberVo getMember(String userid) {
		return memberDao.getMember(userid);
	}

}
