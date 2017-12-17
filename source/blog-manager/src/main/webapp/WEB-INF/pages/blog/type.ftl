<head>
    <link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
    <link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/style.css">
</head>
<body>
    <h1>博客分类管理</h1>
    <div id="blog-type">
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col width="200">
                <col>
            </colgroup>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>分类模块</th>
                    <th>分类名称</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="bean in beans">
                    <td>{{ bean.id }}</td>
                    <td>{{ bean.typeModule }}</td>
                    <td>{{ bean.typeName }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <script src="https://static.heleeos.com/lib/jquery.js"></script>
    <script src="https://static.heleeos.com/lib/vue.js"></script>
    <script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
    <script type="text/javascript">
        $(function(){
            var vm = new Vue({
                el : "#blog-type",
                data: {
                    loadr: true,
                    error: false,
                    beans: []
                }
            });
            
            var data = { "module" : 1 };
            $.post("type.json", data).done(function(res){
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