package action;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boards.CommentDAO;
import boards.CsBoardDAO;
import boards.CsBoardDTO;

// content.jsp에 바로 요청->메서드를 호출->처리결과->공유->jsp로 이동
// /content.do?num=3&pageNum=1
public class CsContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		int cs_num=Integer.parseInt(request.getParameter("cs_num"));//게시물번호
		String pageNum=request.getParameter("pageNum");//페이지 번호
		System.out.println("CsContentAction의 pageNum=>"+pageNum+",cs_num=>"+cs_num);
		 
	
		CsBoardDAO dbPro=new CsBoardDAO();//메서드 호출목적
		CsBoardDTO article=dbPro.CsGetArticle(cs_num);//조회수 증가,레코드 한개
		System.out.println("CsContent.do의 매개변수 확인");
		  
		  // ///////////////////////////////////////////////////////////////////////////////////
		  // Comment List
		  
		request.setCharacterEncoding("utf-8");
			 
		String search=request.getParameter("search");
		String searchtext=request.getParameter("searchtext");
		System.out.println("pageNum=>"+pageNum+",search=>"+search+",searchtext=>"+searchtext);
				
		int count=0;
		List articleList=null;
			 
		CommentDAO codao=new CommentDAO();
		count=codao.CmGetArticleSearchCount(cs_num);
		System.out.println("CmGetArticleSearchCount >>"+count);
			 
		Hashtable<String,Integer> pgList=codao.pageList(pageNum, count);
			 
		if (count > 0){
			System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
			articleList=codao.CmGetBoardArticles(cs_num, pgList.get("startRow"),pgList.get("pageSize"),search,searchtext);
			System.out.println("ListAction articleList=>"+articleList);
		}else {//count=0
			articleList=Collections.EMPTY_LIST;
		}
			   
		request.setAttribute("search", search);
		request.setAttribute("searchtext", searchtext);
		request.setAttribute("pgList", pgList);
		request.setAttribute("articleList", articleList);

				
		  // ///////////////////////////////////////////////////////////////////////////////////
		
		  //2.처리결과를 서버의 메모리에 저장->request->jsp에 ${키명}
		  request.setAttribute("cs_num", cs_num);//${num}
		  request.setAttribute("pageNum", pageNum);
		  request.setAttribute("article", article);//${article}->각각의 필드분리
		  
		return "/7.CsBoard/CsContent.jsp";
	}

}
