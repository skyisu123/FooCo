<%@page import="com.fooco.FoodTruc.menuImage.vo.MenuImageVO"%>
<%@page import="java.util.List"%>
<%@page import="com.fooco.FoodTruc.member.vo.SellerVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="http://malsup.github.com/jquery.cycle2.js"></script>
 -->   <!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="../resources/bootstrap/css/bootstrap.css">

<!-- <script src="../resources/bootstrap/js/bootstrap.js"></script> -->   
<!-- <link href="../resources/bootstrap/css/material-dashboard.css" rel="stylesheet"/> -->
 
<!-- <link href="../resources/bootstrap/vendor/bootstrap/css/bootstrap.css" rel="stylesheet"> -->
<!-- <script src="http://malsup.github.com/jquery.cycle2.js"></script> -->
<!-- Custom Fonts -->
<link href="../resources/bootstrap/vendor/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Satisfy|Bree+Serif|Candal|PT+Sans">
<!-- <link rel="stylesheet" type="text/css" href="../resources/bootstrap/css/font-awesome.min.css"> -->
<link rel="stylesheet" type="text/css" href="../resources/bootstrap/css/style.css">
<link rel="stylesheet" type="text/css" href="../resources/bootstrap/css/TruckViewStyle.css">
<link href="../resources/bootstrap/css/footer.css" rel="stylesheet"/><!-- 푸터 CSS -->
<!-- Theme CSS -->
<link href="../resources/bootstrap/css/agency.css" rel="stylesheet">

<link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>

<!-- lightSlide jQuery 플러그인 사용 -->
<link type="text/css" rel="stylesheet" href="../resources/lightSlidePlugin/lightslider.css" />                  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../resources/lightSlidePlugin/lightslider.js"></script>
<!-- 구글맵 api -->
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDwbRWX5XOMQ47u6KXdjqRK4X327F2i6Sw&callback=initMap">
    </script>
  
<title>detailPage.jsp</title>

<script>
var $j = jQuery.noConflict();

$j(document).ready(function(){
	$("#homeButton").trigger("click");
    goReviewPage(1); //현재 페이지를 1로 설정하여 ajax 호출
   
    if(sellerId != "not"){
		setInterval("seller_alram()", 1000);
	}   
    
    $j('#lightSlider').lightSlider({
		auto: true,
        pauseOnHover: true,
        gallery: true,
        item: 1,
        loop:true,
        slideMargin: 0,
        thumbItem: 9
    });   
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
function initMap() {//위도 , 경도

	   /* alert("위도 : " + '${lat}' + ", 경도 : " + '${lng}'); */
	   var uluru = {lat: ${lat}, lng: ${lng}};
	   var map = new google.maps.Map(document.getElementById('map'), {
	     zoom: 18,
	     center: uluru
	   });
	   var marker = new google.maps.Marker({
	     position: uluru,
	     map: map
	   });
	}
</script>

<style>
#header {
  background-image: url('http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${foodTruckVo.truckImage}');
  background-color:red;
  background-repeat: no-repeat;
  background-attachment: scroll;
  background-position: center center;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  background-size: cover;
  -o-background-size: cover;
  text-align: center;
  color: white;
  margin-top:100px;
  height:500px;
}
th{ font-size:1.3em;}
.fa-caret-right{color:red;}
.profile{
background-image:url('../resources/bootstrap/img/rebonimg.png');
background-repeat: no-repeat;
background-attachment: scroll;
background-position: center center;
-webkit-background-size: cover;
-moz-background-size: cover;
background-size: cover;
-o-background-size: cover;
text-align: left;
color:white; 
font-size:1.5em;
}
.view_img{ width:190px; height:250px; }
.menuImg{
width:1000px;
height:500px;
/* background-image:url('../resources/bootstrap/img/blackboard/board1.jpg'); */
background-repeat: no-repeat;
background-attachment: scroll;
background-position: center center;
-webkit-background-size: cover;
-moz-background-size: cover;
background-size: cover;
-o-background-size: cover;
text-align: left;
color:white; 
font-size:1.5em;
margin:60px 0 0 120px;
}
.player, .player * {
    box-sizing: border-box;
    -webkit-tap-highlight-color: transparent;
}
.player .video-wrapper, .player .video-wrapper .telecine, .player .video-wrapper object, .player .video-wrapper video {
    width: 100%;
    height: 100%;
}
.player, .player h1, .player h2 {
    font-family: "Helvetica Neue",Helvetica,Arial!important;
    font-size: 10px;
}
.player {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
    -webkit-font-smoothing: auto;
    line-height: normal;
    border-collapse: separate;
    user-select: none;
}
.ytp-cued-thumbnail-overlay-image {
    background-size: cover;
    -moz-background-size: cover;
    -webkit-background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    width: 100%;
    height: 100%;
    position: absolute;
}
.btn-link{
	background-color: white;
  	color: #1E90FF;
	box-shadow: 0 14px 26px -12px rgba(153, 153, 153, 0.42), 0 4px 23px 0px rgba(0, 0, 0, 0.12), 0 8px 10px -5px rgba(153, 153, 153, 0.2);
	border-radius:10%;
}
.btn-link:hover{
	color:#1E90FF;
}

#map {
	height: 500px;
	width: 1200px;
}
   
