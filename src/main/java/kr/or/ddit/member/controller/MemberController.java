package kr.or.ddit.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.member.model.MemberVo;
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
		
		return "member/memberList";
	}
	
	@RequestMapping("/get")
	public String getMember(String userid, Model model) {
		model.addAttribute("memberVo",memberService.getMember(userid));
		return "member/member";
	}
	
	@RequestMapping("/registform")
	public String insertMemberView() {
		return "member/insertMemberForm";
	}
	
	@RequestMapping("/regist")
	public String insertMember(MemberVo memberVo, Model model,
			@RequestPart("file")@RequestParam(name="file",required = false) MultipartFile file) {
		
		logger.debug("memberVo : {} ",memberVo);
		logger.debug("file : {}" , file.getOriginalFilename());
		
		List<MemberVo> memberList = memberService.selectAllMember();
		
		for (MemberVo member : memberList) {
			if(memberVo.getUserid().equals(member.getUserid())) { // 중복된 아이디 입력
				return "member/insertMemberForm"; // 실패했을 때
			}
		}
		
		if(file.getSize() > 0) {
			// 확장자 추출
			int index = file.getOriginalFilename().lastIndexOf(".");
			String extension = file.getOriginalFilename().substring(index + 1); // jpg
			
			// 프로필파일 vo 등록
			memberVo.setRealfilename(file.getOriginalFilename());
			String uploadFileName = UUID.randomUUID().toString() + "." + extension;
			memberVo.setFilename("d:\\upload\\" +uploadFileName);
			
			// 파일 업로드
			File uploadFile = new File("d:\\upload\\" + uploadFileName);
			try {
				file.transferTo(uploadFile); // 업로드하는 메서드
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			// 회원등록
			memberService.insertMember(memberVo);
			
		}
		return "redirect:/member/list";
	}
	
	@RequestMapping("/profile")
	public String viewProfile(String userid, HttpServletResponse response) throws IOException {
		
		MemberVo memberVo = memberService.getMember(userid);
		
		// 프로필 출력
		if(memberVo.getFilename() != null) {
			FileInputStream fis = new FileInputStream(memberVo.getFilename());
			ServletOutputStream sos = response.getOutputStream();
			
			byte[] buffer = new byte[512];
			
			while (fis.read(buffer) != -1) {
				sos.write(buffer);
			}
			
			fis.close();
			sos.flush();
			sos.close();
		}
		return null;
	}
	
	@RequestMapping("/profile/download")
	public String downloadProfile(HttpServletResponse response, String userid ) throws IOException {
		response.setContentType("image/png");
		
		MemberVo memberVo = memberService.getMember(userid);
		
		// response content-type 설정 (파일 다운로드를 위한 구문)
		response.setHeader("Content-Disposition", "attachment; filename=\"" + memberVo.getRealfilename() + "\"");
		response.setContentType("application/octet-stream");
		
		// 경로 확인 후 파일 입출력을 통해 응답생성
		// 파일 읽기
		// 응답 생성
		if(memberVo.getFilename() != null) {
			FileInputStream fis = new FileInputStream(memberVo.getFilename());
			ServletOutputStream sos = response.getOutputStream();
			
			byte[] buffer = new byte[512];
			
			while (fis.read(buffer) != -1) {
				sos.write(buffer);
			}
			
			fis.close();
			sos.flush();
			sos.close();
		}
		return null;
	}
	
	@RequestMapping("/updateForm")
	public String updateMemberView(String userid, Model model) {
		MemberVo memberVo = memberService.getMember(userid);
		
		model.addAttribute("memberVo", memberVo);
		return "member/updateMemberForm";
	}
	
	@RequestMapping("/update")
	public String updateMember(MemberVo memberVo,@RequestPart("file")@RequestParam(name = "file", required = false) MultipartFile file) {
		if(file == null) {
			memberService.updateMember(memberVo);
			return "member/member";
		}else {
			// 확장자 추출
			int index = file.getOriginalFilename().lastIndexOf(".");
			String extension = file.getOriginalFilename().substring(index + 1); // jpg
			
			// 프로필파일 vo 등록
			memberVo.setRealfilename(file.getOriginalFilename());
			String uploadFileName = UUID.randomUUID().toString() + "." + extension;
			memberVo.setFilename("d:\\upload\\" +uploadFileName);
			
			// 파일 업로드
			File uploadFile = new File("d:\\upload\\" + uploadFileName);
			try {
				file.transferTo(uploadFile); // 업로드하는 메서드
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			memberService.updateMember(memberVo);
			return "member/member";
		}
	}
}
