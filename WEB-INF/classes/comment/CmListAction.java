package comment;

//List
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import boards.CsBoardDAO;
import payment.PayDTO;
import payment.PaymentDAO;

public class CmListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

	// 1./list.jsp
	HttpSession session=request.getSession();
	String ad_id=(String)session.getAttribute("ad_id");	
	
	 String pageNum=request.getParameter("pageNum");	
	 
	 String search=request.getParameter("search");
	 String searchtext=request.getParameter("searchtext");
	 System.out.println("CsListAction");
	 System.out.println("pageNum=>"+pageNum+",search=>"+search+",searchtext=>"+searchtext);
		
	 int count=0;
	 List articleList=null;
	 
	 CsBoardDAO dbPro=new CsBoardDAO();
	 count=dbPro.AdGetArticleSearchCount(ad_id,search,searchtext);
	 System.out.println("AdGetArticleSearchCount >>"+count);
	 
	 Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
	 
	  if (count > 0){
		  System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
		  articleList=dbPro.AdGetBoardArticles(ad_id,pgList.get("startRow"), pgList.get("pageSize"), search,searchtext);
		  System.out.println("CsListAction articleList=>"+articleList);
	  }else {//count=0
		  articleList=Collections.EMPTY_LIST;
	  }
		request.setAttribute("search", search);
		request.setAttribute("searchtext", searchtext);
		request.setAttribute("pgList", pgList);
		request.setAttribute("articleList", articleList);
		
		return "/7.CsBoard/CsList.jsp";
	}
}




