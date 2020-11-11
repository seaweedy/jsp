package kr.or.ddit.member.repository;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;


@Repository("MemberDao")
public class MemberDao implements MemberDaoI {
	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberVo getMember(String userid) {
		MemberVo memberVo = sqlSession.selectOne("member.getMember", userid);
		return memberVo;
	}

	@Override
	public List<MemberVo> selectAllMember() {
		List<MemberVo> memberList = sqlSession.selectList("member.selectAllMember");
		return memberList;
	}
	
	@Override
	public List<MemberVo> selectMemberPageList(SqlSession sqlSession, PageVo pageVo) {
		return sqlSession.selectList("member.selectMemberPageList", pageVo);
	}
	
	@Override
	public int selectMemberTotalCnt(SqlSession sqlSession) {
		return sqlSession.selectOne("member.selectMemberTotalCnt");
	}

	@Override
	public int insertMember(MemberVo memberVo) {
		return sqlSession.insert("member.insertMember",memberVo);
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		return sqlSession.update("member.updateMember",memberVo);
	}
	
	

}
