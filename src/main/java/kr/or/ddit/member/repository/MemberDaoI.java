package kr.or.ddit.member.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;

public interface MemberDaoI {
	MemberVo getMember(String userid);
	
	List<MemberVo> selectAllMember();
	
	List<MemberVo> selectMemberPageList(SqlSession sqlSession,PageVo pageVo);
	
	int selectMemberTotalCnt(SqlSession sqlSession);
	
	int insertMember(MemberVo memberVo);
	
	int updateMember(MemberVo memberVo);
	
}
