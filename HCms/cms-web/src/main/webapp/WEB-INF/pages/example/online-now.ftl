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
		<div id="online" style="width: 800px; height: 500px;"></div>
	</body>
	<script src="https://static.heleeos.com/lib/jquery.min.js"></script>
	<script src="https://static.heleeos.com/lib/moment.min.js"></script>
	<script src="https://static.heleeos.com/lib/echarts.min.js"></script>
	<script src="/static/charts/line.js"></script>
    <script type="text/javascript">
        var line;
	    function initLine() {
	    	line = new LineChart();
	        line.init(document.getElementById('online'));
	        line.setTitle("在线人数 - 实时");
	        line.setFormatter(function(params){
               return params[0].name + ' -> ' + params[0].value;
            });
	        
	        //模拟随机数据
	        var data ={x:[],y:[]};
            var start = new Date() - 600 * 1000;
            for(var i = 0; i < 10; i++) {
                var x = new Date(start + 60 * 1000 * i);
                var y = parseInt(Math.random() * 100, 0);
                line.pushData(x.getHours() + ":" + x.getMinutes(), y);
            }
            line.flush();
	    }
	    initLine();
	    
	    var date = new Date();
	    function addData() {
	        var value = parseInt(Math.random() * 100, 0);
	        line.pushData(date.getHours() + ":" + date.getMinutes(), value, true);
	        date = new Date(+date + 60 * 1000);//一分钟
	    }
	    
	    function removeData() {
	    	line.shiftData(true);
	    }
    </script>
</html>
