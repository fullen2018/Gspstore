<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>折线图表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>main/include/js/jquery.min.js"></script>
	<script src="<%=basePath%>main/include/js/highcharts.js"></script>
	<script src="<%=basePath%>main/include/js/exporting.js"></script>
  </head>
  <script type="text/javascript">
	$(function() {
		var chart = new Highcharts.Chart( {
			chart: {
				renderTo:'container',			//放置图表的容器 
				defaultSeriesType: 'line',    	//图表类型line:直线, spline:曲线, area:区域, areaspline:区域曲线, column:柱形, bar, pie:饼形 , scatter  
				inverted: false,                //左右显示，默认上下正向  
				marginRight: 130,
				marginBottom: 25
			},
			credits: {
				enabled: false
			},
			title: {
				text: '昨日系统访问数量统计图',  //图标的标题 
				style:{} ,                      //标题样式 
				x: -20 //center
			},
			subtitle: {
				text: '测试',					//图标的副标题 
				x: -20
			},
			xAxis: {
				categories: ${charData.xAxisCategories} //X轴的坐标值
			},
			yAxis: {
				title: {
					text: '个/小时'				//Y轴坐标标题  labels:纵柱标尺
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			tooltip: {							//当鼠标悬置数据点时的格式化提示
				formatter: function() {
		                return '<b>'+ this.series.name +'</b><br/>'+
						this.x +': '+ this.y +'个';
				}
			},
			legend: {							//【图例】位置样式
				layout: 'vertical', 			//【图例】显示的样式：水平（horizontal）/垂直（vertical）  
				align: 'right',
				verticalAlign: 'center',
				x: -10,
				y: 100,
				borderWidth: 0
			},
			exporting: {
					filename: "昨日系统访问数量统计图", //导出的文件名
					type: "image/png",
					url: 'chartsAction!export.action' 
			},
			series:${charData.series}
		});
	});
</script>
  
  <body class="easyui-layout">
    <div align="center">
		<div id="container" style="margin: 0 auto; border: 0;" align="right"></div>
	</div>
  </body>
</html>
