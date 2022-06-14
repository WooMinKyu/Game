package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.AdminDTO;
import boards.HelpBoardDAO;

public class HelpDeleteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int help_num=Integer.parseInt(request.getParameter("help_num"));
		String log_nick=request.getParameter("help_nick");
	    
	    HttpSession session=request.getSession();
	    String ad_id=(String)session.getAttribute("ad_id");
	    //String log_nick=(String)session.getAttribute("log_nick");
	    
	    AdminDTO mdto=new AdminDTO();
	    mdto.getAd_id();
	    HelpBoardDAO dbPro=new HelpBoardDAO();
	    //int check=dbPro.FreeDeleteArticle(com_num, m_id);
	    int check=dbPro.HelpDeleteArticle(help_num, ad_id);
	    
	    //공유
	    request.setAttribute("check", check);
	    request.setAttribute("ad_id", ad_id);
		
		return "/6.HelpBoard/HelpDeleteProc.jsp";
	}
}
