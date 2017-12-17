<head>
    <link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
    <link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/style.css">
</head>
<body style="min-width: 1380px;">
    <h1>博客管理</h1>
    <div id="blog-list">
        <div>
            <button class="layui-btn layui-btn-normal" @click="editor(0)">新增文章</button>
            <button class="layui-btn layui-btn-normal" @click="loadBean()">重新加载</button>
        </div>
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
                    <th>更新时间</th>
                    <th>显示顺序</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="bean in beans">
                    <td>{{ bean.id }}</td>
                    <td>{{ bean.blogTitle }}</td>
                    <td>{{ bean.createTime | datetime }}</td>
                    <td>{{ bean.updateTime | datetime }}</td>
                    <td>{{ bean.dispIndex }}</td>
                    <td>{{ dispState(bean.blogState) }}</td>
                    <td>
                        <button class="layui-btn layui-btn-primary" @click="editor(bean.id)">编辑</button>
                        <button class="layui-btn layui-btn-normal" @click="changeIndex(bean.id, bean.dispIndex + 1)">上调</button>
                        <button class="layui-btn layui-btn-warm" @click="changeIndex(bean.id, bean.dispIndex - 1)">下降</button>
                        <button class="layui-btn layui-btn-primary" @click="changeState(bean.id, 'normal')" v-if="bean.blogState != 0">正常</button>
                        <button class="layui-btn layui-btn-danger" @click="changeState(bean.id, 'delete')" v-if="bean.blogState != 1">删除</button>
                        <button class="layui-btn" @click="changeState(bean.id, 'update')" v-if="bean.blogState != 2">草稿</button>
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
                    load : true,
                    error: false,
                    beans: [],
                    page : 1,
                    rows : 10,
                    count: 0
                },
                methods: {
                    dispState: function(state) {
                        switch (state) {
                            case 0: return "正常状态";
                            case 1: return "删除状态";
                            case 2: return "草稿状态";
                            default: return "其他状态";
                        }
                    },
                    editor: function(id) {
                        parent.openDiv('', 'blog/add.html?id=' + id, vm.loadBean);
                    },
                    changeIndex: function(id, change) {
                        $.post("${request.contextPath}/ajax/blog/changeIndex.json", { "id" : id, "change" : change}).done(function(res){
                            if(res.code === 200) {
                                parent.dispTip("修改成功");
                                vm.loadBean();
                            } else {
                                parent.dispTip(res.message.info);
                            }
                        }).fail(function(){
                            parent.dispMessage("调整位置", "接口请求失败", true);
                        })
                    },
                    changeState: function(id, state) {
                        $.post("${request.contextPath}/ajax/blog/changeState.json", { "id" : id, "state" : state}).done(function(res){
                            if(res.code === 200) {
                                parent.dispTip("修改成功");
                                vm.loadBean();
                            } else {
                                parent.dispTip(res.message.info);
                            }
                        }).fail(function(){
                            parent.dispMessage("修改状态", "接口请求失败", true);
                        })
                    },
                    loadBean: function() {
                        $.post("${request.contextPath}/ajax/blog/list.json").done(function(res){
                            if(res.code === 0) {
                                vm.beans = res.message.beans;
                                vm.page = res.message.page;
                                vm.rows = res.message.rows;
                                vm.count = res.message.count;
                                if(vm.loadr) vm.loadr = false;
                            }
                        }).fail(function(err){
                            vm.error = true;
                        });
                    }
                }
            });
            vm.loadBean();
        });
    </script>
</body>