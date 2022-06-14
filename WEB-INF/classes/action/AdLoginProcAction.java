package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.AdminDAO;
import login.LoginDAO;

public class AdLoginProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String ad_id = request.getParameter("ad_id");
		String ad_pw = request.getParameter("ad_pw");
		
		System.out.println("id"+ad_id);
		System.out.println("pw"+ad_pw);
		AdminDAO AdDAO = new AdminDAO();
		HttpSession session = request.getSession();
		
		boolean loginCheck = AdDAO.loginCheck(ad_id, ad_pw);
		
		if(loginCheck==false) {
			return "/2.Login&Member/AdLogError.jsp";
		}
		
		session.setAttribute("ad_id", ad_id);
		session.setAttribute("ad_pw", ad_pw);
		
		return "/1.Main/index.jsp";
	}

}