</style>

</head>
<body class="bg-light-gray index">

<nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top col-xm-12" >
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
        String memberId = ""; // 현재 로그인 된 회원의 아이디 변수
		%>
		<c:set var="user" value="<%=user%>"/>
		<c:set var="seller" value="<%=seller%>" />
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
            <c:choose>
            	<c:when test="${not empty sessionScope.memberInfo}">
					<c:if test="${not empty user}">
					<%memberId = user.getmemberId(); %>
					<script>var sellerId = "not";</script>
		                <li>
		                    <a href="#">${sessionScope.memberInfo.memberName}님 !</a>
		                </li>
		                <li>
	                   		 <a class="page-scroll" href="../query/queryList.do" data-toggle="modal">문의글 관리</a>
	                	</li>
		            </c:if>
		            <c:if test="${not empty seller}">
		            <%memberId = seller.getSellerId(); %>
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

<!-- 판매자 전용 알람기
능 -->	
<div id="alram" style="width:73px; height:128px; position:fixed; left:1400px; top:110px; z-index:1; visibility:visible;">
	
</div>
<!-- 판매자 전용 알람기능 끝-->	
<!-- 상단 헤더 이미지 (위 css참고) -->
<div class="container col-md-12 text-center" id="header">
</div>
<!-- 헤더 이미지 끝 -->

<!-- 헤더 텍스트 -->
<div class="container-fluid col-md-offset-1 col-md-11" style="margin-top:30px">
	<p style="margin-left:10px; font-family:'Droid Serif'; font-size:4em;">판매트럭 정보</p>
	<a href="../index.do" class="btn btn-link" style="text-transform: none; border:none;">FooCo Home</a>&nbsp;
	<a href="trucFoodTrucList.do" class="btn btn-link" style="text-transform: none; border:none;">Food Truck List</a>&nbsp;
	<a href="truckDetailView.do?truckNum=${foodTruckVo.truckNum }" class="btn btn-link" style="text-transform: none; border:none;">Detail View</a>
</div>
<div class="container-fluid col-md-offset-1 col-md-10">
	<hr style="border: inset 2px black;">
</div>
<!-- 헤더 끝 -->

<!-- 바디 시작 -->
<div class="container-fluid col-md-offset-1 col-md-10" style="margin-top:20px">
	<h1 style="margin-left:10px; font-family:'Kaushan Script'; text-transform:none; "> ${foodTruckVo.truckName}</h1>
</div>
<div class="container-fluid col-md-offset-1 col-md-10" style="margin-top:30px">
	
	<div class="view_nickname col-md-2">
		<i class="fa fa-user fa-1x">&nbsp; 판매자 : ${sellerVo.sellerName}</i>
	</div>
	<div class="view_datetime col-md-offset-4 col-md-2 ">
		<i class="fa fa-clock-o fa-1x">&nbsp; 등록일 : ${foodTruckVo.truckDate} </i>
	</div>
	<div class="view_visit col-md-1 " style="margin-left:90px;">
		<i class="fa fa-eye fa-1x">&nbsp;${foodTruckVo.truckVisit}</i>
	</div>
	<div class="view_hit col-md-1" style="margin-left:20px;">
		<i class="fa fa-thumbs-o-up fa-1x">&nbsp; Like : <span id="likeSpan">${foodTruckVo.truckLike}</span></i>
	</div>
