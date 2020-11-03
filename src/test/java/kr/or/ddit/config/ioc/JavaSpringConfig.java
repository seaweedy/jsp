package kr.or.ddit.config.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.repository.BoardRepository;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.board.service.BoardService;

@Configuration
public class JavaSpringConfig {
	
	// boardRepository, boardService
	// 메서드이름 => 스프링 빈이름
	
	// xml : <bean id="boardRepository(메서드이름)" class="BoardRepository"/>
	@Bean
	public BoardRepositoryI boardRepository() {
		return new BoardRepository(); // 
	}
	
	// xml : <bean id="boardService(메서드이름)" class="BoardService"/>
	@Bean
	public BoardService boardService() {
		BoardService boardService = new BoardService();
		boardService.setBoardRepository(boardRepository());
		
		// 아래와 같이 직접 new 연산자를 통해 생성한 객체는 스프링 빈이 아니다.
		// @Bean 어노테이션이 붙은 메서드를 호출해야 스프링 컨테이너에서 관리되는 스프링빈을
		// 얻을 수 있다.
		// boardService.setBoardRepository(boardRepository()); // 인자도 스프링 빈만이 들어갈 수 있으므로 위의 메서드를 인자로 넣는다.
		return boardService;
	}

}
