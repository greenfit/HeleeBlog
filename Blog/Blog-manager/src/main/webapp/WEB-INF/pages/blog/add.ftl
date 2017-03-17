<head>
    <meta charset="utf-8">
    <title>管理端</title>
    <link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
    <link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/style.css">
</head>
<body style="height:800px;">
    <div class="layui-tab-item layui-show" style="margin-top: 15px;">
        <div id="queryDiv" class="layui-form" style="width: 400px;">
            <div style="display: none">
                <label class="control-label">ID</label>
                <input id="id" type="text" class="form-control" <#if bean??> value="${bean.id}" </#if>>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input id="title" type="text" required placeholder="" class="layui-input" <#if bean??> value="${bean.title}" </#if>>
                </div>
                <div class="layui-input-text"><i class="font-red">*</i></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">发布时间</label>
                <div class="layui-input-block">
                    <input id="pubTime"class="layui-input" <#if bean??> value="${bean.pubTime?datetime}" </#if> onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">阅读次数</label>
                <div class="layui-input-block">
                    <input id="hit" type="number" placeholder="" class="layui-input" <#if bean??> value="${bean.hit}" </#if>>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">摘要</label>
                <div class="layui-input-block">
                    <textarea id="summary" class="layui-textarea"><#if bean??>${bean.summary}</#if></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-block layui-ckeditor">
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
    <script src="https://static.heleeos.com/lib/jquery-3.1.1.js"></script>
    <script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
    <script src="https://static.heleeos.com/lib/ckeditor/ckeditor.js"></script>
    <script src="https://static.heleeos.com/lib/ckeditor/config.js"></script>

    <script type="text/javascript">
        layui.use(['form', 'laydate'], function(){
            var form = layui.form();
            form.on('radio', function(data){
                hot = data.value;
            });
        });
        
        CKEDITOR.replace('editor', {
            height: 270,
            extraPlugins: 'image',
            filebrowserUploadUrl: '${request.contextPath}/image/upload?path=knowledge',
        });
        
        function save(){
            var id = $("#id").val();
            var title = $("#title").val();
            var summary = $("#summary").val();
            var hit = $("#hit").val();
            var content = CKEDITOR.instances.editor.getData();
            
            if(title.length > 255){
                layer.msg('标题不能超过255个字符', {icon : 0});
                return false;
            }
            if(summary.length > 100){
                layer.msg('摘要不能超过100个字符', {icon : 0});
                return false;
            }
            var data = {"id" : id, "title" : title, "summary" : summary, "hit" : hit, "content" : content};
             $.post("update.json", data, function(res){
                if(res.code == 0){
                    layer.msg("提交成功!", {icon : 1});
                    parent.reloadBean("${index}", data);
                }else{
                    layer.msg(res.message.info, {icon : 2});
                }
            });
        }
        function reload(){
            location.reload(true);
        }
    </script>
</body>
