package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.HelpBoardDAO;
import boards.HelpBoardDTO;


public class HelpUpdateProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HelpBoardDTO article=new HelpBoardDTO();

		HttpSession session=request.getSession();
	    String ad_id=(String)session.getAttribute("ad_id");
	    
	    String pageNum=request.getParameter("pageNum");
		
	     
	    article.setHelp_num(Integer.parseInt(request.getParameter("help_num")));
	    article.setHelp_sub(request.getParameter("help_sub"));
	    article.setHelp_content(request.getParameter("help_content"));
	    
	    HelpBoardDAO dbPro=new HelpBoardDAO();
	    int check=dbPro.HelpUpdateArticle(article, ad_id);
		request.setAttribute("check",check);
		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		//list.jsp
		
		return "/6.HelpBoard/HelpUpdateProc.jsp";
	}

}