</div>


<div class="container-fluid col-md-12" id="homeBoard" style="margin-top:0px">
	<section class="col-md-12 section-padding">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12 text-center marb-35">
                    <h1 class="header-h" style="color:black; font-family:Valentina typeface; text-transform:none;">
                    	Food Truck Information
                    </h1>
                    <p class="header-p">푸드트럭 운영자의 다양한 정보를 보실수 있으며, 
                    <br>평점과 리뷰등을 등록하실수 있습니다.</p>
                </div>
                <div class="col-md-12  text-center gallery-trigger">
                    <ul>
                        <!-- <li><a class="filter" data-filter="all">Show All</a></li> -->
						<li><a class="filter" data-filter=".category-1" id="homeButton">Home</a></li>	                       
                        <li><a class="filter" data-filter=".category-2">MenuList</a></li>
						<li><a class="filter" data-filter=".category-3">Image</a></li>
                        <li><a class="filter" data-filter=".category-4" id="reviewButton" onclick="goReviewPage(1);">Review</a></li>
                        <li><a class="filter" data-filter=".category-5">Location</a></li>
                        <li><a class="filter" data-filter=".category-6">Board</a></li>
                    </ul>
                </div>
				<div class="container-fluid col-md-offset-1 col-md-10" style="margin-top:0px">
					<hr style="border: inset 1px ;">
				</div>
				      
                <div class="container-fluid col-md-12 " id="Container">
    				
    				<!-- Home 섹션 -->
    				<div class="container col-md-12 text-center mix category-1" data-myorder="2">
    					<div class="col-md-12 text-center" data-myorder="2">
							<img src="../resources/bootstrap/img/openimg.jpg" width="200" height="200" alt="" />
						</div>
						
						<div class="col-md-offset-2 col-md-10 alert alert-default" style="margin-top:50px">
							<div class="col-md-4 ">
								<%-- <img class="img-responsive" src="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${foodTruckVo.truckImage}"  alt="" /> --%>
	                            <ul id="lightSlider">
	                               	<c:forEach items="${menuImageList}" var="menuImageList" varStatus="loop">
										<li data-thumb="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${menuImageList.imageName}">
								            <img class="img-responsive" style="width:400px; height:500px;"
								            src="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${menuImageList.imageName}" />
								        </li>
		                			</c:forEach>
								</ul>
							</div>
							     
							<div class="col-md-offset-1 col-md-4">
								<table class="table" >
									<tr >
										<th class="profile">PROFILE</th>
									</tr>
									<tr  height="60" >
										<th style="padding-top:20px;"><i class="fa fa-caret-right"></i>&nbsp;상호명</th>
										<td style="padding-top:20px;">${foodTruckVo.businessName}</td>
									</tr>
									<tr height="60">
										<th style="padding-top:20px;"><i class="fa fa-caret-right"></i>&nbsp;연락처</th>
										<td style="padding-top:20px;">${sellerVo.sellerPhone}</td>
									</tr>
									<tr height="60">
										<th style="padding-top:20px;"><i class="fa fa-caret-right"></i>&nbsp;주 영업장소</th>
										<td style="padding-top:20px;">${sellerVo.sellerLocation}</td>
									</tr>
									<tr height="60">
										<th style="padding-top:20px;"><i class="fa fa-caret-right"></i>&nbsp;푸드 스타일</th>
										<td style="padding-top:20px;">${foodTruckVo.truckType}</td>
									</tr>
									<tr height="60">
										<th style="padding-top:20px;"><i class="fa fa-caret-right"></i>&nbsp;판매자 블로그</th>
										<td style="padding-top:20px;">${sellerVo.sellerBlogaddr}</td>
									</tr>
									<tr height="60">
										<th style="padding-top:20px;"><i class="fa fa-caret-right"></i>&nbsp;간략 홍보메세지</th>
										<td style="padding-top:20px;">${foodTruckVo.truckInfo}</td>
									</tr>
									
									<tr height="60">
										<th style="padding-top:20px;"><i class="fa fa-caret-right"></i>&nbsp;SNS</th>
										<td style="padding-top:20px;">
											<i class="fa fa-facebook-official fa-2x" style="color:blue;"></i>&nbsp;
											<i class="fa fa-twitter-square fa-2x" style="color:#1E90FF;" aria-hidden="true"></i>&nbsp;
											<i class="fa fa-instagram fa-2x" style="color:green;" aria-hidden="true"></i>
										</td>
									</tr>
									
									<tr height="60">
										<th style="padding-top:20px;">
											<c:if test="${isLikeExist eq 0}">
												<a onclick="updateLike();" style="cursor:pointer;" id="likeBtn">
													<img alt="" src="../resources/bootstrap/img/캡처.PNG" width="150" height="50" id="likeImg">
												</a>
											</c:if>
											<c:if test="${isLikeExist ne 0}">
												<a onclick="deleteLike();" style="cursor:pointer" id="likeBtn">
													<img alt="" src="../resources/bootstrap/img/싫어요.PNG" width="150" height="50" id="likeImg">
												</a>
											</c:if>
										</th>
										<td style="padding-top:40px;" id="likeTd">
											<b style="font-size:1.3em" id="likeNum">${foodTruckVo.truckLike}</b> 명이 좋아합니다.
											<button type="button" class="btn btn-info"
											 onclick="location.href='../query/querySend.do?id=${sellerVo.sellerId}'" style="cursor:pointer">문의하기
											</button>
										</td>
									</tr>
								</table>
							</div>
						</div>
    				</div>
    				<!-- MenuList 섹션 -->
                    <div class="container col-md-offset-3 col-md-12 text-right mix category-2 menu-restaurant" id="menuSection">
                    	<div class="col-md-12 text-center marb-35">
		                    <h1 class="header-h" style="color:black;">Menu List</h1>
		                    <p class="header-p">푸드트럭의 메뉴입니다. 
		                    <br>가격과 원산지 간략한 설명등을 확인할수 있습니다.</p>
	                	
	                	<c:choose>
	                		<c:when test="${empty menuList}">
	                			<!-- 메뉴 리스트 없는 경우 -->
	                			<h3 style="text-align:center">판매자가 등록한 메뉴가 없습니다.</h3>
	                		</c:when>
	                		<c:otherwise>
	                			<!-- 메뉴 리스트 있으면 -->
	                			<c:forEach items="${menuList}" var="menuList" varStatus="loop">
	                				<div class="menu-restaurant" data-myorder="2">
				                        <span class="clearfix">
				                        <a class="menu-title" href="#" data-meal-img="assets/img/restaurant/rib.jpg">${menuList.menuName}</a>
				                        <span style="left: 166px; right: 44px;" class="menu-line"></span>
				                        <span class="menu-price"><i class="fa fa-krw"></i>${menuList.menuPrice}</span>
				                      	</span>
				                      <span class="menu-subtitle">${menuList.menuCategory}</span> <!-- 설명글 컬럼추가 후 테스트 코드 삽입하여 확인 후 수정해야함 -->
				                    </div>
	                			</c:forEach>
	                		</c:otherwise>
	                	</c:choose>
	                	</div>
                    </div>
                	<!-- image 섹션 -->
                	<div class="mix category-3 col-md-12" data-myorder="2" id="imageSection">
                		<div class="container">
                		<div class="col-md-12 text-center marb-35">
		                    <h1 class="header-h" style="color:black;">Image Section</h1>
		                    <p class="header-p">푸드트럭 운영자가 올린 이미지 게시판입니다. 
		                    <br>이미지를 클릭하시면 크게 보실수 있습니다.</p>
	                	</div>
	                	<div class="row" style="margin-top:20px">
	                	<c:choose>
	                		<c:when test="${empty menuImageList}">
	                			<!-- 메뉴 이미지 리스트 없는 경우 -->
	                			<h3 style="text-align:center">판매자가 등록한 메뉴가 없습니다.</h3>
	                		</c:when>
	                		<c:otherwise>
	                			<!-- 메뉴 이미지 리스트 있으면 -->
	                			<c:forEach items="${menuImageList}" var="menuImageList" varStatus="loop">
									<div class="col-md-2"><img class="view_img img-responsive" src="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${menuImageList.imageName}" alt="" /></div>
	                			</c:forEach>
	                		</c:otherwise>
	                	</c:choose>
                    	</div>
                    	
						<!-- <div class="row" style="margin-top:20px">
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food9.jpg" alt="" /></div>
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food10.jpg" alt="" /></div>
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food11.jpg" alt="" /></div>
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food12.jpg" alt="" /></div>
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food13.jpg" alt="" /></div>
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food14.jpg" alt="" /></div>
						</div>
						<div class="row" style="margin-top:20px">
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food15.jpg" alt="" /></div>
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food16.jpg" alt="" /></div>
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food17.jpg" alt="" /></div>
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food18.jpg" alt="" /></div>
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food19.jpg" alt="" /></div>
							<div class="col-md-2"><img class="view_img img-responsive" src="../resources/bootstrap/img/food/food20.PNG" alt="" /></div>
						</div> -->
				    </div>
				    </div>
				    <!-- 리뷰 섹션 -->
				    <div class="mix category-4 col-md-offset-2 col-md-8" data-myorder="2" id="reviewSection">
				    	<div class="col-md-12 text-center marb-35">
		                    <h1 class="header-h" style="color:black;">Review Section</h1>
		                    <p class="header-p">여러분의 후기 혹은 기대평들을 올려주세요~^^ 
		                    <br>평점도 매기실수 있습니다~.</p>
	                	</div>
				    </div>
				                        
                    <!-- Location 섹션 -->
                    <div class="mix category-5 inner-content elements-media col-md-12">
	                <div class="container">
	                    <div class="col-md-12 text-center marb-35">
	                             <h1 class="header-h" style="color:black;">Location Section</h1>
	                             <p class="header-p">푸드트럭의 위치를 확인하실 수 있습니다.</p>
	                             <p>${foodTruckVo.truckLocation}</p>
	                             <p>구글 맵 링크 :
	                                <a target="_blank" href="https://www.google.co.kr/maps/" style="color:black;" >
	                                 https://www.google.co.kr/maps/
	                                </a>
	                             </p>
	                         </div>
	                    <div id="map"></div>
	                </div>
               </div>
					
               	</div>
            </div>
        </div>
    </section>
