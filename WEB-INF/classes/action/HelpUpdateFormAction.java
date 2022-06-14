package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.HelpBoardDAO;
import boards.HelpBoardDTO;

public class HelpUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
	    String ad_id=(String)session.getAttribute("ad_id");
	    
		//1.content.jsp
		   int help_num=Integer.parseInt(request.getParameter("help_num"));
		   HelpBoardDAO dbPro=new HelpBoardDAO();
		   HelpBoardDTO article=dbPro.HelpUpdateGetArticle(help_num);//view counting X
		  
		//2.Server memory upload
		  request.setAttribute("article",article);
		
		return "/6.HelpBoard/HelpUpdateForm.jsp";
	}
}
