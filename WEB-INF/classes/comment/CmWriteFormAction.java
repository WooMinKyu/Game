package comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import admin.AdminDAO;
import admin.AdminDTO;

public class CmWriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session=request.getSession();
		String ad_id=(String)session.getAttribute("ad_id");
		String cs_num=request.getParameter("cs_num");
		System.out.println("cs_num >>"+cs_num);
		
		AdminDAO mdao=new AdminDAO();
		AdminDTO mdto=mdao.getMember(ad_id);
		System.out.println("ad_id=>"+mdto.getAd_id());
		
		request.setAttribute("m_id", mdto.getAd_id());
		request.setAttribute("cs_num", cs_num);
		
		return "/7.CsBoard/CmWriteForm.jsp";
	}

}
