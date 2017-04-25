<!DOCTYPE html>
<html lang="zh_cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>HCms测试</title>
    <link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
    <link rel="stylesheet" href="https://static.heleeos.com/lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="/static/css/custom.css">
    <link rel="stylesheet" href="/static/css/index.css">
</head>
<body>
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <img class="header-logo" src="https://static.heleeos.com/blog-manager/images/logo.png">
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
                <li class="layui-nav-item layui-nav-title"><a>图表示例</a></li>
                <li class="layui-nav-item layui-nav-link"><a onclick="load('')"><i class="fa fa-folder-open-o"></i> <cite>折线图</cite></a></li>
                <li class="layui-nav-item layui-nav-link"><a onclick="load('')"><i class="fa fa-folder-open-o"></i> <cite>柱状图</cite></a></li>
                <li class="layui-nav-item layui-nav-link"><a onclick="load('')"><i class="fa fa-folder-open-o"></i> <cite>折线图和柱状图</cite></a></li>
                <li class="layui-nav-item layui-nav-link"><a onclick="load('')"><i class="fa fa-folder-open-o"></i> <cite>饼形图</cite></a></li>
            </ul>
        </div>
        <div id="bodyDiv" class="layui-body">
            <iframe :src="src"></iframe>
        </div>
        <div class="layui-footer" style="padding-top: 15px; height: 30px;">
            <p class="center">
                <a href="https://heleeos.com/blog/" target="_black">HCms Demo</a> | 2017 © 李瑜 版权所有
            </p>
        </div>
    </div>
    <script src="https://static.heleeos.com/lib/jquery.min.js"></script>
    <script src="https://static.heleeos.com/lib/vue.min.js"></script>
    <script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
    <script src="/static/js/alert.js"></script>
    <script type="text/javascript">
       var vm = new Vue({ el : "#bodyDiv", data: { src : "logs.html" } });
       function load(page) {
           vm.src = page;
       }
    </script>
</body>
</html>
