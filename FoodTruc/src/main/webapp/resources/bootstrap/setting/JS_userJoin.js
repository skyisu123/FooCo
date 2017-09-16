
var count = 1;
function userIdcheck(id){
	
	var userId = id;
	if(userId==""){
		 alert("아이디를 입력해주세요");
		 return false;
	}

	$.ajax({
		type : 'POST',
        url : 'memberIdCheck.do',	//컨트롤러 전달
        data: { id : id },
        dataType : 'text',	//ajax의 반환값으로 일반 텍스트 (0 혹은 1만 있기에)
        success : function(result){
        	if (result == 0) {
        		//조회결과 중복 아이디가 없으면
        		count = 0;
        		$("#result_id_msg").html("사용가능한 아이디 입니다.");
        		$("#result_id_msg").css('color', 'skyblue');
				return true;
        	}else{
        		//조회결과 중복 아이디가 있으면
        		$("#result_id_msg").html("사용 불가능한 아이디 입니다.");
        		$("#result_id_msg").css('color', 'red');
 				count++;
        		return false;
        	}
        }
	});
	
 }

function checkhippen(){
	    var x = document.getElementById("memberPhone");
	    x.value = x.value.replace("-","");
	}


 function checkForm(){
		var regular = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
		var form = document.userFrm;
	 
	 	 if(count>0){
	 		alert("아이디 중복체크 하세요");
	 		 return false;
	 	 }
		 if(!form.memberId.value){
			 alert("아이디를 입력해주세요");
			 form.memberId.focus();
			 return false;
		 }
		 else if(!form.memberPw.value){
			 alert("비밀번호를 입력해주세요");
			 form.memberPw.focus();
			 return false;
		 }
		 else if(form.memberPw.value != form.userPwCheck.value){
			 alert("비밀번호가 일치하지 않습니다.");
			 form.userPwCheck.focus();
			 return false;
		 }
		 else if(!form.memberName.value){
			 alert("이름를 입력해주세요");
			 form.memberName.focus();
			 return false;
		 }
		 else if(!form.memberEmail.value){
			 alert("이메일을 입력해주세요");
			 form.memberEmail.focus();
			 return false;
		 }
		 else if(!regular.test($("#memberEmail").val())){
				alert("이메일 형식에 맞게 입력하세요.XXX@fooco.com");
				$("memberEmail").focus();
				return false;
			}
		 else if(!form.memberPhone.value){
			 alert("핸드폰을 입력해주세요");
			 form.memberPhone.focus();
			 return false;
		 }
		 else if(!form.memberAddr.value){
			 alert("주소를 입력해주세요");
			 form.memberAddr.focus();
			 return false;
		 }
		
	 return true;
 }
