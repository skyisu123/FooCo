<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int result = Integer.parseInt(request.getAttribute("checkBoolean").toString());
System.out.println(result);
String seller = (String)request.getAttribute("seller");
System.out.println(seller + " @@@");
if(result == 1 && seller.equals("seller")){
	
	out.println("<a href='../query/queryList.do'>");
	out.println("<img src='../resources/bootstrap/img/clock/clockPlus.gif' width='100' height='100'/>");
	out.println("</a>");
	
}
%>

