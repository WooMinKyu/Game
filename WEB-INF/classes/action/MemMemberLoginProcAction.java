package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.LoginDAO;
import member.MemberDAO;
import member.MemberDTO;

public class MemMemberLoginProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String m_id = request.getParameter("m_id");
		String m_pw = request.getParameter("m_pw");
		String m_status = request.getParameter("m_status");
		
		System.out.println("memberupdate 체크의 id=>"+m_id);
		System.out.println("pw=>"+m_pw);
		System.out.println("status=>"+m_status);
		
		LoginDAO LDAO = new LoginDAO();
		HttpSession session = request.getSession();
		
		int loginCheck = LDAO.loginCheck(m_id,m_pw);
		
		if(loginCheck == -1) {
			System.out.println("이용이 제한된 계정입니다");
			return "/2.Login&Member/LogError.jsp";
		} else if(loginCheck == -2) {
			System.out.println("이미 탈퇴한 계정입니다");
			return "/2.Login&Member/LogError.jsp";
		} else if(loginCheck == 0) {
			System.out.println("아이디 또는 비밀번호를 다시 확인하세요");
			return "/2.Login&Member/LogError.jsp";
		}
		
		session.getAttribute(m_id);
		session.setAttribute("m_id",m_id);
        
		return "/1.Main/index.jsp";
	}

}
