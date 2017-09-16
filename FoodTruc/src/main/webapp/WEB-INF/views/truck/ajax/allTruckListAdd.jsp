<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${list }" var="row">
	<div class="col-md-3 col-sm-6 portfolio-item">
		<a href="truckDetailView.do?truckNum=${row.truckNum}" class="portfolio-link" id="truck_image" >
			<div class="portfolio-hover">
				<div class="portfolio-hover-content">
					<i class="fa fa-search fa-3x"></i>
				</div>
			</div>
			<img src="http://localhost:8080/FoodTruc/resources/bootstrap/imageupload/${row.truckImage }"
			style="width: 720px; height: 225px;" class="img-responsive" alt="">
		</a>
		<div class="portfolio-caption">
			
			<h4 id="truck_name">${row.truckName }</h4>
			<p id="truck_info" class="text-muted">${row.truckInfo }</p>
		</div>
	</div>
</c:forEach>
