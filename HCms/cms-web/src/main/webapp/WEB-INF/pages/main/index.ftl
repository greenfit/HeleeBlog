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
            <ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
                <li class="layui-nav-item">
                    <a>网站设置</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-nav-item layui-nav-link"><a data-url="https://www.baidu.com" ><i class="fa fa-bullhorn"></i> <cite>公告设置</cite></a></dd>
                        <dd class="layui-nav-item layui-nav-link"><a data-url="https://www.google.com"><i class="fa fa-cogs"></i> <cite>权限设置</cite></a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a>表单示例</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-nav-item layui-nav-link"><a data-url="/from/add.html"><i class="fa fa-folder-open-o"></i> <cite>文章编辑</cite></a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a>图表示例</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-nav-item layui-nav-link"><a data-url="2"><i class="fa fa-folder-open-o"></i> <cite>折线图</cite></a></dd>
                        <dd class="layui-nav-item layui-nav-link"><a data-url="3"><i class="fa fa-folder-open-o"></i> <cite>柱状图</cite></a></dd>
                        <dd class="layui-nav-item layui-nav-link"><a data-url="4"><i class="fa fa-folder-open-o"></i> <cite>折线图和柱状图</cite></a></dd>
                        <dd class="layui-nav-item layui-nav-link"><a data-url="5"><i class="fa fa-folder-open-o"></i> <cite>饼形图</cite></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
        <div id="bodyDiv" class="layui-body">
            <div class="layui-tab layui-tab-card" lay-allowclose="true" lay-filter="bodyTab">
              <ul class="layui-tab-title">
                <li class="layui-this">开发日志</li>
              </ul>
              <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                  <iframe src="logs.html"></iframe>
                </div>
              </div>
            </div>
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
       
       layui.use('element', function(){
          var element = layui.element();
          
          element.on("nav(leftNav)", function(elem){
             var title = elem.children('a').text();
             var src   = elem.children('a').attr('data-url');
             if($("li[lay-id='" + src + "']").length == 0) {
                 element.tabAdd('bodyTab', {title: title, content: '<iframe src="' + src + '"></iframe>', id: src});
                 element.tabChange('bodyTab', src);
             } else {
                 element.tabChange('bodyTab', src);
             }
          });
          
          $(window).resize(function() {
              $(".layui-body .layui-tab").width($(".layui-body").width() - 30);
              $(".layui-body .layui-tab").height($(".layui-body").height() - 30);
              $(".layui-body .layui-tab .layui-tab-content").height($(".layui-body").height() - 70);
          }).resize();
       });
    </script>
</body>
</html>
