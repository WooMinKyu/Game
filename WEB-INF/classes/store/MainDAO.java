package store;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//ArrayList,List�� ���
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import db.DBConnectionMgr;

public class MainDAO {

	private DBConnectionMgr pool=null;
	
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";
	
	
	public MainDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("DB connection >>"+e);
		}
	}	
	//메인페이지 베스트 게임 
	public List getBestArticles() {
		List bestArticleList=null;
		
		try {
			con=pool.getConnection();
			/* sql="select * from store_b where rownum <=6 order by item_count desc"; */
			sql="select * from (select a.*,rownum as rn from (select * from store_b order by item_count desc) a where rownum <=6) where rn>=1";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bestArticleList=new ArrayList();
				do {
					StoreDTO article=new StoreDTO();
					  article.setItem_num(rs.getString("item_num"));
					  article.setItem_price(rs.getString("item_price"));
					  article.setItem_count(rs.getInt("item_count"));
					  article.setGen_num(rs.getString("gen_num"));
					  article.setGrd_num(rs.getString("grd_num"));
					  
					  article.setAd_id(rs.getString("ad_id"));
					  article.setItem_name(rs.getString("item_name"));
					  article.setItem_img(rs.getString("item_img"));
					  article.setItem_thum(rs.getString("item_thum"));
					  article.setItem_content(rs.getString("item_content"));
					  article.setItem_rels(rs.getString("item_rels"));
					  article.setItem_gen(rs.getString("item_gen"));
					  article.setItem_grd(rs.getString("item_grd"));
					  article.setItem_dev(rs.getString("item_dev"));
					  article.setItem_pub(rs.getString("item_pub"));
					  article.setItem_pf(rs.getString("item_pf"));
					  article.setItem_ft(rs.getString("item_ft"));
					  article.setItem_indate(rs.getString("item_indate"));
					  article.setItem_sale(rs.getInt("item_sale"));
	  
					  bestArticleList.add(article);
				}while(rs.next());
				System.out.println("bestArticleList=>"+bestArticleList);
			}
		}catch(Exception e) {
			System.out.println("getBestArticles() >>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bestArticleList;
	}
	//메인페이지 할인 게임
	public List getDiscoutArticles() {
		List discountArticleList=null;
		
		try {
			con=pool.getConnection();
			/* sql="select * from store_b where rownum <=6 order by item_sale desc"; */
			sql="select * from (select a.*,rownum as rn from (select * from store_b order by item_sale desc) a where rownum <=6) where rn>=1";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				discountArticleList=new ArrayList();
				do {
					StoreDTO article=new StoreDTO();
					  article.setItem_num(rs.getString("item_num"));
					  article.setItem_price(rs.getString("item_price"));
					  article.setItem_count(rs.getInt("item_count"));
					  article.setGen_num(rs.getString("gen_num"));
					  article.setGrd_num(rs.getString("grd_num"));
					  
					  article.setAd_id(rs.getString("ad_id"));
					  article.setItem_name(rs.getString("item_name"));
					  article.setItem_img(rs.getString("item_img"));
					  article.setItem_thum(rs.getString("item_thum"));
					  article.setItem_content(rs.getString("item_content"));
					  article.setItem_rels(rs.getString("item_rels"));
					  article.setItem_gen(rs.getString("item_gen"));
					  article.setItem_grd(rs.getString("item_grd"));
					  article.setItem_dev(rs.getString("item_dev"));
					  article.setItem_pub(rs.getString("item_pub"));
					  article.setItem_pf(rs.getString("item_pf"));
					  article.setItem_ft(rs.getString("item_ft"));
					  article.setItem_indate(rs.getString("item_indate"));
					  article.setItem_sale(rs.getInt("item_sale"));
	  
					  discountArticleList.add(article);
				}while(rs.next());
				System.out.println("discountArticleList=>"+discountArticleList);
			}
		}catch(Exception e) {
			System.out.println("getDiscoutArticles() >>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return discountArticleList;
	}
	//메인페이지에서 새로운 게임
	public List getNewArticles() {
		List newArticleList=null;
		
		try {
			con=pool.getConnection();
			/* sql="select * from store_b where rownum <=6 order by item_indate desc"; */
			sql="select * from (select a.*,rownum as rn from (select * from store_b order by item_indate desc) a where rownum <=6) where rn>=1";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				newArticleList=new ArrayList();
				do {
					StoreDTO article=new StoreDTO();
					  article.setItem_num(rs.getString("item_num"));
					  article.setItem_price(rs.getString("item_price"));
					  article.setItem_count(rs.getInt("item_count"));
					  article.setGen_num(rs.getString("gen_num"));
					  article.setGrd_num(rs.getString("grd_num"));
					  
					  article.setAd_id(rs.getString("ad_id"));
					  article.setItem_name(rs.getString("item_name"));
					  article.setItem_img(rs.getString("item_img"));
					  article.setItem_thum(rs.getString("item_thum"));
					  article.setItem_content(rs.getString("item_content"));
					  article.setItem_rels(rs.getString("item_rels"));
					  article.setItem_gen(rs.getString("item_gen"));
					  article.setItem_grd(rs.getString("item_grd"));
					  article.setItem_dev(rs.getString("item_dev"));
					  article.setItem_pub(rs.getString("item_pub"));
					  article.setItem_pf(rs.getString("item_pf"));
					  article.setItem_ft(rs.getString("item_ft"));
					  article.setItem_indate(rs.getString("item_indate"));
					  article.setItem_sale(rs.getInt("item_sale"));
	  
					  newArticleList.add(article);
				}while(rs.next());
				System.out.println("discountArticleList=>"+newArticleList);
			}
		}catch(Exception e) {
			System.out.println("getDiscoutArticles() >>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return newArticleList;
	}
	
	/*
	 * public Hashtable pageList(String pageNum,int count) {
	 * 
	 * Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();
	 * 
	 * int pageSize=6;
	 * 
	 * 
	 * if(pageNum==null){ pageNum="1"; } int currentPage=Integer.parseInt(pageNum);
	 * int startRow=(currentPage-1)*pageSize+1; int endRow=currentPage*pageSize; int
	 * number=0; System.out.println("(count)=>"+count);
	 * 
	 * number=count-(currentPage-1)*pageSize; System.out.println("number=>"+number);
	 * 
	 * int pageCount=count/pageSize+(count%pageSize==0?0:1); int startPage=0;
	 * if(currentPage%blockSize!=0){ startPage=currentPage/blockSize*blockSize+1;
	 * }else{ startPage=((currentPage/blockSize)-1)*blockSize+1; } int
	 * endPage=startPage+blockSize-1;
	 * System.out.println("startPage="+startPage+",endPage="+endPage);
	 * 
	 * if(endPage > pageCount) endPage=pageCount; pgList.put("pageSize", pageSize);
	 * pgList.put("blockSize", blockSize); pgList.put("currentPage", currentPage);
	 * pgList.put("startRow", startRow); pgList.put("endRow", endRow);
	 * pgList.put("count", count); pgList.put("number", number);
	 * pgList.put("startPage", startPage); pgList.put("endPage", endPage);
	 * pgList.put("pageCount", pageCount);
	 * 
	 * return pgList; }
	 */
}
