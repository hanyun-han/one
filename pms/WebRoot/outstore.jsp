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
		var max = 0;
		$("#sid").change(function(){
			var pid = $("#pid").val();
			var sid = this.value;
			var url = "queryopstore.do?pid="+pid+"&sid="+sid;
			$.get(url,function(n){
				max = n;
				$("#msg-count").text("该商品现有库存数量为"+n);
			});
		});
		$("#qt").blur(function(){
			if(this.value>max){
				this.value = max;
			}
			if(this.value<1){
				this.value = 1;
			}
		});
		$("#qt").change(function(){
			if(this.value>max){
				this.value = max;
			}
			if(this.value<1){
				this.value = 1;
			}
		});
		
		$("#btnsum").click(function(){
			var pid = $("#pid").val();
			if(pid<=0){
				return;
			}
			var sid = $("#sid").val();
			if(sid<=0){
				return;
			}
			var qt = $("#qt").val();
			if(qt<=0 || qt>max){
				return;
			}
			var p = $("#per").val();
			var reg = /^[\u4e00-\u9fa5]{2,5}$/;
			if(!reg.test(p)){
				return;
			}
			$("form").submit();
		});
	});
</script>
</head>
<body>
	<%@include file="nav.jsp" %>
	<div class="container">
		<form action="opstore.do" method="post">
			<input type="hidden" value="2" name="grade">
			<div class="row" style="margin-bottom:15px;">
				<div class="col-md-2">
					<label class="control-label">出库商品</label>
				</div>
				<div class="col-md-6">
					<select class="form-control" id="pid" name="pid">
						<option value="0">请选择出库商品</option>
					</select>
				</div>
				<div class="col-md-4">请选择出库商品</div>
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
				<div class="col-md-4">请选择商品型号</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-2">
					<label class="control-label">出库数量</label>
				</div>
				<div class="col-md-6">
					<input type="number" placeholder="请输入出库数量" class="form-control"
						name="quantity" id="qt" value="1">
				</div>
				<div class="col-md-4" id="msg-count"></div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-2">
					<label class="control-label">经手人</label>
				</div>
				<div class="col-md-6">
					<input placeholder="请输入经手人" id="per" class="form-control" name="person">
				</div>
				<div class="col-md-4">请输入经手人姓名</div>
			</div>
			<div class="row" style="margin-bottom:15px">
				<div class="col-md-12">
					<button type="button" id="btnsum" class="btn btn-primary btn-block">保存</button>
				</div>
			</div>
			<c:if test="param.error!=null">
			<div class="row">
				<div class="col-md-12">
					<span style="color:red">商品出库操作失败，请重试！</span>
				</div>
			</div>
			</c:if>
		</form>
	</div>
</body>
</html>
