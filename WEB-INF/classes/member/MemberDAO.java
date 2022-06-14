package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import db.DBConnectionMgr;
import login.LoginDTO;

public class MemberDAO {
	
	//1).멤버변수에 연결할 클래스의 객체를 선언
	private DBConnectionMgr pool=null;//getConnection(), freeConnection()필요
	
	//2.공통으로 접속할 경우 필요로하는 멤버변수 선언(p401 선언 X)
	private Connection con=null;
	private PreparedStatement pstmt=null;//SQL실행 목적
	private ResultSet rs=null;//select 구문
	private String sql="";//실행시킬 SQL구문 저장목적
	
	//2)생성자를 통해서 자동으로 객체를 얻어올 수 있도록 연결		
	public MemberDAO() {
		// DBConnectionMgr의 객체를 얻어오는 구문
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool=>"+pool);
		} catch(Exception e) {
			System.out.println("Error:커넥션 불러오기 실패"+e);
		}
	}
	// 1. 회원인지를 체크해주는 메서드(인증)
	public boolean loginCheck(String m_id,String m_pw,String ad_id,String ad_pw,String m_status) {
		
		boolean check = false; // 로그인 성공유무
		String status="";
		
		//DB 작업(Select)
		try {
			// DB 접속 구문
			con = pool.getConnection();
			if(ad_id.equals("admin")) {
				sql="select ad_id,ad_pw from admin where ad_id=? and ad_pw=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, ad_id);
				pstmt.setString(2, ad_pw);
				rs = pstmt.executeQuery();
				check = rs.next();
				System.out.println("Admin check=>"+check);					
			}else if(m_id!=("admin")){
				sql = "select m_id,m_pw,m_status from member where m_id=? and m_pw=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, m_id);
				pstmt.setString(2, m_pw);
				pstmt.setString(3, m_status);
				rs = pstmt.executeQuery();
				
				if(rs.next()){//찾는 값이 있다면
					status = rs.getString("m_status");
					
					if(status=="active"){//값이 active라면
						check = rs.next();
						System.out.println("Member check=>"+check);
					}
				}
			}
		} catch(Exception e) {
			System.out.println("=loginCheck 에러=");
			System.out.println("== 에러라인 41==");
			System.out.println(e);
		} finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return check;
	}	
			
	// 2. 중복ID를 체크하는 메서드
	
		public boolean checkId(String m_id) {
			
			boolean check = false; // 중복 ID 유무
			
			// DB작업(Select)
			try {
				// DB 접속구문
				con = pool.getConnection();
				System.out.println("con=>"+con);
				String sql = "select m_id from member where m_id=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,m_id);
				rs = pstmt.executeQuery();
				check = rs.next(); // true
			} catch(Exception e) {
				System.out.println("=checkId()에러=");
				System.out.println("==에러라인 75==");
			    System.out.println(e);
			} finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return check;
		}
		
		// 3. 회원가입	
				public boolean memberInsert(MemberDTO regBean) {
					//DB접속
					Connection con = null;
					PreparedStatement pstmt= null;
					boolean check = false; // 회원가입 성공유무
					
					try {
						// DB접속 구문
						con = pool.getConnection();
						con.setAutoCommit(false);
						
						String sql="insert into member values(?,?,?,?,?,?,?,'active',sysdate)";
						
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, regBean.getM_id());
						pstmt.setString(2, regBean.getM_pw());
						pstmt.setString(3, regBean.getM_hint());
						pstmt.setString(4, regBean.getM_name());
						pstmt.setString(5, regBean.getM_nick());
						pstmt.setString(6, regBean.getM_mobile());
						pstmt.setString(7, regBean.getM_birth());
						
						int memberInsert = pstmt.executeUpdate(); 
						if(memberInsert > 0) { //if(insert==1)
							
							String sql2="insert into login values(?,?,?,'active')";
							
							pstmt = con.prepareStatement(sql2);
							pstmt.setString(1, regBean.getM_id());
							pstmt.setString(2, regBean.getM_pw());
							pstmt.setString(3, regBean.getM_nick());
							
							int loginInsert = pstmt.executeUpdate(); // 반환값 1 (성공) 0(실패)
							System.out.println("insert=>"+loginInsert);
							con.commit();
							check=true;
						}
					} catch(Exception e) {
						System.out.println("=memberInsert()에러=");
						System.out.println("==에러라인 152==");
					    System.out.println(e);
					} finally {
						pool.freeConnection(con,pstmt);
					}
					return check;
				}
		
		// 4. 정보수정->회원정보 찾기
		public MemberDTO getMember(String m_id) {
			//1건의 데이터만 담을 객체선언
		    MemberDTO regBean = null;
		    
		    try {
		    	//DB 접속구문
		    	con = pool.getConnection();
		    	sql = "select * from member where m_id=?";
		    	pstmt = con.prepareStatement(sql);
		    	pstmt.setString(1, m_id);
		    	rs = pstmt.executeQuery();
		    	
		    	//검색한 데이터를 찾아서 벡터에 담는 코딩
		    	if(rs.next()) {// 찾은 데이터가 있다면
		    		regBean = new MemberDTO();
		    		regBean.setM_id(rs.getString("m_id"));
		    		regBean.setM_pw(rs.getString("m_pw"));
		    		regBean.setM_hint(rs.getString("m_hint"));
		    		regBean.setM_name(rs.getString("m_name"));
		    		regBean.setM_nick(rs.getString("m_nick"));
		    		regBean.setM_mobile(rs.getString("m_mobile"));
		    		regBean.setM_birth(rs.getString("m_birth"));
		    		regBean.setM_status(rs.getString("m_status"));
		    		regBean.setM_join(rs.getDate("m_join"));
		    	}
		    } catch(Exception e) {
		      System.out.println("=getMember()에러=");
		  	  System.out.println("==에러라인 202==");
		      System.out.println(e);
		    } finally {	//DB객체를 해제
		        pool.freeConnection(con,pstmt,rs);
			}
		    return regBean;
		}
		
		//5. 찾은 회원을 수정=>회원가입해주는 메서드와 동일(sql구문이 다르다)
		public boolean memberUpdate(MemberDTO regBean) {//SangDTO sang
			boolean check=false;//회원수정 성공유무
			
			//에러가 발생이 되면 흐름에 따라서 디버깅 코딩을 해야 한다.
			System.out.println("==memberUpdate() 내부==");
			System.out.println("regBean.getM_id()=>"+regBean.getM_id());//null
			System.out.println("regBean.getM_pw()=>"+regBean.getM_pw());//null
			System.out.println("regBean.getM_hint()=>"+regBean.getM_hint());//null
			System.out.println("regBean.getM_name()=>"+regBean.getM_name());//null
			System.out.println("regBean.getM_nick()=>"+regBean.getM_nick());//null
			System.out.println("regBean.getM_birth()=>"+regBean.getM_birth());//null
			System.out.println("regBean.getM_join()=>"+regBean.getM_join());//null
			//----------------------------------------------------------------------
			try {
				con=pool.getConnection();
				//추가
				con.setAutoCommit(false);//default->con.setAutoCommit(true);
				//----------------------------
				sql="update member set m_pw=?,m_name=?,m_nick=?,"
				       +" m_mobile=?, m_birth=? where m_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, regBean.getM_pw());
				pstmt.setString(2, regBean.getM_name());
				pstmt.setString(3, regBean.getM_nick());
				pstmt.setString(4, regBean.getM_mobile());
				pstmt.setString(5, regBean.getM_birth());
				pstmt.setString(6, regBean.getM_id());//null
				
				int update=pstmt.executeUpdate();//반환값 1(성공), 0 (실패) (메모리에 insert 성공)
				if(update > 0) { //if(insert==1)
					
					String sql2="update login set m_pw=?,m_nick=? where m_id=?";
					
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, regBean.getM_pw());
					pstmt.setString(2, regBean.getM_nick());
					pstmt.setString(3, regBean.getM_id());
					
					int loginUpdate = pstmt.executeUpdate(); // 반환값 1 (성공) 0(실패)
					System.out.println("update=>"+loginUpdate);
					con.commit();
					check=true;
				}
				con.commit();//실제 테이블에 반영
				System.out.println("update(데이터 수정유무)=>"+update);
				if(update==1) {
					check=true;//데이터 수정성공확인
				}	
			}catch(Exception e) {
				System.out.println("memberUpdate() 실행오류=>"+e);//e.toString()
				try{con.rollback();}catch(Exception e2) {e2.printStackTrace();}
			}finally {
				pool.freeConnection(con,pstmt);//rs->select구문할때만 필요
			}
			return check;//memberUpdate.jsp에서 메서드의 반환값
		}
		
		//============================================================
		//7.회원삭제(수정으로 비활성화 시킴)
		public boolean memberDelete(MemberDTO regBean) {//SangDTO sang
			boolean check=false;//회원수정 성공유무
			
			//에러가 발생이 되면 흐름에 따라서 디버깅 코딩을 해야 한다.
			System.out.println("==memberDelete() 내부==");
			System.out.println("regBean.getM_id()=>"+regBean.getM_id());//null
			System.out.println("regBean.getM_pw()=>"+regBean.getM_pw());//null
			System.out.println("regBean.getM_hint()=>"+regBean.getM_hint());//null
			System.out.println("regBean.getM_name()=>"+regBean.getM_name());//null
			System.out.println("regBean.getM_nick()=>"+regBean.getM_nick());//null
			System.out.println("regBean.getM_birth()=>"+regBean.getM_birth());//null
			System.out.println("regBean.getM_birth()=>"+regBean.getM_status());//null
			System.out.println("regBean.getM_join()=>"+regBean.getM_join());//null
			//----------------------------------------------------------------------
			try {
				con=pool.getConnection();
				//추가
				con.setAutoCommit(false);//default->con.setAutoCommit(true);
				//----------------------------
				sql="update login set m_nick='탈퇴회원',m_status='unactive' where m_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, regBean.getM_id());//null
				
				int delete=pstmt.executeUpdate();//반환값 1(성공), 0 (실패) (메모리에 insert 성공)
				if(delete > 0) { //if(insert==1)
					
					String sql2="update member set m_nick='탈퇴회원',m_status='unactive' where m_id=?";
					
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, regBean.getM_id());
					
					int loginDelete = pstmt.executeUpdate(); // 반환값 1 (성공) 0(실패)
					System.out.println("update=>"+loginDelete);
					con.commit();
					check=true;
				}
				con.commit();//실제 테이블에 반영
				System.out.println("update(데이터 수정유무)=>"+delete);
				if(delete==1) {
					check=true;//데이터 수정성공확인
				}	
			}catch(Exception e) {
				System.out.println("memberUpdate() 실행오류=>"+e);//e.toString()
				try{con.rollback();}catch(Exception e2) {e2.printStackTrace();}
			}finally {
				pool.freeConnection(con,pstmt);//rs->select구문할때만 필요
			}
			return check;//memberUpdate.jsp에서 메서드의 반환값
		}
		
		
		
		
		// //////////////////Member Management ///////////////////////////
		public int getMemberSearchCount(String search,String searchtext) { //getMemberCount() ->MemberDAO에서 작성
			int x=0;//총레코드갯수를 저장
			try {
				con=pool.getConnection();
				System.out.println("con=>"+con);
				//-----------------------------------------------
				if(search=="" || search==null) {//검색분야 선택X(검색어를 입력하지 않은 경우)
					sql="select count(*) from member";
				}else { // 제목, 장르 검색
					sql="select count(*) from member where "+search+" like '%"+searchtext+"%'";
				}
				System.out.println("getMemberSearchCount 검색sql=>"+sql);
				//------------------------------------------------
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {//보여주는 결과가 있다면
					x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X(그룹함수)
					System.out.println("getMemberSearchCount()을 통한 총갯수구현(x)=>"+x);
				}
			}catch(Exception e) {
				System.out.println("getMemberSearchCount() 에러유발=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return x;
		}
		
		// Get Member
		public List getAreaMemeber(int start,int end,String search,String searchtext) {//getMemberList(int start,int end){
			
			List articleList=null;//ArrayList articleList=null;//(O)
			
			try {
				con=pool.getConnection();
			    //-----------------------------------------------------------------
				if(search==null || search=="") {
					//sql="select * from member";
					sql="select * from member m, login l where m.m_id=l.m_id";
				}else {
					//sql="select * from store_b where "+search+" like  '%"+ searchtext+"%' order by item_indate";
					sql="select * from member where "+search+" like '%"+searchtext+"%'";
					//sql="select * from (select rownum as rn, a.* from (select * from store_b order by item_indate desc)a)  where rn >=? AND rn <=?";
				}
					System.out.println("getAreaMemeber()의 sql=>"+sql);
					//------------------------------------------------------------------
					pstmt=con.prepareStatement(sql);
					rs=pstmt.executeQuery();
					
				if(rs.next()) {//보여주는 결과가 있다면
					articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간을 만들어라
					do {
						MemberDTO mdto = new MemberDTO();
						mdto.setM_id(rs.getString("m_id"));
						mdto.setM_pw(rs.getString("m_pw"));
						mdto.setM_hint(rs.getString("m_hint"));
						mdto.setM_name(rs.getString("m_name"));
						mdto.setM_nick(rs.getString("m_nick"));
						mdto.setM_mobile(rs.getString("m_mobile"));
						mdto.setM_birth(rs.getString("m_birth"));
						mdto.setM_status(rs.getString("m_status"));
						mdto.setM_join(rs.getDate("m_join"));
		  
					  articleList.add(mdto);//생략하면 데이터가 저장X->for문 에러유발
					}while(rs.next());
				}
			}catch(Exception e) {
				System.out.println("getAreaMemeber() 에러유발=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return articleList;
		}
		
		// PageList
		public Hashtable pageList(String pageNum,int count) {
			
			//1.페이징 처리결과를 저장할 Hashtable객체를 선언
			Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();

		    int pageSize=100;//numPerPage->페이지당 보여주는 게시물수(=레코드수) 10
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
		
		// Member Status Update
		public int MemUpdateArticle(MemberDTO article) {// insertArticle(BoardDTO article)

			int x = -1;// 게시물의 수정성공유무

			try {
				con = pool.getConnection();
				sql = "select * from member where m_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, article.getM_id());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					sql = "update member set m_status=? where m_id=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setString(1, article.getM_status());
						pstmt.setString(2, article.getM_id());
						
						int update = pstmt.executeUpdate();
						System.out.println("MemUpdateArticle =>" + update);
						x = 1;// 수정성공유무
					}
			} catch (Exception e) {
				System.out.println("MemUpdateArticle() 메서드 에러유발=>" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return x;
		}
		
		// Login Status Update
				public int LogUpdateArticle(LoginDTO ldto) {// insertArticle(BoardDTO article)

					int x = -1;// 게시물의 수정성공유무

					try {
						con = pool.getConnection();
						sql = "select * from login where m_id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, ldto.getM_id());
						rs = pstmt.executeQuery();
						if (rs.next()) {
							sql = "update login set m_status=? where m_id=?";
								pstmt=con.prepareStatement(sql);
								pstmt.setString(1, ldto.getM_status());
								pstmt.setString(2, ldto.getM_id());
								
								int update = pstmt.executeUpdate();
								System.out.println("LogUpdateArticle =>" + update);
								x = 1;// 수정성공유무
							}
					} catch (Exception e) {
						System.out.println("LogUpdateArticle() 메서드 에러유발=>" + e);
					} finally {
						pool.freeConnection(con, pstmt, rs);
					}
					return x;
				}
}

