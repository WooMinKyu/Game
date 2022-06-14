package comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import admin.AdminDTO;
import boards.CommentDAO;

public class CmDeleteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String ad_id=(String)session.getAttribute("ad_id");
		
		int cm_num=Integer.parseInt(request.getParameter("cm_num"));
		System.out.println("cm_num");
	    
	    AdminDTO adto=new AdminDTO();
	    adto.getAd_id();
	    CommentDAO dbPro=new CommentDAO();
	    int check=dbPro.CmDeleteArticle(cm_num, ad_id);
	    System.out.println("ad_id Load >>>>>>>>>>"+ad_id);
	    
	    
	    //공유
	    request.setAttribute("check", check);
	    request.setAttribute("ad_id", ad_id);
		
		return "/7.CsBoard/CmDeleteProc.jsp";
	}
}
