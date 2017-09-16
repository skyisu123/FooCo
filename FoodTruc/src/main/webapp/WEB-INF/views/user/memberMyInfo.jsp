<%@page import="com.fooco.FoodTruc.member.vo.SellerVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>

<%
	Object obj = session.getAttribute("memberInfo");
   	UserVO user = null;
    SellerVO seller = null; 
    if(obj instanceof UserVO){user = (UserVO)obj;}
    else{seller = (SellerVO)obj;}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- footer CSS -->
<link href="../resources/bootstrap/css/footer.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="http://malsup.github.com/jquery.cycle2.js"></script>
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="../resources/bootstrap/css/bootstrap.css">

<script src="../resources/bootstrap/js/bootstrap.js"></script>
<link href="../resources/bootstrap/css/material-dashboard.css"
	rel="stylesheet" />

<link href="../resources/bootstrap/vendor/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<script src="http://malsup.github.com/jquery.cycle2.js"></script>
<!-- Custom Fonts -->
<link
	href="../resources/bootstrap/vendor/font-awesome/css/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<!-- Theme CSS -->
<link href="../resources/bootstrap/css/agency.css" rel="stylesheet">

<title>All Truck Page.jsp</title>
<script>
var sellerId = "not";
sellerId = '${sessionScope.memberInfo}';
function errFunc(){
	alert("실패임 ㅠㅠ;");
}
//성공시 호출할 함수
function sucFunc(){
	alert("성공임 ^^*");
}
	var count = 1;
	var isJoinResult = '${isSellerJoin}';
	$(document).ready(function() { 
		if(isJoinResult == ""){		//새로고침 혹은 뒤로가기때는 아무 동작도 하지않음.
			
		}else{
			alert(isJoinResult);	//오로지 회원가입 성공 유무만 판단.
		}
		
		if(sellerId != "not"){
			setInterval("seller_alram()", 1000);
		}
		//더보기 버튼 클릭시 ajax 구현
		$("#moreButton").click(function(){
			count++;
			alert(count);
			$.ajax({	//ajax폴더의 allTruckListAdd에 있음. 
				url : "allTruckListAdd.do?page="+count+"",
				dataType : "html",
				type : "get",
				success : function(data){
					$("#truckList").append(data);
				},
				error : errFunc
			});
		});
	});
	
	function seller_alram(){
		$.ajax({
			url : "../query/queryAlram.do",
			dataType : "html",
			type : "get",
			
			success : function(data){
				if(data != null){ $("#alram").html(data); }
				
			},
			error : function(response){
				
			}
		});
	}

</script>

<script type="text/javascript">
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
</script>

</head>
<body class="bg-light-gray index">

<!-- Navigation -->
<nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top" >

    <div class="container">
    	 <!-- 로고와 임시 사이트명 -->
       	<div class="col-md-2">
       		<img src="../resources/bootstrap/img/logos/logo.png" style="width:150px; height:53px; margin-right:50px; "/>
       	</div>
       	<div class="col-md-3">
       		<a style="font-size:3em;"class="navbar-brand page-scroll" href="../index.do">
       			FooCo &nbsp; <!-- <small style="color:white; font-size:20px;">Food Truck Comunucation</small> --> 
       		</a>	
       	</div>
       	<!-- 로고 끝 -->
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
            </button>
        </div>
		
		<c:set var="user" value="<%=user%>"/>
		<c:set var="seller" value="<%=seller%>" />
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
            <c:choose>
            	<c:when test="${not empty sessionScope.memberInfo}">
					<c:if test="${not empty user}">
		                <li>
		                    <a href="#">${sessionScope.memberInfo.memberName}님 !</a>
		                </li>
		            </c:if>
		            <c:if test="${not empty seller}">
		                <li>
		                    <a href="#">${sessionScope.memberInfo.sellerName}님 !</a>
		                </li>
		                <li>
		                    <a class="page-scroll" href="truckAdd.do" data-toggle="modal">판매트럭 등록하기</a>
		                </li>
					</c:if>
		                <li>
		                    <a class="page-scroll" href="../user/memberMyPage.do" data-toggle="modal">마이페이지</a>
		                </li>
		                <li>
		                    <a class="page-scroll" href="../user/memberLogout.do" data-toggle="modal">로그아웃</a>
		                </li>
            	</c:when>
            	<c:otherwise>
            	<script>sellerId = "not";</script>
            		<li>
	                    <a class="page-scroll" href="../user/memberLogin.do" data-toggle="modal">로그인</a>
	                </li>
	                
	                <li>
	                	<a class="page-scroll" href="../user/MemberJoin.do" >회원가입</a>
	                </li>
            	</c:otherwise>
            </c:choose>
               
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<!-- 판매자 전용 알람기능 -->	
<div id="alram" style="width:73px; height:128px; position:fixed; left:1400px; top:110px; z-index:1; visibility:visible;">

