package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MemberListServlet.class);
	private MemberServiceI memberService;
       
	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MemberVo> memberList = memberService.selsectAllmember(); // memberList를 service에서 받아옴
		
		request.setAttribute("memberList", memberList); // request속성에 저장
		logger.debug("memberList.size : {} ", memberList.size()); // 로거로 확인
		
		request.getRequestDispatcher("/member/memberList.jsp").forward(request, response); // jsp로 포워딩
	}
}
