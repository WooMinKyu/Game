package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.AdminDTO;
//�߰�
import store.*;//BoardDAO,BoardDTO �ʿ�
import java.sql.Timestamp;//�߰��� �κ�(�ð�)
import java.text.SimpleDateFormat;
import java.util.Date;

// /writePro.do
public class StoreWriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		request.setCharacterEncoding("utf-8");
		StoreDTO article=new StoreDTO();	
		HttpSession session=request.getSession();
		String ad_id=(String)session.getAttribute("ad_id");
		System.out.println("ad id >>"+ad_id);
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String rels = sdf.format(now);
		
		     
		article.setItem_price(request.getParameter("item_price"));
		article.setGen_num(request.getParameter("gen_num"));
		article.setGrd_num(request.getParameter("grd_num"));
		article.setAd_id(ad_id);
		article.setItem_name(request.getParameter("item_name"));
		article.setItem_img(request.getParameter("item_img"));
		article.setItem_thum(request.getParameter("item_thum"));
		article.setItem_content(request.getParameter("item_content"));
		article.setItem_gen(request.getParameter("item_gen"));
		article.setItem_grd(request.getParameter("item_grd"));
		article.setItem_dev(request.getParameter("item_dev"));
		article.setItem_pub(request.getParameter("item_pub"));
		article.setItem_pf(request.getParameter("item_pf"));
		article.setItem_ft(request.getParameter("item_ft"));
		article.setItem_rels(rels);

		StoreDAO dbPro=new StoreDAO();
		dbPro.insertArticle(article);
		
		return "/3.Store/writePro.jsp";
	}
}
