package ex03;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
	1. 서블릿 실행
	    1) 톰캣이 실행된다.
	    2) 톰캣의 컨테이너에 서블릿이 담긴다.
	       같은 이름의 서블릿이 존재할 수 없다.
	2. URL
	    프로토콜://호스트:포트번호/컨텍스트패스/URL매핑
	3. 컨텍스트패스
	    1) 디폴트로 프로젝트명이 사용된다.
	    2) request.getContextPath()로 알아낼 수 있다.
	    3) 프로젝트의 속성(Properties)에서 확인/수정할 수 있다.
	       프로젝트 우클릭 - Properties - Web Project Settings에서 확인 및 수정
	4. URL매핑
	    1) 디폴트로 서블릿명이 사용된다.
	    2) URL매핑 수정하기
	        (1) JAVA annotation 이용하기
	            ① @WebServlet("/URL매핑")
	            ② @WebServlet({"/URL매핑", "/URL매핑"})
	        (2) XML 이용하기
	            web.xml을 열고 태그를 추가하기
	            <servlet>
	            	<servlet-name>OurServlet</servlet-name>
	            	<servlet-class>ex03.OurServlet</servlet-class>
	            </servlet>
	            <servlet-mapping>
	            	<servlet-name>OurServlet</servlet-name>
	            	<url-pattern>/URL매핑</url-pattern>
	            </servlet-mapping>
*/

@WebServlet("/OurServlet")
public class OurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
