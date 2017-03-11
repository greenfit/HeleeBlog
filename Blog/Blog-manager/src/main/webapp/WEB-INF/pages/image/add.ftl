<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>麟龙基金管理端</title>
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" href="${request.contextPath}/static/lib/layui/css/layui.css">
		<link rel="stylesheet" href="${request.contextPath}/static/css/style.css">
	</head>
	<body>
		<iframe id="file_upload_return" name="file_upload_return" hidden="false" src=""></iframe>
		<div class="layui-tab-item layui-show">
			<div id="queryDiv" class="layui-form" style="width: 400px;">
				<form id="imageForm" action="${request.contextPath}/image/uploadImage.json" target="file_upload_return" enctype="multipart/form-data" method="post">
					<div class="layui-form-item" style="display: none">
					    <input id="path" name="path" type="text" value="${path}">
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">图片文件</label>
						<div class="layui-input-block">
							<input id="upload" name="upload" type="file" style="margin-top: 8px;">
						</div>
					</div>
				</form>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-normal" onclick="upload()">上传</button>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">图片路径</label>
					<div class="layui-input-block">
						<input id="url" type="text" placeholder="图片路径" class="layui-input layui-disabled" disabled>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">缩略图</label>
					<div class="layui-input-block">
						<img id="image" alt="" src="" style="width: 290px;">
					</div>
				</div>
			</div>
		</div>
		<script src="${request.contextPath}/static/lib/jquery-3.1.1.js"></script>
		<script src="${request.contextPath}/static/lib/layui/lay/dest/layui.all.js"></script>
		<script src="${request.contextPath}/static/js/image-upload.js"></script>
	</body>
</html>
