package comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import boards.CommentDAO;
import boards.CommentDTO;

// /writePro.do
public class CmWriteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session=request.getSession();
	    String ad_id=(String)session.getAttribute("ad_id");
	    int cs_num=Integer.parseInt(request.getParameter("cs_num"));
	    System.out.println("Final cs_num >>>"+cs_num);
		
	    request.setCharacterEncoding("utf-8");
	    CommentDTO article=new CommentDTO();
	    
	    //5개받아와서 진행
		article.setAd_id(request.getParameter("ad_id"));
		article.setCm_content(request.getParameter("cm_content"));
		article.setCs_num(cs_num);

		CommentDAO dbPro=new CommentDAO();
		dbPro.CmInsertArticle(article);

		return "/7.CsBoard/CmWriteProc.jsp";
	}
}
