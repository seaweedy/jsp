package kr.or.ddit.member.web;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;

@Controller
public class ProfileController {
	@Resource(name="MemberService")
	private MemberService memberService;
	
	@RequestMapping("/profileImgView")
	public String profile(String userid,Model model) throws IOException {
		// 응답으로 생성하려고 하는 것 : 이미지 파일을 읽어서 ouput stream 객체에 쓰는 것 => 이미지로 응답을 만들어냄
		
		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("filepath",memberVo.getFilename());
		
		return "profileImgView";
		
	}
	
	@RequestMapping("/profileImg")
	public void profile(String userid, HttpServletResponse response ) throws IOException {
		// response content-type 설정
		response.setContentType("image/png");

		// db에서 사용자 filename확인
		MemberVo memberVo = memberService.getMember(userid);

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
	}
	
	@RequestMapping("profileDownloadView")
	public String profileDownlaod(String userid, Model model) {
		// 응답으로 생성하려고 하는 것 : 이미지 파일을 읽어서 ouput stream 객체에 쓰는 것 => 이미지로 응답을 만들어냄

		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("filename", memberVo.getFilename());
		model.addAttribute("realfilename", memberVo.getRealfilename());

		return "profileDownloadView";

	}
	
	@RequestMapping("/profileDownload")
	public void profileDownlaod(String userid, HttpServletResponse response) throws IOException {
		MemberVo memberVo = memberService.getMember(userid);

		// response content-type 설정 (파일 다운로드를 위한 구문)
		response.setHeader("Content-Disposition", "attachment; filename=\"" + memberVo.getRealfilename() + "\"");
		response.setContentType("application/octet-stream");

		// 경로 확인 후 파일 입출력을 통해 응답생성
		// 파일 읽기
		// 응답 생성
		if (memberVo.getFilename() != null) {
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
	}
}
