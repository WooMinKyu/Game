package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.*;

public class StoreDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String pageNum=request.getParameter("pageNum");
	    String item_num=request.getParameter("item_num");
	    System.out.println("deletePro.do의 num=>"
	                +item_num+",pageNum=>"+pageNum);
	    
	    StoreDAO dbPro=new StoreDAO();
	    int check=dbPro.deleteArticle(item_num);
	    
	    //공유
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("check", check);
		
		return "/3.Store/deletePro.jsp";
	}
}
