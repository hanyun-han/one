<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript">
	$(function(){
		var url = "storestate.do";
		$.get(url,function(source){
			var names = new Array();
			$.each(source,function(i,d){
				names.push(d.name);
			});
			console.log(names);
			var ediv = echarts.init($("#main").get(0));
			var option = {
			    title : {
			        text: '商品库存统计图表',
			        subtext: new Date().toLocaleString(),
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    toolbox: {
		            show: true,
		            left: 'right',
		            feature: {
		                dataView: {readOnly: false},
		                restore: {},
		                saveAsImage: {}
		            }
		        },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: names
			    },
			    series : [
			        {
			            name: '商品库存',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:source,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
			ediv.setOption(option);
		});
	});
</script>
</head>
<body>
	<%@include file="nav.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div id="main" style="width:100%;height:500px"></div>
			</div>
		</div>
	</div>
</body>
</html>
