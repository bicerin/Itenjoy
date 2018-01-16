function checkForm(writeform){

	if(!writeform.com_kind.value){
	  alert("제품의 분류를 선택하십시오");
	  writeform.com_kind.focus();
	  return false;
	}
	if(!writeform.com_title.value){
	  alert("제품의 제품명을 입력하십시오");
	  writeform.com_title.focus();
	  return false;
	}
	
	if(!writeform.com_price.value){
	  alert("제품의 가격을 입력하십시오");
	  writeform.com_price.focus();
	  return false;
	}
        
	if(!writeform.com_count.value){
	  alert("제품의 수량을 입력하십시오");
	  writeform.com_count.focus();
	  return false;
	}
	
	if(!writeform.manufacture_com.value){
	  alert("제조회사을 입력하십시오");
	  writeform.manufacture_com.focus();
	  return false;
	}
	
	if(!writeform.com_content.value){
	  alert("제품의 설명을 입력하십시오");
	  writeform.com_content.focus();
	  return false;
	}
		
	writeform.action = "computerRegisterPro.jsp";
    writeform.submit();
	
 } 
 
 function updateCheckForm(writeform){

	if(!writeform.com_kind.value){
	  alert("제품의 분류를 선택하십시오");
	  writeform.com_kind.focus();
	  return false;
	}
	if(!writeform.com_title.value){
	  alert("제품의 제품명을 입력하십시오");
	  writeform.com_title.focus();
	  return false;
	}
	
	if(!writeform.com_price.value){
	  alert("제품의 가격을 입력하십시오");
	  writeform.com_price.focus();
	  return false;
	}
        
	if(!writeform.com_count.value){
	  alert("제품의 수량을 입력하십시오");
	  writeform.com_count.focus();
	  return false;
	}
	
	if(!writeform.manufacture_com.value){
	  alert("제조회사을 입력하십시오");
	  writeform.manufacture_com.focus();
	  return false;
	}
	
	if(!writeform.com_content.value){
	  alert("제품의 설명을 입력하십시오");
	  writeform.com_content.focus();
	  return false;
	}
		
	writeform.action = "computerUpdatePro.jsp";
    writeform.submit();
	
 } 
 