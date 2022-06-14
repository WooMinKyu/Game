package store;
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

public class StoreDAO { //MemberDAO

	private DBConnectionMgr pool=null;//1.연결객체선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;//select
	private String sql="";//실행시킬 SQL구문 저장
	
	//2.생성자를 통해서 연결=>의존관계
	public StoreDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}//생성자
	
	//1.페이징 처리를 위한 전체레코드수를 구해와야 한다.
	//select count(*) from board;    select count(*) from member
	public int getArticleCount() { //getMemberCount() ->MemberDAO에서 작성
		int x=0;//총레코드갯수를 저장
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			sql="select count(*) from store_b";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X(그룹함수)
				System.out.println("getArticleCount()을 통한 총갯수구현(x)=>"+x);
			}
		}catch(Exception e) {
			System.out.println("getArticleCount() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//검색어에 해당되는 총레코드수를 구하는 메서드(검색분야,검색어)
	public int getArticleSearchCount(String search,String searchtext) { //getMemberCount() ->MemberDAO에서 작성
		int x=0;//총레코드갯수를 저장
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			//-----------------------------------------------
			if(search=="" || search==null) {//검색분야 선택X(검색어를 입력하지 않은 경우)
				sql="select count(*) from store_b";
			}else if(search.equals("LowToHigh")) {
				sql="select count(*) from store_b order by item_price asc"; 
			}else if(search.equals("HighToLow")) {
				sql="select count(*) from store_b order by item_price desc"; 
			}else if(search.equals("Best")) {
				sql="select count(*) from store_b order by item_count asc"; 
			}else { // 제목, 장르 검색
				sql="select count(*) from store_b where "+search+" like '%"+searchtext+"%'";
			}
			System.out.println("getArticleSearchCount 검색sql=>"+sql);
			//------------------------------------------------
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X(그룹함수)
				System.out.println("getArticleSearchCount()을 통한 총갯수구현(x)=>"+x);
			}
		}catch(Exception e) {
			System.out.println("getArticleSearchCount() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//2.글목록보기에 대한 메서드구현->레코드 한개이상->한 페이지당 10개씩 끊어서 보여준다.
	//1)레코드의 시작번호  2) 불러올 레코드의 갯수
	public List getArticles(int start,int end) {//getMemberList(int start,int end){
		
		List articleList=null;//ArrayList articleList=null;//(O)
		
		try {
			con=pool.getConnection();
			/*
			 * 그룹번호가 가장 최신의 글을 중심으로 정렬하되,만약에 level이 같은 경우에는
			 * step값으로 오름차순을 통해서  몇번째 레코드번호를 기준해서 몇개까지 정렬할것인가를
			 * 지정해주는 SQL구문
			 */
			
			sql="select * from (select rownum as rn, a.* from (select * from store_b order by item_name desc)a)  where rn >=? AND rn <=?";
			System.out.println("getArticles의 sql=>"+sql);
			 
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, start+end-1);
			
			/* DAO2 주석처리 되어있음
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			*/
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간을 만들어라
				do {
					StoreDTO article=new StoreDTO();//MemberDTO=>필드별로 담을것.
					article.setItem_num(rs.getString("item_num"));//상품번호
					article.setAd_id(rs.getString("ad_id"));//관리자아이디
					article.setItem_name(rs.getString("item_name"));//상품이름
					article.setItem_img(rs.getString("item_img"));//상품이미지
					article.setItem_thum(rs.getString("item_thum"));//상품썸네일
					article.setItem_content(rs.getString("item_content"));//상품내용
					article.setItem_price(rs.getString("item_price"));//상품가격
					article.setItem_rels(rs.getString("item_rels"));//상품출시일
					article.setItem_count(rs.getInt("item_count"));//상품구매수
					article.setItem_gen(rs.getString("item_gen"));//상품장르
					article.setItem_grd(rs.getString("item_grd"));//상품등급
					article.setGen_num(rs.getString("gen_num"));//장르번호
					article.setGrd_num(rs.getString("grd_num"));//등급번호
					article.setItem_dev(rs.getString("item_dev"));//개발사
					article.setItem_pub(rs.getString("item_pub"));//퍼블리셔
					article.setItem_pf(rs.getString("item_pf"));//플랫폼
					article.setItem_ft(rs.getString("item_ft"));//기능
					article.setItem_sale(rs.getInt("item_sale"));//상품할인				  
					article.setItem_indate(rs.getString("item_indate"));//상품등록일
	  
				  articleList.add(article);//생략하면 데이터가 저장X->for문 에러유발
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("getArticleCount() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}
	//(2)검색어에 따른 레코드의 범위지정에 대한 메서드
	public List getBoardArticles(int start,int end,String search,String searchtext) {//getMemberList(int start,int end){
		
		List articleList=null;//ArrayList articleList=null;//(O)
		
		try {
			con=pool.getConnection();
		    //-----------------------------------------------------------------
			if(search==null || search=="") {
				sql="select * from (select rownum as rn, a.* from (select * from store_b order by item_indate desc)a)  where rn >=? AND rn <=?";
			}else if(search.equals("LowToHigh")) {
				sql="select * from (select rownum as rn, a.* from (select * from store_b order by item_price asc)a)  where rn >=? AND rn <=?";
			}else if(search.equals("HighToLow")) {
				sql="select * from (select rownum as rn, a.* from (select * from store_b order by item_price desc)a)  where rn >=? AND rn <=?";
			}else if(search.equals("Best")) {
				sql="select * from (select rownum as rn, a.* from (select * from store_b order by item_count asc)a)  where rn >=? AND rn <=?";
			}else {
				//sql="select * from store_b where "+search+" like  '%"+ searchtext+"%' order by item_indate";
				sql="select * from (select rownum as rn, a.* from (select * from store_b where "+search+" like '%"+searchtext+"%' order by item_indate)a) where rn >=? AND rn <=?";
				//sql="select * from (select rownum as rn, a.* from (select * from store_b order by item_indate desc)a)  where rn >=? AND rn <=?";
			}
				System.out.println("StoreGetBoardArticles()의 sql=>"+sql);
				//------------------------------------------------------------------
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, start+end-1);
				rs=pstmt.executeQuery();
				
			if(rs.next()) {//보여주는 결과가 있다면
				articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간을 만들어라
				do {
				  StoreDTO article=new StoreDTO();//MemberDTO=>필드별로 담을것.
				  article.setItem_num(rs.getString("item_num"));//상품번호
				  article.setAd_id(rs.getString("ad_id"));//관리자아이디
				  article.setItem_name(rs.getString("item_name"));//상품이름
				  article.setItem_img(rs.getString("item_img"));//상품이미지
				  article.setItem_thum(rs.getString("item_thum"));//상품썸네일
				  article.setItem_content(rs.getString("item_content"));//상품내용
				  article.setItem_price(rs.getString("item_price"));//상품가격
				  article.setItem_rels(rs.getString("item_rels"));//상품출시일
				  article.setItem_count(rs.getInt("item_count"));//상품구매수
				  article.setItem_gen(rs.getString("item_gen"));//상품장르
				  article.setItem_grd(rs.getString("item_grd"));//상품등급
				  article.setGen_num(rs.getString("gen_num"));//장르번호
				  article.setGrd_num(rs.getString("grd_num"));//등급번호
				  article.setItem_dev(rs.getString("item_dev"));//개발사
				  article.setItem_pub(rs.getString("item_pub"));//퍼블리셔
				  article.setItem_pf(rs.getString("item_pf"));//플랫폼
				  article.setItem_ft(rs.getString("item_ft"));//기능
				  article.setItem_sale(rs.getInt("item_sale"));//상품할인				  
				  article.setItem_indate(rs.getString("item_indate"));//상품등록일
	  
				  articleList.add(article);//생략하면 데이터가 저장X->for문 에러유발
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("getBoardArticles() 에러유발=>"+e);
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

	    int pageSize=16;//numPerPage->페이지당 보여주는 게시물수(=레코드수) 10
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
	
	//----게시판의 글쓰기 및 답변글쓰기-----
	//insert into board values(?,?,,,,
	public void insertArticle(StoreDTO article) {//~(MemberDTO mem)
		
		int number=0;//데이터를 저장하기위한 게시물번호 
		
		try {
			con=pool.getConnection();
			sql="select count(*) from store_b";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				number=rs.getInt(1)+1;//최대값+1
			}else {
				number=1;//테이블에 한개의 데이터가 없다면
			}
			
			//18개=18->item_num,ad_id,item_name,item_img,item_thum,item_content,item_price,item_rels,item_count,item_gen,item_grd,gen_num,grd_num,item_indate,item_dev,item_pub,item_pf,item_ft
			//indate,count = default
			//작성날짜=>sysdate
			
			sql="insert into store_b(item_num,ad_id,item_name,item_img,item_thum,item_content,item_price,item_rels,item_gen,item_grd,gen_num,grd_num,item_dev,item_pub,item_pf,item_ft,item_sale)";
			sql+="values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, article.getGen_num()+article.getGrd_num()+Integer.toString(number));//상품번호
			pstmt.setString(2, article.getAd_id());//관리자아이디
			pstmt.setString(3, article.getItem_name());//상품이름
			pstmt.setString(4, article.getItem_img());//상품이미지
			pstmt.setString(5, article.getItem_thum());//상품섬네일
			pstmt.setString(6, article.getItem_content());//상품내용
			pstmt.setString(7, article.getItem_price());//상품가격
			pstmt.setString(8, article.getItem_rels());//출시일		
			pstmt.setString(9, article.getItem_gen());//장르
			pstmt.setString(10, article.getItem_grd());//등급
			pstmt.setString(11, article.getGen_num());//장르번호
			pstmt.setString(12, article.getGrd_num());//등급번호
			pstmt.setString(13, article.getItem_dev());//개발사
			pstmt.setString(14, article.getItem_pub());//퍼블리셔
			pstmt.setString(15, article.getItem_pf());//플랫폼
			pstmt.setString(16, article.getItem_ft());//기능
			pstmt.setInt(17, article.getItem_sale());//기능
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
	public StoreDTO getArticle(String item_num) {
		StoreDTO article=null;//ArrayList articleList=null;//여러개 담을때
		
		try {
			con=pool.getConnection();
				
			sql="select * from store_b where item_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, item_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				  article=new StoreDTO();//MemberDTO=>필드별로 담을것.
				  article.setItem_num(rs.getString("item_num"));//상품번호
				  article.setAd_id(rs.getString("ad_id"));//관리자아이디
				  article.setItem_name(rs.getString("item_name"));//상품이름
				  article.setItem_img(rs.getString("item_img"));//상품이미지
				  article.setItem_thum(rs.getString("item_thum"));//상품섬네일
				  article.setItem_content(rs.getString("item_content"));//상품내용
				  article.setItem_price(rs.getString("item_price"));//상품가격
				  article.setItem_rels(rs.getString("item_rels"));//상품출시일
				  article.setItem_indate(rs.getString("item_indate"));//상품등록일
				  article.setItem_dev(rs.getString("item_dev"));//개발사
				  article.setItem_pub(rs.getString("item_pub"));//퍼블리셔
				  article.setItem_pf(rs.getString("item_pf"));//플랫폼
				  article.setItem_ft(rs.getString("item_ft"));//기능
				  article.setItem_gen(rs.getString("item_gen"));//장르
				  article.setItem_grd(rs.getString("item_grd"));//등급
				  article.setGen_num(rs.getString("gen_num"));//장르번호
				  article.setGrd_num(rs.getString("grd_num"));//등급번호
				  article.setItem_count(rs.getInt("item_count"));//구매수	
				  article.setItem_sale(rs.getInt("item_sale"));//할인	
			}
		}catch(Exception e) {
			System.out.println("getArticle() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	
	//------------------------------------------------------------------
	//글수정
	//1)수정할 데이터를 찾을 메서드 필요=>select * from board where num=?
	public StoreDTO  updateGetArticle(String item_num) {
		
      StoreDTO article=null;//ArrayList articleList=null;//여러개 담을때	
		try {
			con=pool.getConnection();
			sql="select * from store_b where item_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, item_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				article=new StoreDTO();//MemberDTO=>필드별로 담을것.
				  article.setItem_num(rs.getString("item_num"));//상품번호
				  article.setAd_id(rs.getString("ad_id"));//관리자아이디
				  article.setItem_name(rs.getString("item_name"));//상품이름
				  article.setItem_img(rs.getString("item_img"));//상품이미지
				  article.setItem_thum(rs.getString("item_thum"));//상품섬네일
				  article.setItem_content(rs.getString("item_content"));//상품내용
				  article.setItem_price(rs.getString("item_price"));//상품가격
				  article.setItem_rels(rs.getString("item_rels"));//상품출시일
				  article.setItem_indate(rs.getString("item_indate"));//상품등록일
				  article.setItem_dev(rs.getString("item_dev"));//개발사
				  article.setItem_pub(rs.getString("item_pub"));//퍼블리셔
				  article.setItem_pf(rs.getString("item_pf"));//플랫폼
				  article.setItem_ft(rs.getString("item_ft"));//기능
				  article.setItem_gen(rs.getString("item_gen"));//장르
				  article.setItem_grd(rs.getString("item_grd"));//등급
				  article.setGen_num(rs.getString("gen_num"));//장르번호
				  article.setGrd_num(rs.getString("grd_num"));//등급번호
				  article.setItem_count(rs.getInt("item_count"));//구매수	
				  article.setItem_sale(rs.getInt("item_sale"));//할인
			}
		}catch(Exception e) {
			System.out.println("updateGetArticle() 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	//2)수정시키는 메서드 작성=>본인인지 확인절차=>회원탈퇴(암호를 비교=->탈퇴)와 동일한 기능
	public int updateArticle(StoreDTO article) {// insertArticle(BoardDTO article)

		int x = -1;// 게시물의 수정성공유무

		try {
			con = pool.getConnection();
			sql = "select * from store_b where item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getItem_num());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sql = "update store_b set ad_id=?,item_name=?,item_img=?,item_thum=?,item_content=?,item_price=?,item_rels=?,item_gen=?,item_grd=?,gen_num=?,grd_num=?,item_dev=?,item_pub=?,item_pf=?,item_ft=?,item_sale=? where item_num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, article.getAd_id());//관리자아이디 //
					pstmt.setString(2, article.getItem_name());//상품이름
					pstmt.setString(3, article.getItem_img());//상품이미지
					pstmt.setString(4, article.getItem_thum());//상품섬네일
					pstmt.setString(5, article.getItem_content());//상품내용
					pstmt.setString(6, article.getItem_price());//상품가격
					pstmt.setString(7, article.getItem_rels());//출시일		
					pstmt.setString(8, article.getItem_gen());//장르
					pstmt.setString(9, article.getItem_grd());//등급
					pstmt.setString(10, article.getGen_num());//장르번호
					pstmt.setString(11, article.getGrd_num());//등급번호
					pstmt.setString(12, article.getItem_dev());//개발사
					pstmt.setString(13, article.getItem_pub());//퍼블리셔
					pstmt.setString(14, article.getItem_pf());//플랫폼
					pstmt.setString(15, article.getItem_ft());//기능
					pstmt.setInt(16, article.getItem_sale());//상품번호
					pstmt.setString(17, article.getItem_num());//상품번호
					
					int update = pstmt.executeUpdate();
					System.out.println("게시판의 글수정 성공유무(update)=>" + update);
					x = 1;// 수정성공유무
				}
		} catch (Exception e) {
			System.out.println("updateArticle() 메서드 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//글삭제=>
	public int deleteArticle(String item_num) { //회원탈퇴와 동일한 기능
		
		int x = -1;// 게시물의 삭제성공유무
		
		try {
			con = pool.getConnection();
			sql = "select * from store_b where item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, item_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
					sql = "delete from store_b where item_num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, item_num);
					int delete= pstmt.executeUpdate();
					System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);
					x = 1;// 삭제성공유무
				}  // if(rs.next())
		} catch (Exception e) {
			System.out.println("deleteArticle() 메서드 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
}
