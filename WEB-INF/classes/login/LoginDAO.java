package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnectionMgr;

public class LoginDAO {
	
	//1).멤버변수에 연결할 클래스의 객체를 선언
		private DBConnectionMgr pool=null;//getConnection(), freeConnection()필요
		
		//2.공통으로 접속할 경우 필요로하는 멤버변수 선언(p401 선언 X)
		private Connection con=null;
		private PreparedStatement pstmt=null;//SQL실행 목적
		private ResultSet rs=null;//select 구문
		private String sql="";//실행시킬 SQL구문 저장목적
		
		//2)생성자를 통해서 자동으로 객체를 얻어올 수 있도록 연결		
		public LoginDAO() {
			// DBConnectionMgr의 객체를 얻어오는 구문
			try {
				pool = DBConnectionMgr.getInstance();
				System.out.println("pool=>"+pool);
			} catch(Exception e) {
				System.out.println("Error:커넥션 불러오기 실패"+e);
			}
		}
		
		public int loginCheck(String m_id, String m_pw) {
			
			
			int x=0;
			//DB 작업(Select)
			try {
				// DB 접속 구문
				con = pool.getConnection();
				String sql = "select * from login where m_id=? and m_pw=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, m_id);
				pstmt.setString(2, m_pw);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String m_status=rs.getString("m_status");
					System.out.println("m_status=>"+m_status);
					if(m_status.equals("active")) {
						x=1;
					} else if(m_status.equals("ban")) {
						x=-1;
					} else if(m_status.equals("unactive")) {
						x=-2;
					} 
				}
				System.out.println("check=>"+x);
			} catch(Exception e) {
				System.out.println("=loginCheck 에러=");
				System.out.println("== 에러라인 41==");
				System.out.println(e);
			} finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return x;
		}
		
		public boolean UpDeCheck(String m_id, String m_pw,String m_status) {
			
			boolean check = false; // 로그인 성공유무
			
			//DB 작업(Select)
			try {
				// DB 접속 구문
				con = pool.getConnection();
				String sql = "select * from login where m_id=? and m_pw=? and m_status=? ";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, m_id);
				pstmt.setString(2, m_pw);
				pstmt.setString(3, m_status);
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
		public String getNick(String m_id) {
			String nick="";
			try {
				// DB 접속 구문
				con = pool.getConnection();
				sql = "select m_nick from login where m_id=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, m_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					nick=rs.getString("m_nick");
				}

			} catch(Exception e) {
				System.out.println("=getNick 에러=");
				System.out.println("== 에러라인 41==");
				System.out.println(e);
			} finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return nick;
		}
		public LoginDTO getLogStat(String m_id) {
			LoginDTO ldto=null;
			
			try {
				// DB 접속 구문
				con = pool.getConnection();
				sql = "select m_id, m_status from login where m_id=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, m_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					ldto=new LoginDTO();
					ldto.setM_id(rs.getString("m_id"));
					ldto.setM_status(rs.getString("m_status"));
				}

			} catch(Exception e) {
				System.out.println("=getLogStat 에러=");
				System.out.println("== 에러라인 41==");
				System.out.println(e);
			} finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return ldto;
		}
}
