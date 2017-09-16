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
<title>seller_query.jsp</title>
<script>
var sellerId = "not";
sellerId = '${sessionScope.memberInfo}';
$(document).ready(function() {
	
	if(sellerId != "not"){
		setInterval("seller_alram()", 1000);
	}
});

function seller_alram(){// 판매자 문의 알람
	
	$.ajax({
		url : "../query/queryAlram.do",
		dataType : "html",
		type : "get",
		
		success : function(data){
			$("#alram").html(data);
		},
		error : function(response){
			
		}
	});
}

	function frmCheck()
	{
		var f = document.queryForm;
		
		if(f.queryTitle.value=="")
		{
			alert("문의 제목을 입력해주세요");
			f.queryTitle.focus();
			return false;
		}
		
		if(f.queryContent.value=="")
		{
			alert("문의 내용을 작성해주세요");
			f.queryContent.focus();
			return false;
		}
		
		alert("문의를 보냈습니다");
		return true;
		
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
		<%-- <%
			Object obj = session.getAttribute("memberInfo");
			UserVO user = null;
			SellerVO seller = null;
			if (obj instanceof UserVO) {
				user = (UserVO) obj;
			} else {
				seller = (SellerVO) obj;
			}
		%> --%>
		<c:set var="user" value="<%=user%>" />
		<c:set var="seller" value="<%=seller%>" />
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				  <c:choose>
            	<c:when test="${not empty sessionScope.memberInfo}">
					<c:if test="${not empty user}">
		                <li>
		                    <a href="#">${sessionScope.memberInfo.memberName}님 !</a>
		                </li>
		                <li>
	                   		 <a class="page-scroll" href="../query/queryList.do" data-toggle="modal">문의글 관리</a>
	                	</li>
		            </c:if>
		            <c:if test="${not empty seller}">
		                <li>
		                    <a href="#">${sessionScope.memberInfo.sellerName}님 !</a>
		                </li>
		                <li>
		                    <a class="page-scroll" href="../foodTruck/truckAdd.do" data-toggle="modal">판매트럭 등록하기</a>
		                </li>
		                <li>
	                   		 <a class="page-scroll" href="../query/queryList.do" data-toggle="modal">문의글 관리</a>
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

	<div class="container" style="margin-top: 150px;">
				<div class="row">
					<div class="col-sm-3"></div>
					<div class="col-sm-6 card">
					<div class="card-header" data-background-color="gray">
						<h3>
							<img src="../resources/bootstrap/img/about/mail.png" style="width:40px; height:40px;"/>
							&nbsp;&nbsp;&nbsp;
							문의글 작성하기
						</h3>
					</div>
					<c:choose>
						<c:when test="${not empty seller}">
						<form action="sellerQuerySendAction.do" class="form-horizontal"
							method="post" name="queryForm" enctype="multipart/form-data">
							<div class="form-group">
								<!-- 판매자 아이디는 세션에 저장되어있는 정보로 출력 -->
								<div class="form-group">
									<label class="col-sm-3 control-label"  style="font-weight:bold;"
									 for="sellerId">판매자 아이디</label>
									<div class="col-sm-4">
										<input class="form-control" name="sellerId" id="sellerId"
											value="${sessionScope.memberInfo.sellerId}" readonly
											type="text" style="border-bottom:1px solid purple;
											font-weight:bold;">
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label" style="font-weight:bold;"
									 for="queryType">문의 종류</label>
									<div class="col-sm-4">
										<label class="radio-inline"> <input type="radio"
											name="queryType" value="의견">의견
										</label> <label class="radio-inline"> <input type="radio"
											name="queryType" value="질문" checked>질문
										</label> <label class="radio-inline"> <input type="radio"
											name="queryType" value="항의">항의
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label" style="font-weight:bold;"
									 for="queryTitle">문의 제목</label>
									<div class="col-sm-4">
										<input class="form-control" name="queryTitle" id="queryTitle"
											value="" placeholder="제목을 입력하세요"
											 type="text" style="border-bottom:1px solid purple;
											font-weight:bold;">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label"style="font-weight:bold;"
									 for="queryContent">문의 내용</label>
									<div class="col-sm-9">
										<textarea name="queryContent" placeholder="내용을 입력하세요"
										style="width:100%; height:200px; border:1px solid gray; border-radius:5px;"
										></textarea>
									</div>
								</div>

								<div class="form-group text-center">
									<button type="submit" onclick="return frmCheck();" class="btn btn-info">
										문의등록<i class="fa fa-check spaceLeft"></i>
									</button>
									<button type="reset" class="btn btn-warning">
										재작성하기<i class="fa fa-times spaceLeft"></i>
									</button>
								</div>
							</div>

						</form>
						</c:when>
						<c:otherwise>
						<form action="userQuerySendAction.do" class="form-horizontal"
							method="post" name="queryForm" enctype="multipart/form-data">
							<div class="form-group">
								<!-- 사용자 아이디는 세션에 저장되어있는 정보로 출력 -->
								<div class="form-group">
									<label class="col-sm-2 control-label" style="font-weight:bold;"
									 for="memberId">사용자 아이디</label>
									<div class="col-sm-4">
										<input class="form-control" name="memberId" id="memberId"
											value="${sessionScope.memberInfo.memberId}" readonly
											type="text"style="border-bottom:1px solid purple;
											font-weight:bold;">
									</div>
								</div>
								
								<!-- 판매자 아이디는 파라미터로 받은 값 -->
								<div class="form-group">
									<label class="col-sm-2 control-label" style="font-weight:bold;"
									 for="sellerId">푸드트럭 아이디</label>
									<div class="col-sm-4">
										<input class="form-control" name="sellerId" id="sellerId"
											value="${id}" readonly
											type="text" style="border-bottom:1px solid purple;
											font-weight:bold;">
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 control-label" style="font-weight:bold;"
									 for="queryType">문의 종류</label>
									<div class="col-sm-4">
										<label class="radio-inline"> <input type="radio"
											name="queryType" value="의견">의견
										</label> <label class="radio-inline"> <input type="radio"
											name="queryType" value="질문" checked>질문
										</label> <label class="radio-inline"> <input type="radio"
											name="queryType" value="항의">항의
										</label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label" style="font-weight:bold;"
									 for="queryTitle">문의 제목</label>
									<div class="col-sm-4">
										<input class="form-control" name="queryTitle" id="queryTitle"
											value="" placeholder="제목을 입력하세요"
											 type="text" style="border-bottom:1px solid purple;
											font-weight:bold;">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label"style="font-weight:bold;"
									 for="queryContent">문의 내용</label>
									<div class="col-sm-9">
										<textarea name="queryContent" placeholder="내용을 입력하세요"
										style="width:100%; height:200px; border:1px solid gray; border-radius:5px;"
										></textarea>
									</div>
								</div>

								<div class="form-group text-center">
									<button type="submit" onclick="return frmCheck();" class="btn btn-info">
										문의등록<i class="fa fa-check spaceLeft"></i>
									</button>
									<button type="reset" class="btn btn-warning">
										재작성하기<i class="fa fa-times spaceLeft"></i>
									</button>
								</div>
							</div>

						</form>
						</c:otherwise>
					</c:choose>
						
					</div>
				</div>
			</div>
<!-- footer -->
<div class="container" id="footer">
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
<!-- 	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js"></script> -->

	<!-- Material Dashboard javascript methods -->
	<script src="../resources/bootstrap/js/material-dashboard.js"></script>

	<!-- Material Dashboard DEMO methods, don't include it in your project! -->
	<script src="../resources/bootstrap/js/demo.js"></script>

</body>

</html>


