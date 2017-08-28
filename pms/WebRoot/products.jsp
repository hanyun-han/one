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
  		function setPage(p){
  			if(p<1){
  				p = 1;
  			}
  			var pages = '${cond.pages}';
  			if(p>parseInt(pages)){
  				p = pages;
  			}
  			$("#page").val(p);
  			$("#qf").submit();
  		}
  	</script>
  </head>
  <body>
    <%@include file="nav.jsp" %>
	<div class="container">
		<div class="row" style="margin-bottom:15px">
			<form action="product.do" method="get" id="qf">	
			<input type="hidden" name="page" id="page" value="1">
			<div class="col-md-2">
				<input class="form-control" id="name" placeholder="请输入商品名称" value="${cond.name}" name="name">
			</div>
			<div class="col-md-3">
				<select class="form-control" name="kind">
					<option value="">请选择商品类别</option>
					<c:forEach items="${kinds}" var="k">
					<c:if test="${k==cond.kind}">
					<option selected value="${k}">${k}</option>
					</c:if>
					<c:if test="${k!=cond.kind}">
					<option value="${k}">${k}</option>
					</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="col-md-2">
				<select class="form-control" name="factory">
					<option value="">请选择生产厂商</option>
					<c:forEach items="${factorys}" var="f">
					<c:if test="${f==cond.factory}">
					<option selected value="${f}">${f}</option>
					</c:if>
					<c:if test="${f!=cond.factory}">
					<option value="${f}">${f}</option>
					</c:if>	
					</c:forEach>
				</select>
			</div>
			<div class="col-md-2">
				<input type="number" name="price1" placeholder="请输入价格" class="form-control" value="${param.price1}">
			</div>
			<div class="col-md-2">
				<input type="number" name="price2" placeholder="请输入价格" class="form-control" value="${param.price2}">
			</div>
			<div class="col-md-1">
				<button class="btn btn-primary btn-block">查询</button>
			</div>
			</form>
		</div>
		
    	<div class="table-responsive">
    	<table class="table table-bordered table-hover">
    		<tr class="active">
    			<th class="text-center">序号</th>
    			<th class="text-center">商品名称</th>
    			<th class="text-center">类别</th>
    			<th class="text-center">价格</th>
    			<th class="text-center">厂商</th>
    			<th class="text-center">保质期</th>
    			<th class="text-center">备注</th>
    			<th class="text-center">型号管理</th>
    			<th class="text-center">编辑</th>
    			<th class="text-center">删除</th>
    		</tr>
    		<c:forEach items="${products}" var="p" varStatus="i">
    		<tr>
    			<td>${i.index+1}</td>
    			<td>${p.name}</td>
    			<td>${p.kind}</td>
    			<td>${p.price}</td>
    			<td>${p.factory}</td>
    			<td>${p.trem}</td>
    			<td>${p.info}</td>
    			<td class="text-center"><a title="型号管理" href="#" onclick="sizeDialog(${p.id})"><i class="glyphicon glyphicon-cog"></i></a></td>
    			<td class="text-center"><a title="编辑" href="product.do?id=${p.id}"><i class="glyphicon glyphicon-pencil"></i></a></td>
    			<td class="text-center"><a title="删除" href="#" onclick="openDialog(${p.id})"><i class="glyphicon glyphicon-remove"></i></a></td>
    		</tr>	
    		</c:forEach>
    	</table>
    	</div>
    	<div class="row">
    		<div class="col-md-12 text-center">
    			<nav>
				  <ul class="pagination">
				    <li>
				      <a href="#" onclick="setPage(1)" aria-label="Previous">
				        <span aria-hidden="true">首页</span>
				      </a>
				    </li>
				    <c:if test="${cond.page>1}">
				    <li><a href="#" onclick="setPage(${cond.page-1})">上一页</a></li>
				    </c:if>
					<c:if test="${cond.page<=1}">
				    <li class="disabled"><a href="#">上一页</a></li>
				    </c:if>				    
				    <c:if test="${cond.page>=cond.pages}">
				    <li class="disabled"><a href="#">下一页</a></li>
				    </c:if>
				    <c:if test="${cond.page<cond.pages}">
				    <li><a href="#" onclick="setPage(${cond.page+1})">下一页</a></li>				    
				    </c:if>
				    
				    <li>
				      <a href="#" onclick="setPage(${cond.pages})" aria-label="Next">
				        <span aria-hidden="true">尾页</span>
				      </a>
				    </li>
				  </ul>
				</nav>
    		</div>
    	</div>
    </div>
  </body>
