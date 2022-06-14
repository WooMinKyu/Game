package action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.FreeBoardDAO;
import boards.RevBoardDAO;
import boards.RevBoardDTO;

//글쓰기->/writePro.do
public class RevWriteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
	     request.setCharacterEncoding("utf-8");
	     HttpSession session=request.getSession();
		 String m_id=(String)session.getAttribute("m_id");
		 String item_num=request.getParameter("item_num");
		 String rev_nick=request.getParameter("rev_nick");
		 String rev_content=request.getParameter("rev_content");
		 
		 
	     RevBoardDTO article=new RevBoardDTO();
	     
	     article.setM_id(request.getParameter("rev_m_id"));
	     article.setRev_nick(request.getParameter("rev_nick"));
	     article.setRev_content(request.getParameter("rev_content"));
	     article.setRev_content(request.getParameter("rev_wdate"));
	     

	     RevBoardDAO dbPro=new RevBoardDAO();
	     dbPro.RevInsertArticle(article, item_num);
		//list.jsp로 페이지 이동
		//3.공유->페이지 이동
		return "/3.Store/store_detail.jsp";// /list.do로 처리->/list.jsp
	}

}
