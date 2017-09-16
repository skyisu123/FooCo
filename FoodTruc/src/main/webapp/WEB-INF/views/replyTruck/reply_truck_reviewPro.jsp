<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="../resources/bootstrap/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="../resources/bootstrap/js/star-rating.js" type="text/javascript"></script>


<!-- 리뷰작성 폼 -->
<div class="card my-4">
	<h5 class="card-header">한줄평</h5>
	<div class="card-body">
		<form name="replyFrm">
			<div class="form-group">
				<textarea class="form-control" rows="3" name="replyContent"></textarea>
			</div>
			<div class="text-left">
				<label for="score" class="control-label">평점</label>
				<input id="score" name="score" class="rating rating-loading" data-show-clear="false" data-show-caption="false" data-size="xs">
			</div>
			<div class="text-right">
				<button type="button" class="btn btn-primary" onclick="replyWrite();">한줄평 작성</button>
			</div>
		</form>
	</div>
</div>
<!-- 한줄평 리스트 -->
<c:choose>
	<c:when test="${empty replyTruckList}">
		<!-- 한줄평 리스트 없는 경우 -->
		<h3 style="text-align: center">등록된 한줄평이 없습니다.</h3>
	</c:when>
	<c:otherwise>
		<!-- 한줄평 리스트 있으면 -->
		<c:forEach items="${replyTruckList}" var="replyTruckList" varStatus="loop">
			<div class="media mb-4">
				<div class="media-body">
					<h5 class="mt-0" style="float:left">${replyTruckList.memberId}&nbsp;&nbsp;&nbsp;&nbsp;
					${replyTruckList.replyDate}</h5>
					<div class="text-right">
					<input id="score" name="score" class="rating rating-loading" data-show-clear="false" 
					data-show-caption="false" data-size="xs" readonly value="${replyTruckList.score}">
					</div>
				</div>
				&nbsp;&nbsp;&nbsp;${replyTruckList.replyContent}
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>

<div class="col-sm-12 text-center">
	<c:choose>
		<c:when test="${not empty replyTruckList}">
			<c:if test="${paging.currentPage ne paging.firstPageNo}">
				<a onclick="goReviewPage(${paging.prevPageNo});" class="prev" style="cursor:pointer">이전</a>
			</c:if>
			<span> 
				<c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
					<c:choose>
						<c:when test="${i eq paging.currentPage}">
							<b>
								<font size=+1> 
								<a onclick="goReviewPage(${i});" class="choice" style="cursor:pointer">${i}</a>
								</font> 
							</b>
						</c:when>
						<c:otherwise>
							<a onclick="goReviewPage(${i});" style="cursor:pointer">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</span>
			<c:if test="${paging.currentPage ne paging.finalPageNo}">
				<a onclick="goReviewPage(${paging.nextPageNo});" class="next" style="cursor:pointer">다음</a>
			</c:if>
		</c:when>
	</c:choose>

	
</div>


