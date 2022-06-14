package member;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberDTO {
	
	private String m_id; // 회원 아이디
	private String m_pw; // 비밀번호
	private String m_hint; //  비밀번호 힌트
	private String m_name; // 이름
	private String m_nick; //닉네임
	private String m_mobile; // 전번
	private String m_birth; // 생일
	private String m_status;//상태
	private Date m_join; // 가입일
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = convert(m_id);
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = convert(m_pw);
	}
	public String getM_hint() {
		return m_hint;
	}
	public void setM_hint(String m_hint) {
		this.m_hint = convert(m_hint);
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = convert(m_name);
	}
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = convert(m_nick);
	}
	public String getM_mobile() {
		return m_mobile;
	}
	public void setM_mobile(String m_mobile) {
		this.m_mobile = convert(m_mobile);
	}
	public String getM_birth() {
		return m_birth;
	}
	public void setM_birth(String m_birth) {
		this.m_birth = convert(m_birth);
	}
	public String getM_status() {
		return m_status;
	}
	public void setM_status(String m_status) {
		this.m_status = m_status;
	}
	public Date getM_join() {
		return m_join;
	}
	public void setM_join(Date m_join) {
		this.m_join = m_join;
	}
	//이 클래스에서만 사용하기위해서 접근지정자 private <,>,(,)=>변경메서드
		private static String convert(String name) {
			if(name!=null){
		    	//2.입력받은 문자열중에서 자바스크립트 구문을 실행시킬수 있는 특수기호를 입력X(<,>)
		    	//문자열메서드->replaceAll(1.변경전문자열,2.변경후 문자열)
		    	
		    	name=name.replaceAll("<","&lt");
		    	name=name.replaceAll(">","&gt");
		    	//추가 eval(" " or ' ')
		    	name=name.replaceAll("\\(","&#40");
		    	name=name.replaceAll("\\)","&#41");
		    	//"test"  'test'
		    	name=name.replaceAll("\"","&quot");
		    	name=name.replaceAll("\'","&apos");
		    }else{ //name==null
		    	return null; //입력을 하지 않았다면 더 이상 실행X
		    }
			return name;
		}
}