</div>
<!-- footer -->
<div class="container" id="footer">
	<div class="row">
		<div class="col-md-4 text-center" style="margin-top: 15px;">
			<span>Copyright &copy; Website 2017.09.11</span>
		</div>
		<div class="col-md-3 text-center">
			<ul class="list-inline social-buttons">
				<li>
					<p
						style="font-family: 'Kaushan Script', 'Helvetica Neue', Helvetica, Arial, cursive; font-size: 2em;">
						FooCo</p>
				</li>
				<li><a href="#"><i class="fa fa-facebook fa-2x"></i></a></li>

			</ul>
		</div>
		<div class="col-md-5 text-center" style="margin-top: 5px;">
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

<script>
	function replyWrite(){	// 한줄평 등록 함수
		var frm = document.replyFrm;
		var contentf = frm.replyContent;
		var score = frm.score.value;
		var memberId = '<%=memberId%>';
		
		// 폼 값 유효성 체크
		if(contentf.value == ""){
			alert("한줄평 내용을 입력해주세요.");
			content.focus();
			return false;
		}
		else{
			var replyContent = contentf.value;
			
			$.ajax({
				type : 'post',
				url : '../reply/insertReview.do',
				data : {"replyContent" : replyContent , "score" : score, "truckNum" : '${foodTruckVo.truckNum}', "memberId" : memberId },
				success : function(data){
					//alert("성공!");
					if(data == 1) goReviewPage(1);
				},
				error:function(request,status,error){
		        	alert("이용자로 로그인 후 이용하실 수 있습니다.");
		        }
			});
			
		}
		//alert(frm.replyContent.value + " " + frm.score.value);
	}
	
	function goReviewPage(currentPage){
		$.ajax({
			type : 'get',
			url : '../reply/replyTruckList.do?currentPage=' + currentPage + '&truckNum=${foodTruckVo.truckNum}',
			success : function(data){
				//alert(data);
				$("#reviewSection").html(data);
			}
		});
	}
	
	function updateLike(){
		$.ajax({
			type: 'post',
			url : '../truck/updateTruckLike.do',
			data : {"truckNum" : '${foodTruckVo.truckNum}'},
			success : function(data){
				if(data != -1){
					$("#likeNum").html(data);
					$("#likeSpan").html(data);
					$("#likeBtn").attr("onclick", "deleteLike();");
					$("#likeImg").attr("src", "../resources/bootstrap/img/싫어요.PNG");
				}
				else $("#likeTd").html("불러오는 도중 오류가 발생했습니다.");
			},
			error:function(request,status,error){
	        	alert("이용자로 로그인 후 이용하실 수 있습니다.");
	        }
		});
	}
	function deleteLike(){
		$.ajax({
			type: 'post',
			url : '../truck/deleteTruckLike.do',
			data : {"truckNum" : '${foodTruckVo.truckNum}'},
			success : function(data){
				if(data != -1){
					$("#likeNum").html(data);
					$("#likeSpan").html(data);
					$("#likeBtn").attr("onclick", "updateLike();");
					$("#likeImg").attr("src", "../resources/bootstrap/img/캡처.PNG");
				}
				else $("#likeTd").html("불러오는 도중 오류가 발생했습니다.");
			},
			error:function(request,status,error){
	        	alert("이용자로 로그인 후 이용하실 수 있습니다.");
	        }
		});
	}
