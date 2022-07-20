package mybatis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductListService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("products", ProductDAO.getInstance().selectProductList());
		return new ActionForward("product2/list.jsp", false);
	}

}
