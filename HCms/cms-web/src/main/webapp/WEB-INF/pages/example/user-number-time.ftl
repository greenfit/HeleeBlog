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
            <button class="layui-btn layui-btn-normal" onclick="addData()">新增数据</button>
            <button class="layui-btn layui-btn-danger" onclick="removeData()">移除数据</button>
        </div>
		<div id="user" style="width: 800px; height: 500px;"></div>
	</body>
	<script src="/static/lib/jquery-3.1.1.js"></script>
	<script src="/static/lib/moment.js"></script>
	<script src="/static/lib/echarts.js"></script>
	<script src="/static/charts/bar-line.js"></script>
    <script type="text/javascript">
	    var barLine;
	    function initBarLine() {
	    	barLine = new BarLineChart();
	    	barLine.init(document.getElementById('user'));
	        barLine.setTitle("在线人数和在线时长");
	        barLine.setName("在线总人数", "平均在线时长");
	        	        
	        //模拟随机数据
	        var data ={x:[],y:[],z:[]};
	        var start = new Date() - 8 * 24 * 3600 * 1000;
	        for(var i = 0; i < 8; i++) {
	            var x = new Date(start + 24 * 3600 * 1000 * i);
	            var y = parseInt(Math.random() * 100, 0);
	            var z = parseInt(Math.random() * 24, 0);
	            barLine.pushData(moment(x).format("YYYY-MM-DD"), y, z);
	        }
	        barLine.flush();
	    }
	    initBarLine();
	    
	    var date = new Date();
        function addData() {
            var value1 = parseInt(Math.random() * 100, 0);
            var value2 = parseInt(Math.random() * 24, 0);
            barLine.pushData(moment(date).format("YYYY-MM-DD"), value1, value2, true);
            date = new Date(+date + 24 * 3600 * 1000);//一天
        }
        
        function removeData() {
        	barLine.shiftData(true);
        }
    </script>
</html>
