<%@page import="com.fooco.FoodTruc.member.vo.SellerVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- 상단 CSS, 부트스트랩, JS, jQuery, mete 등등 -->
<%@ include file ="../../../resources/bootstrap/setting/otherCommonTop.jsp" %>

<title>fooco - 로그인</title>
<script>
	var userjoinMsg='${userjoinMsg}';
	var isJoinResult = '${isSellerJoin}';
	$(document).ready(function() { 
		if(isJoinResult == ""){		//새로고침 혹은 뒤로가기때는 아무 동작도 하지않음.
			
		}else{
			alert(isJoinResult);	//오로지 회원가입 성공 유무만 판단.
		}
		if(userjoinMsg==""){}
		else{alert(userjoinMsg); }
	}); 
	
	
</script>
<style>
.img {
	position: relative;
	background-image: url(../resources/bootstrap/img/coverImg/coverIMG10.jpg);
	height: 100vh;
	background-size: cover;
}
/* 
.img-cover {
	position: absolute;
	height: 100%;
	width: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	z-index: 1;
} */

.img .content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	font-size: 5rem;
	color: white;
	z-index: 2;
	text-align: center;
}
.btn-link-1:hover{
	color:white;
}
li{color:white;}
</style>
</head>
<body id="page-top" class="index img">

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
		
	<!-- <div class="img-cover"></div> -->
	<!-- Portfolio Grid Section -->
	<section id="portfolio">
	<div class="container" style="margin-top:60px;">
		<div class="row">
			<div class="col-sm-12 text-center">
				<h2 class="section-heading" style="color: white;">Login</h2>
				
				<div class="form-bottom">
					<form role="form" action="../user/userLogin.do" method="post"
						class="login-form">
						<div class="form-group" style="color: white; font-size: 1.2em">
							<div class="col-md-12 text-center" >ID</div>
							
							<div class="col-md-12 text-center" style="margin-top:10px;">
								<center>
								<input class="form-username form-control" type="text" name="member_id" size="50"
								  placeholder="id를 입력해주세요." style="width:200px; ">
								</center>
								<span style="color: red; font-size: 1.2em">${loginMsg}</span>
							</div>
						</div>
													
						<div class="form-group" style="color: white; font-size: 1.2em">
							<div class="col-md-12 text-center" style="margin-top:10px;">PassWord</div>
							<div class="col-md-12 text-center" style="margin-top:10px;">
								<center>
								<input class="form-username form-control" type="password" name="member_pw" size="50"
								 placeholder="PassWord" style="width:200px; ">
								</center>
							</div>
						</div>
						<div class="col-md-12 text-center" style="margin-top:20px;">
							<button type="submit" class="btn btn-danger">Login</button>
						</div>
					</form>
				</div>
				<div class="col-md-12 text-center" style="margin-top:20px;">
					<button type="button" class="btn btn-link btn-sm" style="color:white;margin-left:20px;"
					onclick="location.href='../user/MemberJoin.do';">회원가입&nbsp;</button>
				<button type="button" class="btn btn-link btn-sm" style="color:white"
					onclick="location.href='../user/memberFindId.do'">아이디찾기</button>
				<button type="button" class="btn btn-link btn-sm" style="color:white"
					onclick="location.href='../user/memberFindPw.do'">비밀번호찾기</button>
				</div>
				


				<div class="col-md-12 text-center social-login"style="margin-top:30px;">
					<h3 style="color:#D2E1FF; text-transform:none; ">...or login with:</h3>
					<div class="social-login-buttons">
						<a class="btn btn-link-1" href="#"> <i
							class="fa fa-facebook"></i> Facebook
						</a> <a class="btn btn-link-1" href="#"> <i
							class="fa fa-twitter"></i> Twitter
						</a> <a class="btn btn-link-1" href="#"> <i
							class="fa fa-google-plus"></i> Google Plus
						</a>
					</div>
				</div>
			</div>
			<div class="col-sm-3">&nbsp;</div>
		</div>
	</div>
	

	</section>

	<!-- footer -->
	<div class="container">
		<div class="row">
			<div class="col-md-4 text-center" style="margin-top: 15px;">
				<span style="color:white;">Copyright &copy; Website 2017.09.11</span>
			</div>
			<div class="col-md-4 text-center">
				<ul class="list-inline social-buttons">
					<li>
						<p style="font-family: 'Kaushan Script', 'Helvetica Neue', Helvetica, Arial, cursive; 
							font-size: 2em; color:white;">
							FooCo</p>
					</li>
					<li><a href="#"><i class="fa fa-facebook fa-2x"></i></a></li>

				</ul>
			</div>
			<div class="col-md-4 text-center" style="margin-top: 5px; ">
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

	<!-- 부트스트랩 script 인클루드 -->
	<%@ include file ="../../../resources/bootstrap/setting/otherCommonBottom.jsp" %>

</body>

</html>


