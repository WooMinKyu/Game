package payment;

public class PayDTO {

	private String pur_num;
	private int pur_stat; // boolean 필요 시, 변경
	private String pur_date;
	private String pur_name;
	private String pur_price;
	private String m_id; // fk
	private String item_num; // fk
	private String pur_thum;
	private int pur_sale;
	private String pur_img;
	

	public String getPur_num() {
		return pur_num;
	}
	public void setPur_num(String pur_num) {
		this.pur_num = pur_num;
	}
	public int getPur_stat() {
		return pur_stat;
	}
	public void setPur_stat(int pur_stat) {
		this.pur_stat = pur_stat;
	}
	public String getPur_date() {
		return pur_date;
	}
	public void setPur_date(String pur_date) {
		this.pur_date = pur_date;
	}
	public String getPur_name() {
		return pur_name;
	}
	public void setPur_name(String pur_name) {
		this.pur_name = pur_name;
	}
	public String getPur_price() {
		return pur_price;
	}
	public void setPur_price(String pur_price) {
		this.pur_price = pur_price;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getItem_num() {
		return item_num;
	}
	public void setItem_num(String item_num) {
		this.item_num = item_num;
	}
	public String getPur_thum() {
		return pur_thum;
	}
	public void setPur_thum(String pur_thum) {
		this.pur_thum = pur_thum;
	}
	public int getPur_sale() {
		return pur_sale;
	}
	public void setPur_sale(int pur_sale) {
		this.pur_sale = pur_sale;
	}
	public String getPur_img() {
		return pur_img;
	}
	public void setPur_img(String pur_img) {
		this.pur_img = pur_img;
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
