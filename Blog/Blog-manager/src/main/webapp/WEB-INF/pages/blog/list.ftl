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
                    <td>{{ dispState(bean.state) }}</td>
                    <td>
                        <button class="layui-btn layui-btn-primary" @click="editor(bean.id)">编辑</button>
                        <button class="layui-btn layui-btn-normal" @click="changeIndex(bean.id, 1)">上调</button>
                        <button class="layui-btn layui-btn-warm" @click="changeIndex(bean.id, -1)">下降</button>
                        <button class="layui-btn layui-btn-danger" @click="changeState(bean.id, 'delete')" v-if="bean.state != 1">删除</button>
                        <button class="layui-btn layui-btn-primary" @click="changeState(bean.id, 'normal')" v-if="bean.state != 0">正常</button>
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
                	dispState: function(state) {
                		switch (state) {
							case 0: return "正常状态";
							case 1: return "删除状态";
							case 2: return "草稿状态";
							default: return "其他状态";
						}
                	},                    
                    editor: function(id) {
                        parent.openDiv('', 'blog/add.html?id=' + id, loadBean);
                    },
                    changeIndex: function(id, change) {
                    	$.post("changeIndex.json", { "id" : id, "change" : change}).done(function(res){
                    		if(res.code == 200) {
                    			parent.dispTip("修改成功");
                    			loadBean();
                    		} else {
                    			parent.dispTip(res.message.info);
                    		}
                    	}).fail(function(){
                    		parent.dispMessage("调整位置", "接口请求失败", true);
                    	})
                    },
                    changeState: function(id, state) {
                    	$.post("changeState.json", { "id" : id, "state" : state}).done(function(res){
                            if(res.code == 200) {
                                parent.dispTip("修改成功");
                                loadBean();
                            } else {
                                parent.dispTip(res.message.info);
                            }
                        }).fail(function(){
                            parent.dispMessage("修改状态", "接口请求失败", true);
                        })
                    }
                }
            });
            
            function loadBean() {
            	$.post("list.json").done(function(res){
                    if(res.code == 0) {
                        vm.beans = res.message.beans;
                        if(vm.loadr) vm.loadr = false;
                    }
                }).fail(function(err){
                    vm.error = true;
                });
            }
            loadBean();
        });
    </script>
</body>