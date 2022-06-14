package payment;

//DB사용
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//ArrayList,List을 사용
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import db.DBConnectionMgr;

public class PaymentDAO {
	
	private DBConnectionMgr pool=null;//1.연결객체선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;//select
	private String sql="";//실행시킬 SQL구문 저장
	
	//2.생성자를 통해서 연결=>의존관계
	public PaymentDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}//생성자
	//장바구니 총 갯수 구하는 메서드
	public int getCountCart(String m_id) {
		int count=0;
		try {
			con=pool.getConnection();
			sql="select count(*) from cart where m_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getCountCart() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return count;
	}
	//상품 구매하면 상품의 판매수가 올라가는 메서드
	public void updateCount(String item_num) { 
		try {
			con=pool.getConnection();
			sql="update store_b set item_count=item_count+1 where item_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, item_num);
			
			int update=pstmt.executeUpdate();
			
			System.out.println("판매 성공유무=>"+update);
		}catch(Exception e) {
			System.out.println("updateCount() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	//상품 구매하기 메서드
	public void insertGame(PayDTO pd) { 
		String number="";
		String pur_num="";
		int pur_stat=1;
		
		try {
			con=pool.getConnection();
			sql="select count(*) from pay";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				number=Integer.toString(rs.getInt(1)+1);//최대값+1
			}else {
				number="1";
			}
			pur_num=pd.getPur_date()+ number + pd.getItem_num();
			
			sql="insert into pay values(?,?,?,?,sysdate,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,pur_num);
			pstmt.setString(2,pd.getPur_name());
			pstmt.setString(3,pd.getPur_thum());
			pstmt.setString(4,pd.getPur_price());
			pstmt.setInt(5,pd.getPur_sale());
			pstmt.setInt(6, pur_stat);
			pstmt.setString(7,pd.getM_id());
			pstmt.setString(8,pd.getItem_num());
			pstmt.setString(9,pd.getPur_img());
			
			
			int insert=pstmt.executeUpdate();
			
			deleteCart(pd.getM_id(),pd.getItem_num());
			deleteWish(pd.getM_id(),pd.getItem_num());
			
			
			System.out.println("구매하기 성공유무=>"+insert);
		}catch(Exception e) {
			System.out.println("insertGame() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	//구매내역에 있는 상품인지 체크하기위한 메서드
	public PayDTO getHaveItem(String item_num,String m_id) {
		PayDTO pd=null;
		try {
			con=pool.getConnection();
			sql="select * from pay where item_num=? and m_id=? and pur_stat=1";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, item_num);
			pstmt.setString(2, m_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
			pd=new PayDTO();
			/*
			 * pd.setPur_name(rs.getString("pur_name"));
			 * pd.setPur_thum(rs.getString("pur_thum"));
			 * pd.setPur_price(rs.getString("pur_price"));
			 * pd.setM_id(rs.getString("m_id"));
			 * pd.setPur_name(rs.getString("pur_name"));
			 * pd.setPur_date(rs.getString("pur_date")); 
			 * pd.setPur_sale(m_id);
			 */
			pd.setItem_num(rs.getString("item_num")); 
			pd.setPur_stat(rs.getInt("pur_stat"));
			}
		}catch(Exception e) {
			System.out.println("getHaveItem() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return pd;
	}
	//장바구니에 있는 상품인지 체크하기위한 메서드
	public CartDTO getHaveCart(String item_num,String m_id) {
		CartDTO cd=null;
		try {
			con=pool.getConnection();
			sql="select * from cart where item_num=? and m_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, item_num);
			pstmt.setString(2, m_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
			cd=new CartDTO();
			/*
			 * cd.setCt_name(rs.getString("ct_name"));
			 * cd.setCt_thum(rs.getString("ct_thum"));
			 * cd.setCt_price(rs.getString("ct_price"));
			 */
			cd.setItem_num(rs.getString("item_num"));
			/*
			 * cd.setM_id(rs.getString("m_id")); 
			 * cd.setGrd_num(rs.getString("grd_num"));
			 */
			}
		}catch(Exception e) {
			System.out.println("getHaveCart() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return cd;
	}
	//위시리스트에 있는 상품인지 체크위한 메서드
	public WishDTO getHaveWishlist(String item_num,String m_id) {
		WishDTO wd=null;
		try {
			con=pool.getConnection();
			sql="select * from wishlist where item_num=? and m_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, item_num);
			pstmt.setString(2, m_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
			wd=new WishDTO();
			/*
			 * wd.setW_name(rs.getString("w_name")); 
			 * wd.setW_thum(rs.getString("w_thum"));
			 * wd.setW_price(rs.getString("w_price"));
			 */
			wd.setItem_num(rs.getString("item_num"));
			/*
			 * wd.setM_id(rs.getString("m_id")); 
			 * wd.setGrd_num(rs.getString("grd_num"));
			 */
			}
		}catch(Exception e) {
			System.out.println("getHaveWishlist() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return wd;
	}
	//장바구니 테이블에서 정보들 가져오기
	public List<CartDTO> getCartList(String m_id) {
		List<CartDTO> cartList=null;
		try {
			con=pool.getConnection();
			
			sql="select * from cart where m_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				cartList=new ArrayList();
				
				do {
					CartDTO cd=new CartDTO();
					cd.setCt_name(rs.getString("ct_name"));
					cd.setCt_thum(rs.getString("ct_thum"));
					cd.setCt_price(rs.getString("ct_price"));
					cd.setItem_num(rs.getString("item_num"));
					cd.setM_id(rs.getString("m_id"));
					cd.setGrd_num(rs.getString("grd_num"));
					cd.setCt_img(rs.getString("ct_img"));
					cd.setCt_sale(rs.getInt("ct_sale"));
					
					cartList.add(cd);
					
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("getCartList() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return cartList;
	}
	//장바구니 삭제기능 메서드
	public void deleteCart(String m_id, String item_num) {
		
		try {
			con=pool.getConnection();
			sql="delete from cart where m_id=? and item_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, item_num);
			int delete=pstmt.executeUpdate();
			
			System.out.println("deleteCart 성공유무=>"+delete);
		}catch(Exception e) {
			System.out.println("deleteCart() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt);
		}
	}
	//위시리스트에 추가 메서드
	public void insertWishlist(WishDTO wd) {
		int number=0;
		try {
			con=pool.getConnection();
			sql="select max(w_num) from wishlist";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				number=rs.getInt(1)+1;
			}
			sql="insert into wishlist values(?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, wd.getItem_num());
			pstmt.setString(3, wd.getW_name());
			pstmt.setString(4, wd.getW_thum());
			pstmt.setString(5, wd.getW_price());
			pstmt.setString(6, wd.getM_id());
			pstmt.setString(7, wd.getGrd_num());
			pstmt.setString(8, wd.getW_img());
			pstmt.setInt(9, wd.getW_sale());
				
			int insert=pstmt.executeUpdate();

			System.out.println("insertWishlist 성공유무=>"+insert);
		}catch(Exception e) {
			System.out.println("insertWishlist() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt);
		}
	}
	//위시리스트 테이블에서 정보 가져오기
	public List<WishDTO> getWishlistList(String m_id) {
		List<WishDTO> wishList=null;
		try {
			con=pool.getConnection();
			
			sql="select * from wishlist where m_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				wishList=new ArrayList();
				
				do {
					WishDTO wd=new WishDTO();
					wd.setW_name(rs.getString("w_name"));
					wd.setW_thum(rs.getString("w_thum"));
					wd.setW_price(rs.getString("w_price"));
					wd.setItem_num(rs.getString("item_num"));
					wd.setM_id(rs.getString("m_id"));
					wd.setGrd_num(rs.getString("grd_num"));
					wd.setW_img(rs.getString("w_img"));
					wd.setW_sale(rs.getInt("w_sale"));
					
					wishList.add(wd);
					
				}while(rs.next());
				
			}
		}catch(Exception e) {
			System.out.println("getWishList() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return wishList;
	}
	//위시리스트 삭제 메서드
	public void deleteWish(String m_id, String item_num) {
		
		try {
			con=pool.getConnection();
			sql="delete from wishlist where m_id=? and item_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, item_num);
			int delete=pstmt.executeUpdate();
			
			System.out.println("deleteWish 성공유무=>"+delete);
		}catch(Exception e) {
			System.out.println("deleteWish() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt);
		}
	}
	//장바구니에 추가 메서드
	public void insertCart(CartDTO cd) {
		int number=0;
		try {
			con=pool.getConnection();
			sql="select max(ct_num) from cart";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				number=rs.getInt(1)+1;
			}
			sql="insert into cart values(?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, cd.getItem_num());
			pstmt.setString(3, cd.getCt_name());
			pstmt.setString(4, cd.getCt_thum());
			pstmt.setString(5, cd.getCt_price());
			pstmt.setString(6, cd.getM_id());
			pstmt.setString(7, cd.getGrd_num());
			pstmt.setString(8, cd.getCt_img());
			pstmt.setInt(9, cd.getCt_sale());
			
			int insert=pstmt.executeUpdate();
			
			System.out.println("insertCart 성공유무=>"+insert);
		}catch(Exception e) {
			System.out.println("insertCart() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt);
		}
	}
	// =======================================================================================
	//구매내역 총 갯수 불러오는 메서드
	public int getMyPurCount(String m_id) {
		int count=0;
		try {
			con=pool.getConnection();
			sql="select count(*) from pay where m_id=? and pur_stat=1";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getMyPurCount() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return count;
	}
	//구매내역 리스트 불러오는 메서드
	public List<PayDTO> getMyPurchase(String m_id,int start,int end) {
		List<PayDTO> payList=null;
		try {
			con=pool.getConnection();
			sql="select * from (select a.*,rownum as rn from (select * from pay where m_id=? and pur_stat=1 order by pur_date desc) a where rownum <=?) where rn>=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				payList=new ArrayList();
				do {
					PayDTO pd=new PayDTO();
					pd.setPur_num(rs.getString("pur_num"));
					pd.setPur_name(rs.getString("pur_name"));
					pd.setPur_thum(rs.getString("pur_thum"));
					pd.setPur_price(rs.getString("pur_price"));
					pd.setPur_date(rs.getString("pur_date"));
					pd.setPur_sale(rs.getInt("pur_sale"));
					pd.setPur_stat(rs.getInt("pur_stat"));
					pd.setM_id(rs.getString("m_id"));
					pd.setItem_num(rs.getString("item_num"));
					pd.setPur_img(rs.getString("pur_img"));
					
					payList.add(pd);
					
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("getMyPurchase() 에러발생=>"+e);
		}
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return payList;
	}
//페이징처리를 위한 메서드
public Hashtable pageList(String pageNum,int count) {
		

		Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();

	    int pageSize=16;
	    int blockSize=3;
	    
	 if(pageNum==null){
		 pageNum="1";
	 }
	 int currentPage=Integer.parseInt(pageNum);
	 //                    
	 int startRow=(currentPage-1)*pageSize+1;
	 int endRow=currentPage*pageSize;
	 int number=0;
	 System.out.println("현재 레코드수(count)=>"+count);
	
	 number=count-(currentPage-1)*pageSize;
	 System.out.println("페이지별 number=>"+number);
	 


	  int pageCount=count/pageSize+(count%pageSize==0?0:1);

     int startPage=0;
     if(currentPage%blockSize!=0){
   	  startPage=currentPage/blockSize*blockSize+1;
     }else{
   	  startPage=((currentPage/blockSize)-1)*blockSize+1;
     }

     int endPage=startPage+blockSize-1;
     System.out.println("startPage="+startPage+",endPage="+endPage);
     
     if(endPage > pageCount) endPage=pageCount;
     
     pgList.put("pageSize", pageSize);
     pgList.put("blockSize", blockSize);
     pgList.put("currentPage", currentPage);
     pgList.put("startRow", startRow);
     pgList.put("endRow",  endRow);
     pgList.put("count", count);
     pgList.put("number", number);
     pgList.put("startPage", startPage);
     pgList.put("endPage", endPage);
     pgList.put("pageCount", pageCount);
     
      return pgList;
	}	
	// =======================================================================================
		public PayDTO getGame(String m_id, String pur_num) {
			PayDTO bringIt=null;
			
			try {
				con=pool.getConnection();
				sql="select * from pay where m_id=? and pur_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, m_id);
				pstmt.setString(2, pur_num);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					bringIt = new PayDTO();
					bringIt.setM_id(rs.getString("m_id"));
					bringIt.setPur_num(rs.getString("pur_num"));
					bringIt.setPur_name(rs.getString("pur_name"));
					bringIt.setPur_price(rs.getString("pur_price"));
					bringIt.setPur_date(rs.getString("pur_date"));
					bringIt.setPur_stat(rs.getInt("pur_stat"));
					bringIt.setPur_thum(rs.getString("pur_thum"));
					bringIt.setPur_sale(rs.getInt("pur_sale"));
					bringIt.setPur_img(rs.getString("pur_img"));
				} // if
			}catch(Exception e) {
				System.out.println("getGame() Error >>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return bringIt; // PurDetails.jsp에서 받아서 출력 >> NullPointerException	
		} // detailGame
		
		public void refundGame(String m_id, String pur_num) {
			try {
				con=pool.getConnection();
				sql="select * from pay where m_id=? and pur_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, m_id);
				pstmt.setString(2, pur_num);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					sql="update pay set pur_stat=0 where pur_num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, pur_num);
					
					int delete=pstmt.executeUpdate();
				}
				
			}catch(Exception e) {
				System.out.println("refundGame() Error >>"+e);
			}finally {
				pool.freeConnection(con, pstmt,rs);
				}
		} // refundGame
		// =======================================================================================
}

