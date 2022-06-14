package boards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//ArrayList,List�� ���
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import db.DBConnectionMgr;

public class FreeBoardDAO {

	private DBConnectionMgr pool=null;

	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";
	

	public FreeBoardDAO() {
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

	public int FreeGetArticleSearchCount(String search,String searchtext) { 
		int x=0;
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			//-----------------------------------------------
			if(search==null || search=="") {
			sql="select count(*) from com_b";
			}else {
				   sql="select count(*) from com_b where "+search+"like'%"+searchtext+"%'";	
			}
			System.out.println("FreegetArticleSearchCount sql=>"+sql);
			//------------------------------------------------
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("FreeGetArticleSearchCount() error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	public List FreeGetArticles(int start,int end) {
		
		List articleList=null;
		
		try {
			con=pool.getConnection();
			 sql="select * from (select rownum as rn, a.* from (select * from com_b order by com_num desc)a)  where rn >=? AND rn <=?";
			  	System.out.println("FreeGetArticles sql=>"+sql);
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, start+end-1);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				articleList=new ArrayList(end);
				do {
				  FreeBoardDTO article=new FreeBoardDTO();
				  article.setCom_num(rs.getInt("com_num"));
				  article.setCom_view(rs.getInt("com_view"));
				  article.setRef(rs.getInt("ref"));
				  article.setRe_step(rs.getInt("re_step"));
				  article.setRe_level(rs.getInt("re_level"));
  
				  article.setM_id(rs.getString("m_id"));
				  article.setCom_nick(rs.getString("com_nick"));
				  article.setCom_sub(rs.getString("com_sub"));
				  article.setCom_wdate(rs.getString("com_wdate"));
				  article.setCom_content(rs.getString("com_content"));

				  articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("FreeGetArticles() error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

public List FreeGetBoardArticles(int start,int end,String search,String searchtext) {
		
		List articleList=null;
		
		try {
			con=pool.getConnection();
		    //-----------------------------------------------------------------
			if(search==null || search=="") {
			   sql="select * from (select rownum as rn, a.* from (select * from com_b order by com_num desc)a)  where rn >=? AND rn <=?";
			}else {
				if(search.equals("com_sub")) {
					sql="select * from(select rownum as rn, a.* from (select * from com_b where com_sub like  '%"+searchtext+"%'order by com_num desc)a) where rn >=? AND rn <=?";
				}else {
				   sql="select * from(select rownum as rn, a.* from (select * from com_b where com_content like  '%"+searchtext+"%'order by com_num desc)a) where rn >=? AND rn <=?";
				}
			}
			System.out.println("FreeGetBoardArticles() sql=>"+sql);
			//------------------------------------------------------------------
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, start+end-1);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				articleList=new ArrayList(end);
				do {
				  FreeBoardDTO article=new FreeBoardDTO();
				  article.setCom_num(rs.getInt("com_num"));
				  article.setCom_view(rs.getInt("com_view"));
				  article.setRef(rs.getInt("ref"));
				  article.setRe_step(rs.getInt("re_step"));
				  article.setRe_level(rs.getInt("re_level"));
  
				  article.setM_id(rs.getString("m_id"));
				  article.setCom_nick(rs.getString("com_nick"));
				  article.setCom_sub(rs.getString("com_sub"));
				  article.setCom_wdate(rs.getString("com_wdate"));
				  article.setCom_content(rs.getString("com_content"));
				  
				  articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("FreeGetBoardArticles() =>"+e);
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
	
	
	public void FreeInsertArticle(FreeBoardDTO article) {
		int num=article.getCom_num();
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		
		int number=0;
		System.out.println("ref="+ref+",re_step="+re_step+",re_level="+re_level);
		
		try {
			con=pool.getConnection();
			sql="select MAX(com_num) from com_b";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				number=rs.getInt(1)+1;
			}else {
				number=1;
			}
			
			
			if(num!=0) {
			  sql="update board set re_step=re_step+1 where ref=? and re_step > ?";
			  pstmt=con.prepareStatement(sql);
			  pstmt.setInt(1, ref);
			  pstmt.setInt(2, re_step);
			  int update=pstmt.executeUpdate();
			  System.out.println("(updte)=>"+update);
			  
			  re_step=re_step+1;
			  re_level=re_level+1;
			}else {
				ref=1;//1,2,3,4,,,
				re_step=0;
				re_level=0;
			}
			
			
			sql="insert into com_b(com_num,m_id,com_nick,com_sub,com_content,ref,re_step,re_level)values(?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1,number);
			pstmt.setString(2, article.getM_id());
			pstmt.setString(3, article.getCom_nick());
			pstmt.setString(4, article.getCom_sub());
			pstmt.setString(5, article.getCom_content());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, re_step);
			pstmt.setInt(8, re_level);
			int insert=pstmt.executeUpdate();
			System.out.println("(insert)=>"+insert);
		}catch(Exception e) {
			System.out.println("FreeInsertArticle() =>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
	}
	
	public FreeBoardDTO FreeGetArticle(int com_num) {
		FreeBoardDTO article=null;
		
		try {
			con=pool.getConnection();
		
			sql="update com_b set com_view=com_view+1 where com_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, com_num);
			int update=pstmt.executeUpdate();
			System.out.println("(update)=>"+update);//1
			
			sql="select * from com_b where com_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, com_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
					System.out.println("com_view >>>>>>>>>>>"+rs.getInt("com_view"));
				  article=new FreeBoardDTO();
				  article.setCom_num(rs.getInt("com_num"));
				  article.setCom_view(rs.getInt("com_view"));
				  article.setRef(rs.getInt("ref"));
				  article.setRe_step(rs.getInt("re_step"));
				  article.setRe_level(rs.getInt("re_level"));
  
				  article.setM_id(rs.getString("m_id"));
				  article.setCom_nick(rs.getString("com_nick"));
				  article.setCom_sub(rs.getString("com_sub"));
				  article.setCom_wdate(rs.getString("com_wdate"));
				  article.setCom_content(rs.getString("com_content"));
			}
		}catch(Exception e) {
			System.out.println("FreeGetArticle() error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	
	//------------------------------------------------------------------
	public FreeBoardDTO FreeUpdateGetArticle(int com_num) {
		
		FreeBoardDTO article=null;	
		try {
			con=pool.getConnection();
			sql="select * from com_b where com_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, com_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article=new FreeBoardDTO();
				  article.setCom_num(rs.getInt("com_num"));
				  article.setCom_view(rs.getInt("com_view"));
				  article.setRef(rs.getInt("ref"));
				  article.setRe_step(rs.getInt("re_step"));
				  article.setRe_level(rs.getInt("re_level"));

				  article.setM_id(rs.getString("m_id"));
				  article.setCom_nick(rs.getString("com_nick"));
				  article.setCom_sub(rs.getString("com_sub"));
				  article.setCom_wdate(rs.getString("com_wdate"));
				  article.setCom_content(rs.getString("com_content"));
			}
		}catch(Exception e) {
			System.out.println("FreeUpdateGetArticle() error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	
	public int FreeUpdateArticle(FreeBoardDTO article, String m_id) {

        int x = -1;

        try {
            con = pool.getConnection();
            sql = "select * from com_b where com_num=? and m_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, article.getCom_num());
            pstmt.setString(2, m_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                    sql = "update com_b set com_sub=?,com_content=? where com_num=?";
                    pstmt = con.prepareStatement(sql);

                    pstmt.setString(1, article.getCom_sub());
                    pstmt.setString(2, article.getCom_content());
                    pstmt.setInt(3, article.getCom_num());

                    int update = pstmt.executeUpdate();
                    System.out.println("(update)=>" + update);
                    x = 1;
                } else {
                    x = 0;
            } // if(rs.next())
        } catch (Exception e) {
            System.out.println("FreeUpdateArticle() error=>" + e);
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return x;
    }
	
	
	public int FreeDeleteArticle(int com_num, String m_id) { 
		
		int x = -1;
		
		System.out.println("FreeDeleteArticle()  num=>"+com_num);
		System.out.println("FreeDeleteArticle()  m_id=>"+m_id);

		try {
			con = pool.getConnection();
			sql = "select * from com_b where com_num=? and m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, com_num);
			pstmt.setString(2, m_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
					sql = "delete from com_b where com_num=? and m_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, com_num);
					pstmt.setString(2, m_id);
					int delete= pstmt.executeUpdate();
					System.out.println("(delete)=>" + delete);
					x = 1;
				} else {
					x = 0;		
			} // if(rs.next())
		} catch (Exception e) {
			System.out.println("FreeDeleteArticle() error=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
}
