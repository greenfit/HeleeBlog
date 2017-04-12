<!DOCTYPE html>
<html lang="zh_cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>博客管理端</title>
    <link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
    <link rel="stylesheet" href="https://static.heleeos.com/lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/login.css">
</head>
<body>
    <div class="login">
        <img class="login-logo" src="https://static.heleeos.com/blog-manager/images/login-logo.png">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input id="username" type="text" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密&nbsp;&nbsp;&nbsp;码</label>
            <div class="layui-input-block">
                <input id="password" type="password" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">验证码</label>
            <div class="layui-input-block cptcha">
                <input id="cptcha" type="text" class="layui-input" onkeydown='if(event.keyCode == 13) login();'>
            </div>
            <div class="cptcha-image">
                <img id="codeImage" alt="点击刷新验证码" src="image/code.jpg" onclick="reloadCode()">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="float: right;">
                <button class="layui-btn layui-btn-normal" onclick="login()">登录</button>
            </div>
        </div>
    </div>
    <script src="https://static.heleeos.com/lib/jquery.js"></script>
    <script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
    <script type="text/javascript">
        layui.use([ 'form' ], function() {
            var form = layui.form();
        });
        function reloadCode() {
            $("#codeImage").attr("src", "image/code.jpg?t=" + Math.random());
        }
        function login(){
            var username = $("#username").val();
            var password = $("#password").val();
            var cptcha =   $("#cptcha").val();
            $.post("login.json", { "username" : username, "password" : password, "cptcha" : cptcha }, function(res){
                if(res.code == 200){
                    //登录成功
                    location.href = "index.html";
                }else{
                    layer.msg(res.message.info, {icon : 2});
                    reloadCode();
                }
            });
        }
    </script>
</body>
</html>
