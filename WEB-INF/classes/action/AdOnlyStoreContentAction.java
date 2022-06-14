package action;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.AdminDAO;
import admin.AdminDTO;
import boards.RevBoardDAO;
import store.StoreDAO;
import store.StoreDTO;

// content.jsp에 바로 요청->메서드를 호출->처리결과->공유->jsp로 이동
// /content.do?num=3&pageNum=1
public class AdOnlyStoreContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//처리할 내용
		  //글상세보기=>게시판(상품의 정보를 자세히(SangDetail.jsp))
		  //list.jsp에서 링크,num,pageNum
			request.setCharacterEncoding("utf-8");			
			HttpSession session=request.getSession();
			String m_id=(String)session.getAttribute("ad_id");
			
		  String item_num=request.getParameter("item_num");//게시물번호
		  String pageNum=request.getParameter("pageNum");//페이지 번호
		  System.out.println("ContentAction의 pageNum=>"+pageNum+",itme_num=>"+item_num);
		 
	
		  StoreDAO dbPro=new StoreDAO();//메서드 호출목적
		  StoreDTO article=dbPro.getArticle(item_num);//조회수 증가,레코드 한개
		 
		  // Rev board
		  
		  String RevPageNum=request.getParameter("RevPageNum");	

		  String search=request.getParameter("search");//검색분야
		  String searchtext=request.getParameter("searchtext");
		  System.out.println("RevListAction의 매개변수 확인");
		  System.out.println("RevPageNum=>"+RevPageNum+",search=>"+search+",searchtext=>"+searchtext);
		  
		  AdminDAO adao=new AdminDAO();
		  AdminDTO adto=adao.getMember(m_id);
		  String ad_nick=adto.getAd_nick();
				
		  int count=0;//총레코드수
		  List articleList=null;//화면에 출력할 레코드를 저장할 변수
			 
		  RevBoardDAO rbdto=new RevBoardDAO();
		  count=rbdto.RevGetArticleCount(item_num);//sql구문에 따라 검색어가 달라진다.
		  System.out.println("현재 레코드수(count)=>"+count);
		  //1.화면에 출력할 페이지번호,2.출력할 레코드갯수
		  Hashtable<String,Integer> pgList=rbdto.RevPageList(RevPageNum, count);
			 
		  if (count > 0){
			  
			  	articleList=rbdto.RevGetBoards(item_num);
				System.out.println("RevBoard articleList=>"+articleList);
			  	}else {//count=0
			  		articleList=Collections.EMPTY_LIST;//비어있는 List객체반환
			  	}
			   	//2.처리한 결과를 공유(서버메모리에 저장)->이동할 페이지에 공유해서 사용(request)
				
		  
		  ///////////////////////////////////////////////////////////
		  
		  //////////////////////////RevWriteForm/////////////////////////////////
		  
			/*
			 * System.out.println("StoreContent.do의 매개변수 확인");
			 * 
			 * MemberDAO mdao=new MemberDAO(); MemberDTO mdto=mdao.getMember(m_id);
			 * System.out.println("m_id=>"+mdto.getM_id());
			 * System.out.println("nick=>"+mdto.getM_nick());
			 * 
			 * 
			 * request.setAttribute("m_id", mdto.getM_id());
			 * request.setAttribute("com_nick", mdto.getM_nick());
			 */
		  
		  
		  ///////////////////////////RevList////////////////////////////////

		  request.setAttribute("search", search);//${search} 검색어
		  request.setAttribute("searchtext", searchtext);//검색어
		  request.setAttribute("pgList", pgList);//페이징 처리 10개 정보
		  request.setAttribute("articleList", articleList);//${articleList}
		  
		  ///////////////////////////////////////////////////////////
		  request.setAttribute("item_num", item_num);//${num}
		  
		  request.setAttribute("ad_nick", ad_nick);//${num}
		  request.setAttribute("RevPageNum", RevPageNum);
		  request.setAttribute("pageNum", pageNum);
		  request.setAttribute("article", article);//${article}->각각의 필드분리
		  
		return "/3.Store/ad_store_detail.jsp";
	}

}
