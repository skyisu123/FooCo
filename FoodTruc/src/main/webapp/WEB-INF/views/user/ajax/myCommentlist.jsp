<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>


<table class="table table-striped" >
			 <thead class="text-info" >
				<th>ID</th>
				<th>한줄평내용</th>
				<th>작성일</th>
			 </thead>
<c:forEach items="${CountContentUserList}" var="comment">
	
			 <tbody>
				<tr>
					<td>${comment.memberId}</td>
					<td ><a href="#">${comment.replyContent}</a></td>
					<td>${comment.replyDate}</td>
				</tr>
			</tbody>
	
</c:forEach>
	</table>