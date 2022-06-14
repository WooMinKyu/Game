package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;

public class RevWriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String m_id=(String)session.getAttribute("m_id");
		
		
		MemberDAO mdao=new MemberDAO();
		MemberDTO mdto=mdao.getMember(m_id);
		System.out.println("m_id=>"+mdto.getM_id());
		System.out.println("nick=>"+mdto.getM_nick());
		
		
		request.setAttribute("m_id", mdto.getM_id());
		request.setAttribute("com_nick", mdto.getM_nick());
		
		return "/3.Store/store_detail.jsp";
	}

}
