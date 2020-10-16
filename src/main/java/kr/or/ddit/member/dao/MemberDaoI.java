package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;

public interface MemberDaoI {
	
	MemberVo getMember(String userId);
	
	List<MemberVo> selectAllMember();
	
	List<MemberVo> selectMemberPageList(SqlSession sqlSession,PageVo pageVo);
	
	int selectMemberTotalCnt(SqlSession sqlSession);
}
