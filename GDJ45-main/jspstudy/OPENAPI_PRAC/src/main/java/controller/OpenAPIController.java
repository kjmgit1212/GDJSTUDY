package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.Covid19InfStateService;
import service.GuroPointFocInfoService;
import service.OpenAPIService;
import service.SearchService;
import service.TourStnInfoService;

@WebServlet("*.do")
public class OpenAPIController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OpenAPIController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		OpenAPIService service = null;
		ActionForward af = null;
		
		switch(command) {
		case "searchPage.do":
			af = new ActionForward("search/search.jsp", false);
			break;
		case "guroPointFocInfoPage.do":
			af = new ActionForward("guro/guro.jsp", false);
			break;
		case "covid19InfStatePage.do":
			af = new ActionForward("covid/covid.jsp", false);
			break;
		case "tourStnInfoPage.do":
			af = new ActionForward("tour/tour.jsp", false);
			break;
		case "search.do":
			service = new SearchService();
			break;
		case "guroPointFocInfoSvc.do":
			service = new GuroPointFocInfoService();
			break;
		case "covid19InfStateSvc.do":
			service = new Covid19InfStateService();
			break;
		case "tourStnInfoSvc.do":
			service = new TourStnInfoService();
			break;
			
		}
		if(service != null) {
			service.execute(request, response);
		}
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