</html>


<div id="dialog" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="mt"></h4>
      </div>
      <div class="modal-body" id="mb">
        
      </div>
      <div class="modal-footer">
      	<div class="row">
      		<div class="col-md-8 text-left">
      			<span id="msg"></span>
      		</div>
      		<div class="col-md-4 text-right">
        		<button type="button" class="btn btn-primary" id="btnOK">确定</button>
      		</div>
      	</div>	
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="row plus" style="margin-bottom:10px;display:none">
	<div class="col-md-9">
		<input type="hidden" name="id"/>
		<input type="hidden" name="pid"/>
		<input placeholder="请输入型号名称" class="form-control" name="sname"/>
	</div>
	<div class="col-md-3 text-right">
		<a onclick="addplus(this)" class="btn btn-default" style="margin-right:5px">+</a>
		<a onclick="subplus(this)" class="btn btn-default">-</a>	
	</div>
</div>

<script type="text/javascript">
	var pid = 0;
	var opt = 0;
	function addplus(a){
		var $row = $(".plus").eq(0).clone(true);
		$row.css("display","block");
		$row.find("input").eq(0).val('');
		$row.find("input").eq(1).val(pid);
		$row.find("input").eq(2).val('');
		$("#mb").append($row);
	}
	function subplus(a){
		$(a).parent().parent().remove();
		if($("#mb").children().length==0){
			var $row = $(".plus").eq(0).clone(true);
			$row.find("input").eq(0).val('');
			$row.find("input").eq(1).val(pid);
			$row.find("input").eq(2).val('');
			$row.css("display","block");
			$("#mb").append($row);
		}
	}
	
	function sizeDialog(id){
		opt = 1;
		pid = id;
		$("#msg").text("");
		$.get("size.do?pid="+id,function(data){
			if(data!=''){
				var sizes = JSON.parse(data);
				$.each(sizes,function(i,s){
					var $row = $(".plus").eq(0).clone(true);
					$row.find("input").eq(0).val(s.id);
					$row.find("input").eq(1).val(s.pid);
					$row.find("input").eq(2).val(s.sname);
					$row.css("display","block");
					$("#mb").append($row);
				});
			}else{
				var $row = $(".plus").eq(0).clone(true);
				$row.css("display","block");
				$row.find("input").eq(0).val('');
				$row.find("input").eq(1).val(pid);
				$row.find("input").eq(2).val('');
				$("#mb").append($row);
			}
		});

		$('#mt').html("型号管理");
		$('#mb').html("");
		$('#dialog').modal({
		  keyboard: false
		});
	}
	
	function openDialog(id){
		opt = 2;
		pid = id;
		$("#msg").text("");
		$('#mt').html("删除提示");
		$('#mb').html("<p>是否要删除该商品记录？</p>");
		$('#dialog').modal({
		  keyboard: false
		});
	}
	$(function(){
		$("#btnOK").click(function(){
			if(opt==2){
				if(pid>0){
					location.href="product.do?id="+pid+"&k=r";
				}
			}else if(opt==1){
				var sizes = new Array();
				$("#mb>div").each(function(){
					var id = $(this).find("input").eq(0).val();
					var pid = $(this).find("input").eq(1).val();
					var sname = $(this).find("input").eq(2).val();
					sizes.push({id:id,pid:pid,sname:sname});
				});
				
				$.ajax({
					url:"size.do?sz="+JSON.stringify(sizes),
					method:"POST",
					success:function(data){
						if(data){
							$("#msg").css("color","green");
							$("#msg").text("商品型号信息保存成功！");
						}else{
							$("#msg").css("color","red");
							$("#msg").text("商品型号信息保存失败！");
						}
					},
					error:function(msg){
						$("#msg").css("color","red");
						$("#msg").text("商品型号信息保存失败！");
					}
				});
			}
		});
	});
</script>
