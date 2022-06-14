package boards;

import java.sql.Date;

public class HelpBoardDTO {

	private int	help_num, help_view;

	private String ad_id, help_nick,	help_sub, help_wdate, help_content;

	public int getHelp_num() {
		return help_num;
	}

	public void setHelp_num(int help_num) {
		this.help_num = help_num;
	}

	public int getHelp_view() {
		return help_view;
	}

	public void setHelp_view(int help_view) {
		this.help_view = help_view;
	}
	
	public String getHelp_nick() {
		return help_nick;
	}

	public void setHelp_nick(String help_nick) {
		this.help_nick = help_nick;
	}

	public String getHelp_sub() {
		return help_sub;
	}

	public void setHelp_sub(String help_sub) {
		this.help_sub = convert(help_sub);
	}

	public String getHelp_wdate() {
		return help_wdate;
	}

	public void setHelp_wdate(String help_wdate) {
		this.help_wdate = help_wdate;
	}

	public String getHelp_content() {
		return help_content;
	}

	public void setHelp_content(String help_content) {
		this.help_content = convert(help_content);
	}

	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
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
			