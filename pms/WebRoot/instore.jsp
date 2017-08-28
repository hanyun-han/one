<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>商品信息管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.get("opstore.do",function(data){
			$.each(data,function(i,p){
				var $op = $("<option value='"+p.id+"'>"+p.name+"</option>");
				$("#pid").append($op);
			});
		});
		$("#pid").change(function(){
			$("#sid>option:gt(0)").remove();
			var url = "opstore.do?pid="+this.value;
			$.get(url,function(data){
				$.each(data,function(i,s){
					var $op = $("<option value='"+s.id+"'>"+s.sname+"</option>");
					$("#sid").append($op);
				});
			});
		});
	});
</script>
</head>
<body>
	<%@include file="nav.jsp" %>
	<div class="container">
		<form action="opstore.do" method="post">
			<input type="hidden" value="1" name="grade">
			<div class="row" style="margin-bottom:15px;">
				<div class="col-md-2">
					<label class="control-label">入库商品</label>
				</div>
				<div class="col-md-6">
					<select class="form-control" id="pid" name="pid">
						<option value="0">请选择入库商品</option>
					</select>
				</div>
				<div class="col-md-4">商品名称不能少于4个字符</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-2">
					<label class="control-label">商品型号</label>
				</div>
				<div class="col-md-6">
					<select class="form-control" id="sid" name="sid">
						<option value="0">请选择商品型号</option>
					</select>
				</div>
				<div class="col-md-4">商品类别不能少于2个字符</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-2">
					<label class="control-label">入库数量</label>
				</div>
				<div class="col-md-6">
					<input type="number" placeholder="请输入入库数量" class="form-control"
						name="quantity">
				</div>
				<div class="col-md-4">商品价格必须介于1~1000000</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-2">
					<label class="control-label">经手人</label>
				</div>
				<div class="col-md-6">
					<input placeholder="请输入经手人" class="form-control" name="person">
				</div>
				<div class="col-md-4">生产厂商不能少于4个字符</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-12">
					<button class="btn btn-primary btn-block">保存</button>
				</div>
			</div>
			<c:if test="param.error!=null">
			<div class="row">
				<div class="col-md-12">
					<span style="color:red">商品入库操作失败，请重试！</span>
				</div>
			</div>
			</c:if>
		</form>
	</div>
</body>
</html>
