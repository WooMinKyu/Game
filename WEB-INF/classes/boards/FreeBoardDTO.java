package boards;

import java.sql.Date;

public class FreeBoardDTO {

	private int	com_num,//글번호
						com_view,//글조회수
						ref,
						re_step,
						re_level;
	private String m_id,//사용자아이디
						com_nick,//글닉네임
						com_sub,//글제목
						com_wdate,// 작성날짜
						com_content; //글내용
	
	public int getCom_num() {
		return com_num;
	}
	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getCom_nick() {
		return com_nick;
	}
	public void setCom_nick(String com_nick) {
		this.com_nick = com_nick;
	}
	public String getCom_sub() {
		return com_sub;
	}
	public void setCom_sub(String com_sub) {
		this.com_sub = convert(com_sub);
	}
	public String getCom_content() {
		return com_content;
	}
	public void setCom_content(String com_content) {
		this.com_content = convert(com_content);
	}
	public int getCom_view() {
		return com_view;
	}
	public void setCom_view(int com_view) {
		this.com_view = com_view;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public String getCom_wdate() {
		return com_wdate;
	}
	public void setCom_wdate(String com_wdate) {
		this.com_wdate = com_wdate;
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
			