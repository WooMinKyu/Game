package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

public class MemRegisterProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		//빈즈객체를 생성 -> 확인용
		MemberDTO regBean = new MemberDTO();
		System.out.println("regBean=>"+regBean);
		
		regBean.setM_id(request.getParameter("m_id"));
		regBean.setM_pw(request.getParameter("m_pw"));
		regBean.setM_hint(request.getParameter("m_hint"));
		regBean.setM_name(request.getParameter("m_name"));
		regBean.setM_nick(request.getParameter("m_nick"));
		regBean.setM_mobile(request.getParameter("m_mobile"));
		
		String m_birth=request.getParameter("m_birth1")+"/"
				+request.getParameter("m_birth2")+"/"+
				request.getParameter("m_birth3");
		regBean.setM_birth(m_birth);
		System.out.println("m_birth=>"+m_birth);
		
		MemberDAO mDAO = new MemberDAO();
		boolean flag = mDAO.memberInsert(regBean);
		System.out.println("웹상의 확인(flag)="+flag);
		
		request.setAttribute("flag", flag);
		return "/2.Login&Member/MemberInsert.jsp";
	}

}
