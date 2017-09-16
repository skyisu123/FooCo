<%@page import="com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO"%>
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

<title>All Truck Page.jsp</title>
<script>
var sellerId = "not";
sellerId = '${sessionScope.memberInfo}';

$(document).ready(function() { 
	
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
<body class="bg-light-gray">

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
        if(obj instanceof UserVO){user = (UserVO)obj;}
        else{seller = (SellerVO)obj;}
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
	<!-- Portfolio Grid Section -->
	<script>
		var msg = '${successMsg}';
		if(msg == '성공') alert("판매트럭 정보 수정이 완료되었습니다.");
		
	</script>
	<section id="portfolio" class="bg-light-gray">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">My Truck List</h2>
			</div>
		</div>
		<div class="card-content table-responsive">
			<table class="table table-hover">
				<colgroup>
					<col width="10%" />
					<col width="20%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
				</colgroup>
				<thead class="text-info" >
					<th style="text-align:center;">내 트럭홍보이미지</th>
					<th style="text-align:center;">트럭이름</th>
					<th style="text-align:center;">조회수</th>
					<th style="text-align:center;">좋아요</th>
					<th style="text-align:center;">등록날짜</th>
					<th style="text-align:center;">상태</th>
					<th style="text-align:center;">비고</th>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty myTruckList}">
							<c:forEach items="${myTruckList}" var="myList" varStatus="loop">
							<tr style="text-align:center;cursor:pointer;">
								<td>
								<a href="../truck/truckDetailView.do?truckNum=${myList.truckNum}">
									<img class="img-fluid img-responsive" src="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${myList.truckImage}
									 " style="width:150px; height:100px">
								</a>
								</td>
								<td style="padding-top:50px;">${myList.truckName}</td>
								<td style="padding-top:50px;">${myList.truckVisit}</td>
								<td style="padding-top:50px;">${myList.truckLike}</td>
								<td style="padding-top:50px;">${myList.truckDate}</td>
								<td style="padding-top:38px;">
									<c:choose>
										<c:when test="${myList.addreq eq 'N'}">
											<button class="btn btn-success btn-sm">승인완료</button>
										</c:when>
										<c:otherwise>
											<button class="btn btn-warning btn-sm">승인대기</button>
										</c:otherwise>
									</c:choose>
								</td>
								<td style="padding-top:7px;">
								<c:if test="${myList.addreq eq 'N'}">
									<button class="btn btn-info btn-sm" 
										onclick="location.href='../foodTruck/truckModify.do?truckNum=${myList.truckNum}'">
										트럭정보 수정
									</button>
									<button class="btn btn-info btn-sm" 
										onclick="location.href='../foodTruck/truckMenuModify.do?truckNum=${myList.truckNum}'">
										메뉴정보 수정
									</button>
								</c:if>
								<c:choose>
									<c:when test="${myList.addreq eq 'N'}">
										<button class="btn btn-danger btn-sm" onclick="deleteTruck('${myList.truckNum}');">삭제</button>
									</c:when>
									<c:otherwise>
										<button style="margin-top:40px;" class="btn btn-danger btn-sm" onclick="deleteTruck('${myList.truckNum}');">삭제</button>
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="7" style="text-align:center">현재 등록한 트럭이 없습니다.<br>
									<button class="btn btn-info btn-sm" onclick="location.href='../foodTruck/truckAdd.do'">트럭등록</button>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		
		<script>
			function deleteTruck(truckNum){
				if(confirm("해당 트럭을 정말 삭제하시겠습니까?\n 삭제하시면 되돌릴 수 없습니다.")){
					location.href="deleteTruck.do?truckNum=" + truckNum;
				}
			}
		</script>
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