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
<!-- 관리자가 생성한 locationList JS 지역 toggle & hide -->
<script src="../resources/bootstrap/setting/JS_locationList.js"></script>

<title>Truck Location List.jsp</title>
<style>
.btn-link{
	background-color: white;
  	color: #1E90FF;
	box-shadow: 0 14px 26px -12px rgba(153, 153, 153, 0.42), 0 4px 23px 0px rgba(0, 0, 0, 0.12), 0 8px 10px -5px rgba(153, 153, 153, 0.2);
	border-radius:10%;
}
.btn-link:hover{
	color:#1E90FF;
}
.btn-default{
	border-radius:10%;
}
</style>
<script>

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
			/* alert("asdas"); */
		}
	});
}
</script>


</head>
<body class="bg-light-gray index">

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

		<%
		Object obj = session.getAttribute("memberInfo");
       	UserVO user = null;
        SellerVO seller = null; 
        if(obj instanceof UserVO){user = (UserVO)obj;}
        else{seller = (SellerVO)obj;}
		%>
		<c:set var="user" value="<%=user%>" />
		<c:set var="seller" value="<%=seller%>" />
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
            	<c:when test="${not empty sessionScope.memberInfo}">
					<c:if test="${not empty user}">
					<script>var sellerId = "not";</script>
		                <li>
		                    <a href="#">${sessionScope.memberInfo.memberName}님 !</a>
		                </li>
		                <li>
	                   		 <a class="page-scroll" href="../query/queryList.do" data-toggle="modal">문의글 관리</a>
	                	</li>
		            </c:if>
		            <c:if test="${not empty seller}">
	                <script>sellerId = '${sessionScope.memberInfo}';</script>
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
	<section id="portfolio" class="bg-light-gray">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">Food Truck List</h2>
				<h3 class="section-subheading text-muted" style="margin-bottom: 20px">Food Truck By Location, 우리지역에는 어떤 푸드트럭이 있을까요???.</h3>
			</div>
		</div>
		<!-- 테마별 버튼 -->
		<div class="row">
			<div class="col-lg-12">
				<h5 class="text-muted" style="font-style: italic">원하시는 테마로
					보고싶으시면 아래의 테마를 선택하세요.</h5>
				
					<!-- Food Truck List -->
					<div style="display: inline-block">
						<div>
							<button type="button" class="btn btn-link" 
							onclick="location.href='trucFoodTrucList.do'" 
							style="font-size:17px;background-color: white; color:#1E90FF ">Truck List</button>
						</div>
					</div>
					
					<!--All Truck   -->
					<div style="display: inline-block">
						<div>
							<button type="button" class="btn btn-link"
								onclick="location.href='allTruckList.do?page=1'"
								style="font-size: 17px;background-color: white; color:#1E90FF">All Truck</button>
						</div>
					</div>
					<!--Rank  -->
					<div style="display: inline-block">
						<button type="button" class="btn btn-link"
							onclick="location.href='truckRankList.do'"
							style="font-size: 17px;background-color: white; color:#1E90FF" >Rank</button>
					</div>
					<!--Location  - active -->
					<div style="display: inline-block">
						<div style="text-align: center;">
							<img src="../resources/bootstrap/img/truckicon.png"
								style="width: 50px; height: 30px; margin-bottom: -5px; background-color: white; " />
						</div>
						<button type="button" class="btn btn-link "
							onclick="location.href='truckLocationList.do'"
							style="font-size: 17px; background-color:#337ab7; color:white;">Location</button>
					</div>
					<!--Food kind  -->
					<div style="display: inline-block">
						<button type="button" class="btn btn-link"
							onclick="location.href='truckFoodKindList.do'"
							style="font-size: 17px;background-color: white; color:#1E90FF">Food Kind</button>
					</div>
					<!-- Open date -->
					<div style="display: inline-block">
						<button type="button" class="btn btn-link"
							onclick="location.href='truckOpenDateList.do?firstJoin=1'"
							style="font-size: 17px;background-color: white; color:#1E90FF">Open Date</button>
					</div>
					
					<!-- Search -->
					<div style="display: inline-block; margin-left:90px;" >
					<form action="truckSearchList.do" method="post">
						<select class="btn btn-white"name="searchColumn" style="height: 40px;">
							<option value="truckName">트럭이름</option>
							<option value="menuName">메뉴명</option>
						</select>
						<input name="searchText"size="30" type="text" class="btn btn-white"
							style="height: 40px;" placeholder="Search Text" />
						<button type="submit" style="height: 40px;"
							class="btn btn-link">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</form>
					</div>
				
			</div>
		</div>
		<!-- 테마별 버튼 끝 -->

		<div class="col-lg-12" style="text-align: left;">
			<div class="btn-group">
				<button type="button" id="seoul" class="btn btn-default" onclick=""	style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/seoul.png"	
					style="width: 20px; height: 20px; "><br />서울
				</button>
				<button type="button" id="incheon" class="btn btn-default" onclick="" style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/incheon.png" 
					style="width: 20px; height: 20px; "><br />인천
				</button>
				<button type="button" id="kyungki" class="btn btn-default" onclick="" style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/kyungki.png" style="width: 20px; height: 20px;"><br />경기
				</button>
				<button type="button" id="chungcheng" class="btn btn-default" onclick="" style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/kyungNam.png" style="width: 20px; height: 20px;"><br />충청
				</button>
				<button type="button" id="junla" class="btn btn-default" onclick="" style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/junla.png" style="width: 20px; height: 20px;"><br />전라
				</button>
				<button type="button" id="kangwon" class="btn btn-default" onclick="" style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/kangwon.png" style="width: 20px; height: 20px;"><br />강원
				</button>
				<button type="button" id="daegu" class="btn btn-default" onclick="" style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/daegu.png" style="width: 20px; height: 20px;"><br />대구
				</button>
				<button type="button" id="busan" class="btn btn-default" onclick="" style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/pusan.png"	style="width: 20px; height: 20px;"><br />부산
				</button>
				<button type="button" id="ulsan" class="btn btn-default" onclick="" style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/ulsan.png"	style="width: 20px; height: 20px;"><br />울산
				</button>
				<button type="button" id="kwangju" class="btn btn-default" onclick="" style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/kwangju.png"	style="width: 20px; height: 20px;"><br />광주
				</button>
				<button type="button" id="jeju" class="btn btn-default" onclick="" style="font-size: 15px; width: 100px">
					<img src="../resources/bootstrap/img/jeju.png"	style="width: 20px; height: 20px;"><br />제주도
				</button>


				<div class="btn-group" id="seoul1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=서울,강남구'" style="font-size: 15px; width: 80px">강남구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=서울,관악구'" style="font-size: 15px; width: 80px">관악구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=서울,강서구'" style="font-size: 15px; width: 80px">강서구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=서울,종로구'" style="font-size: 15px; width: 80px">종로구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=서울,용산구'" style="font-size: 15px; width: 80px">용산구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=서울,노원구'" style="font-size: 15px; width: 80px">노원구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=서울,은평구'" style="font-size: 15px; width: 80px">은평구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=서울,마포구'" style="font-size: 15px; width: 80px">마포구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=서울,영등포구'" style="font-size: 15px; width: 80px">영등포구</button>
				</div>
				<div class="btn-group" id="incheon1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=인천,연수구'" style="font-size: 15px; width: 80px">연수구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=인천,남동구'" style="font-size: 15px; width: 80px">남동구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=인천,남구'" style="font-size: 15px; width: 80px">남구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=인천,동구'" style="font-size: 15px; width: 80px">동구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=인천,서구'" style="font-size: 15px; width: 80px">서구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=인천,중구'" style="font-size: 15px; width: 80px">중구</button>
				</div>
				<div class="btn-group" id="kyungki1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,수원'" style="font-size: 15px; width: 80px">수원</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,성남'" style="font-size: 15px; width: 80px">성남</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,고양'" style="font-size: 15px; width: 80px">고양</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,용인'" style="font-size: 15px; width: 80px">용인</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,부천'" style="font-size: 15px; width: 80px">부천</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,안산'" style="font-size: 15px; width: 80px">안산</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,안양'" style="font-size: 15px; width: 80px">안양</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,화성'" style="font-size: 15px; width: 80px">화성</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,의정부'" style="font-size: 15px; width: 80px">의정부</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,시흥'" style="font-size: 15px; width: 80px">시흥</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=경기,파주'" style="font-size: 15px; width: 80px">파주</button>
				</div>
				
				<div class="btn-group" id="chungcheng1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=충청,천안'" style="font-size: 15px; width: 80px">천안</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=충청,공주'" style="font-size: 15px; width: 80px">공주</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=충청,논산'" style="font-size: 15px; width: 80px">논산</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=충청,아산'" style="font-size: 15px; width: 80px">아산</button>
				</div>
				
				<div class="btn-group" id="junla1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=전라,전주'" style="font-size: 15px; width: 80px">전주</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=전라,군산'" style="font-size: 15px; width: 80px">군산</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=전라,익산'" style="font-size: 15px; width: 80px">익산</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=전라,남원'" style="font-size: 15px; width: 80px">남원</button>
				</div>
				
				<div class="btn-group" id="kangwon1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=강원,춘천'" style="font-size: 15px; width: 80px">춘천</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=강원,원주'" style="font-size: 15px; width: 80px">원주</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=강원,강릉'" style="font-size: 15px; width: 80px">강릉</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=강원,속초'" style="font-size: 15px; width: 80px">속초</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=강원,삼척'" style="font-size: 15px; width: 80px">삼척</button>
				</div>
				
				<div class="btn-group" id="daegu1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=대구,중구'" style="font-size: 15px; width: 80px">중구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=대구,동구'" style="font-size: 15px; width: 80px">동구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=대구,서구'" style="font-size: 15px; width: 80px">서구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=대구,남구'" style="font-size: 15px; width: 80px">남구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=대구,북구'" style="font-size: 15px; width: 80px">북구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=대구,수성구'" style="font-size: 15px; width: 80px">수성구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=대구,달서구'" style="font-size: 15px; width: 80px">달서구</button>
				</div>
				
				<div class="btn-group" id="busan1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=부산,중구'" style="font-size: 15px; width: 80px">중구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=부산,서구'" style="font-size: 15px; width: 80px">서구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=부산,동구'" style="font-size: 15px; width: 80px">동구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=부산,영도구'" style="font-size: 15px; width: 80px">영도구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=부산,부산진구'" style="font-size: 15px; width: 80px">부산진구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=부산,동래구'" style="font-size: 15px; width: 80px">동래구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=부산,남구'" style="font-size: 15px; width: 80px">남구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=부산,북구'" style="font-size: 15px; width: 80px">북구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=부산,해운대구'" style="font-size: 15px; width: 80px">해운대구</button>
				</div>
				
				<div class="btn-group" id="ulsan1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=울산,중구'" style="font-size: 15px; width: 80px">중구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=울산,남구'" style="font-size: 15px; width: 80px">남구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=울산,동구'" style="font-size: 15px; width: 80px">동구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=울산,북구'" style="font-size: 15px; width: 80px">북구</button>
				</div>
				
				<div class="btn-group" id="gwangju1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=광주,동구'" style="font-size: 15px; width: 80px">동구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=광주,서구'" style="font-size: 15px; width: 80px">서구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=광주,남구'" style="font-size: 15px; width: 80px">남구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=광주,북구'" style="font-size: 15px; width: 80px">북구</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=광주,광산구'" style="font-size: 15px; width: 80px">광산구</button>
				</div>
				
				<div class="btn-group" id="jeju1">
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=제주,제주시'" style="font-size: 15px; width: 80px">제주시</button>
					<button type="button" class="btn btn-link" onclick="location.href='truckLocationList.do?location=제주,서귀포시'" style="font-size: 15px; width: 80px">서귀포시</button>
				</div>
			</div>
		</div>

		<div class="col-md-12">
			<hr style="border: inset 1px black; margin-top: 15px">
		</div>
		
		<div class="row text-left" style="margin-bottom: 30px;">
			<div class="col-lg-12">
				<h2 style="text-transform:none; font-family:arial;">Food Truck By Location</h2>
				<h4>지역별 푸드트럭 리스트를 볼수 있습니다.</h4>  
		   	</div>
		</div>
		
		<div class="row">
		<c:choose>
			<c:when test="${empty locationFoodList}">
				<!-- 가져온 위치 리스트가 없는 경우 -->
				<h3 style="text-align:center;">해당 지역에 등록된 트럭이 없습니다.</h3>
			</c:when>
			<c:otherwise>
				<!-- 리스트가 있으면 -->
				<c:forEach items="${locationFoodList}" var="loList" varStatus="loop">
				<div class="col-md-3 col-sm-6 portfolio-item">
					<a href="truckDetailView.do?truckNum=${loList.truckNum}" class="portfolio-link">
						<div class="portfolio-hover">
							<div class="portfolio-hover-content">
								<i class="fa fa-plus fa-3x"></i>
							</div>
						</div> <img src="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${loList.truckImage}" style="width: 720px; height: 225px;" class="img-responsive" alt="">
					</a>
					<div class="portfolio-caption">
						<h4>${loList.truckName}</h4><!-- 트럭이름 -->
						<p class="text-muted">${loList.businessName}</p><!-- 업체명 -->
					</div>
				</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		</div>
		
		<!-- 페이징 시작 -->
		<div class="col-sm-12 text-center">
			<c:if test="${paging.currentPage ne paging.firstPageNo}">
				<a
					href="${urlPath}&currentPage=${paging.prevPageNo}&setType=${setType}"
					class="prev btn btn-default"
					style="color: black; font-weight: bold; background-color: white;">
					이전 </a>
			</c:if>

			<span> <c:forEach var="i" begin="${paging.startPageNo}"
					end="${paging.endPageNo}" step="1">
					<c:choose>
						<c:when test="${i eq paging.currentPage}">
							<b> <font size=+1> <a
									href="${urlPath}&currentPage=${i}&setType=${setType}"
									class="prev btn btn-default"
									style="color: yellow; background-color: black;"> ${i} </a>
							</font>
							</b>
						</c:when>
						<c:otherwise>
							<a href="${urlPath}&currentPage=${i}&setType=${setType}"
								class="btn btn-default"
								style="color: black; background-color: white;">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</span>

			<c:if test="${paging.currentPage ne paging.finalPageNo}">
				<a
					href="${urlPath}&currentPage=${paging.nextPageNo}&setType=${setType}"
					class="next btn btn-default"
					style="color: black; background-color: white; font-weight: bold;">다음</a>
			</c:if>
		</div>
		<!-- 페이징 끝  -->
	</div>
	<!-- ./container end -->
	</section>
	
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
	<!-- footer end -->

<!-- 부트스트랩 script 인클루드 -->
<%@ include file ="../../../resources/bootstrap/setting/otherCommonBottom.jsp" %>
</body>
</html>