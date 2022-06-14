package action;

//List
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boards.RevBoardDAO;

//요청명령어에 해당되는 명령어 처리클래스=액션클래스=컨트롤러클래스
public class RevListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum=request.getParameter("pageNum");	

		String search=request.getParameter("search");//검색분야
		String searchtext=request.getParameter("searchtext");
		System.out.println("RevListAction의 매개변수 확인");
		System.out.println("pageNum=>"+pageNum+",search=>"+search+",searchtext=>"+searchtext);
			
		int count=0;//총레코드수
		List articleList=null;//화면에 출력할 레코드를 저장할 변수
		 
		RevBoardDAO dbPro=new RevBoardDAO();
		count=dbPro.RevGetArticleSearchCount(search,searchtext);//sql구문에 따라 검색어가 달라진다.
		System.out.println("현재 레코드수(count)=>"+count);
		//1.화면에 출력할 페이지번호,2.출력할 레코드갯수
		Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
		 
		if (count > 0){
			articleList=dbPro.RevGetBoardArticles(pgList.get("startRow"),//첫번째레코드번호
			pgList.get("pageSize"),search,searchtext);//검색분야,검색어
			System.out.println("ListAction의 articleList=>"+articleList);
		  	}else {//count=0
		  		articleList=Collections.EMPTY_LIST;//비어있는 List객체반환
		  	}
		   	//2.처리한 결과를 공유(서버메모리에 저장)->이동할 페이지에 공유해서 사용(request)
			request.setAttribute("search", search);//${search} 검색어
			request.setAttribute("searchtext", searchtext);//검색어
			request.setAttribute("pgList", pgList);//페이징 처리 10개 정보
			request.setAttribute("articleList", articleList);//${articleList}
			
			//3.공유해서 이동할 수있도록 페이지를 지정
		return "/3.Store/store_detail.jsp";//컨트롤러가 이동시키면서 공유시켜준다.
	}
}




