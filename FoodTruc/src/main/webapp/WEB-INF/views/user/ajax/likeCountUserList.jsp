<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<table class="table table-striped" >
			 <thead class="text-info" >
				<th>Ʈ����</th>
				<th>Ÿ��</th>
				<th>�Ұ�</th>
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