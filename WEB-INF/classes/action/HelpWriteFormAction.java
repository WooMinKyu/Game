package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.AdminDAO;
import admin.AdminDTO;

public class HelpWriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String ad_id=(String)session.getAttribute("ad_id");
		
		AdminDAO mdao=new AdminDAO();
		AdminDTO mdto=mdao.getMember(ad_id);
		System.out.println("m_id=>"+mdto.getAd_id());
		System.out.println("nick=>"+mdto.getAd_nick());
		
		request.setAttribute("ad_id", mdto.getAd_id());
		request.setAttribute("help_nick", mdto.getAd_nick());
		
		return "/6.HelpBoard/HelpWriteForm.jsp";
	}

}
