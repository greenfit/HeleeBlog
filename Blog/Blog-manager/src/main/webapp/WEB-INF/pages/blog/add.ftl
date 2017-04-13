<head>
    <meta charset="utf-8">
    <title>博客管理端</title>
    <link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
    <link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/style.css">
    <link rel="stylesheet" href="https://static.heleeos.com/lib/font-awesome/css/font-awesome.min.css">
    <style type="text/css">
        .input-markdown {
            display: none;
        }
        .input-markdown textarea {
            width: 100%;
            height: 400px;
        }
    </style>
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
                    <input id="title" type="text" class="layui-input" <#if bean??> value="${bean.title}" </#if>>
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">显示URL</label>
                <div class="layui-input-block">
                    <input id="disp" type="text" class="layui-input" <#if bean??> value="${bean.disp}" </#if>>
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">标签</label>
                <div class="layui-input-block">
                    <input id="tags" type="text" class="layui-input" <#if bean??> value="${bean.tags}" </#if>>
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">摘要</label>
                <div class="layui-input-block">
                    <textarea id="summary" class="layui-textarea"><#if bean??>${bean.summary}</#if></textarea>
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">分类</label>
                <div class="layui-input-block">
                    <select id="type">
                        <option value="">请选择分类</option>
                            <#list types as type>
                               <option value="${type.name}" <#if bean??><#if bean.type == type.name>selected</#if></#if>>${type.name}</option>
                            </#list>
                    </select>
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">内容类型</label>
                <div class="layui-input-block">
                    <select id="contentType" lay-filter="editor">
                        <option value="0">HTML</option>
                        <option value="1" <#if bean??><#if bean.contentType == 1>selected</#if></#if>>Markdown</option>
                    </select>
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-block input-ckeditor" <#if bean??><#if bean.contentType == 1>style="display:none;"</#if></#if>>
                    <div id="editor"><#if bean??> ${bean.content} </#if></div>
                </div>
                <div class="layui-input-block input-markdown" <#if bean??><#if bean.contentType == 1>style="display:block;"</#if></#if>>
                    <textarea id="editor2"><#if bean??>${bean.content}</#if></textarea>
                </div>
            </div>
            
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" onclick="save()">保存</button>
                </div>
            </div>
        </div>
    </div>
    <script src="https://static.heleeos.com/lib/jquery.min.js"></script>
    <script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
    <script src="https://static.heleeos.com/lib/ckeditor/ckeditor.js"></script>
    <script src="https://static.heleeos.com/lib/ckeditor/config.js"></script>
    <script type="text/javascript">
    
        CKEDITOR.replace('editor', {
            height: 270,
            extraPlugins: 'image',
            filebrowserUploadUrl: '/image/ck-upload?path=' + $("#disp").val(),
        });
              
        function save() {
            var id = $("#id").val();
            var title = $("#title").val();
            var type = $("#type").val();
            var disp = $("#disp").val();
            var tags = $("#tags").val();
            var summary = $("#summary").val();
            var contentType = $("#contentType").val();
            var content;
            
            if(title.length > 250){
                layer.msg('标题不能超过250个字符', {icon : 0});
                return false;
            }
            
            if(summary.length > 250){
                layer.msg('摘要不能超过250个字符', {icon : 0});
                return false;
            }
            
            if(tags.length > 250){
                layer.msg('摘要不能超过250个字符', {icon : 0});
                return false;
            }
            
            if(contentType == 0) {
                content = CKEDITOR.instances.editor.getData();
            } else {
                content = $("#editor2").val();
            }
            
            var data = {"id" : id, "title" : title, "type" : type, "disp" : disp, "tags" : tags, "summary" : summary, "contentType" : contentType, "content" : content};
            $.post("update.json", data, function(res){
                if(res.code == 200){
                    layer.msg("提交成功!", {icon : 1});
                    parent.divCall();
                }else{
                    layer.msg(res.message.info, {icon : 2});
                }
            });
        }
        
        layui.use('form', function(){
           var form = layui.form();
           
           form.on('select(editor)', function(data){
               if(data.value == 0) {
                   $(".input-ckeditor").css("display", "block");
                   $(".input-markdown").css("display", "none");
               } else if(data.value == 1){
                   $(".input-ckeditor").css("display", "none");
                   $(".input-markdown").css("display", "block");
               } 
           });
        });
    </script>
</body>
