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
	$(function() {
		$(".btnPlus").click(function() {
			var $row = $(this).parent().parent();
			var $row2 = $row.clone(true);
			$("#pluses").append($row2);
		});
		$(".btnSub").click(function() {
			if ($(".btnSub").length > 1) {
				var $row = $(this).parent().parent();
				$row.remove();
			}
		});
	});
</script>
</head>
<body>
	<%@include file="nav.jsp" %>
	<div class="container">
		<form action="product.do" method="post">
			<input type="hidden" name="id" value="${product.id}">
			<div class="row" style="margin-bottom:15px;">
				<div class="col-md-2">
					<label class="control-label">商品名称</label>
				</div>
				<div class="col-md-6">
					<input placeholder="请输入商品名称" class="form-control" name="name"
						value="${product.name}">
				</div>
				<div class="col-md-4">商品名称不能少于4个字符</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-2">
					<label class="control-label">商品类别</label>
				</div>
				<div class="col-md-6">
					<input placeholder="请输入商品类别" class="form-control" name="kind"
						value="${product.kind}">
				</div>
				<div class="col-md-4">商品类别不能少于2个字符</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-2">
					<label class="control-label">商品价格</label>
				</div>
				<div class="col-md-6">
					<input type="number" placeholder="请输入商品价格" class="form-control"
						name="price" value="${product.price}">
				</div>
				<div class="col-md-4">商品价格必须介于1~1000000</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-2">
					<label class="control-label">生产厂商</label>
				</div>
				<div class="col-md-6">
					<input placeholder="请输入生产厂商" class="form-control" name="factory"
						value="${product.factory}">
				</div>
				<div class="col-md-4">生产厂商不能少于4个字符</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-2">
					<label class="control-label">保质期</label>
				</div>
				<div class="col-md-6">
					<input placeholder="请输入保质期" class="form-control" name="trem"
						value="${product.trem}">
				</div>
				<div class="col-md-4">保质期不能少于2个字符</div>
			</div>
			<c:if test="${param.id==null}">
				<div class="row" style="margin-bottom:15px">
					<div class="col-md-2">
						<label class="control-label">商品型号</label>
					</div>
					<div class="col-md-10" id="pluses">
						<div class="row" style="margin-bottom:15px">
							<div class="col-md-5">
								<input class="form-control" name="sname" placeholder="请输入型号名称">
							</div>
							<div class="col-md-2 text-right">
								<a class="btn btn-default btnPlus">+</a> <a
									class="btn btn-default btnSub">-</a>
							</div>
						</div>
					</div>
				</div>
			</c:if>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-2">
					<label class="control-label">备注</label>
				</div>
				<div class="col-md-6">
					<textarea rows="5" class="form-control" name="info">${product.info}</textarea>
				</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-12">
					<button class="btn btn-primary btn-block">保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
