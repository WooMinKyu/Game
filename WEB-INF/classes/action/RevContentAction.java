package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boards.RevBoardDAO;
import boards.RevBoardDTO;

// content.jsp에 바로 요청->메서드를 호출->처리결과->공유->jsp로 이동
// /content.do?num=3&pageNum=1
public class RevContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		//처리할 내용
		//글상세보기=>게시판(상품의 정보를 자세히(SangDetail.jsp))
		//list.jsp에서 링크,num,pageNum
		int rev_num=Integer.parseInt(request.getParameter("rev_num"));//게시물번호
		String pageNum=request.getParameter("pageNum");//페이지 번호
		System.out.println("FreeContentAction의 pageNum=>"+pageNum+",rev_num=>"+rev_num);
		 
	
		RevBoardDAO dbPro=new RevBoardDAO();//메서드 호출목적
		RevBoardDTO article=dbPro.RevGetArticle(rev_num);//조회수 증가,레코드 한개
		
		System.out.println("FreeContent.do의 매개변수 확인");
		
		//2.처리결과를 서버의 메모리에 저장->request->jsp에 ${키명}
		request.setAttribute("rev_num", rev_num);//${num}
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);//${article}->각각의 필드분리
		  
		return "/8.RevBoard/RevContent.jsp";
	}

}
