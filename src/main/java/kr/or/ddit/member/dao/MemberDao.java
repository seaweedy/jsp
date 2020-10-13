package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;

public class MemberDao implements MemberDaoI {

	@Override
	public MemberVo getMember(String userId) {
		// DB에서 데이터를 조회하는 로직이 있어야하나
		// Controller기능에 집중 -> 하드코딩을 통해 dao, service는 간략하게 넘어간다.
		// Mock (가짜)

		/*
		 * MemberVo memberVo = new MemberVo(); memberVo.setUserId("brown"); // 하드코딩
		 * memberVo.setPassword("passBrown");
		 */

		SqlSession sqlSession = MybatisUtil.getSqlSession();

		// select
		// 한 건 : selectOne
		// 여러 건 : selectList

		MemberVo memberVo = sqlSession.selectOne("member.getMember", userId);

		sqlSession.close();

		return memberVo;

	}

	public List<MemberVo> selectAllMember() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();

		List<MemberVo> memberList = sqlSession.selectList("member.selectAllMember");

//		sqlSession.commit(); // insert or update or delete는 작성해야함
//		sqlSession.rollback();

		sqlSession.close();

		return memberList;
	}

}
