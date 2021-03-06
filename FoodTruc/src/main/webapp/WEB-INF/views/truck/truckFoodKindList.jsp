<%@page import="com.fooco.FoodTruc.member.vo.SellerVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<!-- 상단 CSS, 부트스트랩, JS, jQuery, mete 등등 -->
<%@ include file ="../../../resources/bootstrap/setting/otherCommonTop.jsp" %>


<title>Truck Food Kind List.jsp</title>
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
                <h3 class="section-subheading text-muted" style="margin-bottom:20px">Food Truck By Food Kind, 한식,양식,일식,중식,디저트,간식등등</h3>
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
					
					<!--All Truck  -->
					<div style="display: inline-block">
						<div>
							<button type="button" class="btn btn-link"
								onclick="location.href='allTruckList.do?page=1'"
								style="font-size: 17px;">All Truck</button>
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
					<!--Food kind - active   -->
					<div style="display: inline-block">
						<div style="text-align: center;">
							<img src="../resources/bootstrap/img/truckicon.png"
								style="width: 50px; height: 30px; margin-bottom: -5px;" />
						</div>
						<button type="button" class="btn btn-link" id="byKindBTN"
							onclick="location.href='truckFoodKindList.do'"
							style="font-size: 17px; background-color:#337ab7; color:white;">Food Kind</button>
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
          <div class="col-lg-12" style="text-align:left;">
	       		<div class="btn-group">
	       		<form method="post" action="truckFoodKindList.do">
					<button type="submit" class="btn btn-link" name="truckType" value="분식"
								 style="font-size:15px; width:80px; color:green;">
					<img src="../resources/bootstrap/img/food/noodles.png" style="width:20px; height:20px;">
					  	<br />분식
					 </button>
					<button type="submit" class="btn btn-link" name="truckType" value="일식"
								 style="font-size:15px;width:80px; color:green;" >
					<img src="../resources/bootstrap/img/food/sushi.png" style="width:20px; height:20px;">
					    <br/>일식
					</button>
					<button type="submit" class="btn btn-link" name="truckType" value="양식"
					    		name="truckType" style="font-size:15px;width:80px; color:green; ">
					<img src="../resources/bootstrap/img/food/steak.png" style="width:20px; height:20px;">
					     <br/>양식
					</button>
					<button type="submit" class="btn btn-link" name="truckType" value="중식"
					    		name="truckType" style="font-size:15px;width:80px; color:green;">
					<img src="../resources/bootstrap/img/food/jiaozi.png" style="width:20px; height:20px;">
					    <br/>중식
					</button>
					<button type="submit" class="btn btn-link" name="truckType" value="한식"
					   		name="truckType" style="font-size:15px;width:80px; color:green;">
					<img src="../resources/bootstrap/img/food/rice.png" style="width:20px; height:20px;">
					    <br/>한식
					</button>
					   <button type="submit" class="btn btn-link" name="truckType" value="음료"
					     			style="font-size:15px;width:80px; color:green;">
					<img src="../resources/bootstrap/img/food/glass.png" style="width:20px; height:20px;">
					     <br/>음료
					</button>
					     <button type="submit" class="btn btn-link" name="truckType" value="디저트"
					     			 style="font-size:15px;width:80px; color:green;">
					<img src="../resources/bootstrap/img/food/cupcake.png" style="width:20px; height:20px;">
					      <br/>디저트
					</button>		   
	       		</form>
			        
			    </div>
	      </div>
        <div class="col-md-12" >
			<hr style="border: inset 1px black; margin-top:15px">
		</div>
        
        <div class="row text-left" style="margin-bottom: 30px;">
			<div class="col-lg-12">
				<h2 style="text-transform:none; font-family:arial;">Food Truck By Food Kind</h2>
				<h4>음식 종류별 푸드트럭 리스트를 볼수 있습니다.</h4>  
		   	</div>
		</div>
		
         <div class="row">
         <c:choose>
         	<c:when test="${list!=null }">
         		<c:forEach items="${list }" var="row">
		         	<div class="col-md-3 col-sm-6 portfolio-item">
		                <a href="truckDetailView.do?truckNum=${row.truckNum}" class="portfolio-link" >
		                    <div class="portfolio-hover">
		                        <div class="portfolio-hover-content">
		                            <i class="fa fa-plus fa-3x"></i>
		                        </div>
		        
		                    </div>
		                    <img src="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${row.truckImage}" style="width:720px; height:225px;"class="img-responsive" alt="">
		                </a>
		                <div class="portfolio-caption">
		                    <h4>${row.businessName }</h4>
		                    <p class="text-muted">${row.truckName }</p>
		                </div>
		            </div>
	         	</c:forEach>
         	</c:when>
         	<c:otherwise>
		             <center><h2>등록된 업체가 없습니다.</h2> </center>
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