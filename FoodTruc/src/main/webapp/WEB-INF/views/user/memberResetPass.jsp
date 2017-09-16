<%@page import="com.fooco.FoodTruc.member.vo.SellerVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" integrity="sha384-0s5Pv64cNZJieYFkXYOTId2HMA2Lfb6q2nAcx2n0RTLUnCAoTTsS0nKEO27XyKcY" crossorigin="anonymous"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js" integrity="sha384-ZoaMbDF+4LeFxg6WdScQ9nnR1QC2MIRxA1O9KWEXQwns1G8UNyIEZIQidzb0T1fo" crossorigin="anonymous"></script>
<![endif]-->
<title>fooco - 비밀번호 재설정</title>
<script>
	
	var sellerId = "not";
	sellerId = '${sessionScope.memberInfo}';
	var isJoinResult = '${isSellerJoin}';
	$(document).ready(function() {
		if (isJoinResult == "") { //새로고침 혹은 뒤로가기때는 아무 동작도 하지않음.

		} else {
			alert(isJoinResult); //오로지 회원가입 성공 유무만 판단.
		}
		
		if(sellerId != "not"){
			setInterval("seller_alram()", 1000);
		}
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

</head>
<body id="page-top" class="bg-light-gray index">

	<!-- Navigation -->
	<nav id="mainNav"
		class="navbar navbar-default navbar-custom navbar-fixed-top">

	<div class="container">
		<!-- 로고와 임시 사이트명 -->
		<div class="col-md-2">
			<img src="../resources/bootstrap/img/logos/logo.png"
				style="width: 150px; height: 53px; margin-right: 50px;" />
		</div>
		<div class="col-md-3">
			<a style="font-size: 3em;" class="navbar-brand page-scroll"
				href="../index.do"> FooCo &nbsp; <!-- <small style="color:white; font-size:20px;">Food Truck Comunucation</small> -->
			</a>
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
				<li><a class="page-scroll" href="memberLogin.do"
					style="color: white;">로그인</a></li>

				<li><a class="page-scroll" href="memberJoin.do">회원가입</a></li>
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

	<section id="portfolio" class="bg-light-gray">
	<div class="container">
		<div class="row">
			<div class="col-sm-12 text-center">
				<h2 class="section-heading">비밀번호 재설정</h2>
				<div class="col-sm-3">&nbsp;</div>

				<div class="col-sm-6">
					<div class="form-top">
						<div class="form-top-right">
							비밀번호 재설정 페이지 입니다.<br /> 꼭 기억하세요~
						</div>
						</br> </br>
					</div>
					<!--
					각기 다른 form이므로 div id="resultId1 or 2 .."에 
					ajax의 결과값을 심어줄 것임.
					-->
					<div class="row">
						<div class="panel-group" >
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										비밀번호 재설정 하기
									</h4>
								</div>
								<div id="collapse1" class="panel-collapse collapse in">
									<div class="panel-body">
											<div class="form-group">
												<input type="text" class="form-control" name="memberId" id="memberId1" placeholder="ID">
											</div>
											<div class="form-group">
												<input type="text" class="form-control" name="memberPw" id="memberPw" placeholder="기존 비밀번호 입력" >
											</div>
											<div class="form-group">
												<input type="text" class="form-control" name="newMemberPw" id="newMemberPw" placeholder="바꿀 비밀번호 입력" >
											</div>
											<div class="form-group">
												<input type="text" class="form-control" name="newMemberPwCheck" id="newMemberPwCheck" placeholder="바뀐 비밀번호 재입력" >
											</div>
											<button class="btn btn-info" id="resetPw">
												확인 <i class="fa fa-mail-forward spaceLeft"></i>
											</button>
										<div class="table-responsive" id="findIdResult1">
											
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-12 text-center">
								<button class="btn btn-basic btn-sm" style="width:100%" onclick="location.href='memberLogin.do'">돌아가기</button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3">&nbsp;</div>

			</div>
		</div>
	</div>
	</section>
	
	<script>
		$("#resetPw").click(function(){
			if($("#memberId1").val() == ""){
				alert("ID을 입력하세요.");
				$("memberId1").focus();
				return;
			}
			if($("#memberPw").val() == ""){
				alert("기존 사용하신 비밀번호를 입력하세요.");
				$("memberPw").focus();
				return;
			}
			if($("#newMemberPw").val() == ""){
				alert("앞으로 사용하실 비밀번호를 입력하세요.");
				$("newMemberPw").focus();
				return;
			}
			if($("#newMemberPwCheck").val() == ""){
				alert("앞으로 사용하실 비밀번호를 한번더 입력해주세요");
				$("newMemberPwCheck").focus();
				return;
			}
			if($("#newMemberPwCheck").val() != $("#newMemberPw").val()){
				alert("사용하실 비밀번호와 비밀번호 확인란의 비밀번호가 일치하지 않습니다.");
				$("newMemberPwCheck").focus();
				return;
			}
			$.ajax({
				type : 'get',
				url : '../user/memberResetPwAction.do',
				data : {"memberId" : $("#memberId1").val(), "memberPw" : $("#memberPw").val(),
						"newMemberPw" : $("#newMemberPw").val()
						},
				success : function(data){
					if(data==1){
						alert("비밀번호 수정이 되었습니다.");
						location.href="memberLogin.do";
					}else{
						alert("비밀번호수정이 안되었습니다.바뀌기전 비밀번호 확인해주세요");
					}

				}
			});
		});
		
	</script>

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
	<script src="../resources/bootstrap/js/jqBootstrapValidation.js"></script>
	<!-- <script src="js/contact_me.js"></script> -->

	<!-- Theme JavaScript -->
	<script src="../resources/bootstrap/js/agency.js"></script>
	<script src="../resources/bootstrap/js/jquery-3.2.1.js"></script>

	<!--  -------------------------------------------------- -->
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
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js"></script>

	<!-- Material Dashboard javascript methods -->
	<script src="../resources/bootstrap/js/material-dashboard.js"></script>

	<!-- Material Dashboard DEMO methods, don't include it in your project! -->
	<script src="../resources/bootstrap/js/demo.js"></script>

</body>

</html>


