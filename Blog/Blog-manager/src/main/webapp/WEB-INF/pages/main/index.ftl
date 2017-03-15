<!DOCTYPE html>
<html lang="zh_cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>博客管理端</title>
	<link rel="stylesheet" href="${request.contextPath}/static/lib/layui/css/layui.css">
	<link rel="stylesheet" href="${request.contextPath}/static/lib/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="${request.contextPath}/static/css/custom.css">
	<link rel="stylesheet" href="${request.contextPath}/static/css/index.css">
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<img class="header-logo" src="${request.contextPath}/static/images/logo.png">
		</div>
		<div class="nav-user">
			<div class="nav" style="cursor: pointer; top: 0px;">
				<a onclick="exit()"><i class="fa fa-power-off"></i>退出</a>
			</div>
		</div>
		<div class="layui-side layui-bg-black">
			<ul class="layui-nav layui-nav-tree">
				<li class="layui-nav-item layui-nav-title"><a>网站设置</a></li>
				<li class="layui-nav-item layui-nav-link"><a onclick="change('')"><i class="fa fa-puzzle-piece"></i> <cite>公告设置</cite>
				</a></li>
				<li class="layui-nav-item layui-nav-link"><a onclick="change('')"><i class="fa fa-puzzle-piece"></i> <cite>权限设置</cite>
				</a></li>
				<li class="layui-nav-item layui-nav-title"><a>文章设置</a></li>
				<li class="layui-nav-item layui-nav-link"><a onclick="change('')"><i class="fa fa-wpforms"></i> <cite>技术干货</cite>
				</a></li>
				<li class="layui-nav-item layui-nav-link"><a onclick="change('')"><i class="fa fa-wpforms"></i> <cite>读书感悟</cite>
				</a></li>
			</ul>
		</div>
		<div id="bodyDiv" class="layui-body">
		    <iframe :src="src"></iframe>
			<!-- <div class="layui-tab"  lay-allowclose="true">
		              <ul class="layui-tab-title">
		                <li class="layui-this" lay-id="11">网站设置</li>
		                <li>用户管理</li>
		                <li>权限分配</li>
		                <li>商品管理</li>
		                <li>订单管理</li>
		              </ul>
		         </div> -->
		</div>
		<div class="layui-footer" style="padding-top: 15px; height: 30px;">
			<p class="center">
				<a href="https://heleeos.com/blog/" target="_black">Heleeos 博客</a> | 2017 © 李瑜 版权所有
			</p>
		</div>
	</div>
	<script src="${request.contextPath}/static/lib/jquery-3.1.1.js"></script>
	<script src="${request.contextPath}/static/lib/vue.js"></script>
	<script src="${request.contextPath}/static/lib/layui/lay/dest/layui.all.js"></script>
	<script type="text/javascript">
	   var vm = new Vue({
	       el : "#bodyDiv",
	       data: {
	           src : ""
	       }
	   });
	   function load() {
	       vm.src = "logs.html";
	   }
	   load();
	</script>
</body>
</html>
