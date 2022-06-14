package boards;
//DBConnectionMgr(DB접속,관리),BoardDTO(매개변수,반환형,데이터를 담는 역할)

//DB사용
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//ArrayList,List을 사용
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import db.DBConnectionMgr;

public class CsBoardDAO { //MemberDAO

	private DBConnectionMgr pool=null;//1.연결객체선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;//select
	private String sql="";//실행시킬 SQL구문 저장
	
	//2.생성자를 통해서 연결=>의존관계
	public CsBoardDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}//생성자
	//----------------------------------------------------------------리스트시작
	//1.페이징 처리를 위한 전체레코드수를 구해와야 한다.
	public int CsGetArticleCount(String m_id) { 
		int x=0;//총레코드갯수를 저장
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			sql="select count(*) from cs_b where m_id";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X(그룹함수)
			}
		}catch(Exception e) {
			System.out.println("CsGetArticleCount() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	//검색어에 해당되는 총레코드수를 구하는 메서드(검색분야,검색어)
	public int CsGetArticleSearchCount(String m_id,String search,String searchtext) { 
		int x=0;//총레코드갯수를 저장
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			//-----------------------------------------------
			if(search==null || search=="") {//검색분야 선택X(검색어를 입력하지 않은 경우)
			sql="select count(*) from cs_b where m_id=?";
			}else {
				   sql="select count(*) from cs_b where m_id=?,"+search+" like  '%"+
				           searchtext+"%' ";	
			}
			System.out.println("CsGetArticleSearchCount 검색sql=>"+sql);
			//------------------------------------------------
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X(그룹함수)
			}
		}catch(Exception e) {
			System.out.println("CsGetArticleSearchCount() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	public int CsGetArticleSearchCount(String search,String searchtext) { 
		int x=0;//총레코드갯수를 저장
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			//-----------------------------------------------
			if(search==null || search=="") {//검색분야 선택X(검색어를 입력하지 않은 경우)
			sql="select count(*) from cs_b";
			}else {
				   sql="select count(*) from cs_b "+search+" like  '%"+searchtext+"%' ";	
			}
			System.out.println("CsGetArticleSearchCount 검색sql=>"+sql);
			//------------------------------------------------
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X(그룹함수)
			}
		}catch(Exception e) {
			System.out.println("CsGetArticleSearchCount() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	// AD가 전체리스트 보기
	public int AdGetArticleSearchCount(String m_id,String search,String searchtext) { 
		int x=0;//총레코드갯수를 저장
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			//-----------------------------------------------
			if(search==null || search=="") {//검색분야 선택X(검색어를 입력하지 않은 경우)
			sql="select count(*) from cs_b";
			}else {
				   sql="select count(*) from cs_b where m_id=?,"+search+" like  '%"+
				           searchtext+"%' ";	
			}
			System.out.println("AdGetArticleSearchCount 검색sql=>"+sql);
			//------------------------------------------------
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X(그룹함수)
			}
		}catch(Exception e) {
			System.out.println("AdGetArticleSearchCount() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//2.글목록보기에 대한 메서드구현->레코드 한개이상->한 페이지당 10개씩 끊어서 보여준다.
	//1)레코드의 시작번호  2) 불러올 레코드의 갯수
	public List CsGetArticles(String m_id,int start,int end) {//getMemberList(int start,int end){
		
		List articleList=null;//ArrayList articleList=null;//(O)
		
		try {
			con=pool.getConnection();
			/*
			 * 그룹번호가 가장 최신의 글을 중심으로 정렬하되,만약에 level이 같은 경우에는
			 * step값으로 오름차순을 통해서  몇번째 레코드번호를 기준해서 몇개까지 정렬할것인가를
			 * 지정해주는 SQL구문
			 */
			sql="select * from (select rownum as rn, a.* from (select * from cs_b where m_id=? order by cs_num desc)a)  where rn >=? AND rn <=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, start+end-1);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간을 만들어라
				do {
				  CsBoardDTO article=new CsBoardDTO();//MemberDTO=>필드별로 담을것.
				  
				  article.setM_id(rs.getString("m_id"));
				  article.setAd_id(rs.getString("ad_id"));
				  article.setCs_sub(rs.getString("cs_sub"));
				  article.setCs_content(rs.getString("cs_content"));
				  article.setCs_wdate(rs.getDate("cs_wdate"));
				 
				  article.setCs_num(rs.getInt("cs_num"));
				  article.setRef(rs.getInt("ref"));
				  
				  articleList.add(article);//생략하면 데이터가 저장X->for문 에러유발
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("CsGetArticles() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}
	//(2)검색어에 따른 레코드의 범위지정에 대한 메서드
	public List CsGetBoardArticles(String m_id,int start,int end,String search,String searchtext) {//getMemberList(int start,int end){
		
		List articleList=null;//ArrayList articleList=null;//(O)
		
		try {
			con=pool.getConnection();
		    //-----------------------------------------------------------------
			if(search==null || search=="") {
				   sql="select * from (select rownum as rn, a.* from (select * from cs_b where m_id=? order by cs_num desc)a)  where rn >=? AND rn <=?";
				}else {
					if(search.equals("com_sub")) {
						sql="select * from(select rownum as rn, a.* from (select * from cs_b where m_id=?, cs_sub like  '%"+searchtext+"%'order by cs_num desc)a) where rn >=? AND rn <=?";
					}else {
					   sql="select * from(select rownum as rn, a.* from (select * from cs_b  where m_id=?,  cs_content like  '%"+searchtext+"%'order by cs_num desc)a) where rn >=? AND rn <=?";
					}
				}
			System.out.println("CsGetBoardArticles()의 sql=>"+sql);
			//------------------------------------------------------------------
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, start+end-1);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간을 만들어라
				do {
				  CsBoardDTO article=new CsBoardDTO();//MemberDTO=>필드별로 담을것.
				  
				  article.setM_id(rs.getString("m_id"));
				  article.setAd_id(rs.getString("ad_id"));
				  article.setCs_sub(rs.getString("cs_sub"));
				  article.setCs_content(rs.getString("cs_content"));
				  article.setCs_wdate(rs.getDate("cs_wdate"));
				  
				  article.setCs_num(rs.getInt("cs_num"));
				  article.setRef(rs.getInt("ref"));
				  
				  articleList.add(article);//생략하면 데이터가 저장X->for문 에러유발
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("CsGetBoardArticles() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}
public List CsGetBoardArticles(int start,int end,String search,String searchtext) {//getMemberList(int start,int end){
		
		List articleList=null;//ArrayList articleList=null;//(O)
		
		try {
			con=pool.getConnection();
		    //-----------------------------------------------------------------
			if(search==null || search=="") {
				   sql="select * from (select rownum as rn, a.* from (select * from cs_b order by cs_num desc)a)  where rn >=? AND rn <=?";
				}else {
					if(search.equals("com_sub")) {
						sql="select * from(select rownum as rn, a.* from (select * from cs_b where cs_sub like  '%"+searchtext+"%'order by cs_num desc)a) where rn >=? AND rn <=?";
					}else {
					   sql="select * from(select rownum as rn, a.* from (select * from cs_b  where cs_content like  '%"+searchtext+"%'order by cs_num desc)a) where rn >=? AND rn <=?";
					}
				}
			System.out.println("CsGetBoardArticles()의 sql=>"+sql);
			//------------------------------------------------------------------
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, start+end-1);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간을 만들어라
				do {
				  CsBoardDTO article=new CsBoardDTO();//MemberDTO=>필드별로 담을것.
				  
				  article.setM_id(rs.getString("m_id"));
				  article.setAd_id(rs.getString("ad_id"));
				  article.setCs_sub(rs.getString("cs_sub"));
				  article.setCs_content(rs.getString("cs_content"));
				  article.setCs_wdate(rs.getDate("cs_wdate"));
				  
				  article.setCs_num(rs.getInt("cs_num"));
				  article.setRef(rs.getInt("ref"));
				  
				  articleList.add(article);//생략하면 데이터가 저장X->for문 에러유발
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("CsGetBoardArticles() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}
	
	//
	//(2)검색어에 따른 레코드의 범위지정에 대한 메서드
		public List AdGetBoardArticles(String m_id,int start,int end,String search,String searchtext) {//getMemberList(int start,int end){
			
			List articleList=null;//ArrayList articleList=null;//(O)
			
			try {
				con=pool.getConnection();
			    //-----------------------------------------------------------------
				if(search==null || search=="") {
					   sql="select * from (select rownum as rn, a.* from (select * from cs_b order by cs_num desc)a)  where rn >=? AND rn <=?";
					}else {
						if(search.equals("com_sub")) {
							sql="select * from(select rownum as rn, a.* from (select * from cs_b where cs_sub like  '%"+searchtext+"%'order by cs_num desc)a) where rn >=? AND rn <=?";
						}else {
						   sql="select * from(select rownum as rn, a.* from (select * from cs_b  where cs_content like  '%"+searchtext+"%'order by cs_num desc)a) where rn >=? AND rn <=?";
						}
					}
				System.out.println("CsGetBoardArticles()의 sql=>"+sql);
				//------------------------------------------------------------------
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, start+end-1);
				rs=pstmt.executeQuery();
				if(rs.next()) {//보여주는 결과가 있다면
					articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간을 만들어라
					do {
					  CsBoardDTO article=new CsBoardDTO();//MemberDTO=>필드별로 담을것.
					  
					  article.setM_id(rs.getString("m_id"));
					  article.setAd_id(rs.getString("ad_id"));
					  article.setCs_sub(rs.getString("cs_sub"));
					  article.setCs_content(rs.getString("cs_content"));
					  article.setCs_wdate(rs.getDate("cs_wdate"));
					  
					  article.setCs_num(rs.getInt("cs_num"));
					  article.setRef(rs.getInt("ref"));
					  
					  articleList.add(article);//생략하면 데이터가 저장X->for문 에러유발
					}while(rs.next());
				}
			}catch(Exception e) {
				System.out.println("CsGetBoardArticles() 에러유발=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return articleList;
		}
	
	//(3)페이징 처리 계산 정리해주는 메서드(ListAction)
	//Hashtable=>페이징 처리에 관련된 처리결과를 저장할 변수들을 하나의 객체에 담아서 반환
	public Hashtable pageList(String pageNum,int count) {
		
		//1.페이징 처리결과를 저장할 Hashtable객체를 선언
		Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();

	    int pageSize=4;//numPerPage->페이지당 보여주는 게시물수(=레코드수) 10
	    int blockSize=5;//pagePerBlock->블럭당 보여주는 페이지수 10
	  
	 //게시판을 맨 처음 실행시키면 무조건 1페이지 부터  출력->가장 최근의 글이 나오기때문에 
	 if(pageNum==null){
		 pageNum="1";//default(무조건 1페이지는 선택하지 않아도 보여줘야 되기때문에)
	 }
	 int currentPage=Integer.parseInt(pageNum);//"1"->1 (=nowPage(현재페이지))
	 //                    (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21
	 int startRow=(currentPage-1)*pageSize+1;//시작 레코드 번호
	 int endRow=currentPage*pageSize;//1*10=10,2*10=20,3*10=30(마지막 레코드번호)
	 int number=0;//beginPerPage=>페이지별로 시작하는 맨 처음에 나오는 게시물번호
	 System.out.println("현재 레코드수(count)=>"+count);
	 //             122-(1-1)*10=122,122-(2-1)*10=112,,,
	 number=count-(currentPage-1)*pageSize;
	 System.out.println("페이지별 number=>"+number);
	 
	 //총페이지수,시작,종료페이지 계산->list.jsp에서 이미 코딩->모델1에서의 list.jsp에서 복사해온다.
	//1.총페이지수 구하기
	  //                     122/10=12.2+1.0=13.2=13,(122%10)=1
	  int pageCount=count/pageSize+(count%pageSize==0?0:1);
     //2.시작페이지
     int startPage=0;
     if(currentPage%blockSize!=0){//1~9,11~19,21~29
   	  startPage=currentPage/blockSize*blockSize+1;
     }else{//10%10=0,(10,20,30,40)
   		              //((10/10)-1)*10+1
   	  startPage=((currentPage/blockSize)-1)*blockSize+1;//1
     }
     //종료페이지
     int endPage=startPage+blockSize-1;//1+10-1=10
     System.out.println("startPage="+startPage+",endPage="+endPage);
     //블럭별로 구분해서 링크 걸어서 출력(마지막 페이지 > 총페이지수)
     // 11>10=>endPage=10
     if(endPage > pageCount) endPage=pageCount;//마지막페이지=총페이지수
     
     //페이징처리에 대한 계산결과=>Hashtable,HashMap=>ListAction에 전달->
     //메모리에 저장->공유->list.jsp에서 불러다 사용
     pgList.put("pageSize", pageSize);//<->pgList.get(키명)("pageSize")
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
	public Hashtable AdpageList(String pageNum,int count) {
		
		//1.페이징 처리결과를 저장할 Hashtable객체를 선언
		Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();

	    int pageSize=10;//numPerPage->페이지당 보여주는 게시물수(=레코드수) 10
	    int blockSize=5;//pagePerBlock->블럭당 보여주는 페이지수 10
	  
	 //게시판을 맨 처음 실행시키면 무조건 1페이지 부터  출력->가장 최근의 글이 나오기때문에 
	 if(pageNum==null){
		 pageNum="1";//default(무조건 1페이지는 선택하지 않아도 보여줘야 되기때문에)
	 }
	 int currentPage=Integer.parseInt(pageNum);//"1"->1 (=nowPage(현재페이지))
	 //                    (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21
	 int startRow=(currentPage-1)*pageSize+1;//시작 레코드 번호
	 int endRow=currentPage*pageSize;//1*10=10,2*10=20,3*10=30(마지막 레코드번호)
	 int number=0;//beginPerPage=>페이지별로 시작하는 맨 처음에 나오는 게시물번호
	 System.out.println("현재 레코드수(count)=>"+count);
	 //             122-(1-1)*10=122,122-(2-1)*10=112,,,
	 number=count-(currentPage-1)*pageSize;
	 System.out.println("페이지별 number=>"+number);
	 
	 //총페이지수,시작,종료페이지 계산->list.jsp에서 이미 코딩->모델1에서의 list.jsp에서 복사해온다.
	//1.총페이지수 구하기
	  //                     122/10=12.2+1.0=13.2=13,(122%10)=1
	  int pageCount=count/pageSize+(count%pageSize==0?0:1);
     //2.시작페이지
     int startPage=0;
     if(currentPage%blockSize!=0){//1~9,11~19,21~29
   	  startPage=currentPage/blockSize*blockSize+1;
     }else{//10%10=0,(10,20,30,40)
   		              //((10/10)-1)*10+1
   	  startPage=((currentPage/blockSize)-1)*blockSize+1;//1
     }
     //종료페이지
     int endPage=startPage+blockSize-1;//1+10-1=10
     System.out.println("startPage="+startPage+",endPage="+endPage);
     //블럭별로 구분해서 링크 걸어서 출력(마지막 페이지 > 총페이지수)
     // 11>10=>endPage=10
     if(endPage > pageCount) endPage=pageCount;//마지막페이지=총페이지수
     
     //페이징처리에 대한 계산결과=>Hashtable,HashMap=>ListAction에 전달->
     //메모리에 저장->공유->list.jsp에서 불러다 사용
     pgList.put("pageSize", pageSize);//<->pgList.get(키명)("pageSize")
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
	
	//----------------------------------------------------------------------------리스트끝
	
	//----게시판의 글쓰기 및 답변글쓰기-----
	//insert into board values(?,?,,,,
	public void CsInsertArticle(CsBoardDTO article) {//~(MemberDTO mem)
		//1.article->신규글 인지 답변글인지 확인
		int cs_num=article.getCs_num();//0 신규글 0이아닌경우 (답변글)
		int ref=article.getRef();
		
		int number=0;//데이터를 저장하기위한 게시물번호 
		System.out.println("CsInsertArticle메서드의 내부 cs_num=>"+cs_num);//0 신규글
		System.out.println("ref="+ref);
		
		try {
			con=pool.getConnection();
			sql="select max(cs_num) from cs_b";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				number=rs.getInt(1)+1;//최대값+1
			}else {
				number=1;//테이블에 한개의 데이터가 없다면
			}

			//12개->num,reg_date,readcount(생략)->default
			//작성날짜=>sysdate, now() (mysql)
			sql="insert into cs_b(cs_num,ref,";
			sql+=" m_id,ad_id,cs_sub,cs_content)values(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, article.getRef());
			pstmt.setString(3, article.getM_id());//웹에서는 이미 데이터저장된 상태(Setter~)
			pstmt.setString(4, article.getAd_id());
			pstmt.setString(5, article.getCs_sub());
			pstmt.setString(6, article.getCs_content());//5번째에 ? 대신에 now()
			int insert=pstmt.executeUpdate();
			System.out.println("게시판의 글쓰기 성공유무(insert)=>"+insert);
		}catch(Exception e) {
			System.out.println("insertArticle() 메서드 에러유발=>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
	}
	//글상세보기
	//content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>
	//형식)select * from board where num=3
	//형식2)update board set readcount=readcount+1 where num=3
	//public MemberDTO getMember(String id){~}
	public CsBoardDTO CsGetArticle(int cs_num) {
		CsBoardDTO article=null;//ArrayList articleList=null;//여러개 담을때
		
		try {
			con=pool.getConnection();
			
			sql="select * from cs_b where cs_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cs_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				 article=new CsBoardDTO();//MemberDTO=>필드별로 담을것.
				  article.setCs_num(rs.getInt("cs_num"));
				  article.setRef(rs.getInt("ref"));
				  
				  article.setM_id(rs.getString("m_id"));
				  article.setAd_id(rs.getString("ad_id"));
				  article.setCs_sub(rs.getString("cs_sub"));
				  article.setCs_content(rs.getString("cs_content"));
				  article.setCs_wdate(rs.getDate("cs_wdate"));
			}
		}catch(Exception e) {
			System.out.println("getArticle() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	
	
	//글수정-----------------------------------------------------------
	public CsBoardDTO CsUpdateGetArticle(int cs_num) {
		
		CsBoardDTO article=null;	
		try {
			con=pool.getConnection();
			sql="select * from cs_b where cs_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cs_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				  article=new CsBoardDTO();
				  article.setCs_num(rs.getInt("cs_num"));
				  article.setRef(rs.getInt("ref"));

				  article.setM_id(rs.getString("m_id"));
				  article.setCs_sub(rs.getString("cs_sub"));
				  article.setCs_wdate(rs.getDate("cs_wdate"));
				  article.setCs_content(rs.getString("cs_content"));
			}
		}catch(Exception e) {
			System.out.println("CsUpdateGetArticle() error=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	
	public int CsUpdateArticle(CsBoardDTO article, String m_id) {

        int x = -1;

        try {
            con = pool.getConnection();
            sql = "select * from cs_b where cs_num=? and m_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, article.getCs_num());
            pstmt.setString(2, m_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                    sql = "update cs_b set cs_sub=?,cs_content=? where cs_num=?";
                    pstmt = con.prepareStatement(sql);

                    pstmt.setString(1, article.getCs_sub());
                    pstmt.setString(2, article.getCs_content());
                    pstmt.setInt(3, article.getCs_num());

                    int update = pstmt.executeUpdate();
                    System.out.println("(update)=>" + update);
                    x = 1;
                } else {
                    x = 0;
            } // if(rs.next())
        } catch (Exception e) {
            System.out.println("CsUpdateArticle() error=>" + e);
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
        return x;
    }
	
	//글삭제=>암호를 비교->delete from board where num=?
		public int CsDeleteArticle(int cs_num,String m_id) { //회원탈퇴와 동일한 기능
			
			int x = -1;// 게시물의 삭제성공유무
			
			System.out.println("CsDeleteArticle에서의 cs_num=>"+cs_num);

			try {
				con = pool.getConnection();
				sql = "select m_id from cs_b where cs_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cs_num);
				rs = pstmt.executeQuery();
				if (rs.next()) {// 보여주는 결과가 있다면
						sql = "delete from cs_b where cs_num=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, cs_num);
						int delete= pstmt.executeUpdate();
						System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);
						x = 1;// 삭제성공유무
						if(x==1) {
							sql = "delete from cs_comb where cs_num=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, cs_num);
							int delete2 = pstmt.executeUpdate();
							System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete2);
							x = 1;// 삭제성공유무
						}
					} else {// 암호가 틀린경우
						x = 0;// 삭제실패
					}
				 // if(rs.next())
			} catch (Exception e) {
				System.out.println("CsDeleteArticle() 메서드 에러유발=>" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return x;
		}
		
		public int AdminCsDeleteArticle(int cs_num) { //회원탈퇴와 동일한 기능
			
			int x = -1;// 게시물의 삭제성공유무
			
			System.out.println("CsDeleteArticle에서의 cs_num=>"+cs_num);

			try {
				con = pool.getConnection();
				sql = "select * from cs_b where cs_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cs_num);
				rs = pstmt.executeQuery();
				if (rs.next()) {// 보여주는 결과가 있다면
						sql = "delete from cs_b where cs_num=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, cs_num);
						int delete= pstmt.executeUpdate();
						System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);
						x = 1;// 삭제성공유무
						if(x==1) {
							sql = "delete from cs_comb where cs_num=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, cs_num);
							int delete2 = pstmt.executeUpdate();
							System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete2);
							x = 1;// 삭제성공유무
						}
					} else {// 암호가 틀린경우
						x = 0;// 삭제실패
					}
				 // if(rs.next())
			} catch (Exception e) {
				System.out.println("CsDeleteArticle() 메서드 에러유발=>" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return x;
		}
		
		public CsBoardDTO AdminCsUpdateGetArticle(int cs_num) {
			
			CsBoardDTO article=null;	
			try {
				con=pool.getConnection();
				sql="select * from cs_b where cs_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, cs_num);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					  article=new CsBoardDTO();
					  article.setCs_num(rs.getInt("cs_num"));
					  article.setRef(rs.getInt("ref"));

					  article.setCs_sub(rs.getString("cs_sub"));
					  article.setCs_wdate(rs.getDate("cs_wdate"));
					  article.setCs_content(rs.getString("cs_content"));
				}
			}catch(Exception e) {
				System.out.println("CsUpdateGetArticle() error=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return article;
		}
		
		
		
		public int AdminCsUpdateArticle(CsBoardDTO article) {

	        int x = -1;

	        try {
	            con = pool.getConnection();
	            sql = "select * from cs_b where cs_num=?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, article.getCs_num());
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                    sql = "update cs_b set cs_sub=?,cs_content=? where cs_num=?";
	                    pstmt = con.prepareStatement(sql);

	                    pstmt.setString(1, article.getCs_sub());
	                    pstmt.setString(2, article.getCs_content());
	                    pstmt.setInt(3, article.getCs_num());

	                    int update = pstmt.executeUpdate();
	                    System.out.println("(update)=>" + update);
	                    x = 1;
	                } else {
	                    x = 0;
	            } // if(rs.next())
	        } catch (Exception e) {
	            System.out.println("CsUpdateArticle() error=>" + e);
	        } finally {
	            pool.freeConnection(con, pstmt, rs);
	        }
	        return x;
	    }
}
