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
	<script src="/static/charts/bar.js"></script>
    <script type="text/javascript">
	    var bar;
	    function initBar() {
	    	bar = new BarChart();
	    	bar.init(document.getElementById('user'));
	    	bar.setTitle("在线人数 - 历史");
	        
	        //模拟随机数据
	        var data ={x:[],y:[]};
	        var start = new Date() - 8 * 24 * 3600 * 1000;
	        for(var i = 0; i < 8; i++) {
	            var x = new Date(start + i * 24 * 3600 * 1000);
	            var y = parseInt(Math.random() * 100, 0);
	            bar.pushData(moment(x).format("YYYY-MM-DD"), y);
	        }
	        bar.flush();
	    }
	    initBar();
	    
	    var date = new Date();
        function addData() {
            var value = parseInt(Math.random() * 100, 0);
            bar.pushData(moment(date).format("YYYY-MM-DD"), value, true);
            date = new Date(+date + 24 * 3600 * 1000);//一天
        }
        
        function removeData() {
        	bar.shiftData(true);
        }
    		    
    </script>
</html>
