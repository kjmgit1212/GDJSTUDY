package ex01;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * javadoc
 * - 문서화되는 주석
 * - 자바 document가 됨
 */

/*
	Servlet
	
	1. 자바를 이용해서 웹 화면을 만든다.
	2. Servlet을 실행하면 웹 브라우저가 실행된다.
	3. URL 구성
	    프로토콜://호스트:포트번호/ContextPath/URLMapping
	    http://localhost:9090/SERVLET/HelloServlet
	4. Tomcat이 있어야 실행할 수 있다.
*/

// @WebServlet(URLMapping)
@WebServlet("/HelloServlet")

// 모든 Servlet은 HttpServlet을 상속 받는다.
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 1. 생성자
     *     1) 가장 먼저 호출된다.
     *     2) 생성자 호출 뒤 init() 메소드가 (있으면) 호출된다.
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("HelloServlet 생성자 호출");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 * 2. init() 메소드
	 *     1) 환경 설정 용도로 1번만 실행된다.
	 *     2) init() 메소드 호출 뒤 service() 메소드가 (있으면) 호출된다.
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init() 메소드 호출");
	}
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 * 3. service() 메소드
	 *     1) 실제 업무 처리를 할 수 있다.
	 *     2) 업무 처리를 위해서 필요한 것 2가지(요청과 응답)
	 *         (1) 요청(request)
	 *             ① 클라이언트(client, 사용자)가 서버(server) 측으로 요청한다.
	 *                클라이언트가 서버 측으로 데이터를 보내는 것도 요청이다.
	 *             ② HttpServletRequest request 매개변수가 요청을 처리하는 인스턴스이다.
	 *         (2) 응답(response)
	 *             ① 서버가 클라이언트에게 응답한다.
	 *             ② 서버가 클라이언트에게 데이터를 보내거나 완성된 화면을 보여준다.
	 *             ③ HttpServletResponse response 매개변수가 응답을 처리하는 인스턴스이다.
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service() 메소드 호출");
		
		// 요청 메소드를 확인해서 get 방식이면 doGet() 메소드 호출, post 방식이면 doPost() 메소드 호출
		if(request.getMethod().equalsIgnoreCase("get")) {
			doGet(request, response);
		} else if(request.getMethod().equalsIgnoreCase("post")) {
			doPost(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 4. doGet() 메소드
	 *     1) 요청 메소드가 get인 경우에 실행한다.
	 *     2) 요청 메소드가 get인 경우
	 *         (1) <form method="get">, <form>
	 *         (2) $.ajax({
	 *                 type: 'get',
	 *                 url: '/SERVLET/HelloServlet'
	 *             });
	 *         (3) <a href="http://localhost:9090/SERVLET/HelloServlet">
	 *         (4) location.href="http://localhost:9090/SERVLET/HelloServlet"
	 *     3) service() 메소드가 없으면 실제 업무 처리는 doGet() 메소드가 담당한다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// response는 실행화면을 만드는 인스턴스이다.
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 5. doPost() 메소드
	 *     1) 요청 메소드가 post인 경우에 실행한다.
	 *     2) 요청 메소드가 post인 경우
	 *         (1) <form method="post">
	 *         (2) $.ajax({
	 *                 type: 'post',
	 *                 url: '/SERVLET/HelloServlet'
	 *             });
	 *     3) 아무 일도 하지 않는다.
	 *     4) 업무 처리를 위해서 필요한 2개 정보(요청, 응답)를 모두 doGet() 메소드에 전달한다.
	 *        업무 처리는 실제로 doGet() 메소드에서 이뤄진다.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
