package store;

import java.sql.Timestamp;

public class StoreDTO {

// **Member variables**
	private int	item_count,
					item_sale;
	private String ad_id,
						item_num,
						item_name,
						item_img,
						item_thum,
						item_content,
						item_gen,
						item_grd,
						item_dev,
						item_pub,
						item_pf,
						item_ft,
						gen_num,
						grd_num,
						item_indate,
						item_price,
						item_rels;
	
	public String getItem_pf() {
		return item_pf;
	}
	public void setItem_pf(String item_pf) {
		this.item_pf = item_pf;
	}
	public String getItem_ft() {
		return item_ft;
	}
	public void setItem_ft(String item_ft) {
		this.item_ft = item_ft;
	}
	
	public String getItem_num() {
		return item_num;
	}
	public void setItem_num(String item_num) {
		this.item_num = item_num;
	}
	public String getItem_price() {
		return item_price;
	}
	public void setItem_price(String item_price) {
		this.item_price = item_price;
	}
	public int getItem_count() {
		return item_count;
	}
	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}
	
	public String getGen_num() {
		return gen_num;
	}
	public void setGen_num(String gen_num) {
		this.gen_num = gen_num;
	}
	public String getGrd_num() {
		return grd_num;
	}
	public void setGrd_num(String grd_num) {
		this.grd_num = grd_num;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_img() {
		return item_img;
	}
	public void setItem_img(String item_img) {
		this.item_img = item_img;
	}
	public String getItem_thum() {
		return item_thum;
	}
	public void setItem_thum(String item_thum) {
		this.item_thum = item_thum;
	}
	public String getItem_content() {
		return item_content;
	}
	public void setItem_content(String item_content) {
		this.item_content = item_content;
	}
	
	public String getItem_gen() {
		return item_gen;
	}
	public void setItem_gen(String item_gen) {
		this.item_gen = item_gen;
	}
	public String getItem_grd() {
		return item_grd;
	}
	public void setItem_grd(String item_grd) {
		this.item_grd = item_grd;
	}
	
	public String getItem_indate() {
		return item_indate;
	}
	public void setItem_indate(String item_indate) {
		this.item_indate = item_indate;
	}
	public String getItem_rels() {
		return item_rels;
	}
	public void setItem_rels(String item_rels) {
		this.item_rels = item_rels;
	}
	public String getItem_dev() {
		return item_dev;
	}
	public void setItem_dev(String item_dev) {
		this.item_dev = item_dev;
	}
	public String getItem_pub() {
		return item_pub;
	}
	public void setItem_pub(String item_pub) {
		this.item_pub = item_pub;
	}
	public int getItem_sale() {
		return item_sale;
	}
	public void setItem_sale(int item_sale) {
		this.item_sale = item_sale;
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
