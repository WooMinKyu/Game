package action;

//List
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDTO;
import member.MemberDAO;

public class AdMemberListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

	String pageNum=request.getParameter("pageNum");	
	String search=request.getParameter("search");
	String searchtext=request.getParameter("searchtext");

	System.out.println("pageNum=>"+pageNum+",search=>"+search+",searchtext=>"+searchtext);
		
	int count=0;
	List articleList=null;
	 
	MemberDAO dbPro=new MemberDAO();
	count=dbPro.getMemberSearchCount(search,searchtext);
	System.out.println("게시글 count >>>"+count);
	LoginDTO ldto=new LoginDTO();
	ldto.setM_status(request.getParameter("m_status"));

	Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
	 
	if (count > 0){
		articleList=dbPro.getAreaMemeber(pgList.get("startRow"), pgList.get("pageSize"), search,searchtext);
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
	return "/Admin/MemberList.jsp";
	}
}




