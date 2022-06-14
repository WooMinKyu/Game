package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//추가
import store.*;//BoardDAO,BoardDTO 필요
import java.sql.Timestamp;//추가할 부분(시간)

// /writePro.do
public class StoreUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//한글처리
		     request.setCharacterEncoding("utf-8");
		     //추가(수정된 게시물로 이동시키기위해서 필요)
		     String pageNum=request.getParameter("pageNum");
		     System.out.println("UpdateProAction에서의 pageNum=>"+pageNum);//0
		     //--------------------------------------------------------
		     HttpSession session=request.getSession();
		     String ad_id=(String)session.getAttribute("ad_id");
		     StoreDTO article=new StoreDTO();	     
		     
		     
		    article.setItem_price(request.getParameter("item_price"));//상품가격
		    article.setItem_num(request.getParameter("item_num"));//장르번호  
		    article.setGen_num(request.getParameter("gen_num"));//장르번호  
		    article.setGrd_num(request.getParameter("grd_num"));//등급번호  
  		  
			  article.setAd_id(request.getParameter("ad_id"));//관리자아이디
			  article.setItem_name(request.getParameter("item_name"));//상품이름
			  article.setItem_img(request.getParameter("item_img"));//상품이미지
			  article.setItem_thum(request.getParameter("item_thum"));//상품썸네일
			  article.setItem_content(request.getParameter("item_content"));//상품내용
			  article.setItem_gen(request.getParameter("item_gen"));//상품장르
			  article.setItem_grd(request.getParameter("item_grd"));//상품등급
			  article.setItem_dev(request.getParameter("item_dev"));//개발사
			  article.setItem_pub(request.getParameter("item_pub"));//퍼블리셔
			  article.setItem_pf(request.getParameter("item_pf"));//플랫폼
			  article.setItem_ft(request.getParameter("item_ft"));//기능
			  
			  article.setItem_rels(request.getParameter("item_rels"));//상품출시일

		     
		    StoreDAO dbPro=new StoreDAO();
		    int check=dbPro.updateArticle(article);
		   
		    //2개의 공유값이 필요
		    request.setAttribute("ad_id", article.getAd_id());
		    request.setAttribute("pageNum",pageNum);//수정할 페이지로 이동
		    request.setAttribute("check",check);//데이터 수정성공유무
		    
		return "/3.Store/updatePro.jsp";
	}
}
