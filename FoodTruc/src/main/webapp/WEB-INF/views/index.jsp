<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.fooco.FoodTruc.member.vo.SellerVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.UserVO"%>
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

<title>index.jsp</title>
<!-- 상단 CSS, 부트스트랩, JS, jQuery, mete 등등 -->
<%@ include file ="../../resources/bootstrap/setting/indexCommonTop.jsp" %>

<script>
var sellerId = "not";
	sellerId = '${sessionScope.memberInfo}';

	$(document).ready(function() { 
		
		if(sellerId != "not"){
			setInterval("seller_alram()", 2000);
		}
	}); 
	
	function seller_alram(){
		$.ajax({
			url : "./query/queryAlram.do",
			dataType : "html",
			type : "get",
			
			success : function(data){
				if(data != null){ $("#alram").html(data); }
				
			},
			error : function(response){
				/* alert("오류생김."); */
			}
		});
	}
</script>
<style>
.portfolio-item {
	width: 220px;
	height: 350px;
}

.portfolio-link img {
	width: 220px;
	height: 200px;
}
</style>
</head>

<body id="page-top" class="index">

	<!-- Navigation -->
	<nav id="mainNav"class="navbar navbar-default navbar-custom navbar-fixed-top">

	<div class="container">
		<!-- 로고와 임시 사이트명 -->
		<div class="col-md-2">
			<img src="resources/bootstrap/img/logos/logo.png"
				style="width: 150px; height: 53px; margin-right: 50px;" />
		</div>
		<div class="col-md-3">
			<a style="font-size: 3em;" class="navbar-brand page-scroll"
				href="#page-top"> FooCo &nbsp; <!-- <small style="color:white; font-size:20px;">Food Truck Comunucation</small> -->
			</a>
		</div>
		<!-- 로고 끝 -->
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="col-md-offset-5 navbar-header page-scroll"">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> Menu <i
					class="fa fa-bars"></i>
			</button>
		</div>
		<div class="col-md-offset-4 collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>

				<li><a class="page-scroll" href="truck/trucFoodTrucList.do">Look
						around</a></li>

				<li><a class="page-scroll" href="#services">Services</a></li>
				<li><a class="page-scroll" href="#portfolio">FooCo-Top10 </a></li>
				<li><a class="page-scroll" href="#merit">FooCo-Merit</a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- Header -->
	<header>
	<div class="container">
		<div class="intro-text">
			<div class="intro-lead-in">Welcome To Our Studio!</div>
			<div class="intro-heading" style="font-size: 2em;">Food Truck
				Communication Site</div>
			<a href="truck/trucFoodTrucList.do"><button type="button"
					class="btn btn-warning btn-lg"
					style="box-shadow: 3px 3px 5px grey;">Food Truck More</button></a>
		</div>
	</div>
	</header>

	<!-- Services Section -->
	<section id="services">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">Services</h2>
				<h3 class="section-subheading text-muted">Lorem ipsum dolor sit
					amet consectetur.</h3>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-4">
				<div class="timeline-image" style="margin-left: 30px;">
					<img class="img-circle img-responsive"
						src="resources/bootstrap/img/openimg.jpg"
						style="width: 300px; height: 200px;" alt="">
				</div>
				<h4 class="service-heading">Open Date Food Truck List</h4>
				<p class="text-muted">내가 원하는 날짜에 푸드트럭이 영업중인지 확인 하실수 있습니다. 원하는
					날짜로 검색해보세요~!</p>
			</div>

			<div class="col-md-4">
				<div class="timeline-image" style="margin-left: 30px;">
					<img class="img-circle img-responsive"
						src="resources/bootstrap/img/trukImoticon.jpg"
						style="width: 300px; height: 200px;" alt="">
				</div>
				<h4 class="service-heading">Food Truck List</h4>
				<p class="text-muted">다양한 푸드트럭들의 정보를 볼수 있습니다. 푸드트럭을 운영하시는 분들은 내
					푸드트럭을 다양한 방법으로 홍보하실수 있습니다.</p>
			</div>

			<div class="col-md-4">
				<div class="timeline-image" style="margin-left: 30px;">
					<img class="img-circle img-responsive"
						src="resources/bootstrap/img/gold-medal.jpg"
						style="width: 300px; height: 200px;" alt="">
				</div>
				<h4 class="service-heading">Rank Food Truck List</h4>
				<p class="text-muted">랭킹별 푸드트럭 맛집들을 보실수있습니다~ 어느 푸드트럭이 이달의 1위를
					차지했을까요???</p>
			</div>
		</div>
	</div>
	</section>

	<!-- Portfolio Grid Section -->
	<section id="portfolio" class="bg-light-gray">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">Food Truck Top 10!!!</h2>
				<h3 class="section-subheading text-muted">이번달 랭크에 올라온 푸드트럭
					리스트입니다.</h3>
			</div>
		</div>

		<div class="row">

			<h2>${errorMsg }</h2>
			<c:forEach items="${rankLists}" var="ranklike">

				<div class="col-md-3 col-sm-6 portfolio-item">

					<a href="truck/truckDetailView.do?truckNum=${ranklike.truckNum}"
						class="portfolio-link"> <!-- main 이미지 시작 -->
						<div class="portfolio-hover">
							<div class="portfolio-hover-content">
								<i class="fa fa-search fa-3x"></i>
							</div>
						</div>
						<div style="position: relative; z-index: 1;">
							<div style="position: relative; top: 0px; left: 0px;">
								<img
									src="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${ranklike.truckImage}"
									style="width: 720px; height: 225px;" class="img-responsive"
									alt=""></img>
							</div>
						</div> <!-- main 이미지 끝 --> <!-- 순위 시작 -->
						<div style="position: absolute; z-index: 2;">
							<div style="position: relative; top: 0px; left: 0px;">
								<img src="./resources/bootstrap/img/tag.png"
									style="width: 40px; height: 80px" class="img-responsive" alt=""></img>
							</div>
						</div>

						<div style="position: absolute; z-index: 3;">
							<div style="position: relative; top: 0px; left: 10px;">
								<h3 style="color: red">${ranklike.RN}</h3>

							</div>
						</div> <!-- 순위 끝 -->
					</a>

					<div class="portfolio-caption">
						<h4>${ranklike.truckName}</h4>
						<p class="text-muted">${ranklike.truckType}</p>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>
	</section>

	<!-- About Section -->
	<section id="merit">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">Merit</h2>
				<h3 class="section-subheading text-muted">저희 사이트를 이용하면 이런점이 좋아요
					~</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<ul class="timeline">
					<li>
						<div class="timeline-image">
							<img class="img-circle img-responsive"
								src="resources/bootstrap/img/1.jpg" alt="">
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<h4>푸드트럭 홍보!</h4>
								<!-- <h4 class="subheading">Our Humble Beginnings</h4> -->
							</div>
							<div class="timeline-body">
								<p class="text-muted">푸드트럭을 운영중에 계시거나 운영을 계획중이신 사장님 및 예비
									사장님분들을 위한 정보공유 와 홍보수단으로 활용할 수 있습니다.</p>
							</div>
						</div>
					</li>
					<li class="timeline-inverted">
						<div class="timeline-image">
							<img class="img-circle img-responsive"
								src="resources/bootstrap/img/2.jpg" alt="">
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<h4>매출상승!</h4>
								<!-- <h4 class="subheading">An Agency is Born</h4> -->
							</div>
							<div class="timeline-body">
								<p class="text-muted">효과적인 홍보로 인해 푸드트럭 이용자가 급증하여 매출로 이어질수
									있습니다.</p>
							</div>
						</div>
					</li>
					<li>
						<div class="timeline-image">
							<img class="img-circle img-responsive"
								src="resources/bootstrap/img/3.jpg" alt="">
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<h4>원하는 음식을 한눈에 ~</h4>
								<!-- <h4 class="subheading">Transition to Full Service</h4> -->
							</div>
							<div class="timeline-body">
								<p class="text-muted">원하는 푸드트럭의 위치와 영업시간을 알아내어 시간을 절약해보세요!</p>
							</div>
						</div>
					</li>
					<li class="timeline-inverted">
						<div class="timeline-image">
							<img class="img-circle img-responsive"
								src="resources/bootstrap/img/4.jpg" alt="">
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<h4>개인적인 홍보는 이제그만 ~</h4>
								<!-- <h4 class="subheading">Phase Two Expansion</h4> -->
							</div>
							<div class="timeline-body">
								<p class="text-muted">여기저기 흩어져 있는 정보를 한곳에!</p>
							</div>
						</div>
					</li>
					<li class="timeline-inverted">
						<div class="timeline-image">
							<h4>
								Be Part <br>Of Our <br>Story!
							</h4>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	</section>

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
<!-- 부트스트랩 script 인클루드 -->
<%@ include file ="../../resources/bootstrap/setting/indexCommonBottom.jsp" %>

</body>
</html>