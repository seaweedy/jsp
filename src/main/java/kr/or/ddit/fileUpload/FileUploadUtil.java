package kr.or.ddit.fileUpload;

public class FileUploadUtil {
	
	//FileUploadUtilTest 만들어서 테스팅
	public static String getFileName(String contentDisPosition) {
		String[] values = contentDisPosition.split("; ");
		String[] value = null;
		String result = "";
		for(int i = 0 ; i < values.length; i ++) {
			value = values[i].split("=");
			if(value[0].equals("filename")) {
				return value[1].replaceAll("\"", "");
			}
		}
		return result;
		
		
//		String[] attrs = contentDisPosition.split("; ");
//		for(String attr : attrs) {
//			String[] attrArray = attr.split("=");
//			if(attrArray[0].equals("filename")) {
//				return attrArray[1].replaceAll("\"","");
//			}
//		}
//		return  "";
	}
}
