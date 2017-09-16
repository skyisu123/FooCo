<%@page import="com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.SellerVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>

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
var menuAddResult = '${menuAddResult}';
if(menuAddResult == "성공") alert("메뉴판 수정이 성공적으로 완료되었습니다.");

function errFunc(){
	alert("실패임 ㅠㅠ;");
}
//성공시 호출할 함수
function sucFunc(){
	alert("성공임 ^^*");
}
	var count = 1;
	var isJoinResult = '${isSellerJoin}';
	var update = '${updateMsgS}';
	
	$(document).ready(function() { 
		
		if(update ==""){
			
		}else{
			alert(update);//회원정보수정유무 확인
		}
		
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
			/* alert(count); */
			$.ajax({	//ajax폴더의 allTruckListAdd에 있음. 
				url : "allTruckListAdd.do?page="+count+"",
				dataType : "html",
				type : "get",
				success : function(data){
					$("#truckList").append(data);
				},
				error : errFunc
			});
		});
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
	<%-- 	<%
		Object obj = session.getAttribute("memberInfo");
       	UserVO user = null;
        SellerVO seller = null; 
        if(obj instanceof UserVO){user = (UserVO)obj;}
        else{seller = (SellerVO)obj;}
		%> --%>
		<c:set var="user" value="<%=user%>"/>
		<c:set var="seller" value="<%=seller%>" />
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
<section id="portfolio" class="bg-light-gray">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">My Page</h2>
				<h3 class="section-subheading text-muted"
					style="margin-bottom: 20px">fooCo는 회원님의 정보를 무단으로 사용하지 않습니다.</h3>
			</div>
		</div>
		<!-- 사용자 마이페이지 -->
		<script>
		$(document).ready(function(){
		    $("#MyComentUser").mouseover(function(){
		        $("#MyComentUser1").text("한줄평수");
		    });
		    $("#MyLikeUser").mouseover(function(){
		        $("#MyLikeUser1").text("좋아요수");
		    });
		    $("#MyInfoUser").mouseover(function(){
		        $("#MyInfoUser1").text("정보수정");
		    });

		    $("#MyComentUser").mouseout(function(){
		        $("#MyComentUser1").text("MyComment");
		    });
		    $("#MyLikeUser").mouseout(function(){
		        $("#MyLikeUser1").text("MyLike");
		    });
		    $("#MyInfoUser").mouseout(function(){
		        $("#MyInfoUser1").text("MyInfo");
		    });
		    
		});
		</script>
		<c:if test="${not empty user}">
			<div class="row">
				<div class="col-lg-4 col-md-6 col-sm-6">
					<button type="button" id="myComentbtn" > <!-- reply_truck=>reply_content -->
						<div class="card card-stats" style="height:160px;" id="MyComentUser">
							<div class="card-header" data-background-color="red" style="width:250px; height:80px;">
								<i class="material-icons" id="MyComentUser1">MyComment</i>
							</div>
							<div class="card-content" >
								<h1 class="title" style="padding:30%;"> ${CountContentUser} <small>&nbsp;개</small></h1>
							</div>
						</div>
					 </button>
					</div>
				<div class="col-lg-4 col-md-6 col-sm-6">
					<button type="button" onclick="" >
						<div class="card card-stats" style="height:160px;" id="MyLikeUser">
							<div class="card-header" data-background-color="red" style="width:250px; height:80px;">
								<i class="material-icons" id="MyLikeUser1">MyLike</i>
							</div>
							<div class="card-content" >
								<h1 class="title" style="padding:30%;"> ${likeCountUser} <small>&nbsp;개</small></h1>
							</div>
						</div>
					 </button>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6">
					<button type="button" onclick="location.href='MyInfoPage.do'" >
						<div class="card card-stats" style="height:160px;" id="MyInfoUser">
							<div class="card-header" data-background-color="red" style="width:250px; height:80px;">
								<i class="material-icons" id="MyInfoUser1">MyInfo</i>
							</div>
							<div class="card-content" >
								<h4 class="title" style="padding-top:30%; text-align:center;">${sessionScope.memberInfo.memberName}님<br/><br/>정보수정</h4>
							</div>
						</div>
					 </button>
				</div>
			</div>	
		</c:if>	
		
		<script>
			$("#myComentbtn").click(function(){
				$.ajax({	
					url : "CountContentUserList.do",
					dataType : "html",
					type : "get",
					success : function(data){
						$("#myCommentbtnC").html(data);
						
					},
					error :function(e){alert(e.responseText)}
				});
			});
			
			$("#MyLikeUser").click(function(){
				$.ajax({	
					url : "likeCountUserList.do",
					dataType : "html",
					type : "get",
					success : function(data){
						$("#myCommentbtnC").html(data);
						
					},
					error :function(e){alert(e.responseText)}
				});
			});
		</script>
		<div class="row" id="myCommentbtnC">
		
		</div>
		
		<!-- 사용자 마이페이지 끝 -->
		
		
		
		<!-- 판매자 마이페이지 시작-->
		<script>
		$(document).ready(function(){
		    $("#MyComentSeller").mouseover(function(){
		        $("#MyComentSeller1").text("한줄평수");
		    });
		    $("#MyLikeSeller").mouseover(function(){
		        $("#MyLikeSeller1").text("좋아요수");
		    });
		    $("#MyScoreSeller").mouseover(function(){
		        $("#MyScoreSeller1").text("평점");
		    });
		    $("#MyInfoSeller").mouseover(function(){
		        $("#MyInfoSeller1").text("정보수정");
		    });
		    $("#MyTruckSeller").mouseover(function(){
		        $("#MyTruckSeller1").text("내 트럭");
		    });
		    
		    $("#MyComentSeller").mouseout(function(){
		        $("#MyComentSeller1").text("Comment");
		    });
		    $("#MyLikeSeller").mouseout(function(){
		        $("#MyLikeSeller1").text("Like");
		    });
		    $("#MyScoreSeller").mouseout(function(){
		        $("#MyScoreSeller1").text("Score");
		    });
		    $("#MyInfoSeller").mouseout(function(){
		        $("#MyInfoSeller1").text("MyInfo");
		    });
		    $("#MyTruckSeller").mouseout(function(){
		        $("#MyTruckSeller1").text("MyTruck");
		    });
		    
		});
		</script>
		
		<c:if test="${not empty seller}">
			<div class="row">
				<div class="col-lg-3 col-md-5 col-sm-6">
					<button type="button" onclick="" >
						<div class="card card-stats" style="height:160px;" id="MyComentSeller">
							<div class="card-header" data-background-color="blue" style="width:250px; height:80px;">
								<i class="material-icons" id="MyComentSeller1">Comment</i>
							</div>
							<div class="card-content" >
								<h1 class="title" style="padding:30%;"> ${countContentSeller} <small>&nbsp;개</small></h1>
							</div>
						</div>
					 </button>
				</div>
					
				<div class="col-lg-3 col-md-5 col-sm-6">
					<button type="button" onclick="" >
						<div class="card card-stats" style="height:160px;" id="MyLikeSeller">
							<div class="card-header" data-background-color="blue" style="width:250px; height:80px;">
								<i class="material-icons" id="MyLikeSeller1">Like</i>
							</div>
							<div class="card-content" >
								<h1 class="title" style="padding:30%;">
								<c:choose>
									<c:when test="${likeCountSeller ne null}">
										${likeCountSeller.truckLike} 
										<small>&nbsp;개</small>
									</c:when>
									<c:otherwise>
										0
										<small>&nbsp;개</small>
									</c:otherwise>
								</c:choose>
								</h1>
							</div>
						</div>
					 </button>
				</div>
				
				<div class="col-lg-3 col-md-5 col-sm-6">
					<button type="button" onclick="location.href='MyInfoPage.do'" >
						<div class="card card-stats" style="height:160px;" id="MyInfoSeller">
							<div class="card-header" data-background-color="blue" style="width:250px; height:80px;">
								<i class="material-icons" id="MyInfoSeller1">My Info</i>
							</div>
							<div class="card-content" >
								<h4 class="title" style="padding-top:30%; text-align:center;">${sessionScope.memberInfo.sellerName}님<br/><br/>정보수정</h4>
							</div>
						</div>
					 </button>
				</div>
				<div class="col-lg-3 col-md-5 col-sm-6">
					<button type="button" onclick="location.href='myTruckListPage.do'" >
						<div class="card card-stats" style="height:160px;" id="MyTruckSeller">
							<div class="card-header" data-background-color="blue" style="width:250px; height:80px;">
								<i class="material-icons" id="MyTruckSeller1">My Truck</i>
							</div>
							<div class="card-content" >
								<h4 class="title" style="padding-top:30%; text-align:center;"><br>내 트럭관리</h4>
							</div>
						</div>
					 </button>
				</div>
		</div>
		
		</c:if>
		<!-- 판매자 마이페이지 끝-->
	</div>
	
</section>
<!-- section end -->
<!-- footer -->
<div class="container" >
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