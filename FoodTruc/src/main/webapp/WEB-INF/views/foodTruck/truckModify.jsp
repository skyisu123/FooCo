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
	if (obj instanceof UserVO) {
		user = (UserVO) obj;
	} else {
		seller = (SellerVO) obj;
	}
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

<title>truckAdd.jsp</title>
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
	
	<script>
		var truckType;
		var sTime = '${foodTruckVo.truckStime}';	//시간은 나중에 해보는걸로..
		var eTime = '${foodTruckVo.truckEtime}';
		$(document).ready(function(){
			truckType = '${foodTruckVo.truckType}';
			if(truckType == "분식"){
				$("input:radio[value='분식']").attr("checked", true);
			}
			else if(truckType == "양식"){
				$("input:radio[value='양식']").attr("checked", true);
			}
			else{
				$("input:radio[value='한식']").attr("checked", true);
			}
			
			//$("#truckStime").attr("value", new Date('2016', '12', '30', '12', '22'));
		});
		
		function checkInfoForm(){
			var frm = document.TruckModifyForm;
			if(frm.truckName.value == ""){
				alert("변경할 트럭이름을 입력하세요.");
				return false;
			}
			if(frm.truckStime.value == ""){
				alert("변경할 시작 영업시간을 입력하세요.");
				return false;
			}
			if(frm.truckEtime.value == ""){
				alert("변경할 종료 영업시간을 입력하세요.");
				return false;
			}
			if(frm.truckReservation.value == ""){
				alert("변경할 예약가능 여부를 입력하세요.");
				return false;
			}
			if(frm.truckInfo.value == ""){
				alert("변경할 트럭 소개글을 입력하세요.");
				return false;
			}
			if(frm.truckImage.value == ""){
				if(confirm("대체할 홍보이미지 파일이 선택되지 않았습니다. \n 기존의 이미지파일로 계속사용하시겠습니까?")){
					return true;
				}
				else {
					return false;
				}
			}
			$("#existFile").attr("value", "not");	//변경할 이미지가 선택되면 hidden의 value를 not으로변경
		}
	</script>
	
	<div class="container" style="margin-top: 150px;">
		<c:choose>
			<c:when test="${empty oneStepResult}">
				<div class="row">
					<div class="col-sm-2">&nbsp;</div>

					<div class="col-sm-10">
						<form action="truckModifyAction.do" class="form-horizontal"
							method="post" name="TruckModifyForm" enctype="multipart/form-data" onsubmit="return checkInfoForm();">
							<div class="form-group">
								<!-- 판매자 아이디는 세션에 저장되어있는 정보로 출력 -->
								<div class="form-group">
									<label class="col-sm-3 control-label" for="sellerId">판매자
										아이디</label>
									<div class="col-sm-6">
										<input class="form-control" name="sellerId" id="sellerId"
											value="${sessionScope.memberInfo.sellerId}" readonly
											type="text" style="width: 240px;">
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label" for="businessName">트럭
										업체명</label>
									<div class="col-sm-6">
										<input class="form-control" name="businessName" readonly
											id="businessName" type="text" style="width: 240px;" value="${foodTruckVo.businessName}">
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label" for="truckName">트럭
										이름</label>
									<div class="col-sm-6">
										<input class="form-control" name="truckName" id="truckName"
											type="text" value="${foodTruckVo.truckName}" style="width: 240px;">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="truckType">주
										푸드 테마</label>
									<div class="col-sm-6">
										<label class="radio-inline"> <input type="radio"
											name="truckType" value="분식" checked>분식
										</label> <label class="radio-inline"> <input type="radio"
											name="truckType" value="양식">양식
										</label> <label class="radio-inline"> <input type="radio"
											name="truckType" value="한식">한식
										</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label" for="truckStime">트럭
										영업날짜</label>
									<div class="col-sm-6">
										<input type="datetime-local" class="form-control"
											name="truckStime" id="truckStime" style="width: 250px; display: inline">
										~ <input type="datetime-local" class="form-control"
											name="truckEtime" id="truckEtime" style="width: 250px; display: inline">
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label" for="truckReservation">예약
										가능여부</label>
									<div class="col-sm-6">
										<input class="form-control" name="truckReservation"
											id="truckReservation" type="text" value="${foodTruckVo.truckReservation}" style="width: 240px;">
									</div>
								</div>

								<div class="form-group">
									<input type="hidden" name="existFile" id="existFile" value="${foodTruckVo.truckImage}" />
									<input type="hidden" name="truckNum" value="${truckNum}">
									
									<label class="col-sm-3 control-label" for="truckInfo">트럭
										홍보 및 소개글</label>
									<div class="col-sm-6">
										<input class="form-control" name="truckInfo" id="truckInfo"
											type="text" style="width: 240px;">
									</div>
								</div>
							</div>
								<div >
									<label class="col-sm-3 control-label" for="truckImage">대표
										홍보 이미지</label>
									<div class="col-sm-6">
										<input name="truckImage" id="truckImage"
											type="file" style="width: 240px;">
									</div>
								</div>
							<div class="form-group">
								<div class="form-group text-center">
									<button type="submit" class="btn btn-info">
										다음단계<i class="fa fa-check spaceLeft"></i>
									</button>
									<button type="reset" class="btn btn-warning">
										등록취소<i class="fa fa-times spaceLeft"></i>
									</button>
								</div>
							</div>
						</form>
					</div>

					<div class="col-sm=2">&nbsp;</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-2">&nbsp;</div>

					<div class="col-sm-10">
						<form action="../menu/truckMenuAddAction.do" class="form-horizontal" method="post" name="TruckaddForm" enctype="multipart/form-data">
							<input type="hidden" value="${truckNum}" name="truckNum"/>

								<table style="border-spacing: 30px;" id="menuInfo">
								<colgroup>
									<col width="20%">
									<col width="20%">
									<col width="20%">
									<col width="20%">
									<col width="10%">
									<col width="*">
								</colgroup>
									<tr style="text-align: center;">
										<th style="text-align: center;">메뉴 카테고리</th>
										<th style="text-align: center;">메뉴 이름</th>
										<th style="text-align: center;">메뉴 가격</th>
										<th style="text-align: center;">재고 수량</th>
										<th style="text-align: center;">메뉴 이미지</th>
										<th></th>
									</tr>
									<tr>
										<td style="text-align: center;">
										<div class="form-group">
											<select name="menuCategory">
												<option value="분식">분식</option>
												<option value="양식">양식</option>
												<option value="중식">중식</option>
												<option value="한식">한식</option>
												<option value="일식">일식</option>
												<option value="패스트푸드">패스트푸드</option>
												<option value="기타">기타</option>
											</select>
										</div>
										</td>
										<td style="text-align: center;">
											<div class="form-group">
												<input type="text" class="form-control"	name="menuName" placeholder="메뉴명 입력">
											</div>
										</td>
										<td style="text-align: center;">
											<div class="form-group">
												<input type="text" class="form-control"	name="menuPrice" placeholder="메뉴가격 입력">
											</div>
										</td>
										<td style="text-align: center;">
											<div class="form-group">
												<input type="text" class="form-control"	name="inventory" placeholder="판매가능 재고수량">
											</div>
										</td>
										<td style="text-align: center;">
											<div><input type="file" id="file" name="IMAGE_0" /></div>
										</td>
									</tr>
								</table>
								<div class="text-center">
									<button type="button" class="btn btn-primary" id="addMenu">+ 메뉴추가<i class="fa fa-check spaceLeft"></i></button>
								</div>
								<br><br><br>
								<div class="text-center">
									<button type="submit" class="btn btn-info">등록신청<i class="fa fa-check spaceLeft"></i></button>
									<button type="reset" class="btn btn-danger">다시작성<i class="fa fa-check spaceLeft"></i></button>
								</div>
						</form>
					</div>

					<div class="col-sm=2">&nbsp;</div>
				</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	
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
	
	
	<script>
		var menuCount = 1;
	    
	    $(document).ready(function(){
	        $("#addMenu").on("click", function(e){ //메뉴 추가 버튼
	            e.preventDefault();
	            fn_addMenu();
	        });
	        $("a[name='delete']").on("click", function(e){ //삭제 버튼
	            e.preventDefault();
	            fn_deleteMenu($(this));
	        });
	    });
	    function fn_addMenu(){
	    	var str = "<tr>";
			str += "<td style='text-align: center;'>";
			str += "<div class='form-group'>";
            str += "	<select name='menuCategory'>";
            str += "	<option value='분식'>분식</option>";
            str += "	<option value='양식'>양식</option>";
            str += "	<option value='중식'>중식</option>";
            str += "	<option value='한식'>한식</option>";  
            str += "	<option value='일식'>일식</option>";
            str += "	<option value='패스트푸드'>패스트푸드</option>";
            str += "	<option value='기타'>기타</option>";
            str += "	</select>";
            str += "</div>";
            str += "</td>";
            str += "<td style='text-align: center;'>";
            str += "<div class='form-group'>";
            str += "	<input type='text' class='form-control' name='menuName' placeholder='메뉴명 입력'>";
            str += "</div>";
            str += "</td>";
            str += "<td style='text-align: center;'>";
            str += "<div class='form-group'>";
            str += "	<input type='text' class='form-control' name='menuPrice' placeholder='메뉴가격 입력'>";
            str += "</div>";
            str += "</td>";
            str += "<td style='text-align: center;'>";
            str += "<div class='form-group'>";
            str += "	<input type='text' class='form-control' name='inventory' placeholder='판매가능 재고수량'>";
            str += "</div>";
            str += "</td>";
            str += "<td style='text-align: center;'>";
            str += "<div>";
            str += "	<input type='file' id='file' name='IMAGE_" + (menuCount)+ "'>";
            str += "</div>";
            str += "</td>";
            str += "<td style='text-align: center;'>";
            str += "	<a href='#this' class='btn btn-warning' id='delete' name='delete'>삭제</a>";
			str += "</td>";
			str += "</tr>";
			
			menuCount++;
            $("#menuInfo").append(str);
            $("a[name='delete']").on("click", function(e){ //삭제 버튼
                e.preventDefault();
                fn_deleteMenu($(this));
            });
            function fn_deleteMenu(obj){
	            obj.parent().parent().remove();
	            //obj.parents('table').find('tr').remove();
	        }
        }
	</script>


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


