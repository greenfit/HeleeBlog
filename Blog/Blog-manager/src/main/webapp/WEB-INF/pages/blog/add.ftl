<head>
    <meta charset="utf-8">
    <title>博客管理端</title>
    <link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
    <link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/style.css">
</head>
<body>
    <div class="layui-tab-item layui-show">
        <div class="layui-form">
            <div style="display: none">
                <label class="control-label">ID</label>
                <input id="id" type="text" class="form-control" <#if bean??> value="${bean.id}" </#if>>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input id="title" type="text" required placeholder="" class="layui-input" <#if bean??> value="${bean.title}" </#if>>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">摘要</label>
                <div class="layui-input-block">
                    <textarea id="summary" class="layui-textarea"><#if bean??>${bean.summary}</#if></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">内容类型</label>
                <div class="layui-input-block">
                    <select id="contentType">
                        <option value="0">HTML</option>
				        <option value="1" <#if bean??><#if bean.contentType == 1>selected</#if></#if>>Markdown</option>
				    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-block input-ckeditor">
                    <div id="editor"><#if bean??> ${bean.content} </#if></div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" onclick="save()">保存</button>
                </div>
            </div>
        </div>
    </div>
    <script src="https://static.heleeos.com/lib/jquery.js"></script>
    <script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
    <script src="https://static.heleeos.com/lib/ckeditor/ckeditor.js"></script>
    <script src="https://static.heleeos.com/lib/ckeditor/config.js"></script>

    <script type="text/javascript">
        CKEDITOR.replace('editor', {
            height: 270,
            extraPlugins: 'image',
            filebrowserUploadUrl: '/image/ck-upload?path=blog',
        });
        
        function save(){
            var id = $("#id").val();
            var title = $("#title").val();
            var summary = $("#summary").val();
            var contentType = $("#contentType").val();
            var content = CKEDITOR.instances.editor.getData();
            
            if(title.length > 255){
                layer.msg('标题不能超过255个字符', {icon : 0});
                return false;
            }
            if(summary.length > 100){
                layer.msg('摘要不能超过100个字符', {icon : 0});
                return false;
            }
            var data = {"id" : id, "title" : title, "summary" : summary, "contentType" : contentType, "content" : content};
            $.post("update.json", data, function(res){
                if(res.code == 200){
                    layer.msg("提交成功!", {icon : 1});
                }else{
                    layer.msg(res.message.info, {icon : 2});
                }
            });
        }
    </script>
</body>
