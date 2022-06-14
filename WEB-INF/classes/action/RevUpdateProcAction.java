package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boards.FreeBoardDTO;
import boards.RevBoardDTO;

public class RevUpdateProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String pageNum=request.getParameter("pageNum");
		System.out.println("RevUpdateAction에서의 pageNum >>"+pageNum);
		RevBoardDTO article=new RevBoardDTO();
		
		article.setRev_num(Integer.parseInt(request.getParameter("rev_num")));
		article.setRev_nick(request.getParameter("rev_nick"));
		article.setRev_sub(request.getParameter("rev_sub"));
		article.setRev_content(request.getParameter("rev_content"));
		
		
		return "/8.RevBoard/RevUpdatePro.jsp";
	}

}