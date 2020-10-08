package kr.or.ddit.member.dao;

import kr.or.ddit.member.model.MemberVo;

public class MemberDao implements MemberDaoI{

	@Override
	public MemberVo getMember(String userId) {
		// DB에서 데이터를 조회하는 로직이 있어야하나
		// Controller기능에 집중 -> 하드코딩을 통해 dao, service는 간략하게 넘어간다.
		// Mock (가짜)
		
		MemberVo memberVo = new MemberVo();
		memberVo.setUserId("brown"); // 하드코딩
		memberVo.setPassword("passBrown");
		
		return memberVo;
	}

}
