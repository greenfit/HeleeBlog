<!DOCTYPE html>
<html lang="zh_cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Heleeos - 管理端</title>
    <link rel="stylesheet" href="${request.contextPath}/static/lib/layui/css/layui.css">
   <link rel="stylesheet" href="${request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="layui-code">
        Version 1.0.1 (2017-3-8)
        1.[新增]所有的数据访问层
        2.[新增]所有的数据访问层单元测试代码
        <br/>
        Version 1.0.0 (2016-3-9)
        1.[新增]基本的框架
    </div>
    <script src="${request.contextPath}/static/lib/jquery-3.1.1.js"></script>
    <script src="${request.contextPath}/static/lib/layui/lay/dest/layui.all.js"></script>
    <script type="text/javascript">
        layui.use('code', function(){ layui.code({ title : "开发日志", about : false }); });
    </script>
</body>
</html>
