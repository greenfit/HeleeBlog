<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>麟龙数据分析系统</title>
		<link rel="stylesheet" href="/static/lib/layui/css/layui.css">
		<link rel="stylesheet" href="/static/css/style.css">
	</head>
	<body>
	    <div>
            <button class="layui-btn layui-btn-normal" onclick="loadData()">重新加载</button>
        </div>
		<div id="user" style="width: 800px; height: 500px;"></div>
	</body>
	<script src="/static/lib/jquery-3.1.1.js"></script>
	<script src="/static/lib/echarts.js"></script>
	<script src="/static/charts/pie.js"></script>
    <script type="text/javascript">
	    var pie;
	    function initPie() {
	    	pie = new PieChart();
	    	pie.init(document.getElementById('user'));
	    	pie.setTitle("用户年龄");
	    	pie.setLabelName("用户个数");
	    	loadData();
	    }
	    
	    function loadData() {
	    	pie.clearData();
	    	//模拟随机数据
            var data = {x:["18以下", "18~30", "31~45", "46~70", "70~80", "80以上"],y:[]};
            for(var i = 0; i < 6; i++) {
                var y = parseInt(Math.random() * (3 - (i % 3)) * 100, 0);
                pie.pushData(data.x[i], { name : data.x[i], value : y });
            }
            pie.flush();
	    }
	    
	    initPie();
    </script>
</html>
