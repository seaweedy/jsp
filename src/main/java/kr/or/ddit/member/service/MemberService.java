package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.repository.MemberDaoI;

@Service("MemberService")
public class MemberService implements MemberServiceI  {
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	@Resource(name="MemberDao")
	private MemberDaoI memberDao;
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
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
//		logger.debug("첫번째 insert 시작 전");
//		memberDao.insertMember(memberVo);
//		logger.debug("첫번째 insert 종료 후");
		
		// 첫번째 쿼리는 정상적으로 실행되지만
		// 두번째 쿼리에서 동일한 데이터를 입력하여 PRIMARY KEY 제약조건에 의해
		// SQL 실행 실패
		// 첫번째 쿼리도 성공했지만 트랜잭션 설정을 SERVICE 레벨에 설정하였기 때문에
		// 서비스 메서드에서 실행된 모든 쿼리를 rollback 처리한다.
		
		
//		logger.debug("두번째 insert 시작 전");
//		memberDao.insertMember(memberVo);
//		logger.debug("두번째 insert 종료 후");
		
		return memberDao.insertMember(memberVo);
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		return memberDao.updateMember(memberVo);
	}

}
