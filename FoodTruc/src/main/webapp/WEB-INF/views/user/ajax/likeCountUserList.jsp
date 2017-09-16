<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<table class="table table-striped" >
			 <thead class="text-info" >
				<th>트럭명</th>
				<th>타입</th>
				<th>소개</th>
			 </thead>
<c:forEach items="${likeCountUserList}" var="like">
	
			 <tbody>
				<tr>
					<td ><a href="#">${like.truckName}</a></td>
					<td >${like.truckType}</td>
					<td>${like.truckInfo}</td>
				</tr>
			</tbody>
	
</c:forEach>
	</table>