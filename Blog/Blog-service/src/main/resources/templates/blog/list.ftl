<head>
    <link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
    <link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/style.css">
</head>
<body style="min-width: 1152px;">
    <h1>博客管理</h1>
    <div id="blog-list">
        <div><button class="layui-btn layui-btn-normal" @click="editor(0)">新增文章</button></div>
        <table class="layui-table">
            <colgroup>
                <col width="50">
                <col width="200">
                <col width="200">
                <col width="200">
                <col width="100">
                <col width="80">
                <col width="350">
            </colgroup>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>标题</th>
                    <th>发布时间</th>
                    <th>修改时间</th>
                    <th>显示顺序</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="bean in beans">
                    <td>{{ bean.id }}</td>
                    <td>{{ bean.title }}</td>
                    <td>{{ bean.time | datetime }}</td>
                    <td>{{ bean.lasttime | datetime }}</td>
                    <td>{{ bean.dispIndex }}</td>
                    <td>{{ bean.state }}</td>
                    <td>
                        <button class="layui-btn layui-btn-primary" @click="editor(bean.id)">编辑</button>
                        <button class="layui-btn layui-btn-normal">上调</button>
                        <button class="layui-btn layui-btn-warm">下降</button>
                        <button class="layui-btn layui-btn-danger">删除</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <script src="https://static.heleeos.com/lib/jquery.min.js"></script>
    <script src="https://static.heleeos.com/lib/vue.min.js"></script>
    <script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
    <script src="https://static.heleeos.com/js/vue-filter.js"></script>
    <script type="text/javascript">
        $(function(){
            var vm = new Vue({
                el : "#blog-list",
                data: {
                    loadr: true,
                    error: false,
                    beans: []
                },
                methods: {
                    editor: function(id) {
                        parent.openDiv('', 'blog/add.html?id=' + id);
                    }
                }
            });
            
            var data = { "module" : 1 };
            $.post("list.json", data).done(function(res){
                if(res.code == 0) {
                    vm.beans = res.message.beans;
                    if(vm.loadr) vm.loadr = false;
                }
            }).fail(function(err){
                vm.error = true;
            });
        });
    </script>
</body>