package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.CsBoardDAO;
import boards.CsBoardDTO;

public class AdminCsUpdateProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		CsBoardDTO article=new CsBoardDTO();

		HttpSession session=request.getSession();
	    
	    String pageNum=request.getParameter("pageNum");
	     
	    article.setCs_num(Integer.parseInt(request.getParameter("cs_num")));
	    article.setCs_sub(request.getParameter("cs_sub"));
	    article.setCs_content(request.getParameter("cs_content"));
	    
	    CsBoardDAO dbPro=new CsBoardDAO();
	    int check=dbPro.AdminCsUpdateArticle(article);
		request.setAttribute("check",check);
		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		//list.jsp
		
		return "/7.CsBoard/AdminCsUpdateProc.jsp";
	}

}
