function writeSave(){
	
	if(document.writeform.item_name.value==""){
	  alert("게임이름을 입력하십시요.");
	  document.writeform.item_name.focus();
	  return false;
	}
	if(document.writeform.ad_id.value==""){
	  alert("아이디를 입력하십시요.");
	  document.writeform.ad_id.focus();
	  return false;
	}
	
	if(document.writeform.item_price.value==""){
	  alert("상품가격을 입력하십시요.");
	  document.writeform.item_price.focus();
	  return false;
	}
        
	if(document.writeform.item_count.value==""){
	  alert("상품구매수를 입력하십시요.");
	  document.writeform.item_count.focus();
	  return false;
	}
	if(document.writeform.gen_num.value==""){
	  alert("장르번호를 입력하십시요.");
	  document.writeform.gen_num.focus();
	  return false;
	}
	if(document.writeform.grd_num.value==""){
	  alert("등급번호를 입력하십시요.");
	  document.writeform.grd_num.focus();
	  return false;
	}
	if(document.writeform.item_img.value==""){
	  alert("상품이미지를 입력하십시요.");
	  document.writeform.item_img.focus();
	  return false;
	}
	if(document.writeform.item_thum.value==""){
	  alert("상품썸네일을 입력하십시요.");
	  document.writeform.item_thum.focus();
	  return false;
	}
	if(document.writeform.item_rels.value==""){
	  alert("상품출시일을 입력하십시요.");
	  document.writeform.item_rels.focus();
	  return false;
	}
	if(document.writeform.item_gen.value==""){
	  alert("상품장르를 입력하십시요.");
	  document.writeform.item_gen.focus();
	  return false;
	}
	if(document.writeform.item_grd.value==""){
	  alert("상품등급을 입력하십시요.");
	  document.writeform.item_grd.focus();
	  return false;
	}
	if(document.writeform.item_dev.value==""){
	  alert("개발사를 입력하십시요.");
	  document.writeform.item_dev.focus();
	  return false;
	}
	if(document.writeform.item_pub.value==""){
	  alert("퍼블리셔를 입력하십시요.");
	  document.writeform.item_pub.focus();
	  return false;
	}
	if(document.writeform.item_pf.value==""){
	  alert("플랫폼을 입력하십시요.");
	  document.writeform.item_pf.focus();
	  return false;
	}
	if(document.writeform.item_ft.value==""){
	  alert("상품기능을 입력하십시요.");
	  document.writeform.item_ft.focus();
	  return false;
	}
	if(document.writeform.item_indate.value==""){
	  alert("상품등록일을 입력하십시요.");
	  document.writeform.item_indate.focus();
	  return false;
	}
	if(document.writeform.item_content.value==""){
	  alert("싱품내용을 입력하십시요.");
	  document.writeform.item_content.focus();
	  return false;
	}
 }    

