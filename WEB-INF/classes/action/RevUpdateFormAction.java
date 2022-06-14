package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boards.RevBoardDAO;
import boards.RevBoardDTO;

public class RevUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		// content.jsp >> updateForm
		int rev_num=Integer.parseInt(request.getParameter("rev_num"));
		String pageNum=request.getParameter("pageNum");
		System.out.println("FreeUpdateFormAction pageNum >>"+pageNum);
		RevBoardDAO fbd=new RevBoardDAO();
		RevBoardDTO article=fbd.RevUpdateGetArticle(rev_num); // FreeUpdateGetArticle
		
		// 서버의 메모리에 저장
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
		
		return "/8.RevBoard/RevUpdateForm.jsp";	
	}

}
