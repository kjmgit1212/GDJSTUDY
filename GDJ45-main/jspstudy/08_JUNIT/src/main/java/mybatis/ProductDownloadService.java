package mybatis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDownloadService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String target = request.getParameter("target");  // 파일명
		String realPath = request.getServletContext().getRealPath("storage");  // 경로
		
		File file = new File(realPath, target);
		
		response.setHeader("Content-Disposition", "attachment; filename=" + target);
		response.setHeader("Content-Length", file.length() + "");
		response.setHeader("Content-Type", "application/x-msdownload");
		
		try {

			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));     // 서버에 저장된 파일 읽기
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());  // 응답(response)으로 보내기
			
			byte[] b = new byte[1024];  // 1KB 저장소
			int readByte = 0;           // 실제로 읽은 바이트 수
			while( (readByte = bis.read(b)) != -1 ) {
				bos.write(b, 0, readByte);
			}
		
			if(bos != null) bos.close();
			if(bis != null) bis.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;  // execute() 실행 결과가 null이면 페이지 이동이 없다.
		
	}

}
