<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>商品入库信息列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
  </head>
  <body>
    <%@include file="nav.jsp" %>
	<div class="container">
    	<div class="table-responsive">
    	<table class="table table-bordered table-hover">
    		<tr class="active">
    			<th class="text-center">序号</th>
    			<th class="text-center">商品名称</th>
    			<th class="text-center">型号</th>
    			<th class="text-center">入库日期</th>
    			<th class="text-center">入库数量</th>
    			<th class="text-center">经手人</th>
    		</tr>
    		<c:forEach items="${opstores}" var="p" varStatus="i">
    		<tr>
    			<td>${i.index+1}</td>
    			<td>${p.name}</td>
    			<td>${p.sname}</td>
    			<td>${p.indate}</td>
    			<td>${p.quantity}</td>
    			<td>${p.person}</td>
    		</tr>	
    		</c:forEach>
    	</table>
    	</div>
    </div>
  </body>
</html>



