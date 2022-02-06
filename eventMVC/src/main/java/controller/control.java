package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Member;
import service.EventService;
import service.Service;

/**
 * Servlet implementation class control
 */
@WebServlet("/control")
public class control extends HttpServlet { // HttpServlet를 상속 받는
	private static final long serialVersionUID = 1L;
	/**
	 * 직렬화는 단기간 혹은 장기간 데이터를 보존하는 용도로 나눌 수 있는데, 단기 저장은 예컨대 네트워크로 전송하는 경우이고 장기 저장은 게임의
	 * 세이브 파일과 같은 용도입니다. 기본적으로 직렬화를 해제하기 위해서는 직렬화 시점의 클래스와 해제 시점의 클래스가 일치해야하는데 이는 저장
	 * 기간이 길어질수록 문제가 됩니다. serialVersionUID를 선언하면 해당 변수의 값이 같으면 실제 클래스 내용이 바뀌어도 동일한
	 * 클래스로 간주하고 직렬화 해제를 시도합니다. 예컨대 게임 세이브 파일을 직렬화를 통해 구현했는데, 다음 버전에서 해당 클래스에 필드가 하나
	 * 추가되었다면 만일 명시적으로 serialVersionUID를 선언하지 않은 경우 업그레이드 후 이전 버전의 세이브 파일을 불러오지 못하게
	 * 됩니다.
	 * 
	 * serialVersionUID는 이러한 문제를 해결하기 위해 주로 사용합니다.UID, serialVerionUID 라는 이름의 필드로, 이
	 * 번호를 명시하지 않으면 시스템이 런타임에 암호해시 함수(SHA-1)를 적용해 자동으로 클래스 안에 생성해 넣습니다. 이 후 클래스가
	 * 변경되면 직렬 버전 UID 값도 변경되면서, 자동 생성되는 값에 의존하면 쉽게 호환성이 깨져버려 런타임에
	 * InvalidClassException 이 발생합니다.
	 */

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public control() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		String resultView = "event/";
		Service service = new EventService();
		if (type.equals("insert")) {
			Member m = new Member(id, email, pwd);
			service.insert(m);
			resultView = "control ?type=list";
		} else if (type.equals("list")) {
			ArrayList<Member> data = service.list();
			request.setAttribute("data", data);
			resultView += "list.jsp";
		} else if (type.equals("editForm")) {
			int num = Integer.parseInt(request.getParameter("num"));
			Member m = service.getMember(num);
			request.setAttribute("member", m);
			resultView = "edit.jsp";
		} else if (type.equals("edit")) {
			int num = Integer.parseInt(request.getParameter("num"));
			Member m = new Member(num, id, email, pwd);
			service.edit(m);
			resultView = "control?type=list";
		} else if (type.equals("delete")) {
			int num = Integer.parseInt(request.getParameter("num"));
			service.delete(num);
			resultView = "control?type=list";
		}
		RequestDispatcher dis = request.getRequestDispatcher(resultView);
		dis.forward(request, response);

	}

}
