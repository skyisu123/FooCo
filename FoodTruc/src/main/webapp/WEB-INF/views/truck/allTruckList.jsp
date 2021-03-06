<%@page import="com.fooco.FoodTruc.member.vo.SellerVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<!-- 상단 CSS, 부트스트랩, JS, jQuery, mete 등등 -->
<%@ include file ="../../../resources/bootstrap/setting/otherCommonTop.jsp" %>

<title>All Truck Page.jsp</title>
<script>
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
			$.ajax({	 
				url : "allTruckListAdd.do?page="+count+"",
				dataType : "html",
				type : "get",
				success : function(data){
					$("#truckList").append(data);
				},
			});
		});
		
	});
	
	function seller_alram(){// 판매자 문의 알람
		
		$.ajax({
			url : "../query/queryAlram.do",
			dataType : "html",
			type : "get",
			
			success : function(data){
				if(data != null){ $("#alram").html(data); }
				
			},
			error : function(response){
			/* 	alert("asdas"); */
			}
		});
	
	}
</script>
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

</style>
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
		<%
		Object obj = session.getAttribute("memberInfo");
       	UserVO user = null;
        SellerVO seller = null; 
        if(obj instanceof UserVO){user = (UserVO)obj;}
        else{seller = (SellerVO)obj;}
		%>
		<c:set var="user" value="<%=user%>"/>
		<c:set var="seller" value="<%=seller%>" />
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
				<h3 class="section-subheading text-muted"
					style="margin-bottom: 20px">All Food Truck, 모든 푸드트럭 리스트입니다.
					천천히 둘러보세요.</h3>
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
							style="font-size:17px;">Truck List</button>
						</div>
					</div>
					
					<!--All Truck - active   -->
					<div style="display: inline-block">
						<div style="text-align: center;">
							<img src="../resources/bootstrap/img/truckicon.png"
								style="width: 50px; height: 30px; margin-bottom: -5px;" />
						</div>
						<div>
							<button type="button" class="btn btn-link"
								onclick="location.href='allTruckList.do?page=1'"
								style="font-size: 17px; background-color:#337ab7; color:white;">All Truck</button>
						</div>
					</div>
					<!--Rank  -->
					<div style="display: inline-block">
						<button type="button" class="btn btn-link"
							onclick="location.href='truckRankList.do'"
							style="font-size: 17px;">Rank</button>
					</div>
					<!--Location  -->
					<div style="display: inline-block">
						<button type="button" class="btn btn-link "
							onclick="location.href='truckLocationList.do'"
							style="font-size: 17px;">Location</button>
					</div>
					<!--Food kind  -->
					<div style="display: inline-block">
						<button type="button" class="btn btn-link"
							onclick="location.href='truckFoodKindList.do'"
							style="font-size: 17px;">Food Kind</button>
					</div>
					<!-- Open date -->
					<div style="display: inline-block">
						<button type="button" class="btn btn-link"
							onclick="location.href='truckOpenDateList.do?firstJoin=1'"
							style="font-size: 17px;">Open Date</button>
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
		
		<!-- hr라인 -->
		<div class="row">
			<hr style="border: inset 1px black; margin-top: 30px">
		</div>
		
		<!-- all food truck 시작 -->
		<div class="row text-left" style="margin-bottom: 30px;">
			<div class="col-lg-12">
				<h2 style="text-transform:none; font-family:arial;">All Food Truck</h2>
				<h4>모든 푸드트럭을 보실수 있습니다.</h4>  
		   	</div>
		</div>
		<!-- portpolio시작(list시작) -->
		<div class="row" id="truckList">
		<c:forEach items="${list }" var="row">
			
			<div class="col-md-3 col-sm-6 portfolio-item">
				<a href="truckDetailView.do?truckNum=${row.truckNum}" class="portfolio-link" id="truck_image" >
					<div class="portfolio-hover">
						<div class="portfolio-hover-content">
							<i class="fa fa-search fa-3x"></i>
						</div>
					</div>
					<!-- 이미지명 삽입받을곳. --><!-- TRUCK_IMAGE(컬럼명) -->
					
					<img src="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${row.truckImage}"
					style="width: 720px; height: 225px;" class="img-responsive" alt="">
					
				</a>
				<div class="portfolio-caption">
					<!-- 트럭 이름과 트력  -->
					<h4 id="truck_name">${row.truckName }</h4><!-- TRUCK_NAME(컬럼명) -->
					<p id="truck_info" class="text-muted">${row.truckInfo }</p><!-- TRUCK_INFO(컬럼명) -->
				</div>
			</div>
		</c:forEach>
		</div>
		<!-- portpolio끝 -->	
		<div class="row" align="center" >
			<button type="submit"id="moreButton" class="btn btn-link pull-center"
				style="font-size:20px; color:black;">+ More</button>
		</div>	
			
	</div>
	<!--  -->
</section>
<!-- section end -->
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