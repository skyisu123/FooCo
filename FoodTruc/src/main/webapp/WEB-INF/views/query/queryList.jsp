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

	var delSuccess = '${delSuccess}';
	if(delSuccess == "성공"){
		alert("삭제가 성공적으로 완료되었습니다.");
		<%request.setAttribute("delSuccess", null);%>
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
					<div class="col-sm-10">
					<c:choose>
						<c:when test="${not empty user }">
<div class="col-sm-2">
	<h2>문의사항</h2>
	<hr />	
<ul style="font-size:1.3em;">
	<li><a href="queryList.do?read=N" style="color:black;">읽음</a></li>
	<li><a href="queryList.do?read=Y" style="color:black;">읽지않음</a></li>
</ul>
</div>
<div class="card col-sm-10">
	<div class="card-header" data-background-color="orange">
	<h3>
	<img src="../resources/bootstrap/img/about/mailbox.png" style="width:40px; height:40px;"/>
	&nbsp;&nbsp;&nbsp;
	문의글 게시판
	</h3>
       </div>
	<table style="text-align:center;" class="table table-bordered table-hover table-responsive">

        	<tr style="font-weight:bold; font-size:1.2em;">
        		<td width="25%;">문의 종류</td>
        		<td width="40%;">문의 제목</td>
        		<td width="13%;">문의상태</td>
        		<td width="20%;">문의날짜</td>
        		<td width="12%;">이동</td>
        		
        	</tr>
							<c:if test="${empty list }">
       	<tr>
       		<td colspan="5" style="height:100px;">조회된 문의가 없습니다</td>
       	</tr> 
							</c:if>
							<c:if test="${not empty user }">

	<c:forEach items="${list}" var="row">
	
	
		<tr>
       		<td>${row.queryType }</td>
       		<td>${row.queryTitle }</td>
       		<td>${row.queryRead }</td>
       		<td>${row.queryDate }</td>
       		<td><input type="button" class="btn btn-xs" style="margin-bottom:3px;"
		onclick="location.href='queryView.do?queryNum=${row.queryNum}&listType=${listType }'" value="상세보기" />
		<c:if test="${row.queryRead ne'N' }">
		<input type="button" class="btn btn-xs"
		onclick="location.href='queryDelete.do?queryNum=${row.queryNum }&listType=${listType }'" value="삭제" />
		</c:if>
		</td>
		
	</c:forEach>
							</c:if>
                    	 
	</table>
<br /><br />
</div>
						</c:when>
						<c:otherwise>
							
<div class="col-sm-2">
	<h2>보낸문의</h2>
	<hr />	
<ul style="font-size:1.3em;">
	<li><a href="queryList.do?id=${sessionScope.memberInfo.sellerId}" style="color:black;">답변</a></li>
	<li><a href="queryList.do?listType=send" style="color:black;">질문</a></li>
</ul>
	<hr />
	
	<h2>받은문의</h2>
	<hr />	
<ul style="font-size:1.3em;">
	<li><a href="queryList.do?read=N&listType=receive" style="color:black;">읽음</a></li>
	<li><a href="queryList.do?read=Y&listType=receive" style="color:black;">읽지않음</a></li>
</ul>
</div>
<div class="card col-sm-10">
	<div class="card-header" data-background-color="orange"> 
		<h3>
		<img src="../resources/bootstrap/img/about/mailbox.png" style="width:40px; height:40px;"/>
		&nbsp;&nbsp;&nbsp;
		문의글 게시판
		</h3>
       </div>
        <div align="right">
       
	        <input type="button" class="btn btn-info" name="send"
			onclick="location.href='querySend.do'" value="관리자문의하기" />
		
       </div>
	<table style="text-align:center;" class="table table-bordered table-hover table-responsive">
	<c:choose>
		<c:when test="${not empty reply }">
			<tr style="font-weight:bold; font-size:1.2em;">
        		<td width="20%;">답변 제목</td>
        		<td width="45%;">답변 내용</td>
        		<td width="20%;">답변 날짜</td>
        		<td width="15%;"></td>
        		
        	</tr>
							<c:if test="${empty list }">
       	<tr>
       		<td colspan="5" style="height:100px;">조회된 문의가 없습니다</td>
       	</tr> 
							</c:if>
							<c:if test="${not empty seller }">
		<input type="hidden" value="${listType }"/>
	<c:forEach items="${list }" var="row">
	
		<tr>
       		<td>${row.replyTitle }</td>
       		<td>${row.replyContent }</td>
       		<td>${row.replyDate }</td>
     		<td><input type="button" class="btn btn-xs"  style="margin-top:1px;"
		onclick="location.href='replyView.do?queryCode=${row.queryCode}'" value="상세보기" />
			</td>
		</tr>
	</c:forEach>
							</c:if>
	</c:when>
		<c:otherwise>
		<tr style="font-weight:bold; font-size:1.2em;">
        		<td width="25%;">문의 종류</td>
        		<td width="40%;">문의 제목</td>
        		<td width="13%;">문의상태</td>
        		<td width="20%;">문의날짜</td>
        		<td width="12%;">이동</td>
        		
        	</tr>
							<c:if test="${empty list }">
       	<tr>
       		<td colspan="5" style="height:100px;">조회된 문의가 없습니다</td>
       	</tr> 
							</c:if>
							<c:if test="${not empty seller }">
		<input type="hidden" value="${listType }"/>
	<c:forEach items="${list }" var="row">
	
		<tr>
       		<td>${row.queryType }</td>
       		<td>${row.queryTitle }</td>
       		<td>${row.queryRead }</td>
       		<td>${row.queryDate }</td>
       			<td><input type="button" class="btn btn-default"  style="margin-top:1px;"
		onclick="location.href='queryView.do?queryNum=${row.queryNum}&listType=${listType }'" value="상세보기" />
		<c:if test="${row.queryRead ne 'N' and listType eq 'send' }">
		<input type="button" class="btn btn-default" 
		onclick="location.href='queryDelete.do?queryNum=${row.queryNum }&listType=${listType }'" value="삭제" />
		</c:if>
			</td>
		</tr>
	</c:forEach>
							</c:if>
		</c:otherwise>
	</c:choose>
            	 
	</table>
	<br /><br />
</div>
							
						</c:otherwise>
					</c:choose>
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
					<p style="font-family: 'Kaushan Script', 'Helvetica Neue', Helvetica, Arial, cursive; font-size: 2em;">
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


