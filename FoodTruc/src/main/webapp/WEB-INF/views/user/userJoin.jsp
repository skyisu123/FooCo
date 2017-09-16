<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.fooco.FoodTruc.member.vo.SellerVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.UserVO"%>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- footer CSS -->
<link href="../resources/bootstrap/css/footer.css" rel="stylesheet">
<link
	href="../resources/bootstrap/vendor/font-awesome/css/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href="../resources/bootstrap/vendor/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/earlyaccess/jejumyeongjo.css">
<!-- Theme CSS -->
<link rel="stylesheet" href="../resources/bootstrap/css/agency.css">
<link rel="stylesheet"
	href="../resources/bootstrap/css/font-awesome.min.css">
<link rel="stylesheet" href="../resources/bootstrap/css/style.css">
<link rel="stylesheet"
	href="../resources/bootstrap/css/material-dashboard.css" />
<!--Google Font link-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Raleway:400,600,700"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
	

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>


<title>UserJoin.jsp</title>


</head>
<script>
function searchZipCode(){ 
    // 다음 우편번호 API 기능 이용.
    new daum.Postcode({
       oncomplete : function(data) {
          var fn = document.userFrm;
          fn.memberAddr.value = data.zonecode
                + " " + data.address;
          fn.memberAddr.focus();
       }
    }).open();
 }
 </script>
 





<body class="bg-light-gray">
	<nav id="mainNav"
		class="navbar navbar-default navbar-custom navbar-fixed-top"
		style="background-color:black;">
	<div class="container">
		<!-- 로고와 임시 사이트명 -->
		<div class="col-md-2">
			<img src="../resources/bootstrap/img/logos/logo.png"
				style="width: 150px; height: 53px; margin-right: 50px;" />
		</div>
		<div class="col-md-3">
			<a style="font-size: 3em; color: yellow;"
				class="navbar-brand page-scroll" href="../index.do"> FooCo
				&nbsp; </a>
		</div>
		<!-- 로고 끝 -->
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> Menu <i
					class="fa fa-bars"></i>
			</button>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>
				<li><a class="page-scroll" href="#login" data-toggle="modal"
					style="color: white;">Login</a></li>

				<li><a class="page-scroll" href="memberJoin.do">Join</a></li>
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

	<script>

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

</script>




	<div class="container" style="margin-top:;">
		<div class="row">
			<div class="main_pricing roomy-100">
				<div class="col-md-offset-2 col-md-8 col-sm-12">
					<div class="pricing_item sm-m-top-30">
						<div class="container">
							<div class="row">
								<form class="form-horizontal" name="userFrm"
									action="userEntry.do" method="post">

									<div class="card" style="width: 800px; margin: 0;">
										<div class="card-header" data-background-color="purple">
											<h4 class="title">FooCo 회원가입</h4>
											<p class="category">Fooco의 가족이 되어주세요~</p>
										</div>
										<div class="card" style="margin: 0">
											<div class="form-group">
												<label class="col-sm-3 " for="memberId"><strong>아이디
												</strong></label>
												<div class="col-sm-4">
													<input class="form-control" name="memberId" id="memberId"
														type="text" style="width: 240px;" placeholder="아이디">
													<span id="result_id_msg" style="font-size: 1em;"></span>
												</div>
												<div class="col-sm-3">
													<button type="button" class="btn btn-info btn-xs pull-left"
														onclick="return userIdcheck(this.form.memberId.value)"
														style="display: inline-block;">중복확인</button>
												</div>
											</div>
										</div>

										<div id="userIdCheckTrue">
											<div class="card" style="margin: 0">
												<div class="form-group">
													<label class="col-sm-3" for="memberPw"><strong>비밀번호</strong></label>
													<div class="col-sm-6">
														<input class="form-control control" name="memberPw"
															id="memberPw" type="password" placeholder="비밀번호">
														<p class="help-block">숫자, 특수문자 포함시 더욱 안전합니다.</p>
													</div>
												</div>

												<div class="form-group" style="margin-bottom: 20px">
													<label class="col-sm-3" for="userPwCheck"><strong>비밀번호확인</strong></label>
													<div class="col-sm-6">
														<input class="form-control" id="userPwCheck"
															type="password" placeholder="비밀번호 확인">
														<p class="help-block">비밀번호를 한번더 입력해주세요</p>
													</div>
												</div>
											</div>

											<div class="card" style="margin: 0">
												<div class="form-group">
													<label class="col-sm-3" for="memberName"><strong>이름</strong></label>
													<div class="col-sm-6">
														<input class="form-control" name="memberName"
															id="memberName" type="text" placeholder="이름">
													</div>
												</div>
											</div>

											<div class="card" style="margin: 0">
												<div class="form-group">
													<label class="col-sm-3" for="memberEmail"><strong>이메일</strong></label>
													<div class="col-sm-6">
														<input class="form-control" name="memberEmail"
															id="memberEmail" type="email" placeholder="이메일">
													</div>
												</div>
											</div>

											<div class="card" style="margin: 0">
												<div class="form-group">
													<label class="col-sm-3" for="memberPhone"><strong>휴대폰번호</strong></label>
													<div class="col-sm-6" style="margin-bottom: 20px">

														<input type="text" class="form-control" name="memberPhone"
															id="memberPhone" placeholder="- 없이 입력해 주세요"
															onkeyup="checkhippen()" />

														<p class="help-block">[ex] 01012345678</p>
													</div>
												</div>
											</div>
											<div class="card" style="margin: 0">
												<div class="form-group">
													<label class="col-sm-3 " for="memberAddr"><strong>주소</strong></label>
													<div class="col-sm-6">
								                        <span class="input-group-btn">
								                          <input type="button" class="btn btn-info btn-xs pull-left" onclick="searchZipCode()" value=" 주소 찾기 " style="display:inline-block; margin:0px">
								                           <input type="text" class="form-control" name="memberAddr" id="memberAddr"  readOnly/>
								                        </span>
													</div>
												</div>
											</div>
											<div class="card text-center" style="margin: 0">
												<button type="submit" class="btn btn-primary"
													onclick="return checkForm()">가입</button>
												<button type="reset" class="btn btn-worning">초기화</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

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

	<!-- Plugin JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"
		integrity="sha384-mE6eXfrb8jxl0rzJDBRanYqgBxtJ6Unn4/1F7q4xRRyIw7Vdg9jP4ycT7x1iVsgb"
		crossorigin="anonymous"></script>

	<!-- Contact Form JavaScript -->
	<!-- <script src="../resources/bootstrap/js/jqBootstrapValidation.js"></script>
	<script src="../resources/bootstrap/js/contact_me.js"></script> -->

	<!-- Theme JavaScript -->
	<script src="../resources/bootstrap/js/agency.js"></script>

	<!--   Core JS Files   -->
	<script src="../resources/bootstrap/js/jquery-3.1.0.min.js"
		type="text/javascript"></script>
	<script src="../resources/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="../resources/bootstrap/js/material.min.js"
		type="text/javascript"></script>

	<!--  Charts Plugin -->
	<script src="../resources/bootstrap/js/chartist.min.js"></script>

	<!--  Notifications Plugin    -->
	<script src="../resources/bootstrap/js/bootstrap-notify.js"></script>

	<!--  Google Maps Plugin    -->
<!-- 	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js"></script> -->

	<!-- Material Dashboard javascript methods -->
	<script src="../resources/bootstrap/js/material-dashboard.js"></script>

	<!-- Material Dashboard DEMO methods, don't include it in your project! -->
	<script src="../resources/bootstrap/js/demo.js"></script>

</body>
</html>