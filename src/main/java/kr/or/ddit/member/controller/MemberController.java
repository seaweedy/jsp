package kr.or.ddit.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.model.MemberVoValidator;
import kr.or.ddit.member.service.MemberService;

@RequestMapping("/member")
@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Resource(name="MemberService")
	private MemberService memberService;
	
	@RequestMapping("/list")
	public String selectMemberPageList(Model model,
			@RequestParam(name="page", required = false, defaultValue = "1") int page,
			@RequestParam(name="pageSize", required = false, defaultValue = "10") int pageSize) {
		
		PageVo pageVo = new PageVo();
		
		logger.debug("page : {}", page);
		logger.debug("pageSize : {}", pageSize);
		
		pageVo.setPage(page);
		pageVo.setPageSize(pageSize);
		
		model.addAttribute("page",page);
		model.addAttribute("pageSize",pageSize);
		
		Map<String, Object> map = memberService.selectMemberPageList(pageVo);
		
		model.addAttribute("memberPageList", map.get("memberList"));
		model.addAttribute("pages", map.get("pages"));
		
//		return "tiles.memberList"; // definition name을 따라간다.
		return "tiles/member/memberListContent"; // definition name을 따라간다.
	}
	
	@RequestMapping("/listAjaxPage")
	public String listAjaxPage() {
		
		return "tiles/member/listAjaxPage";
	}
	
	// 페이지 요청(/list와 다르게 page, pageSize 파라미터가 반드시 존재한다는 가정으로 작성
	@RequestMapping("/listAjax")
	public String listAjax(PageVo pageVo, Model model) {
		logger.debug("pageVo : {}", pageVo);
		
		Map<String, Object> map = memberService.selectMemberPageList(pageVo);
		
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("pages", map.get("pages"));
		logger.debug("pages : {}", map.get("pages"));
		return "jsonView"; 
	}
	
	@RequestMapping("/listAjaxHTML")
	public String listAjaxHTML(PageVo pageVo, Model model) {
		logger.debug("pageVo : {}", pageVo);
		
		Map<String, Object> map = memberService.selectMemberPageList(pageVo);
		
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("pages", map.get("pages"));
		logger.debug("pages : {}", map.get("pages"));
		
		// 응답을 html ==> jsp로 생성
		return "member/listAjaxHTML"; 
	}
	
	@RequestMapping("/get")
	public String getMember(String userid, Model model) {
		model.addAttribute("memberVo",memberService.getMember(userid));
//		return "member/member";
		return "tiles/member/member";
	}
	
	@RequestMapping("/getAjax")
	public String getMemberAjax(String userid, Model model) {
		model.addAttribute("memberVo",memberService.getMember(userid));
//		return "member/member";
		return "tiles/member/AjaxMember";
	}
	
	@RequestMapping("/getHTML")
	public String getMemberHTML(String userid, Model model) {
		model.addAttribute("memberVo",memberService.getMember(userid));
//		return "member/member";
		return "member/memberHTML";
	}
	
	@RequestMapping("/registform")
	public String insertMemberView() {
		return "tiles/member/insertMemberFormContent";
	}
	
	@RequestMapping("/regist")
	public String insertMember(Model model,@Valid MemberVo memberVo,BindingResult br,@RequestParam(name="file",required = false) MultipartFile file) {
//	public String insertMember(Model model,@Valid JSRMemberVo memberVo,BindingResult br,@RequestParam(name="file",required = false) MultipartFile file) {
		
		new MemberVoValidator().validate(memberVo, br);
		
		// 검증을 통과하지 못했으므로 사용자 등록 화면으로 이동
		if(br.hasErrors()) {
			return "tiles/member/insertMemberFormContent";
		}
		
		logger.debug("memberVo : {} ",memberVo);
		logger.debug("file : {}" , file.getOriginalFilename());
		
		List<MemberVo> memberList = memberService.selectAllMember();
		
		for (MemberVo member : memberList) {
			if(memberVo.getUserid().equals(member.getUserid())) { // 중복된 아이디 입력
				return "tiles/member/insertMemberFormContent"; // 실패했을 때
			}
		}
		
		if(file.getSize() > 0) {
			// 확장자 추출
			int index = file.getOriginalFilename().lastIndexOf(".");
			String extension = file.getOriginalFilename().substring(index + 1); // jpg
			
			// 프로필파일 vo 등록
			memberVo.setRealfilename(file.getOriginalFilename());
			String uploadFileName = UUID.randomUUID().toString() + "." + extension;
			memberVo.setFilename("d:\\profile\\" +uploadFileName);
			
			// 파일 업로드
			File uploadFile = new File("d:\\profile\\" + uploadFileName);
			try {
				file.transferTo(uploadFile); // 업로드하는 메서드
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			// 회원등록
			memberService.insertMember(memberVo);
		}else {
			logger.debug("프로필 없이 등록: {},{}" , memberVo.getFilename(), memberVo.getRealfilename());
			memberService.insertMember(memberVo);
		}
		return "redirect:/member/list";
	}
	
//	@RequestMapping("/profile")
//	public String viewProfile(String userid, HttpServletResponse response) throws IOException {
//		
//		MemberVo memberVo = memberService.getMember(userid);
//		
//		// 프로필 출력
//		if(memberVo.getFilename() != null) {
//			FileInputStream fis = new FileInputStream(memberVo.getFilename());
//			ServletOutputStream sos = response.getOutputStream();
//			
//			byte[] buffer = new byte[512];
//			
//			while (fis.read(buffer) != -1) {
//				sos.write(buffer);
//			}
//			
//			fis.close();
//			sos.flush();
//			sos.close();
//		}
//		return null;
//	}
	
//	@RequestMapping("/profile/download")
//	public String downloadProfile(HttpServletResponse response, String userid ) throws IOException {
//		response.setContentType("image/png");
//		
//		MemberVo memberVo = memberService.getMember(userid);
//		
//		// response content-type 설정 (파일 다운로드를 위한 구문)
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + memberVo.getRealfilename() + "\"");
//		response.setContentType("application/octet-stream");
//		
//		// 경로 확인 후 파일 입출력을 통해 응답생성
//		// 파일 읽기
//		// 응답 생성
//		if(memberVo.getFilename() != null) {
//			FileInputStream fis = new FileInputStream(memberVo.getFilename());
//			ServletOutputStream sos = response.getOutputStream();
//			
//			byte[] buffer = new byte[512];
//			
//			while (fis.read(buffer) != -1) {
//				sos.write(buffer);
//			}
//			
//			fis.close();
//			sos.flush();
//			sos.close();
//		}
//		return null;
//	}
	
	@RequestMapping("/updateForm")
	public String updateMemberView(String userid, Model model) {
		MemberVo memberVo = memberService.getMember(userid);
		
		model.addAttribute("memberVo", memberVo);
		return "tiles/member/updateMemberFormContent";
	}
	
	@RequestMapping("/update")
	public String updateMember(MemberVo memberVo,
			@RequestParam(name = "file", required = false) MultipartFile file) {
		if(file.getSize() > 0) {
			// 확장자 추출
			int index = file.getOriginalFilename().lastIndexOf(".");
			String extension = file.getOriginalFilename().substring(index + 1); // jpg
			
			// 프로필파일 vo 등록
			memberVo.setRealfilename(file.getOriginalFilename());
			String uploadFileName = UUID.randomUUID().toString() + "." + extension;
			memberVo.setFilename("d:\\profile\\" +uploadFileName);
			
			// 파일 업로드
			File uploadFile = new File("d:\\profile\\" + uploadFileName);
			try {
				file.transferTo(uploadFile); // 업로드하는 메서드
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			memberService.updateMember(memberVo);
			return "tiles/member/member";
		}else {
			memberService.updateMember(memberVo);
			return "tiles/member/member";
		}
	}
}
