package kr.or.ddit.member.repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;

@Repository("MemberDao")
public class MemberDao implements MemberDaoI {
	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	
	@Override
	public MemberVo getMember(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		MemberVo memberVo = sqlSession.selectOne("member.getMember", userid);
		sqlSession.close();
		return memberVo;
	}

}
