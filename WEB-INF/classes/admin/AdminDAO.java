package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnectionMgr;
import member.MemberDTO;

public class AdminDAO {
	
	//1).멤버변수에 연결할 클래스의 객체를 선언
		private DBConnectionMgr pool=null;//getConnection(), freeConnection()필요
		
		//2.공통으로 접속할 경우 필요로하는 멤버변수 선언(p401 선언 X)
		private Connection con=null;
		private PreparedStatement pstmt=null;//SQL실행 목적
		private ResultSet rs=null;//select 구문
		private String sql="";//실행시킬 SQL구문 저장목적
		
		//2)생성자를 통해서 자동으로 객체를 얻어올 수 있도록 연결		
		public AdminDAO() {
			// DBConnectionMgr의 객체를 얻어오는 구문
			try {
				pool = DBConnectionMgr.getInstance();
				System.out.println("pool=>"+pool);
			} catch(Exception e) {
				System.out.println("Error:커넥션 불러오기 실패"+e);
			}
		}
		
		public boolean loginCheck(String ad_id, String ad_pw) {
			
			boolean check = false; // 로그인 성공유무
			
			//DB 작업(Select)
			try {
				// DB 접속 구문
				con = pool.getConnection();
				String sql = "select ad_id,ad_pw from admin where ad_id=? and ad_pw=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, ad_id);
				pstmt.setString(2, ad_pw);
				rs = pstmt.executeQuery();
				check = rs.next();
				System.out.println("check=>"+check);
			} catch(Exception e) {
				System.out.println("=loginCheck 에러=");
				System.out.println("== 에러라인 41==");
				System.out.println(e);
			} finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return check;
		}
		
		public AdminDTO getMember(String ad_id) {
			//1건의 데이터만 담을 객체선언
			AdminDTO regBean = null;
		    
		    try {
		    	//DB 접속구문
		    	con = pool.getConnection();
		    	sql = "select * from admin where ad_id=?";
		    	pstmt = con.prepareStatement(sql);
		    	pstmt.setString(1, ad_id);
		    	rs = pstmt.executeQuery();
		    	
		    	//검색한 데이터를 찾아서 벡터에 담는 코딩
		    	if(rs.next()) {// 찾은 데이터가 있다면
		    		regBean = new AdminDTO();
		    		regBean.setAd_id(rs.getString("ad_id"));
		    		regBean.setAd_pw(rs.getString("ad_pw"));
		    		regBean.setAd_nick(rs.getString("ad_nick"));
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
}