</script>

<!-- jQuery -->
<!-- <script src="../resources/bootstrap/vendor/jquery/jquery.js"></script> -->

<!-- Bootstrap Core JavaScript -->
<!-- <script src="../resources/bootstrap/vendor/bootstrap/js/bootstrap.js"></script> -->

<!-- Plugin JavaScript -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js" integrity="sha384-mE6eXfrb8jxl0rzJDBRanYqgBxtJ6Unn4/1F7q4xRRyIw7Vdg9jP4ycT7x1iVsgb" crossorigin="anonymous"></script> -->

<!-- Contact Form JavaScript -->
<!-- <script src="../resources/bootstrap/js/jqBootstrapValidation.js"></script> -->
<!-- <script src="js/contact_me.js"></script> -->

<!-- Theme JavaScript -->
<!-- <script src="../resources/bootstrap/js/agency.js"></script> -->
<!-- <script src="../resources/bootstrap/js/jquery-3.2.1.js"></script> -->

<!--  -------------------------------------------------- -->
<!--   Core JS Files   -->
<!-- <script src="../resources/bootstrap/js/jquery-3.1.0.min.js" type="text/javascript"></script>
<script src="../resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../resources/bootstrap/js/material.min.js" type="text/javascript"></script>
 -->
<!--  Charts Plugin -->
<!-- <script src="../resources/bootstrap/js/chartist.min.js"></script> -->

