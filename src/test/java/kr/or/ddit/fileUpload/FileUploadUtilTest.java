package kr.or.ddit.fileUpload;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadUtilTest {
private static final Logger logger = LoggerFactory.getLogger(FileUploadUtilTest.class);
	@Test
	public void FileUploadUtiltest() {
		/***Given***/
		String contentDisposition = "form-data; name=\"img\"; filename=\"sally.png\"";

		/***When***/
		String fileName = FileUploadUtil.getFileName(contentDisposition);
		

		/***Then***/
		assertEquals("sally.png", fileName);
	}

}
