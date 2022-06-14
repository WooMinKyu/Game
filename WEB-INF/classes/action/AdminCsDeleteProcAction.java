package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.CsBoardDAO;
import member.MemberDTO;

public class AdminCsDeleteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int cs_num=Integer.parseInt(request.getParameter("cs_num"));
		String log_nick=request.getParameter("com_nick");
	    
	    HttpSession session=request.getSession();
	    //String log_nick=(String)session.getAttribute("log_nick");
	    
	    MemberDTO mdto=new MemberDTO();
	    mdto.getM_id();
	    CsBoardDAO dbPro=new CsBoardDAO();
	    int check=dbPro.AdminCsDeleteArticle(cs_num);
	    System.out.println("m_id Load >>>>>>>>>>");
	    
	    //공유
	    request.setAttribute("check", check);
	   
		
		return "/7.CsBoard/AdminCsDeleteProc.jsp";
	}

}