</div>
<!-- 판매자 전용 알람기능 끝-->	
<!-- Portfolio Grid Section -->
<section id="portfolio" class="bg-light-gray">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">정보수정</h2>
				<h3 class="section-subheading text-muted"
					style="margin-bottom: 20px">fooCo는 회원님의 정보를 무단으로 사용하지 않습니다.</h3>
			</div>
		</div>
<!-- 사용자 마이페이지 -->
		<script>
			function checkfrm(){
				var userForm = document.userFrm;
				if(!userForm.inputPasswordCheck.value){
					alert("비밀번호 확인 하세요.");
					userForm.inputPasswordCheck.focus();
					return false;
				}
				if(userForm.userPw.value!=userForm.inputPasswordCheck.value){
					alert("비밀번호가 틀립니다. 확인 하세요.");
					userForm.inputPasswordCheck.focus();
					return false;
				}
			}
		</script>
		<c:if test="${not empty user}">
			
		<form class="form-horizontal" name="userFrm" action="userUpdate.do" method="post" onclick="return checkfrm()">
			<div class="form-group">
				<label class="col-sm-3 control-label" for="memberId">아이디</label>
				<div class="col-sm-6">
					<input class="form-control" name="memberId" id="memberId" type="text" style="width:240px;" value="${sessionScope.memberInfo.memberId}" readonly>
					<!-- <button type="button" class="btn btn-info btn-xs" onclick="" style="display:inline;">중복확인</button> -->
					<span id="result_id_msg" style="font-size:1.5em;"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="memberPw">비밀번호</label>
				<div class="col-sm-6">
					<input class="form-control" name="memberPw" id="memberPw" type="password" placeholder="비밀번호" value="${sessionScope.memberInfo.memberPw}">
					<p class="help-block">숫자, 특수문자 포함 8자 이상</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="inputPasswordCheck">비밀번호확인</label>
				<div class="col-sm-6">
					<input class="form-control" name="inputPasswordCheck"id="inputPasswordCheck" type="password" placeholder="비밀번호 확인">
					<p class="help-block">비밀번호를 한번 더 입력해주세요.</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="memberName">이름</label>
				<div class="col-sm-6">
					<input class="form-control" name="memberName" id="memberName" type="text" placeholder="이름" value="${sessionScope.memberInfo.memberName}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="memberEmail">이메일</label>
				<div class="col-sm-6">
					<input class="form-control" name="memberEmail" id="memberEmail" type="email" placeholder="이메일" value="${sessionScope.memberInfo.memberEmail}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="memberPhone">휴대폰번호</label>
				<div class="col-sm-6">
					<div class="input-group">
						<input type="tel" class="form-control" name="memberPhone" id="memberPhone" placeholder="- 없이 입력해 주세요" value="${sessionScope.memberInfo.memberPhone}"/> <!-- <span class="input-group-btn">
							<button class="btn btn-success">
								인증번호 전송<i class="fa fa-mail-forward spaceLeft"></i>
							</button>
							
						</span> -->
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="memberAddr">지역</label>
				<div class="col-sm-6">
					<input class="form-control" name="memberAddr" id="memberAddr" placeholder="지역" value="${sessionScope.memberInfo.memberAddr}">
					<input type="hidden"  name="memberDate" id="memberDate" value="${sessionScope.memberInfo.memberDate}">				
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type="submit">
						수정완료<i class="fa fa-check spaceLeft"></i>
					</button>
				</div>
			</div>
		</form>
				
		</c:if>	
