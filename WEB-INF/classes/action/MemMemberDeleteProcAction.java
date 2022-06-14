package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.LoginDAO;
import member.MemberDAO;
import member.MemberDTO;

public class MemMemberDeleteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String m_id = request.getParameter("m_id");
		String m_pw = request.getParameter("m_pw");
		String m_nick = request.getParameter("m_nick");
		String m_status = request.getParameter("m_status");
		
		System.out.println("id=>"+m_id);
		System.out.println("pw=>"+m_pw);
		System.out.println("status=>"+m_status);
		
		HttpSession session = request.getSession();
		//빈즈객체를 생성 -> 확인용
		MemberDTO regBean = new MemberDTO();
		System.out.println("regBean=>"+regBean);
		
		regBean.setM_id(request.getParameter("m_id"));
		regBean.setM_pw(request.getParameter("m_pw"));
		regBean.setM_nick(request.getParameter("m_nick"));
		regBean.setM_status(request.getParameter("m_status"));

		MemberDAO mDAO = new MemberDAO();
		boolean flag = mDAO.memberDelete(regBean);
		System.out.println("웹상의 확인(flag)="+flag);
		System.out.println("MemberDeleteProcAction의 m_id=>"+regBean.getM_id());//null
		
		request.setAttribute("flag", flag);
		
		return "/2.Login&Member/MemberDeleteProc.jsp";
	}

}
