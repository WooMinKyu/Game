package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.CsBoardDAO;
import boards.CsBoardDTO;

public class AdminCsUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
	    
		//1.content.jsp
		   int cs_num=Integer.parseInt(request.getParameter("cs_num"));
		   CsBoardDAO dbPro=new CsBoardDAO();
		   CsBoardDTO article=dbPro.AdminCsUpdateGetArticle(cs_num);//view counting X
		  
		//2.Server memory upload
		  request.setAttribute("article",article);
		
		return "/7.CsBoard/AdminCsUpdateForm.jsp";
	}

}
