package kr.or.ddit.mvc.view;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

public class ExcelDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<String> header = (List<String>)model.get("header");
		List<Map<String, String>> data = (List<Map<String, String>>)model.get("data");
		
		// excel 파일 contentType : application/vnd.ms-excel; UTF-8
		response.setContentType("application/vnd.ms-excel; UTF-8");
		
		// 첨부파일을 암시
		response.setHeader("Content-Disposition", "attachment; filename=test.xlsx");
		
		// poi라이브러리를 이용하여 excel 파일 생성
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("lineFriends");
		
		int rownum = 0;
		int column = 0;
		Row row = sheet.createRow(rownum++);
		
		// 헤더 설정
		for (String h : header) {
			row.createCell(column++).setCellValue(h);
		}
		
		// 데이터 설정
		for (Map<String, String> map: data) {
			row = sheet.createRow(rownum++);
			
			column = 0; // 행마다 index 초기화 
			// 컬럼에 값 입력
			row.createCell(column++).setCellValue(map.get("userid"));
			row.createCell(column++).setCellValue(map.get("usernm"));
		}
		
		// 파일에 입력
		OutputStream os = response.getOutputStream();
		workbook.write(os); // 기존의 방식보다 간단하다
		
		os.flush();
		os.close();
		
		workbook.close();
		
	}

}
