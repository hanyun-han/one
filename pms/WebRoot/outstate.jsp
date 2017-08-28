<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>商品出库统计</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/echarts.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var now = new Date();
			var y = now.getFullYear();
			for(var i=y;i>y-5;i--){
				$op = $("<option value='"+i+"'>"+i+"</option>");
				$("#y").append($op);
			}
			
			$("#y").change(function(){
				var url = "ostate.do?year="+this.value;
				$.get(url,function(source){
					var names = new Array();
					var months = new Array();
					var totals = new Array();
					$.each(source,function(i,d){
						var f = true;
						for(var i=0;i<names.length;i++){
							if(names[i]==d.name){
								f = false;
								break;
							}
						}
						if(f){
							names.push(d.name);
						}
						
						var f = true;
						for(var i=0;i<months.length;i++){
							if(months[i]==d.month){
								f = false;
								break;
							}
						}
						if(f){
							months.push(d.month);
						}
					});
					
					for(var i=0;i<names.length;i++){
						var ai = new Array();
						for(var j=0;j<source.length;j++){
							if(source[j].name==names[i]){
								ai.push(source[j].total);
							}
						}
						totals.push(ai);
					}
					
					var mdiv = echarts.init($("#main").get(0));
					option = {
					    tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'cross',
					            crossStyle: {
					                color: '#999'
					            }
					        }
					    },
					    toolbox: {
					        feature: {
					            dataView: {show: true, readOnly: false},
					            magicType: {show: true, type: ['line', 'bar']},
					            restore: {show: true},
					            saveAsImage: {show: true}
					        }
					    },
					    legend: {
					        data:names
					    },
					    xAxis: [
					        {
					            type: 'category',
					            data: months,
					            axisPointer: {
					                type: 'shadow'
					            }
					        }
					    ],
					    yAxis: [
					        {
					            type: 'value',
					            name: '出库量',
					            
					            interval: 50,
					            axisLabel: {
					                formatter: '{value} 件'
					            }
					        }
					    ],
					    series:[]
					};
					
					for(var i=0;i<totals.length;i++){
						option.series.push({
							name:names[i],
							type:'bar',
							data:totals[i]
						});
					}
					
					mdiv.setOption(option);
				});
			});
		});
	</script>
  </head>
  <body>
    <%@include file="nav.jsp" %>
	<div class="container">
		<div class="row">
    		<div class="col-md-12">
    			<select id="y" class="form-control">
    				<option value="0">请选择年份</option>
    			</select>
    		</div>
    	</div>
    	<div class="row">
    		<div class="col-md-12">
    			<div id="main" style="width:100%;height:500px"></div>
    		</div>
    	</div>
    </div>
  </body>
</html>