<!--  Notifications Plugin    -->
<!-- <script src="../resources/bootstrap/js/bootstrap-notify.js"></script> -->

<!-- Material Dashboard javascript methods -->
<!-- <script src="../resources/bootstrap/js/material-dashboard.js"></script> -->

<!-- Material Dashboard DEMO methods, don't include it in your project! -->
<script src="../resources/bootstrap/js/demo.js"></script>
<script src="../resources/bootstrap/js/jquery.min.js"></script>
<script src="../resources/bootstrap/js/jquery.easing.min.js"></script>
<script src="../resources/bootstrap/js/bootstrap.min.js"></script>
<script src="../resources/bootstrap/js/jquery.mixitup.min.js"></script>
<script src="../resources/bootstrap/js/custom.js"></script>
</body>
</html>

<!-- Board 섹션 -->
<!-- <div class="mix category-6 menu-restaurant container col-md-12" id="portfolio">
	<div class="container">
		<div class="container col-md-offset-2 text-center marb-35">
                  <h1 class="header-h" style="color:black;">Board Section</h1>
                  <p class="header-p">푸드트럭 메세지전달(이미지 + 글) 게시판입니다. 
               		<br />예를 들어 9월8일날 홍대 놀이터로 출동!!!
               		<br />9월9일 도깨비 야시장에 제 푸드트럭이 참여하게되었습니다 + 이미지등
               	</p>
             	</div>
             	<div class="container col-md-offset-2 col-md-12">
            <div class="col-md-3 col-sm-6 portfolio-item">
                <a href="detailPage.jsp" class="portfolio-link" >
                    <div class="portfolio-hover">
                        <div class="portfolio-hover-content">
                            <i class="fa fa-plus fa-3x"></i>
                        </div>
        
                    </div>
                    <img src="../resources/bootstrap/img/dokkaebi.jpg" style="width:720px; height:225px;"class="img-responsive" alt="">
                </a>
                <div class="portfolio-caption">
                    <h4>Truck Obtimus</h4>
                    <p class="text-muted">Obtimus Prime 8월24일 밤도깨비 야시장 출동!!!</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 portfolio-item">
                <a href="#portfolioModal2" class="portfolio-link" data-toggle="modal">
                    <div class="portfolio-hover">
                        <div class="portfolio-hover-content">
                            <i class="fa fa-plus fa-3x"></i>
                        </div>
                    </div>
                    <img src="../resources/bootstrap/img/portfolio/portImg1.jpg" style="width:720px; height:225px;" class="img-responsive" alt="">
                </a>
                <div class="portfolio-caption">
                    <h4>Truc Sting</h4>
                    <p class="text-muted">Sting~</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 portfolio-item">
                <a href="#portfolioModal8" class="portfolio-link" data-toggle="modal">
                    <div class="portfolio-hover">
                        <div class="portfolio-hover-content">
                            <i class="fa fa-plus fa-3x"></i>
                        </div>
                    </div>
                    <img src="../resources/bootstrap/img/portfolio/portImg2.jpg"  style="width:720px; height:225px;" class="img-responsive" alt="">
                </a>
                <div class="portfolio-caption">
                    <h4>Truc Hound</h4>
                    <p class="text-muted">Hot Dog~</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 portfolio-item">
                <a href="#portfolioModal4" class="portfolio-link" data-toggle="modal">
                    <div class="portfolio-hover">
                        <div class="portfolio-hover-content">
                            <i class="fa fa-plus fa-3x"></i>
                        </div>
                    </div>
                    <img src="../resources/bootstrap/img/portfolio/portImg3.jpg"  style="width:720px; height:225px;" class="img-responsive" alt="">
                </a>
                <div class="portfolio-caption">
                    <h4>Truc Bumblbi</h4>
                    <p class="text-muted">hot hot chiken</p>
                </div>
            </div>
        </div>
	</div>
