
$(document).ready(function(){
	//var regex = /^[A-Za-z0-9]{5,14}$/; 패턴체크용
	
	var checkAjaxSetTimeout;
	$('#sellerId').keyup(function(){
		/* if(!regex.test($('#sellerId').val())) $('#result_id_msg').html("사용할 수 없는 문자가 있습니다. (영 소,대문자 숫자만 사용 가능)");
    	$("#result_id_msg").css('color', 'red'); */
		clearTimeout(checkAjaxSetTimeout);
		checkAjaxSetTimeout = setTimeout(function(){
			if( $('#sellerId').val().length > 6){
				
				var id = $('#sellerId').val();
				//ajax로 컨트롤러에 값 id값을 넘기고 중복체크함
				$.ajax({
					type : 'POST',
	                url : 'memberIdCheck.do',	//컨트롤러 전달
	                data: { id : id },
	                dataType : 'text',	//ajax의 반환값으로 일반 텍스트 (0 혹은 1만 있기에)
	                success : function(result){
	                	console.log(result);	//F12 개발자도구 콘솔에 찍힘
	                	if (result == 0) {
	                		//조회결과 중복 아이디가 없으면
	                		$("#result_id_msg").html("사용가능한 아이디 입니다.");
	                		$("#result_id_msg").css('color', 'skyblue');
	                	}else{
	                		//조회결과 중복 아이디가 있으면
	                		$("#result_id_msg").html("사용 불가능한 아이디 입니다.");
	                		$("#result_id_msg").css('color', 'red');
	                	}
	                }
				});
			}
			else{
				$("#result_id_msg").html("");
			}
		}, 1000);	//1초마다 SellerMemberController컨트롤러에 체크넘김.
	});
});

var listText = new Array();
var listValue = new Array();
/* 서울 */
listText[0] = new Array('강남구','관악구','강서구','종로구','용산구','노원구','은평구','마포구','영등포구'); // 각 option의 text
listValue[0] = new Array('강남구','관악구','강서구','종로구','용산구','노원구','은평구','마포구','영등포구'); // 각 option의 value
/* 인천 */
listText[1] = new Array('연수구','남동구','남구','동구','서구','중구');
listValue[1] = new Array('연수구','남동구','남구','동구','서구','중구');
/* 경기 */
listText[2] = new Array('수원시','성남시','고양시','용인시','부천시','안산시','안양시','화성시','의정부','시흥시','파주시');
listValue[2] = new Array('수원시','성남시','고양시','용인시','부천시','안산시','안양시','화성시','의정부','시흥시','파주시');
/* 충청도 */
listText[3] = new Array('천안시','공주시','논산시','아산시');
listValue[3] = new Array('천안시','공주시','논산시','아산시');
/* 전라도 */
listText[4] = new Array('전주시','군산시','익산시','남원시');
listValue[4] = new Array('전주시','군산시','익산시','남원시');
/* 강원도 */
listText[5] = new Array('춘천시','원주시','강릉시','속초시','삼척시');
listValue[5] = new Array('춘천시','원주시','강릉시','속초시','삼척시');
/* 대구 */
listText[6] = new Array('중구','동구','서구','남구','북구','수성구','달서구');
listValue[6] = new Array('중구','동구','서구','남구','북구','수성구','달서구');
/* 부산 */
listText[7] = new Array('중구','서구','동구','영도구','부산진구','동래구','남구','북구','해운대구');
listValue[7] = new Array('중구','서구','동구','영도구','부산진구','동래구','남구','북구','해운대구');
/* 울산 */
listText[8] = new Array('중구','남구','동구','북구');
listValue[8] = new Array('중구','남구','동구','북구');
/* 광주 */
listText[9] = new Array('동구','서구','남구','북구','광산구');
listValue[9] = new Array('동구','서구','남구','북구','광산구');
/* 제주도 */
listText[10] = new Array('제주시','서귀포시');
listValue[10] = new Array('제주시','서귀포시');


function getList(sel_id) {
   var sellerLocation = document.getElementsByName('sellerLocation');
   var sel_id = sel_id - 1;
   if(sel_id >= 0) {
	   sellerLocation[1].options.length = listText[sel_id].length;
      for(i = 0; i < listText[sel_id].length; i++) {
    	  sellerLocation[1].options[i] = new Option(listText[sel_id][i], listValue[sel_id][i]);
      }
   } else {
	  sellerLocation[1].options.length = 1;
	  sellerLocation[1].options[0].text = "------";
	  sellerLocation[1].options[0].value = 0;
   }
}                    	