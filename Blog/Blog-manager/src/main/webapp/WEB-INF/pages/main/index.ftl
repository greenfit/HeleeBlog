<!DOCTYPE html>
<html lang="zh_cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>博客管理端</title>
	<link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
	<link rel="stylesheet" href="https://static.heleeos.com/lib/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/custom.css">
	<link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/index.css">
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
				<li class="layui-nav-item layui-nav-link"><a onclick="load('')"><i class="fa fa-bullhorn"></i> <cite>公告设置</cite></a></li>
				<li class="layui-nav-item layui-nav-link"><a onclick="load('')"><i class="fa fa-cogs"></i> <cite>权限设置</cite></a></li>
				<li class="layui-nav-item layui-nav-title"><a>博客设置</a></li>
				<li class="layui-nav-item layui-nav-link"><a onclick="load('/blog/type.html')"><i class="fa fa-folder-open-o"></i> <cite>分类管理</cite></a></li>
				<li class="layui-nav-item layui-nav-link"><a onclick="load('')"><i class="fa fa-tags"></i> <cite>标签管理</cite></a></li>
				<li class="layui-nav-item layui-nav-link"><a onclick="load('/blog/list.html')"><i class="fa fa-pencil-square-o"></i> <cite>文章管理</cite></a></li>
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
	<script src="https://static.heleeos.com/lib/jquery.js"></script>
	<script src="https://static.heleeos.com/lib/vue.js"></script>
	<script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
	<script type="text/javascript">
	   var vm = new Vue({
	       el : "#bodyDiv",
	       data: {
	           src : "logs.html"
	       }
	   });
	   function load(page) {
		    vm.src = page;
	   }
	</script>
</body>
</html>
