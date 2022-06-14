package action;

import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.*;//BoardDAO
import java.util.*;//List

public class AdOnlyStoreListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

	String pageNum=request.getParameter("pageNum");	
	String search=request.getParameter("search");
	String searchtext=request.getParameter("searchtext");

	System.out.println("pageNum=>"+pageNum+",search=>"+search+",searchtext=>"+searchtext);
		
	int count=0;
	List articleList=null;
	 
	StoreDAO dbPro=new StoreDAO();
	count=dbPro.getArticleSearchCount(search,searchtext);
	System.out.println("게시글 count >>>"+count);

	Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
	 
	if (count > 0){
		System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
		articleList=dbPro.getBoardArticles(pgList.get("startRow"), pgList.get("pageSize"), search,searchtext);
		System.out.println("ListAction articleList=>"+articleList);
	}else {//count=0
		articleList=Collections.EMPTY_LIST; //비어있는 List객체반환
	}
	
	//2.처리한 결과를 공유(서버메모리에 저장)->이동할 페이지에 공유해서 사용(request)
		request.setAttribute("search", search);//${search} 검색어
		request.setAttribute("searchtext", searchtext);//검색어
		request.setAttribute("pgList", pgList);//페이징 처리 10개 정보
		request.setAttribute("articleList", articleList);//${articleList}
			
	//3.공유해서 이동할 수있도록 페이지를 지정
	return "/3.Store/ad_store_item.jsp";
	}
}




