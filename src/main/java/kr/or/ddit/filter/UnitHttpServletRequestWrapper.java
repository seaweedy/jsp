package kr.or.ddit.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UnitHttpServletRequestWrapper extends HttpServletRequestWrapper{
	
	private Map<String, String[]> parameterMap;
	
	public UnitHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		parameterMap = new HashMap<String, String[]>(request.getParameterMap());
	}

	@Override
	public String getParameter(String name) {
		
		//String[] val = new String[] {};
		
		String[] values = parameterMap.get(name);
		if(values != null && values.length >= 1) { // null이어도 길이가 존재할 수 있으므로 같이 check
			return values[0];
		}else {
			return null;
		}
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		// map객체의 key값을 Enumeration type으로 리턴
		return Collections.enumeration(parameterMap.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
		return parameterMap.get(name);
	}
	
	public void setUnit() {
		// 파라미터로 UNT_CD가 있는지 조사
		// 1. 존재하면
		//	특별한 작업 없음
		// 2. 없을 경우
		//	UNT_CD 파라미터로 DDIT라는 문자열 값을 파라미터로 추가
		if(parameterMap.get("UNT_CD")==null) {
			parameterMap.put("UNT_CD", new String[] {"DDIT"});
		}
		
	}
	

}
