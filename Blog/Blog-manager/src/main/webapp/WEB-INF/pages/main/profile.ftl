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
		<script src="${request.contextPath}/static/lib/jquery-3.1.1.js"></script>
		<script src="${request.contextPath}/static/lib/layui/layui.js"></script>
		<script src="${request.contextPath}/static/lib/layui/lay/dest/layui.all.js"></script>
		<style type="text/css">
			.layui-layedit{
				height: 500px;
			}
		</style>
	</head>
	<body style="height:800px;">
		<div class="layui-tab layui-tab-card" style="width: 99%;">
		  <ul class="layui-tab-title">
		  	<li class="layui-this">个人设置</li>
		  </ul>
		  <div class="layui-tab-content">
		    <div class="layui-tab-item layui-show">
		    	<div class="layui-tab-item layui-show">
					<div id="queryDiv" class="layui-form" style="width: 400px;">
						<div style="display: none">
							<label class="control-label">ID</label>
						    <input id="id" type="text" class="form-control" <#if admin??> value="${admin.id}" </#if>>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">真实姓名</label>
							<div class="layui-input-block">
								<input id="realname" type="text" placeholder="真实姓名" class="layui-input" <#if admin??> value="${admin.realname}" </#if>>
							</div>
							<div class="layui-input-text"><i class="font-red">*</i></div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">用户名</label>
							<div class="layui-input-block">
								<input id="username" type="text" placeholder="用户名" class="layui-input" <#if admin??> value="${admin.username}" </#if>>
							</div>
							<div class="layui-input-text"><i class="font-red">*</i></div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">密码</label>
							<div class="layui-input-block">
								<input id="password" type="password" placeholder="密码" class="layui-input">
							</div>
							<div class="layui-input-text">为空,不做修改</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">头像</label>
							<div class="layui-input-block">
								<input id="picture" type="text" placeholder="" class="layui-input" <#if admin??> value="${admin.picture}" </#if>>
							</div>
							<div class="layui-input-button"><button class="layui-btn layui-btn-normal" onclick="image()">选择</button></div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block" style='float: right;'>
								<button class="layui-btn layui-btn-normal" onclick="save()">保存</button>
							</div>
						</div>
					</div>
				</div>
		    </div>
		  </div> 
		</div>
		<script type="text/javascript">
			layui.use(['form',], function(){
				var form = layui.form();
			});
			
			function save(){
				var id = $("#id").val();
				var realname = $("#realname").val();
				var username = $("#username").val();
				var password = $("#password").val();
				var picture = $("#picture").val();
				 
				var data = {"id" : id, "realname" : realname, "username" : username, "password" : password, "picture" : picture};
				 $.post("${request.contextPath}/update_manager.json", data, function(res){
					if(res.code == 0){
						layer.msg("提交成功,1秒后返回首页!", {icon : 1});
						setTimeout(function(){ window.parent.location.href = "${request.contextPath}/index.html" }, 1000);
					}else{
						layer.msg(res.message.info, {icon : 2});
					}
				});
			}
			function image(){
				layer.open({
					title: '选择图片',					
					type: 2, 
					content: ['${request.contextPath}/image/add.html?path=profile', 'no'],
					area: ['430px', '560px'],
					btn: ['确定', '取消'],
					yes: function(index, layero){
						var img = layer.getChildFrame('#url', index).val();
						$("#picture").val(img);
						//$("#imageView").attr('src', '${path}' + img);
						layer.close(index);
					}
				}); 
			}
		</script>
	</body>
</html>