<!-- 사용자 마이페이지 끝 -->


<!-- 판매자 마이페이지 시작-->

		<script>
			function checkfrmS(){
				var sellerFrm = document.sellerFrm;
				if(!sellerFrm.sellerinputPasswordCheck.value){
					alert("비밀번호 확인 하세요.");
					sellerFrm.sellerinputPasswordCheck.focus();
					return false;
				}
				if(sellerFrm.sellerPw.value!=sellerFrm.sellerinputPasswordCheck.value){
					alert("비밀번호가 틀립니다. 확인 하세요.");
					sellerFrm.sellerinputPasswordCheck.focus();
					return false;
				}
				return true;
			}
		</script>		
		<script>
			$(document).ready(function() {
				$("#sellerInfoMore1").hide();
				$("#sellerInfoMore").click(function() {
					$("#sellerInfoMore1").toggle();
				});
			});
		</script>

		<c:if test="${not empty seller}">
		<form class="form-horizontal" name="sellerFrm" action="sellerUpdate.do" method="post" onclick="return checkfrmS()">
			<div class="form-group">
				<label class="col-sm-3 control-label" for="sellerId">아이디</label>
				<div class="col-sm-6">
					<input class="form-control" name="sellerId" id="sellerId" type="text" style="width:240px;" value="${sessionScope.memberInfo.sellerId}" readonly>
					<!-- <button type="button" class="btn btn-info btn-xs" onclick="" style="display:inline;">중복확인</button> -->
					<span id="result_id_msg" style="font-size:1.5em;"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="sellerPw">비밀번호</label>
				<div class="col-sm-6">
					<input class="form-control" name="sellerPw" id="sellerPw" type="password" placeholder="비밀번호" value="${sessionScope.memberInfo.sellerPw}">
					<p class="help-block">숫자, 특수문자 포함 8자 이상</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="sellerinputPasswordCheck">비밀번호확인</label>
				<div class="col-sm-6">
					<input class="form-control" name="sellerinputPasswordCheck"id="sellerinputPasswordCheck" type="password" placeholder="비밀번호 확인">
					<p class="help-block">비밀번호를 한번 더 입력해주세요.</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="sellerName">이름</label>
				<div class="col-sm-6">
					<input class="form-control" name="sellerName" id="sellerName" type="text" placeholder="이름" value="${sessionScope.memberInfo.sellerName}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="sellerEmail">이메일</label>
				<div class="col-sm-6">
					<input class="form-control" name="sellerEmail" id="sellerEmail" type="email" placeholder="이메일" value="${sessionScope.memberInfo.sellerEmail}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label" for="sellerPhone">휴대폰번호</label>
				<div class="col-sm-6">
					<div class="input-group">
						<input type="tel" class="form-control" name="sellerPhone" id="sellerPhone" placeholder="- 없이 입력해 주세요" value="${sessionScope.memberInfo.sellerPhone}"/> <!-- <span class="input-group-btn">
							<button class="btn btn-success">
								인증번호 전송<i class="fa fa-mail-forward spaceLeft"></i>
							</button>
							
						</span> -->
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-info" id="sellerInfoMore" type="button" onclick="" style="width:80%;">
						판매자 정보 수정하기
					</button>
				</div>
			</div>
			<div class="form-group" id="sellerInfoMore1">
				<div class="form-group">
					<label class="col-sm-2 control-label" for="sellerCode">사업자 번호</label>
					<div class="col-sm-2">
						<c:set var="sellercode"  value="${sessionScope.memberInfo.sellerCode.replace(',','')}"/>
						<input class="form-control" name="sellerCode" id="sellerCode" type="text" pattern="\d{3}\" title="3글자 형태로 입력해주세요"  value="${sellercode.substring(0,3)}" >
					</div>
					<div class="col-sm-1">-</div>
					<div class="col-sm-2">
						<input class="form-control" name="sellerCode" id="sellerCode" type="text"  value="${sellercode.substring(3,6)}" >
					</div>
					<div class="col-sm-1">-</div>
					<div class="col-sm-2">
						<input class="form-control" name="sellerCode" id="sellerCode" type="text"  value="${sellercode.substring(6)}">
					</div>
				</div><!-- seller_code1,2,3 조합해야함 -->
				<div class="form-group">
					<label class="col-sm-3 control-label" for="codeName">사업자 대표자이름</label>
					<div class="col-sm-6">
						<input class="form-control" name="codeName" id="codeName" type="text" value="${sessionScope.memberInfo.codeName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="sellerLocation">주 사업지역</label>
					<div class="col-sm-6">
					<input type="hidden" value="${sessionScope.memberInfo.sellerLocation}">
					
					<c:set var="locationsell" value="${sessionScope.memberInfo.sellerLocation.replace(',','')}"/>
				<c:choose>
					<c:when test="${locationsell.substring(0,2) eq '서울'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울" selected>서울</option>
							<option value="인천">인천</option>
							<option value="경기">경기</option>
							<option value="충청도">충청도</option>
							<option value="전라도">전라도</option>
							<option value="강원도">강원도</option>
							<option value="대구">대구</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="제주">제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="${locationsell.substring(2)}"></option>
						</select>
					</c:when>	
					<c:when test="${locationsell.substring(0,2) eq '인천'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울">서울</option>
							<option value="인천" selected>인천</option>
							<option value="경기">경기</option>
							<option value="충청도">충청도</option>
							<option value="전라도">전라도</option>
							<option value="강원도">강원도</option>
							<option value="대구">대구</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="제주">제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="">${locationsell.substring(2)}</option>
						</select>
					</c:when>	
					<c:when test="${locationsell.substring(0,2) eq '경기'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기" selected>경기</option>
							<option value="충청도">충청도</option>
							<option value="전라도">전라도</option>
							<option value="강원도">강원도</option>
							<option value="대구">대구</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="제주">제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="">${locationsell.substring(2)}</option>
						</select>
					</c:when>	
					<c:when test="${locationsell.substring(0,3) eq '충청도'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기">경기</option>
							<option value="충청도" selected>충청도</option>
							<option value="전라도">전라도</option>
							<option value="강원도">강원도</option>
							<option value="대구">대구</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="제주">제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="">${locationsell.substring(3)}</option>
						</select>
					</c:when>	
					<c:when test="${locationsell.substring(0,3) eq '전라도'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기">경기</option>
							<option value="충청도">충청도</option>
							<option value="전라도" selected>전라도</option>
							<option value="강원도">강원도</option>
							<option value="대구">대구</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="제주">제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="">${locationsell.substring(3)}</option>
						</select>
					</c:when>	
					<c:when test="${locationsell.substring(0,3) eq '강원도'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기">경기</option>
							<option value="충청도">충청도</option>
							<option value="전라도">전라도</option>
							<option value="강원도" selected>강원도</option>
							<option value="대구">대구</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="제주">제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="">${locationsell.substring(3)}</option>
						</select>
					</c:when>	
					<c:when test="${locationsell.substring(0,2) eq '대구'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기">경기</option>
							<option value="충청도">충청도</option>
							<option value="전라도">전라도</option>
							<option value="강원도">강원도</option>
							<option value="대구" selected>대구</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="제주">제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="">${locationsell.substring(2)}</option>
						</select>
					</c:when>	
					<c:when test="${locationsell.substring(0,2) eq '부산'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기">경기</option>
							<option value="충청도">충청도</option>
							<option value="전라도">전라도</option>
							<option value="강원도">강원도</option>
							<option value="대구">대구</option>
							<option value="부산" selected>부산</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="제주">제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="">${locationsell.substring(2)}</option>
						</select>
					</c:when>	
					<c:when test="${locationsell.substring(0,2) eq '울산'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기">경기</option>
							<option value="충청도">충청도</option>
							<option value="전라도">전라도</option>
							<option value="강원도">강원도</option>
							<option value="대구">대구</option>
							<option value="부산">부산</option>
							<option value="울산" selected>울산</option>
							<option value="광주">광주</option>
							<option value="제주">제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="">${locationsell.substring(2)}</option>
						</select>
					</c:when>	
					<c:when test="${locationsell.substring(0,2) eq '광주'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기">경기</option>
							<option value="충청도">충청도</option>
							<option value="전라도">전라도</option>
							<option value="강원도">강원도</option>
							<option value="대구">대구</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="광주" selected>광주</option>
							<option value="제주">제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="">${locationsell.substring(2)}</option>
						</select>
					</c:when>	
					<c:when test="${locationsell.substring(0,2) eq '제주'}" >
						<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
							<option value="">선택</option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기">경기</option>
							<option value="충청도">충청도</option>
							<option value="전라도">전라도</option>
							<option value="강원도">강원도</option>
							<option value="대구">대구</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="제주" selected>제주</option>
						</select>
						<select name="sellerLocation" class="form-control">
							<option value="">${locationsell.substring(2)}</option>
						</select>
					</c:when>
					<c:otherwise>
						<div class="col-sm-6">
							<select name="sellerLocation" onchange="getList(this.selectedIndex)" class="form-control">
								<option value="">선택</option>
								<option value="서울">서울</option>
								<option value="인천">인천</option>
								<option value="경기">경기</option>
								<option value="충청도">충청도</option>
								<option value="전라도">전라도</option>
								<option value="강원도">강원도</option>
								<option value="대구">대구</option>
								<option value="부산">부산</option>
								<option value="울산">울산</option>
								<option value="광주">광주</option>
								<option value="제주">제주</option>
							</select>
								
							<select name="sellerLocation" class="form-control">
								<option value="">------</option>
							</select>
						</div>
					</c:otherwise>
				</c:choose>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="sellerBlogaddr">개인 블로그 및 사이트 주소</label>
					<div class="col-sm-6">
						<input class="form-control" name="sellerBlogaddr" id="sellerBlogaddr" type="text" value="${sessionScope.memberInfo.sellerBlogaddr}">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<button class="btn btn-primary" type="submit">
						수정완료<i class="fa fa-check spaceLeft"></i>
					</button>
				</div>
			</div>
		</form>
				
		</c:if>	
		<!-- 판매자 마이페이지 끝-->
	</div>
	
