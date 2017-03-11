<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>博客管理端</title>
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" href="${request.contextPath}/static/lib/layui/css/layui.css">
		<link rel="stylesheet" href="${request.contextPath}/static/css/style.css">
		<link rel="stylesheet" href="${request.contextPath}/static/lib/font-awesome/css/font-awesome.css">
	</head>
	<body>
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<img class="header-logo" src="${request.contextPath}/static/images/logo.png">
			</div>
			<div class="nav-user">
				<a target="BODY_FRAME" class="avatar" href="${request.contextPath}/profile.html">
					<#if admin.picture?length &lt; 2>
						<img src="static/images/100.gif">
					<#else>
						<#if admin.picture?starts_with('http')>
							<img src="${admin.picture}">
						<#else>
							<img src="${imageHost}${admin.picture}">
						</#if>
					</#if>
					<cite>${admin.realname}</cite>
				</a>
				<div class="nav" style="cursor: pointer;top: 0px;">
					<a onclick="exit()"><i class="fa fa-power-off"></i>退出</a>
				</div>
			</div>
			<div id="leftFrame" class="layui-side layui-bg-black">
				<div class="layui-side-scroll">
					<ul class="layui-nav layui-nav-tree">
						<li class="layui-nav-item layui-nav-title"><a>网站设置</a></li>
						<li class="layui-nav-item layui-nav-link">
							<a onclick="change('advertise/index.html')"> 
								<i class="fa fa-puzzle-piece"></i> <cite>广告设置</cite>
							</a>
						</li>
						<!-- <li class="layui-nav-item layui-nav-link">
							<a onclick="change('link/index.html')"> 
								<i class="fa fa-link"></i> <cite>链接设置</cite>
							</a>
						</li> -->
						<li class="layui-nav-item layui-nav-link">
							<a onclick="change('syn/index.html')"> 
								<i class="fa fa-refresh"></i> <cite>同步设置</cite>
							</a>
						</li>
						<li class="layui-nav-item layui-nav-title"><a>基金设置</a></li>
						<li class="layui-nav-item layui-nav-link">
							<a onclick="change('business/fund/fund-info-list.html')"> 
								<i class="fa fa-rocket"></i> <cite>基金信息</cite>
							</a>
						</li>
						<li class="layui-nav-item layui-nav-link">
							<a onclick="change('business/fund/fund-value-list.html')"> 
								<i class="fa fa-line-chart"></i> <cite>普通型净值</cite>
							</a>
						</li>
						<li class="layui-nav-item layui-nav-link">
							<a onclick="change('business/fund/fund-money-list.html')"> 
								<i class="fa fa-line-chart"></i> <cite>理财型收益</cite>
							</a>
						</li>
						<li class="layui-nav-item layui-nav-title"><a>信息设置</a></li>
						<li class="layui-nav-item layui-nav-link">
							<a onclick="change('business/info/list.html')"> 
								<i class="fa fa-wpforms"></i> <cite>信息披露</cite>
							</a>
						</li>
						<li class="layui-nav-item layui-nav-link">
							<a onclick="change('business/notice/list.html')"> 
								<i class="fa fa-wpforms"></i> <cite>信息公告</cite>
							</a>
						</li>
						<li class="layui-nav-item layui-nav-link">
							<a onclick="change('business/knowledge/list.html')"> 
								<i class="fa fa-wpforms"></i> <cite>基金知识</cite>
							</a>
						</li>
					</ul>
					<script>
				      layui.use('element', function(){});
				      $(".layui-nav-link").on('click', function(){
					      $(".layui-nav-tree li[class$='layui-this']").removeClass("layui-this");
					      $(this).addClass('layui-this');
				      });
				      function change(src){
				    	  var start = (new Date()).valueOf();
				    	  $.post("${request.contextPath}/islogin.json", function(res){
				    		 var end = (new Date()).valueOf();
						 	 if(res.code == 0) {
						 		$("#BODY_FRAME").attr("src", "${request.contextPath}/" + src);
						 	 } else{
						 		location.reload(true);
						 	 }
						  });
				      }
				    </script>
				</div>
			</div>
			<div id="bodyFrame" class="layui-body" style="padding: 10px;">
				<iframe id="BODY_FRAME" name="BODY_FRAME" src="logs.html" frameborder="0" width="100%" height="99%"></iframe>
			</div>
			<div class="layui-footer" style="padding-top: 15px;height:30px;">
			  <center><p>2016 © <a href="http://www.win-stock.com.cn/" target="_black">沈阳麟龙科技股份有限公司</a></p></center>
			</div>
		</div>
		<script src="${request.contextPath}/static/lib/jquery-3.1.1.js"></script>
        <script src="${request.contextPath}/static/lib/layui/layui.js"></script>
        <script src="${request.contextPath}/static/lib/layui/lay/dest/layui.all.js"></script>
		<script type="text/javascript">
			$(".layui-body").css("height", $(".layui-side").css("height"));
			$(".layui-body").css("top", "65px");
			function exit() {
				layer.confirm('确定要退出？', {
					title : '退出?',
					btn : [ '确定', '取消' ]
				}, function() { 
					$.post("${request.contextPath}/logout.json", function(res){
						layer.msg('退出成功', {icon : 1});
						setTimeout(function(){location.reload(true);}, 500);
					});
				});
			}
			function resizeWin() {
				$("#bodyFrame").height($("#leftFrame").height() - 50);
			}
			resizeWin();
			$(window).resize(function() {
				resizeWin();
			});
		</script>
	</body>
</html>
