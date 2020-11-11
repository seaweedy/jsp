package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class ProfilingAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAdvice.class);
	
	// pointCut 설정을 위한 의미 없는 메서드
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {
		
	}
	
	@Before("dummy()")
	public void beforeMethod(JoinPoint joinPoint) {
		logger.debug("beforeMethod : {}",joinPoint.getSignature().getName());
	}
	
	@Around("dummy()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		// 메서드 실행 전 공통 관심사항
		long start = System.currentTimeMillis();
		
		// 메서드 실행
		Object[] args = joinPoint.getArgs(); // 핵심 메서드 실행할때 전달되는 인자
		Object ret = joinPoint.proceed(args); // 실행할 메서드에 인자를 전달
		
		// 메서드 실행 후 공통 관심사항
		long end =System.currentTimeMillis();
		logger.debug("profiling : {} {} - {}",
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(),
				end-start + " ms");
		// ProceedingJoinPoint => 원본 코드를 콜한다.
		
		return ret;
	}
}
