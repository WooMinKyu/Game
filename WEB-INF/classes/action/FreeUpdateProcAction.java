package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.FreeBoardDAO;
import boards.FreeBoardDTO;


public class FreeUpdateProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		FreeBoardDTO article=new FreeBoardDTO();

		HttpSession session=request.getSession();
	    String m_id=(String)session.getAttribute("m_id");
	    
	    String pageNum=request.getParameter("pageNum");
		
	     
	    article.setCom_num(Integer.parseInt(request.getParameter("com_num")));
	    article.setCom_sub(request.getParameter("com_sub"));
	    article.setCom_content(request.getParameter("com_content"));
	    
	    FreeBoardDAO dbPro=new FreeBoardDAO();
	    int check=dbPro.FreeUpdateArticle(article, m_id);
		request.setAttribute("check",check);
		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		//list.jsp
		
		return "/5.FreeBoard/FreeUpdateProc.jsp";
	}

}
