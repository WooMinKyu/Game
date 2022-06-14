package action;

//List
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boards.CsBoardDAO;
import payment.PayDTO;
import payment.PaymentDAO;

public class CsListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		// 1./list.jsp
		HttpSession session=request.getSession();
		String m_id=(String)session.getAttribute("m_id");	
	
		 String pageNum=request.getParameter("pageNum");	
		 
		 String search=request.getParameter("search");
		 String searchtext=request.getParameter("searchtext");
		 System.out.println("CsListAction");
		 System.out.println("pageNum=>"+pageNum+",search=>"+search+",searchtext=>"+searchtext);
			
		 int count=0;
		 List articleList=null;
		 
		 CsBoardDAO dbPro=new CsBoardDAO();
		 count=dbPro.CsGetArticleSearchCount(m_id,search,searchtext);
		 System.out.println("CsGetArticleSearchCount >>"+count);
		 
		 Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
	 
		  if (count > 0){
			  System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
			  articleList=dbPro.CsGetBoardArticles(m_id,pgList.get("startRow"),
					                                            pgList.get("pageSize"),
					                                            search,searchtext);
			  System.out.println("CsListAction articleList=>"+articleList);
		  }else {//count=0
			  articleList=Collections.EMPTY_LIST;
		  }
			request.setAttribute("search", search);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("pgList", pgList);
			request.setAttribute("articleList", articleList);
			
			//구매내역----------------------------------------------------------------------------------------
			
			PayDTO pd=new PayDTO();
			PaymentDAO pdao=new PaymentDAO();
			List payList=null;
			
			
			pageNum=request.getParameter("mpageNum");//일부러 가져오는 값은 없는 값으로 설정->그렇지 않으면 cs게시판 넘어갈때 2페이지부터는 상단구매내역표시X
			count=0;
			count=pdao.getMyPurCount(m_id);
			Hashtable<String,Integer> mpgList=pdao.pageList(pageNum, count);//pageNum이 null이면 1이 고정됨->그래서 페이지이동해도 문제없음
			//pgList명이 같아서 일어나는 문제(이것 때문에 구매내역이 없으면 cs게시판까지 출력이 안됨)
			if (count > 0){
				  payList=pdao.getMyPurchase(m_id,mpgList.get("startRow"),mpgList.get("endRow"));
				  System.out.println("MemMyPurchaseAction articleList=>"+payList);
			  }else {
				  payList=Collections.EMPTY_LIST;
			  }
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("payList", payList);
			request.setAttribute("mpgList", mpgList);
			request.setAttribute("count",count);
			
			return "/2.Login&Member/MyPage.jsp";
	}
}




