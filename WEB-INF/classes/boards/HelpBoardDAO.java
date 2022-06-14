package boards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//ArrayList,List�� ���
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import db.DBConnectionMgr;

public class HelpBoardDAO {

	private DBConnectionMgr pool=null;

	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";
	

	public HelpBoardDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("DB Connection error=>"+e);
		}
	}
	
	
	public int HelpGetArticleCount() {
		int x=0;
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			sql="select count(*) from help_b";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("HelpGetArticleCount() Error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	public int HelpGetArticleSearchCount(String search,String searchtext) { 
		int x=0;
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			//-----------------------------------------------
			if(search=="" || search==null) {
				sql="select count(*) from help_b";				
			}else if(search.equals("help_sub")){
				sql="select count(*) from help_b where help_sub like '%"+searchtext+"%'";
			}else if(search.equals("help_content")){
				sql="select count(*) from help_b where help_content like '%"+searchtext+"%'";
			}
			
			System.out.println("HelpgetArticleSearchCount sql=>"+sql);
			//------------------------------------------------
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("HelpGetArticleSearchCount() error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	public List HelpGetArticles(int start,int end) {
		
		List articleList=null;
		
		try {
			con=pool.getConnection();
			sql="select * from (select rownum as rn, a.* from (select * from help_b order by help_num desc)a)  where rn >=? AND rn <=?";
			System.out.println("HelpGetArticles sql=>"+sql);
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, start+end-1);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				articleList=new ArrayList(end);
				do {
					HelpBoardDTO article=new HelpBoardDTO();
					article.setHelp_num(rs.getInt("help_num"));
					article.setHelp_view(rs.getInt("help_view"));
					article.setAd_id(rs.getString("ad_id"));
					article.setHelp_nick(rs.getString("help_nick"));
					article.setHelp_sub(rs.getString("help_sub"));
					article.setHelp_wdate(rs.getString("help_wdate"));
					article.setHelp_content(rs.getString("help_content"));

					articleList.add(article);
					
					}while(rs.next());
				}
		}catch(Exception e) {
			System.out.println("HelpGetArticles() error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

public List HelpGetBoardArticles(int start,int end,String search,String searchtext) {
		
		List articleList=null;
		
		try {
			con=pool.getConnection();
		    //-----------------------------------------------------------------
			if(search==null || search=="") {
			   sql="select * from (select rownum as rn, a.* from (select * from help_b order by help_num desc)a)  where rn >=? AND rn <=?";
			}else {
				if(search.equals("help_sub")) {
					sql="select * from(select rownum as rn, a.* from (select * from help_b where help_sub like  '%"+searchtext+"%'order by help_num desc)a) where rn >=? AND rn <=?";
				}else {
				   sql="select * from(select rownum as rn, a.* from (select * from help_b where help_content like  '%"+searchtext+"%'order by help_num desc)a) where rn >=? AND rn <=?";
				}
			}
			System.out.println("HelpGetBoardArticles() sql=>"+sql);
			//------------------------------------------------------------------
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, start+end-1);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				articleList=new ArrayList(end);
				do {
					HelpBoardDTO article=new HelpBoardDTO();
					article.setHelp_num(rs.getInt("help_num"));
					article.setHelp_view(rs.getInt("help_view"));
					article.setAd_id(rs.getString("ad_id"));
					article.setHelp_nick(rs.getString("help_nick"));
					article.setHelp_sub(rs.getString("help_sub"));
					article.setHelp_wdate(rs.getString("help_wdate"));
					article.setHelp_content(rs.getString("help_content"));
				  
					articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("HelpGetBoardArticles() =>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
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
	
	
	public void HelpInsertArticle(HelpBoardDTO article) {
		int num=article.getHelp_num();
		
		int number=0;
		try {
			con=pool.getConnection();
			sql="select MAX(help_num) from help_b";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				number=rs.getInt(1)+1;
			}else {
				number=1;
			}
			
			sql="insert into help_b(help_num,ad_id,help_nick,help_sub,help_content)values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1,number);
			pstmt.setString(2, article.getAd_id());
			pstmt.setString(3, article.getHelp_nick());
			pstmt.setString(4, article.getHelp_sub());
			pstmt.setString(5, article.getHelp_content());
			int insert=pstmt.executeUpdate();
			System.out.println("(insert)=>"+insert);
		}catch(Exception e) {
			System.out.println("HelpInsertArticle() =>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
	}
	
	public HelpBoardDTO HelpGetArticle(int help_num) {
		HelpBoardDTO article=null;
		
		try {
			con=pool.getConnection();
		
			sql="update help_b set help_view=help_view+1 where help_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, help_num);
			int update=pstmt.executeUpdate();
			System.out.println("(update)=>"+update);//1
			
			sql="select * from help_b where help_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, help_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("help_view >>>>>>>>>>>"+rs.getInt("help_view"));
				article=new HelpBoardDTO();
				article.setHelp_num(rs.getInt("help_num"));
				article.setHelp_view(rs.getInt("help_view"));
				article.setAd_id(rs.getString("ad_id"));
				article.setHelp_nick(rs.getString("help_nick"));
				article.setHelp_sub(rs.getString("help_sub"));
				article.setHelp_wdate(rs.getString("help_wdate"));
				article.setHelp_content(rs.getString("help_content"));
			}
		}catch(Exception e) {
			System.out.println("HelpGetArticle() error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	
	//------------------------------------------------------------------
	public HelpBoardDTO HelpUpdateGetArticle(int help_num) {
		
		HelpBoardDTO article=null;	
		try {
			con=pool.getConnection();
			sql="select * from help_b where help_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, help_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article=new HelpBoardDTO();
				  article.setHelp_num(rs.getInt("help_num"));
				  article.setHelp_view(rs.getInt("help_view"));
				  article.setAd_id(rs.getString("ad_id"));
				  article.setHelp_nick(rs.getString("help_nick"));
				  article.setHelp_sub(rs.getString("help_sub"));
				  article.setHelp_wdate(rs.getString("help_wdate"));
				  article.setHelp_content(rs.getString("help_content"));
			}
		}catch(Exception e) {
			System.out.println("HelpUpdateGetArticle() error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	
	public int HelpUpdateArticle(HelpBoardDTO article, String ad_id) {

        int x = -1;

        try {
            con = pool.getConnection();
            sql = "select * from help_b where help_num=? and ad_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, article.getHelp_num());
            pstmt.setString(2, ad_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                    sql = "update help_b set help_sub=?,help_content=? where help_num=?";
                    pstmt = con.prepareStatement(sql);

                    pstmt.setString(1, article.getHelp_sub());
                    pstmt.setString(2, article.getHelp_content());
                    pstmt.setInt(3, article.getHelp_num());

                    int update = pstmt.executeUpdate();
                    System.out.println("(update)=>" + update);
                    x = 1;
                } else {
                    x = 0;
            } // if(rs.next())
        } catch (Exception e) {
            System.out.println("HelpUpdateArticle() error=>" + e);
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return x;
    }
	
	
	public int HelpDeleteArticle(int help_num, String ad_id) { 
		
		int x = -1;
		
		System.out.println("HelpDeleteArticle()  num=>"+help_num);
		System.out.println("HelpDeleteArticle()  ad_id=>"+ad_id);

		try {
			con = pool.getConnection();
			sql = "select * from help_b where help_num=? and ad_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, help_num);
			pstmt.setString(2, ad_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
					sql = "delete from help_b where help_num=? and ad_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, help_num);
					pstmt.setString(2, ad_id);
					int delete= pstmt.executeUpdate();
					System.out.println("(delete)=>" + delete);
					x = 1;
				} else {
					x = 0;		
			} // if(rs.next())
		} catch (Exception e) {
			System.out.println("HelpDeleteArticle() error=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
}
