<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.fooco.FoodTruc.member.vo.SellerVO"%>
<%@page import="com.fooco.FoodTruc.member.vo.UserVO"%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- 상단 CSS, 부트스트랩, JS, jQuery, mete 등등 -->
<%@ include file ="../../../resources/bootstrap/setting/otherCommonTop.jsp" %>

<link rel="stylesheet" href="../resources/bootstrap/css/style.css">
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/earlyaccess/jejumyeongjo.css">

<title>joinPage_Test.jsp</title>


<style>
.pricing2_top_border{
    height: 4px;
    width:100%;
    background-color: #3CB371;
}
.pricing_body ul{
    width:55%;
    margin: 0 auto;
}
.pricing_price_border2{
    background-color: #ff6863;
    padding: 10px;
    border-radius: 50%;
    margin: 0 auto;
    width: 150px;
    height: 150px;
    position: absolute;
    top: 18.6%;
    left: 25%;
    right: 25%;
}
.pricing_price_border2 .pricing_price2{
    background-color: #ff6863;
    border: 2px solid;
    border-color: #fff;
    width: 130px;
    height: 130px;
    border-radius: 50%;
    padding-top: 23%;
}
</style>
</head>
<body style="background-color:#f2f2f2;" class="bg-light-gray index">
<nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top" style="background-color:black;">
    <div class="container">
    	 <!-- 로고와 임시 사이트명 -->
       	<div class="col-md-2">
       		<img src="../resources/bootstrap/img/logos/logo.png" style="width:150px; height:53px; margin-right:50px; "/>
       	</div>
       	<div class="col-md-3">
       		<a style="font-size:3em; color:yellow;"class="navbar-brand page-scroll" href="../index.do">
       			FooCo &nbsp;
       		</a>	
       	</div>
       	<!-- 로고 끝 -->
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="hidden">
                    <a href="#page-top"></a>
                </li>
                <li>
                    <a class="page-scroll" href="memberLogin.do" style="color:white;">로그인</a>
                </li>
                
                <li>
                	<a class="page-scroll" href="memberJoin.do" >회원가입</a>
                </li>
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
<!--Pricing Section-->
<div class="container" style="margin-top:50px;">
    <div class="row" >
        <div class="main_pricing roomy-100">
            <div class="col-md-8 col-md-offset-2 col-sm-12">
                <div class="head_title text-center">
                    <h2>FooCo Member Join</h2>
                    <div class="separator_auto"></div>
                </div>
            </div>

            <div class="col-md-offset-2 col-md-4 col-sm-12">
                <div class="pricing_item sm-m-top-30">
                <div class="pricing_top_border"></div>
                    <div class="pricing_head p-top-30 p-bottom-100 text-center">
                        <h3 class="text-uppercase">푸드트럭 운영자</h3>
                    </div>
                    <div class="pricing_price_border text-center">
                        <div class="pricing_price">
                            <h3 class="text-white"><i class="fa fa-truck fa-2x"></i></h3>
                            <!-- <p class="text-white">per month</p> -->
                        </div>
                    </div>

                    <div class="pricing_body bg-white p-top-110 p-bottom-60">
                        <ul>
                            <li style="font-weight:bold; font-size:13px; text-align:center;">
                            	블라블라블라<br />
                             </li>
                            <li style="font-weight:bold; font-size:15px; text-align:center;"> </li>
                            <li style="font-weight:bold; font-size:15px; text-align:center;">푸드트럭 운영자 전용</li>

                        </ul>
                        <div class="pricing_btn text-center m-top-40">
                            <a href="sellerJoin.do" class="btn btn-primary">Member Join</a>
                        </div>
                    </div>
                </div>
            </div><!-- End off col-md-4 -->

		    <div class="col-md-4 col-sm-12">
		        <div class="pricing_item sm-m-top-30">
		        	<div class="pricing2_top_border"></div>
		            <div class="pricing_head p-top-30 p-bottom-100 text-center">
		                <h3 class="text-uppercase">이용자</h3>
		            </div>
		            <div class="pricing_price_border2 text-center" style="background-color:green;">
		                <div class="pricing_price2" style="background-color:green;">
		                    <h3 class="text-white"><i class="fa fa-user fa-2x"></i></h3>
		                    <!-- <p class="text-white">per month</p> -->
		                </div>
		            </div>
		
		            <div class="pricing_body bg-white p-top-110 p-bottom-60">
		                <ul>
		                    <li style="font-weight:bold; font-size:15px; text-align:center;"></li>
		                    <li style="font-weight:bold; font-size:15px; text-align:center;"> Unlimited Bandwidth</li>
		                    <li style="font-weight:bold; font-size:15px; text-align:center;">이용자 전용 가입 페이지.</li>
		                </ul>
		                <div class="pricing_btn text-center m-top-40">
		                    <a href="userJoin.do" class="btn btn-info" >Member Join</a>
		                </div>
		            </div>
		        </div>
		    </div><!-- End off col-md-4 -->
	    </div>
	</div><!--End off row-->
</div><!--End off container -->
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
<%@ include file ="../../../resources/bootstrap/setting/otherCommonBottom.jsp" %>

</body>
</html>