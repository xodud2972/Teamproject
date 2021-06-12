package market;

public class FileManager {
	// 확장자 추출
	public static String getExtend(String path, String token) {
		int lastIndex = path.lastIndexOf(token); //넘겨받은 문자열내의 가장 마지막 디렉토리구분자의 위치
		String filename = path.substring(lastIndex+1, path.length());
		String ext = filename.substring(filename.lastIndexOf(".")+1, filename.length());
		
		return ext;
	}

}
