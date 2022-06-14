package boards;

import java.sql.Date;

public class CsBoardDTO {

	private int cs_num, ref;
	private String m_id, ad_id, cs_sub, cs_content;	
	private Date cs_wdate;
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public int getCs_num() {
		return cs_num;
	}
	public void setCs_num(int cs_num) {
		this.cs_num = cs_num;
	}
	public String getCs_sub() {
		return cs_sub;
	}
	public void setCs_sub(String cs_sub) {
		this.cs_sub = convert(cs_sub);
	}
	public String getCs_content() {
		return cs_content;
	}
	public void setCs_content(String cs_content) {
		this.cs_content = convert(cs_content);
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public Date getCs_wdate() {
		return cs_wdate;
	}
	public void setCs_wdate(Date cs_wdate) {
		this.cs_wdate = cs_wdate;
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
			