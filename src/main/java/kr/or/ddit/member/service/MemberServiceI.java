package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;

public interface MemberServiceI {
	MemberVo getMember(String userid);
	
	List<MemberVo> selectAllMember();
	
	Map<String, Object> selectMemberPageList(PageVo pageVo);
	
	int insertMember(MemberVo memberVo);
	
	int updateMember(MemberVo memberVo);
}
