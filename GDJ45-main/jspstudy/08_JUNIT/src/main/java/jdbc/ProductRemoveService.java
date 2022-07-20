package jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductRemoveService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터 product_no
		
		Long product_no = 0L;
		String strNo = request.getParameter("product_no");
		if(strNo == null || strNo.isEmpty()) {
			product_no = 0L;
		} else {
			product_no = Long.parseLong(strNo);
		}
		
		int res = ProductDAO.getInstance().deleteProduct(product_no);
		
		/* 삭제 성공 메시지 가지고 이동 */
		try {
			if(res > 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('제품 정보가 삭제되었습니다.')");
				out.println("location.href='/JUNIT/list.do'");
				out.println("</script>");
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		/* 삭제 성공 메시지 없이 이동 */
		/*
		ActionForward af = null;
		if(res > 0) {
			af = new ActionForward("/JUNIT/list.do", true);
		}
		return af;
		*/
		
	}

}
