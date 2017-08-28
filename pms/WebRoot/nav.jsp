<%@ page pageEncoding="utf-8"%>
<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">
			<i class="glyphicon glyphicon-piggy-bank" style="color:black"></i> 
			进销存管理信息系统
			</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1" >
			<ul class="nav navbar-nav">
				<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">商品信息管理 <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="product.jsp">添加商品</a></li>
		            <li class="divider"></li>
		            <li><a href="product.do">查询商品</a></li>
		          </ul>
		        </li>
				<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">商品入库管理 <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="instore.jsp">商品入库</a></li>
		            <li class="divider"></li>
		            <li><a href="queryopstore.do?g=1">入库信息查询</a></li>
		          </ul>
		        </li>
				<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">商品出库管理 <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="outstore.jsp">商品出库</a></li>
		            <li class="divider"></li>
		            <li><a href="queryopstore.do?g=2">出库信息查询</a></li>
		          </ul>
		        </li>
				
			</ul>
		</div>
	</div>
</nav>
