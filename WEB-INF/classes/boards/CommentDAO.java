package boards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import db.DBConnectionMgr;

public class CommentDAO {

	private DBConnectionMgr pool=null;

	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";
	

	public CommentDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("DB Connection error=>"+e);
		}
	}
	
	
	public int FreeGetArticleCount() {
		int x=0;
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			sql="select count(*) from com_b";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("FreeGetArticleCount() Error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	// 총 갯수를 가져오는 메서드
	public int CmGetArticleSearchCount(int cs_num) { 
		int x=0;
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			//-----------------------------------------------
			sql="select count(*) from cs_comb where cs_num=?";
			System.out.println("CmGetArticleSearchCount sql=>"+sql);
			
			//------------------------------------------------
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cs_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("CmGetArticleSearchCount() error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	public Hashtable pageList(String pageNum,int count) {
		
		Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();

	    int pageSize=15;
	    int blockSize=5;
	  
	 
	    if(pageNum==null){
	    	pageNum="1";
	    }
	    int currentPage=Integer.parseInt(pageNum);
	    int startRow=(currentPage-1)*pageSize+1;
	    int endRow=currentPage*pageSize;
	    int number=0;
	    System.out.println("(count)=>"+count);
		
	    number=count-(currentPage-1)*pageSize;
	    System.out.println("number=>"+number);
		 
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
	
	public List CmGetBoardArticles(int cs_num, int start,int end,String search,String searchtext) {
		
		List articleList=null;
		
		try {
			con=pool.getConnection();
		    //-----------------------------------------------------------------

			   sql="select * from cs_comb where cs_num=?";

			System.out.println("CmGetBoardArticles() sql=>"+sql);
			//------------------------------------------------------------------
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cs_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				articleList=new ArrayList(end);
				do {
				  CommentDTO article=new CommentDTO();
				  article.setCm_num(rs.getInt("cm_num"));
				  article.setAd_id(rs.getString("ad_id"));
				  article.setCm_date(rs.getString("cm_date"));
				  article.setCm_content(rs.getString("cm_content"));
				  article.setCs_num(rs.getInt("cs_num"));
				  
				  articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("CmGetBoardArticles() =>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}
	
	// Comment Write (insert)
	public void CmInsertArticle(CommentDTO article) {//~(MemberDTO mem)
		//1.article->신규글 인지 답변글인지 확인
		int cm_num=article.getCm_num();
		int cs_num=article.getCs_num();
		
		int number=0;//데이터를 저장하기위한 게시물번호 
		System.out.println("CmInsertArticle메서드의 내부 cs_num=>"+cs_num+", cm_num >>"+cm_num);//0 신규글
		
		try {
			con=pool.getConnection();
			sql="select max(cm_num) from cs_comb";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				number=rs.getInt(1)+1;//최대값+1
			}else {
				number=1;//테이블에 한개의 데이터가 없다면
			}

			//12개->num,reg_date,readcount(생략)->default
			//작성날짜=>sysdate, now() (mysql)
			sql="insert into cs_comb(cm_num, ad_id, cm_date, cm_content, cs_num)values(?,?,sysdate,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, article.getAd_id());
			pstmt.setString(3, article.getCm_content());
			pstmt.setInt(4, article.getCs_num());
			int insert=pstmt.executeUpdate();
			System.out.println("게시판의 글쓰기 성공유무(insert)=>"+insert);
		}catch(Exception e) {
			System.out.println("CmInsertArticle() 메서드 에러유발=>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
	}
	public int CmDeleteArticle(int cm_num,String ad_id) { //회원탈퇴와 동일한 기능
		
		int x = -1;// 게시물의 삭제성공유무
		
		System.out.println("CsDeleteArticle에서의 cm_num=>"+cm_num);

		try {
			con = pool.getConnection();
			sql = "select ad_id from cs_comb where cm_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cm_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
					sql = "delete from cs_comb where cm_num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, cm_num);
					int delete= pstmt.executeUpdate();
					System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);
					x = 1;// 삭제성공유무
				} else {// 암호가 틀린경우
					x = 0;// 삭제실패
				}
			 // if(rs.next())
		} catch (Exception e) {
			System.out.println("CmDeleteArticle() 메서드 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
}
