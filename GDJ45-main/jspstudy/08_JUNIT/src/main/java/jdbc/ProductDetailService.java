package jdbc;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDetailService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("product_no"));
		Long product_no = Long.parseLong(opt.orElse("0"));
		request.setAttribute("product", ProductDAO.getInstance().selectProductByNo(product_no));
		request.setAttribute("contextPath", request.getContextPath());
		return new ActionForward("product/detail.jsp", false);
		
	}

}