</div> -->

<!-- Video 섹션
<div class="row">
	<div class="col-md-6">
		<div class="block-heading">
			<h3><span>Vimeo</span></h3>
		</div>
		Vimeo
		<div class="video">
			<iframe src="https://player.vimeo.com/video/68663240?title=0&amp;byline=0&amp;portrait=0" width="500" height="281"></iframe>
		</div>
		End Vimeo
	</div>
	<div class="col-md-6">
		<div class="block-heading">
			<h3><span>Youtube</span></h3>
		</div>
		YouTube
		<div class="video">
			<iframe src="http://www.youtube.com/embed/jHFhZLJajlc" width="500" height="281">
			</iframe>
		</div>
		End YouTube
	</div>
</div>
<div class="clearfix space30"></div>
<div class="row ">
	<div class="col-md-6">
		<div class="block-heading">
			<h3><span>Dailymotion</span></h3>
		</div>
		DailyMotion
		<div class="video">
			<iframe src="http://www.dailymotion.com/embed/video/xqpq2o_der-lorax-trailer-2-englisch_shortfilms?width=560&amp;autoPlay=&amp;foreground=%23FFFFFF&amp;highlight=%23CCCCCC&amp;background=%23000000&amp;logo=0&amp;hideInfos=1" width="500" height="281">
			</iframe>
		</div>
		End DailyMotion
	</div>
	<div class="col-md-6">
		<div class="block-heading">
			<h3><span>Soundcloud - Audio</span></h3>
		</div>
		<div class="video">
			<iframe width="500" height="281" src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/207738557&amp;auto_play=false&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;visual=true"></iframe>
		</div>
	</div>
</div> -->