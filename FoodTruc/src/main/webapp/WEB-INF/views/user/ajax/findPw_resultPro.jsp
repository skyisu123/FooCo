<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br/>
<div> 찾으신 비밀번호 결과입니다. </div>
<c:choose>
	<c:when test="${not empty memberPw}">
		<table class="table">
			<thead>
				<th>&nbsp;</th>
			</thead>
			<tbody>
				
				<tr>
					<td>${memberPw}</td>
				</tr>
				<tr>
					<td>
						<button class="btn btn-info btn-sm" onclick="location.href='memberLogin.do'">로그인</button>
						<button class="btn btn-danger btn-sm" onclick="location.href='memberResetPw.do'">비밀번호 재설정</button>
					</td>
				</tr>
			</tbody>
		</table>
	</c:when>
	<c:when test="${memberPw eq null}">
		<table class="table">
			<thead>
				<th>&nbsp;</th>
			</thead>
			<tbody>
				<tr>
					<td>조회결과가 없습니다.</td>
				</tr>
			</tbody>
		</table>
	</c:when>
</c:choose>
