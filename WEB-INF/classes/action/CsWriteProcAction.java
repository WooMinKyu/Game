package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.CsBoardDAO;
import boards.CsBoardDTO;

// /writePro.do
public class CsWriteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session=request.getSession();
	    String m_id=(String)session.getAttribute("m_id");
		
	    request.setCharacterEncoding("utf-8");
	    CsBoardDTO article=new CsBoardDTO();
	    
	    //5개받아와서 진행
		article.setM_id(request.getParameter("m_id"));
		// article.setM_id(m_id);
		article.setCs_sub(request.getParameter("cs_sub"));
		article.setCs_content(request.getParameter("cs_content"));
		     

		CsBoardDAO dbPro=new CsBoardDAO();
		dbPro.CsInsertArticle(article);

		return "/7.CsBoard/CsWriteProc.jsp";
	}
}
