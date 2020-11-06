package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;
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

	@Override
	public List<MemberVo> selectAllMember() {
		return memberDao.selectAllMember(); 
	}
	
	@Override
	public Map<String, Object> selectMemberPageList(PageVo pageVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		Map<String, Object> map = new HashMap<>();
		map.put("memberList", memberDao.selectMemberPageList(sqlSession,pageVo));
		
		// 15건, 페이지 사이즈를 7로 가정했을 때 3개의 페이지가 나와야한다.
		// 15/7은 
		int totalCnt = memberDao.selectMemberTotalCnt(sqlSession);
		int pages = (int)Math.ceil((double)totalCnt/pageVo.getPageSize());
		
		map.put("pages", pages);
		
		return map;
	}

	@Override
	public int insertMember(MemberVo memberVo) {
		return memberDao.insertMember(memberVo);
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		return memberDao.updateMember(memberVo);
	}

}
