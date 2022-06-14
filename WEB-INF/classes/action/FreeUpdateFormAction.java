package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.FreeBoardDAO;
import boards.FreeBoardDTO;

public class FreeUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
	    String m_id=(String)session.getAttribute("m_id");
	    
		//1.content.jsp
		   int com_num=Integer.parseInt(request.getParameter("com_num"));
		   FreeBoardDAO dbPro=new FreeBoardDAO();
		   FreeBoardDTO article=dbPro.FreeUpdateGetArticle(com_num);//view counting X
		  
		//2.Server memory upload
		  request.setAttribute("article",article);
		
		return "/5.FreeBoard/FreeUpdateForm.jsp";
	}
}