</section>
<!-- section end -->
<!-- footer -->
<div class="container">
	<div class="row">
		<div class="col-md-4 text-center" style="margin-top: 15px;">
			<span>Copyright &copy; Website 2017.09.11</span>
		</div>
		<div class="col-md-4 text-center">
			<ul class="list-inline social-buttons">
				<li>
					<p
						style="font-family: 'Kaushan Script', 'Helvetica Neue', Helvetica, Arial, cursive; font-size: 2em;">
						FooCo</p>
				</li>
				<li><a href="#"><i class="fa fa-facebook fa-2x"></i></a></li>

			</ul>
		</div>
		<div class="col-md-4 text-center" style="margin-top: 5px;">
			<ul class="list-inline quicklinks">
				<li>Creator</li>
				<li>송영민</li>
				<li>유영민</li>
				<li>이형무</li>
				<li>서동섭</li>
				<li>김태영</li>
			</ul>
		</div>
	</div>
</div>
<!-- footer end -->

<!-- jQuery -->
<script src="../resources/bootstrap/vendor/jquery/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../resources/bootstrap/vendor/bootstrap/js/bootstrap.js"></script>

<!-- Theme JavaScript -->
<script src="../resources/bootstrap/js/agency.js"></script>
<script src="../resources/bootstrap/js/jquery-3.2.1.js"></script>

</body>
</html>