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
<!-- <script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script> -->
	
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="../resources/bootstrap/css/bootstrap.css">

<!-- <script src="../resources/bootstrap/js/bootstrap.js"></script> -->

<link href="../resources/bootstrap/css/material-dashboard.css" rel="stylesheet" /> 

<link href="../resources/bootstrap/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
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
				href="../index.do"> FooCo &nbsp; 
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
	//////////////////////////////////////////// 폼 체크 /////////////////////////////////////
		function checkMenuForm(){
			var frm = document.TruckMenuModifyForm;
			
			if($("input[name=menuName]").length == 0){
				alert("메뉴 최소 1개는 입력하셔야 합니다.");
				return false;
			}
			var count = -1;
			$("input[name=menuName]").each(function (idx){
				var value = $(this).val();
				if(value == ""){
					alert("메뉴이름을 입력하세요.");
					count = 1;	// 누락된 태그가 있음
					return false;
				}
			});
			if(count == 1) return false;
			$("input[name=menuPrice]").each(function (idx){
				var value = $(this).val();
				if(value == ""){
					alert("메뉴가격을 입력하세요.");
					count = 1;	// 누락된 태그가 있음
					return false;
				}
			});
			if(count == 1) return false;
			$("input[name=inventory]").each(function (idx){
				var value = $(this).val();
				if(value == ""){
					alert("메뉴재고를 입력하세요.");
					count = 1;	// 누락된 태그가 있음
					return false;
				}
			});
			if(count == 1) return false;
			$("input[type=file]").each(function (idx){
				var value = $(this).val();
				if(value == ""){
					alert("파일이 선택되지 않았습니다.\n 파일은 선택하세요(필수)");
					count = 1;	// 누락된 태그가 있음
					return false;
				}
			});
			if(count == 1) return false;
			return confirm("입력 사항을 모두 확인하였습니까?");
		}
	///////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////// 취소 버튼 //////////////////////////////////////////
		function checkCancel(){
			if(confirm("정말로 취소하시겠습니까? \n 입력된 내용은 저장되지 않습니다.")){
				location.href="../user/memberMyPage.do";
			}
		}
	///////////////////////////////////////////////////////////////////////////////////////
	</script>
	
	<div class="container" style="margin-top: 150px;">
		<div class="row">
			<div class="col-sm-2">&nbsp;</div>
			<div class="col-sm-10">
				<form action="../menu/truckMenuModifyAction.do" class="form-horizontal" method="post" name="TruckMenuModifyForm"
				enctype="multipart/form-data" onsubmit="return checkMenuForm();">
					<input type="hidden" value="${truckNum}" name="truckNum" />
	
					<table style="border-spacing: 30px;" id="menuInfo">
					<colgroup>
						<col width="20%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
						<col width="10%">
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
						<th></th>
					</tr>
					
					<c:choose>
						<c:when test="${not empty menuList && not empty menuImageList}">
						<c:forEach items="${menuList}" var="menuList" varStatus="status">
							<tr>
								<td style="text-align: center;">
									<div class="form-group">
										<select name="menuCategory">
											<option value="분식" <c:if test="${menuList.menuCategory eq '분식'}">selected</c:if>>분식</option>
											<option value="양식" <c:if test="${menuList.menuCategory eq '양식'}">selected</c:if>>양식</option>
											<option value="중식" <c:if test="${menuList.menuCategory eq '중식'}">selected</c:if>>중식</option>
											<option value="한식" <c:if test="${menuList.menuCategory eq '한식'}">selected</c:if>>한식</option>
											<option value="일식" <c:if test="${menuList.menuCategory eq '일식'}">selected</c:if>>일식</option>
											<option value="패스트푸드" <c:if test="${menuList.menuCategory eq '패스트푸드'}">selected</c:if>>패스트푸드</option>
											<option value="기타" <c:if test="${menuList.menuCategory eq '기타'}">selected</c:if>>기타</option>
										</select>
									</div>
								</td>
								<td style="text-align: center;">
									<div class="form-group">
										<input type="text" class="form-control" name="menuName"
											placeholder="메뉴명 입력" value="${menuList.menuName}">
									</div>
								</td>
								<td style="text-align: center;">
									<div class="form-group">
										<input type="text" class="form-control" name="menuPrice"
											placeholder="메뉴가격 입력" value="${menuList.menuPrice}">
									</div>
								</td>
								<td style="text-align: center;">
									<div class="form-group">
										<input type="text" class="form-control" name="inventory" 
											placeholder="판매가능 재고수량" value="${menuList.inventory}">
									</div>
								</td>
							
								<td style="text-align:center;">
									<div>
										<img id="loadImg_${status.index}" class="img img-responsive" style="width:120px; height:80px;"
										src="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${menuImageList[status.index].imageName}"/>
									</div>
								</td>
								<td>
									<input type="file" id="file" name="IMAGE_${status.index}"
									style="margin-left:15px;" onchange="LoadImg(this, ${status.index});" />
								</td>
								<td style='text-align: center;'>
									<a href='#this' class='btn btn-warning' id='delete' name='delete'>삭제</a>
								</td>
							
							<%-- </c:forEach> --%>
							</tr>
						</c:forEach>
						</c:when>
					</c:choose>
				</table>

				<div class="text-center">
					<button type="button" class="btn btn-primary" id="addMenu">
						+ 메뉴추가<i class="fa fa-check spaceLeft"></i>
					</button>
				</div>
				<br>
				<br>
				<br>
				<div class="text-center">
					<button type="submit" class="btn btn-info">
						수정<i class="fa fa-check spaceLeft"></i>
					</button>
					<button type="button" class="btn btn-danger" onclick="checkCancel();">
						취소<i class="fa fa-check spaceLeft"></i>
					</button>
				</div>
			</form>
		</div>
	<div class="col-sm=2">&nbsp;</div>
	</div>
</div>
	<script>
		////////////////////////////////////// 이미지 미리보기 ////////////////////////////
		function LoadImg(value, index){
			
			if(value.files && value.files[0]){
				var reader = new FileReader();
				reader.onload = function(e){
					$("#loadImg").css('width', '120px');
					$("#loadImg").css('height', '80px');
					$("#loadImg_"+index).attr('src', e.target.result);
				}
				reader.readAsDataURL(value.files[0]);
			}
		}
		//////////////////////////////////////////////////////////////////////////////
		
		
		//////////////////////////// 메뉴추가 버튼 누를 시 ////////////////////////////////////
		var menuCount = ${menuSize}; // 추가 버튼누르면 
	    
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
            str += "	<img id='loadImg_" + (menuCount) +  "'class='img img-responsive'/>";
            str += "<td>";
            str += "	<input type='file' id='file' name='IMAGE_" + (menuCount)+ "' onchange='LoadImg(this,"+ (menuCount) + ");' style='margin-left:15px;'>";
            str += "</td>";	
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
            
        }
	    function fn_deleteMenu(obj){
            obj.parent().parent().remove();
        }
		///////////////////////////////////////////////////////////////////////////////////////////
	</script>
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
</body>
</html>


