package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.FreeBoardDAO;
import boards.FreeBoardDTO;

// /writePro.do
public class FreeWriteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
	    String m_id=(String)session.getAttribute("m_id");

	    FreeBoardDTO article=new FreeBoardDTO();
	    
	    //5개받아와서 진행
		article.setM_id(request.getParameter("com_m_id"));
		// article.setM_id(m_id);
		article.setCom_nick(request.getParameter("com_nick"));
		article.setCom_sub(request.getParameter("com_sub"));
		article.setCom_content(request.getParameter("com_content"));
		     

		FreeBoardDAO dbPro=new FreeBoardDAO();
		dbPro.FreeInsertArticle(article);

		return "/5.FreeBoard/FreeWriteProc.jsp";
	}
}
