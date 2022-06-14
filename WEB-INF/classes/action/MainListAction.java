package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.MainDAO;

public class MainListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		/*
		 * request.getParameter("bestSeller"); request.getParameter("discount");
		 * request.getParameter("topSeller");
		 */

		
		MainDAO mdao=new MainDAO();
		List bestGameList=mdao.getBestArticles();

		List discountGameList=mdao.getDiscoutArticles();
		
		List newGameList=mdao.getNewArticles();

		
		request.setAttribute("bestGameList", bestGameList);
		request.setAttribute("discountGameList", discountGameList);
		request.setAttribute("newGameList", newGameList);
		
		return "/1.Main/main.jsp";
	}

}
