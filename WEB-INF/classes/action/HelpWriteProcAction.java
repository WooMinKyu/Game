package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.HelpBoardDAO;
import boards.HelpBoardDTO;

// /writePro.do
public class HelpWriteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
	    String ad_id=(String)session.getAttribute("ad_id");

	    HelpBoardDTO article=new HelpBoardDTO();
	    
	    //5개받아와서 진행
		article.setAd_id(request.getParameter("help_ad_id"));
		// article.setM_id(m_id);
		article.setHelp_nick(request.getParameter("help_nick"));
		article.setHelp_sub(request.getParameter("help_sub"));
		article.setHelp_content(request.getParameter("help_content"));
		     

		HelpBoardDAO dbPro=new HelpBoardDAO();
		dbPro.HelpInsertArticle(article);

		return "/6.HelpBoard/HelpWriteProc.jsp";
	}
}
