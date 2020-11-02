package kr.or.ddit.config.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.repository.BoardRepositoryI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/ioc/scope.xml"})
public class IocScopeTest {
	private static final Logger logger = LoggerFactory.getLogger(IocScopeTest.class);
	
	@Autowired
	ApplicationContext context;
	
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRespository;
	
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRespository2;
	
	@Resource(name="boardRepository_a")
	private BoardRepositoryI boardRespository_a;
	
	
	@Resource(name="boardRepository_p")
	private BoardRepositoryI boardRepository_p;
	
	@Resource(name="boardRepository_p")
	private BoardRepositoryI boardRepository_p2;
	
	@Test
	public void prototypeTest() {
		/***Given***/
		
		/***When***/
		for(int i = 0; i <10; i++) {
			BoardRepositoryI boardRepository = context.getBean("boardRepository", BoardRepositoryI.class);
			
			BoardRepositoryI boardRepositoryp = context.getBean("boardRepository_p", BoardRepositoryI.class);
			
			logger.debug("singleton-boardRepository : {}, prototype-boardRepository : {}", boardRepository, boardRepositoryp);
		}

		/***Then***/
		assertNotEquals(boardRepository_p, boardRepository_p2);
	}
	
	@Test
	public void singletonTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertEquals(boardRespository, boardRespository2);
		assertNotEquals(boardRespository, boardRespository_a);
		assertNotEquals(boardRespository2, boardRespository_a);
	}

}
