package action;

//List
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boards.FreeBoardDAO;

public class FreeListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		request.setCharacterEncoding("utf-8");
		// 1./list.jsp
		String pageNum=request.getParameter("pageNum");	
		 
		String search=request.getParameter("search");
		String searchtext=request.getParameter("searchtext");
		System.out.println("FreeListAction");
		System.out.println("pageNum=>"+pageNum+",search=>"+search+",searchtext=>"+searchtext);
			
		int count=0;
		List articleList=null;
		 
		FreeBoardDAO dbPro=new FreeBoardDAO();
		count=dbPro.FreeGetArticleSearchCount(search,searchtext);
		System.out.println("FreeGetArticleSearchCount >>"+count);
		 
		Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
		 
		if (count > 0){
			System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
			articleList=dbPro.FreeGetBoardArticles(pgList.get("startRow"),pgList.get("pageSize"),search,searchtext);
			System.out.println("ListAction articleList=>"+articleList);
		}else {//count=0
			articleList=Collections.EMPTY_LIST;
		}
		   
			request.setAttribute("search", search);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("pgList", pgList);
			request.setAttribute("articleList", articleList);
		
		
		return "/5.FreeBoard/FreeList.jsp";
	}
}




